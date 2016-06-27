package jp.co.jyotaku.rssreader;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;

import jp.co.jyotaku.rssreader.convert.ConvertSyndFeed;
import jp.co.jyotaku.rssreader.convert.ConvertSyndFeedImpl;
import jp.co.jyotaku.rssreader.convert.dto.ConvertConditionDto;
import jp.co.jyotaku.rssreader.input.LoadRssFeed;
import jp.co.jyotaku.rssreader.input.LoadRssFeedImpl;
import jp.co.jyotaku.rssreader.output.OutputStrCreator;
import jp.co.jyotaku.rssreader.output.OutputStrCreatorImpl;
import jp.co.jyotaku.rssreader.output.dto.OutputConditionDto;

public class RssReaderImpl implements RssReader{

	private final RssReaderWindow rssReaderWindow;

	public RssReaderImpl(RssReaderWindow rssReaderFrame) {
		this.rssReaderWindow = rssReaderFrame;
	}

	@Override
	public void run() {
		// RSSソースリストを取得
		List<String> srcList = this.rssReaderWindow.getInputSourceList();
		
		// 変換条件を初期化
		Optional<ConvertConditionDto> convertConditionDto = this.rssReaderWindow.getConvertConditionDto();
		if (!convertConditionDto.isPresent()) {
			return;
		}
		
		// 出力条件を初期化
		OutputConditionDto outputConditionDto = this.rssReaderWindow.getOutputConditionDto();
		if(outputConditionDto.noShowFiled()){
			this.rssReaderWindow.showMessage("少なくとも一つの表示項目を選択してください。");
		    return;	
		}
		
		// 前回の結果をクリア
		this.rssReaderWindow.clearResultText();
		
		// RSSソースごとに処理
		processEachSource(srcList, convertConditionDto, outputConditionDto);

	}

	private void processEachSource(List<String> srcList, Optional<ConvertConditionDto> convertConditionDto,
			OutputConditionDto outputConditionDto) {
		srcList.forEach(src -> {
			StringBuffer rltSb = new StringBuffer();
			try {
				// Feedを取得
				LoadRssFeed loadRssFeed = new LoadRssFeedImpl(src);
				Optional<SyndFeed> feed = loadRssFeed.loadFeed();
                if(!feed.isPresent()){
                	this.rssReaderWindow.showMessage("データを取得できませんでした。RSSのURLをチェックしてください。");
                }
                
				// Entryごとに中身を変換して、出力文字列を作成
				feed.get().getEntries().forEach(entry -> {
					ConvertSyndFeed convertSyndFeed = new ConvertSyndFeedImpl(convertConditionDto.get());
					SyndEntry convertedEntry = convertSyndFeed.convertWithCondition(entry);
					OutputStrCreator outputCreator = new OutputStrCreatorImpl(convertedEntry, outputConditionDto);
					rltSb.append(outputCreator.getOutputString());
				});
                
				// TextAreaに結果を追加
                appendToTextArea(src, rltSb);

			} catch (MalformedURLException urlException) {
				this.rssReaderWindow.showMessage("URLが無効です。");
				urlException.printStackTrace();
			} catch (Exception e){
				this.rssReaderWindow.showMessage("予想外のエラーが発生しました。");
				e.printStackTrace();
			}
		});
	}

	private void appendToTextArea(String src, StringBuffer rltSb) {
		this.rssReaderWindow.appendResultText("******************************************************************************\n");
		this.rssReaderWindow.appendResultText("RSSソース：" + src + "\n");
		this.rssReaderWindow.appendResultText("******************************************************************************\n");
		this.rssReaderWindow.appendResultText(rltSb.toString() + "\n");
		this.rssReaderWindow.appendResultText("\n");
	}
}
