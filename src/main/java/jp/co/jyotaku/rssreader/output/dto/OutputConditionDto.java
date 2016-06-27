package jp.co.jyotaku.rssreader.output.dto;

public class OutputConditionDto {

	private boolean showTitle;
	private boolean showDescription;
	private boolean showUrl;
	private boolean showDate;
	
	public OutputConditionDto(){
	}

	public OutputConditionDto(boolean showTitle, boolean showDescription, boolean showUrl, boolean showDate) {
		super();
		this.showTitle = showTitle;
		this.showDescription = showDescription;
		this.showUrl = showUrl;
		this.showDate = showDate;
	}
	
	public boolean noShowFiled(){
		return !showTitle 
				&& !showDescription
				&& !showUrl
				&& !showDate;
	}

	public boolean isShowTitle() {
		return showTitle;
	}

	public void setShowTitle(boolean showTitle) {
		this.showTitle = showTitle;
	}

	public boolean isShowDescription() {
		return showDescription;
	}

	public void setShowDescription(boolean showDescription) {
		this.showDescription = showDescription;
	}

	public boolean isShowUrl() {
		return showUrl;
	}

	public void setShowUrl(boolean showUrl) {
		this.showUrl = showUrl;
	}

	public boolean isShowDate() {
		return showDate;
	}

	public void setShowDate(boolean showDate) {
		this.showDate = showDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (showDate ? 1231 : 1237);
		result = prime * result + (showDescription ? 1231 : 1237);
		result = prime * result + (showTitle ? 1231 : 1237);
		result = prime * result + (showUrl ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OutputConditionDto other = (OutputConditionDto) obj;
		if (showDate != other.showDate)
			return false;
		if (showDescription != other.showDescription)
			return false;
		if (showTitle != other.showTitle)
			return false;
		if (showUrl != other.showUrl)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OutputConditionDto [showTitle=" + showTitle + ", showDescription=" + showDescription + ", showUrl="
				+ showUrl + ", showDate=" + showDate + "]";
	}	
}
