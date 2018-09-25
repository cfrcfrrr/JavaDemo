package springmvccruddemo;

public class SpringMVCCRUDMessageType {
	private String typeTitle;

	public String getTitle() {
		return typeTitle;
	}

	public void setTitle(String title) {
		this.typeTitle = title;
	}

	@Override
	public String toString() {
		return "SpringMVCCRUDType [title=" + typeTitle + "]";
	}
}
