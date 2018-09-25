package chapter.twenty.springmvcdemo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SpringMVCMessage implements Serializable {
	private Integer mid;
	private String title;
	private Double price;
	private Date pubdate;
	private SpringMVCType type;
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
	public SpringMVCType getType() {
		return type;
	}
	public void setType(SpringMVCType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "SpringMVCMessage [mid=" + mid + ", title=" + title + ", price=" + price + ", pubdate=" + pubdate
				+ ", type=" + type + "]";
	}
	
}
