package chapter.three;

class Link {
	// inner class
	class Node {
		private String data;
		private Node next;

		public Node(String data) {
			this.data = data;
		}

		public void addNode(Node newNode) {
			if (this.next == null) {
				this.next = newNode;
			} else {
				this.next.addNode(newNode);
			}
		}

		// public void sizeNode() { // �Ż���û�����±�����������������Ԫ��ʱֱ���޸���������count
		// Link.this.foot ++;
		// if (this.next != null) {
		// this.next.sizeNode();
		// }
		// }
		public boolean containsNode(String data) {
			if(data.equals(this.data)) { // ����my: if (this.data == data) {
				return true;
			} else if (this.next == null) {
				return false;
			} else {
				return this.next.containsNode(data);
			}
		}
		public String getNode(int index) {
			if (Link.this.foot ++ == index) {
				return this.data;
			} else {
				return this.next.getNode(index);
			}		
		}
		public void setNode(int index, String data) {
			if (Link.this.foot ++ == index) {
				this.data = data;
			} else {
				this.next.setNode(index, data);
			}
		}
		public void removeNode(Node previous, String data) {
			if (data.equals(this.data)) {
				previous.next = this.next;
//				this.count --;
			} else {
				this.next.removeNode(this, data);
			}
		}
		public void toArrayNode() {
			Link.this.dataArray[Link.this.foot ++] = this.data;
			if (this.next != null) {
				this.next.toArrayNode();
			}
		}
	}

	// outer class
	private Node root;
	private int count;
	private int foot;
	private String [] dataArray;

	public void add(String data) {
		Node newNode = new Node(data);
		if (this.root == null) {
			this.root = newNode;
			// } else if (this.root.next == null) { // �Ż�����ʵ��Link�ڵ��ҵ����ڵ㣬���ڵ�Ҳ�Ǻ������ڵ�һ������
			// this.root.next = newNode;
			// } else {
			// this.root.next.addNode(newNode);
		} else {
			this.root.addNode(newNode);
		}
		this.count++;
	}

	public int size() {
		return this.count;
		// this.foot = 0; // �Ż���û�����±�����������������Ԫ��ʱֱ���޸���������count
		// if (this.root == null) {
		// this.foot = 0;
		// } else {
		// this.root.sizeNode();
		// }
		// return this.foot;
	}

	public boolean isEmpty() {
		return this.count == 0;
	}

	public boolean contains(String data) {
		if (data == null || this.root == null) { // my: if(this.root == null) {
			return false;
		} else {
			return this.root.containsNode(data);
		}
	}
	public String get(int index) {
		this.foot = 0; // ����û�и�������ʼ�����з���
		if (index < 0 || index > this.count || this.root == null) {
			return null;
		} else {
			return this.root.getNode(index);
		}
	}
	public void set(int index, String data) {
		if (index < 0 || index > this.count || data == null || this.root == null) {
			return;
		} else {
			this.foot = 0;
			this.root.setNode(index, data);
		}
	}
	public void remove(String data) {
		if (this.contains(data)) { // �Ľ���ֱ��ʹ��contains�����ж�
	//		if (data == null | this.root == null) {
	//			return;
	//		} else if (data.equals(this.root.data)) {
			if (data.equals(this.root.data)) {
				this.root = this.root.next;
	//			this.count --;
			} else {
				this.root.next.removeNode(this.root, data);
			}
			this.count --;
		}
	}
	public String [] toArray() {
		if (this.root == null) {
			return null;
		} else {
			this.foot = 0;
			this.dataArray = new String [this.count];
			this.root.toArrayNode();
			return this.dataArray;
		}
	}
}

public class LinkDemo {
	public static void main(String[] args) {
		Link test = new Link();
		System.out.println("Link size is " + test.size());
		System.out.println("Link is empty " + test.isEmpty());
		System.out.println("Link contains hello " + test.contains("hello"));
		test.add("hello");
		System.out.println("Link size is " + test.size());
		System.out.println("Link is empty " + test.isEmpty());
		System.out.println("Link contains hello " + test.contains("hello"));
		test.add("world");
		test.add("disk");
		System.out.println(test.get(1));
		test.set(1, "newworld");
		System.out.println(test.get(1));
		test.remove("disk");
		System.out.println(test.size());
		String [] data = test.toArray();
		for (int x = 0; x < data.length; x ++) {
			System.out.println(data[x]);
		}
	}

}
