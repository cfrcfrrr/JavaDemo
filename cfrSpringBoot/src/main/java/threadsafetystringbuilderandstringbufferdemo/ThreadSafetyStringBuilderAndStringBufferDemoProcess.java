package threadsafetystringbuilderandstringbufferdemo;

public class ThreadSafetyStringBuilderAndStringBufferDemoProcess {
	private StringBuilder builder;
	private StringBuffer buffer;

	public void addA() {
		for (int i = 0; i < 5; i++) {
			builder.append("a");
			buffer.append("a");
		}
	}

	public void addB() {
		for (int i = 0; i < 5; i++) {
			builder.append("b");
			buffer.append("b");
		}
	}

	public StringBuilder getBuilder() {
		return builder;
	}

	public StringBuffer getBuffer() {
		return buffer;
	}

	public void setBuilder(StringBuilder builder) {
		this.builder = builder;
	}

	public void setBuffer(StringBuffer buffer) {
		this.buffer = buffer;
	}
}
