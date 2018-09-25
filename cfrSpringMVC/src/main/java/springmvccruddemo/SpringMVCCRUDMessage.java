package springmvccruddemo;

import java.util.Date;

public class SpringMVCCRUDMessage {
	private Integer mid;
	private String title;
	private Double price;
	private Date pubdate;
	private SpringMVCCRUDMessageType type;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public SpringMVCCRUDMessageType getType() {
		return type;
	}
	public void setType(SpringMVCCRUDMessageType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SpringMVCCRUDMessage [mid=" + mid + ", title=" + title + ", price=" + price + ", pubdate=" + pubdate
				+ ", type=" + type + "]";
	}
}
