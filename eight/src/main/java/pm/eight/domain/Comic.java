package pm.eight.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "comics")
public class Comic implements Serializable {

	private static final long serialVersionUID = 3915902099036361635L;

	public Comic(){
		
	}
	
	public Comic(String title, String week, String link, String thumbnailUri) {
		this.title = title;
		this.week = week;
		this.link = link;
		this.thumbnailUri = thumbnailUri;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "title", length = 255, nullable = false)
	private String title;

	@Column(name = "thumbnail_uri", length = 255, nullable = false)
	private String thumbnailUri;

	@Column(name = "link", length = 255, nullable = false)
	private String link;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic", cascade = CascadeType.ALL)
	private Set<Episode> episodes = new HashSet<Episode>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.comic", cascade = CascadeType.ALL)
	private Set<AuthorComic> authorComics = new HashSet<AuthorComic>(0);

	@Column(name = "week", nullable = true)
	private String week;

	@Temporal(TemporalType.DATE)
	@Column(name = "create_date", nullable = true)
	private Date createDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "modify_date", nullable = true)
	private Date modifyDate;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnailUri() {
		return thumbnailUri;
	}

	public void setThumbnailUri(String thumbnailUri) {
		this.thumbnailUri = thumbnailUri;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Set<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(Set<Episode> episodes) {
		this.episodes = episodes;
	}

	public Set<AuthorComic> getAuthorComics() {
		return authorComics;
	}

	public void setAuthorComics(Set<AuthorComic> authorComics) {
		this.authorComics = authorComics;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "Comic [id=" + id + ", title=" + title + ", thumbnailUri="
				+ thumbnailUri + ", link=" + link + ", episodes=" + episodes
				+ ", authorComics=" + authorComics + ", week=" + week
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ "]";
	}
	
}
