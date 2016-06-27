package jp.co.jyotaku.rssreader.input;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

public class LoadRssFeedImpl implements LoadRssFeed {

	private final String rssSrc;

	public LoadRssFeedImpl(String src) {
		this.rssSrc = src;
	}

	public Optional<SyndFeed> loadFeed() throws MalformedURLException, FeedException, IOException {

		if (srcIsUrl()) {
			return loadFeedFromUrl();
		}

		// 将来的にファイルからも読み込める機能を拡張できる
		// if (srcIsFilePath) {
		// return loadFeedsFromFile();
		// }

		return Optional.empty();
	}

	private Optional<SyndFeed> loadFeedFromUrl() throws MalformedURLException, FeedException, IOException {

		URL feedUrl = new URL(rssSrc);

		SyndFeedInput input = new SyndFeedInput();

		SyndFeed feed = input.build(new XmlReader(feedUrl));

		return Optional.of(feed);
	}

	private boolean srcIsUrl() throws MalformedURLException {
		new URL(rssSrc);
		return true;
	}
}
