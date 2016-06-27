package jp.co.jyotaku.rssreader.convert;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;

import jp.co.jyotaku.rssreader.convert.dto.ConvertConditionDto;

public class ConvertSyndFeedImpl implements ConvertSyndFeed{

	private final ConvertConditionDto convertConditionDto;
	
	public ConvertSyndFeedImpl(ConvertConditionDto conditionDto){
		this.convertConditionDto = conditionDto;
	}
	
	public SyndEntry convertWithCondition(SyndEntry entry){
        // タイトル変換
		String title = entry.getTitle();
		int titleLength = convertConditionDto.getTitleLength();
		if(title.length() > titleLength && titleLength != 0){
			title = title.substring(0, titleLength);
			entry.setTitle(title);
		}
		
		//本文変換
		SyndContent description = entry.getDescription();
		String value = description.getValue();
		value = value.replaceAll("<.+?>", "").replaceAll("\r\n","").replaceAll("\n","");
		int descLength = convertConditionDto.getDescriptionLength();
		if(value.length() > descLength && descLength !=0){
			description.setValue(value.substring(0, descLength));
			entry.setDescription(description);
		}else if(descLength == 0){
			description.setValue(value);
			entry.setDescription(description);
		}
		
		return entry;	
	}
}
