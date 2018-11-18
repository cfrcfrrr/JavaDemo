package genericsdemo;

public class GenericsClass<T> {
	private T x;
	public void setX(T x) {
		this.x = x;
	}
	public T getX() {
		return this.x;
	}
}
