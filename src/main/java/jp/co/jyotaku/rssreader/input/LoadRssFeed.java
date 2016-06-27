package jp.co.jyotaku.rssreader.input;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

public interface LoadRssFeed {
	
	Optional<SyndFeed> loadFeed() throws MalformedURLException, FeedException, IOException ;

}
