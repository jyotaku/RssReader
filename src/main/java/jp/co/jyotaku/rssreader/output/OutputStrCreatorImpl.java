package jp.co.jyotaku.rssreader.output;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import com.rometools.rome.feed.synd.SyndEntry;

import jp.co.jyotaku.rssreader.output.dto.OutputConditionDto;

public class OutputStrCreatorImpl implements OutputStrCreator{
	
	private final SyndEntry syndEntry;
	private final OutputConditionDto outputConditionDto;
	
	public OutputStrCreatorImpl(SyndEntry entry, OutputConditionDto conditionDto){
		this.syndEntry = entry;
		this.outputConditionDto = conditionDto;
	}
	
	public String getOutputString(){
		StringBuffer sb = new StringBuffer();
		
		if(outputConditionDto.isShowTitle()){
			sb.append("タイトル：");
			sb.append(syndEntry.getTitle());		
			sb.append("\n");
		}
		
		if(outputConditionDto.isShowDate()){
			sb.append("公開日：");
			String dateTimeStr = getDate(syndEntry.getPublishedDate());
			sb.append(dateTimeStr);	
			sb.append("\n");
		}
		
		if(outputConditionDto.isShowUrl()){
			sb.append("URL：");
			sb.append(syndEntry.getLink());	
			sb.append("\n");
		}
		
		if(outputConditionDto.isShowDescription()){
			sb.append("本文：");
			sb.append(syndEntry.getDescription().getValue());
			sb.append("\n");
		}
		sb.append("----------------------------------------\n");
		
		return sb.toString();
	}
	
	private String getDate(Date date){
		
		String dateStr = date.toString();
		String[] wordArray = dateStr.split(" ");
		ZoneId zonedId = ZoneId.systemDefault();
		try{
			if(wordArray.length > 5){
				zonedId = ZoneId.of(wordArray[4], ZoneId.SHORT_IDS);
			}
		}catch(Exception e){
			//変換失敗の場合、システムデフォルトを利用する
		}
				
		ZonedDateTime dateTiem = ZonedDateTime.ofInstant(date.toInstant(), zonedId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd(E) HH:mm:ss", Locale.JAPAN);
		return dateTiem.format(formatter);
	}
	
}
