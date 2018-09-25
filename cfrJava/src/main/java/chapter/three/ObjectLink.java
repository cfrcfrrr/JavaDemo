package chapter.three;

class LinkObj {
	// inner class
	class Node {
		private Object data;
		private Node next;

		public Node(Object data) {
			this.data = data;
		}

		public void addNode(Node newNode) {
			if (this.next == null) {
				this.next = newNode;
			} else {
				this.next.addNode(newNode);
			}
		}
		public boolean containsNode(Object data) {
			if(data.equals(this.data)) { // 错误：my: if (this.data == data) {
				return true;
			} else if (this.next == null) {
				return false;
			} else {
				return this.next.containsNode(data);
			}
		}
		public Object getNode(int index) {
			if (LinkObj.this.foot ++ == index) {
				return this.data;
			} else {
				return this.next.getNode(index);
			}		
		}
		public void setNode(int index, Object data) {
			if (LinkObj.this.foot ++ == index) {
				this.data = data;
			} else {
				this.next.setNode(index, data);
			}
		}
		public void removeNode(Node previous, Object data) {
			if (data.equals(this.data)) {
				previous.next = this.next;
			} else {
				this.next.removeNode(this, data);
			}
		}
		public void toArrayNode() {
			LinkObj.this.dataArray[LinkObj.this.foot ++] = this.data;
			if (this.next != null) {
				this.next.toArrayNode();
			}
		}
	}

	// outer class
	private Node root;
	private int count;
	private int foot;
	private Object [] dataArray;

	public void add(Object data) {
		Node newNode = new Node(data);
		if (this.root == null) {
			this.root = newNode;
		} else {
			this.root.addNode(newNode);
		}
		this.count++;
	}

	public int size() {
		return this.count;
	}

	public boolean isEmpty() {
		return this.count == 0;
	}

	public boolean contains(Object data) {
		if (data == null || this.root == null) { // my: if(this.root == null) {
			return false;
		} else {
			return this.root.containsNode(data);
		}
	}
	public Object get(int index) {
		this.foot = 0;
		if (index < 0 || index > this.count || this.root == null) {
			return null;
		} else {
			return this.root.getNode(index);
		}
	}
	public void set(int index, Object data) {
		if (index < 0 || index > this.count || data == null || this.root == null) {
			return;
		} else {
			this.foot = 0;
			this.root.setNode(index, data);
		}
	}
	public void remove(Object data) {
		if (this.contains(data)) {
			if (data.equals(this.root.data)) {
				this.root = this.root.next;
			} else {
				this.root.next.removeNode(this.root, data);
			}
			this.count --;
		}
	}
	public Object [] toArray() {
		if (this.root == null) {
			return null;
		} else {
			this.foot = 0;
			this.dataArray = new Object [this.count];
			this.root.toArrayNode();
			return this.dataArray;
		}
	}
}

public class ObjectLink {
	public static void main(String[] args) {
		LinkObj test = new LinkObj();
		System.out.println("Link size is: " + test.size());
		System.out.println("Link is empty: " + test.isEmpty());
		System.out.println("Link contains 111: " + test.contains(111));
		test.add(111);
		System.out.println("Link size is: " + test.size());
		System.out.println("Link is empty: " + test.isEmpty());
		System.out.println("Link contains 111: " + test.contains(111));
		test.add("hello");
		System.out.println(test.get(1));
		test.set(1, "newHello");
		System.out.println(test.get(1));
		test.remove("111");
		System.out.println(test.size());
		test.add(new int [] {1,2,3});
		Object [] data = test.toArray();
		for (int x = 0; x < data.length; x ++) {
			System.out.println(data[x]); // 返回的数据是String类型，不能直接用int数组接收
		}
	}
}
