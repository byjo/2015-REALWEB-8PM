package pm.eight.dto;

public class EpisodePageDTO {
	
	private String publishingDay;
	private String link;
	private long amount;
	
	public String getPublishingDay() {
		return publishingDay;
	}
	public String getLink() {
		return link;
	}
	public long getAmount() {
		return amount;
	}
	public void setPublishingDay(String publishedDay) {
		this.publishingDay = publishedDay;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

}
