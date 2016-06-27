package jp.co.jyotaku.rssreader.convert.dto;

public class ConvertConditionDto {
	
	private int titleLength;
	private int descriptionLength;
	
	public ConvertConditionDto(){
	}

	public int getTitleLength() {
		return titleLength;
	}

	public void setTitleLength(int titleLength) {
		this.titleLength = titleLength;
	}

	public int getDescriptionLength() {
		return descriptionLength;
	}

	public void setDescriptionLength(int descriptionLength) {
		this.descriptionLength = descriptionLength;
	}

	public ConvertConditionDto(int titleLength, int descriptionLength) {
		super();
		this.titleLength = titleLength;
		this.descriptionLength = descriptionLength;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (descriptionLength ^ (descriptionLength >>> 32));
		result = prime * result + (int) (titleLength ^ (titleLength >>> 32));
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
		ConvertConditionDto other = (ConvertConditionDto) obj;
		if (descriptionLength != other.descriptionLength)
			return false;
		if (titleLength != other.titleLength)
			return false;
		return true;
	}

}
