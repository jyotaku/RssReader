package jp.co.jyotaku.rssreader.convert;

import com.rometools.rome.feed.synd.SyndEntry;

public interface ConvertSyndFeed {

	SyndEntry convertWithCondition(SyndEntry entry);
}
