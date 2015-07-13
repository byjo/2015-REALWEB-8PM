package pm.eight.dto;

public class EpisodePageDTO {
	
	private String publishDay;
	private String link;
	private long amount;
	
	public String getPublishDay() {
		return publishDay;
	}
	public String getLink() {
		return link;
	}
	public long getAmount() {
		return amount;
	}
	public void setPublishingDay(String publishDay) {
		this.publishDay = publishDay;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

}
