package pm.eight.model;

public class Comic {
	private int id;
	private String title;
	private String week;
	private String link_for_epList;
	private String thrumnailImg;

	public Comic(String title, String week, String link_for_epList,
			String thrumnailImg) {
		super();
		this.title = title;
		this.week = week;
		this.link_for_epList = link_for_epList;
		this.thrumnailImg = thrumnailImg;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getWeek() {
		return week;
	}

	public String getLink_for_epList() {
		return link_for_epList;
	}

	public String getThrumnailImg() {
		return thrumnailImg;
	}

	@Override
	public String toString() {
		return "Comic [title=" + title + ", week=" + week
				+ ", link_for_epList=" + link_for_epList + ", thrumnailImg="
				+ thrumnailImg + "]";
	}
	
	
	
}
