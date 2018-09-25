package chapter.three;
class Book<T> {
	@SuppressWarnings("unused")
	private T title ;
	public void setTitle(T title) {
		this.title = title ;
	}
}
public class AnnotationDemo {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		Book book = new Book() ;
		book.setTitle("Hello") ;
	}
}
