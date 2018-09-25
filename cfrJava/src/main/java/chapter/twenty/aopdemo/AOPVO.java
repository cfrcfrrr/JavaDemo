package chapter.twenty.aopdemo;

//import org.springframework.stereotype.Component;

//@Component
public class AOPVO {
	private String mid;
	private String name;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "AOPMember [mid=" + mid + ", name=" + name + "]";
	}
	
}
