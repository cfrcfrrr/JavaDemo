package chapter.twenty.collectioninjectdemo;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class CollectionInjectClass {
	private List<Integer> li;
	private List<String> ls;
	private Set<Double> sd;
	private Map<Integer,String> mis;
	private Properties pro;
	
	public List<Integer> getLi() {
		return li;
	}
	public void setLi(List<Integer> li) {
		this.li = li;
	}
	public List<String> getLs() {
		return ls;
	}
	public void setLs(List<String> ls) {
		this.ls = ls;
	}
	public Set<Double> getSd() {
		return sd;
	}
	public void setSd(Set<Double> sd) {
		this.sd = sd;
	}
	public Map<Integer, String> getMis() {
		return mis;
	}
	public void setMis(Map<Integer, String> mis) {
		this.mis = mis;
	}
	public Properties getPro() {
		return pro;
	}
	public void setPro(Properties pro) {
		this.pro = pro;
	}
	

}
