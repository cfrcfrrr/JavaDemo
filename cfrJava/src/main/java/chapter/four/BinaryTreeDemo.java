package chapter.four;

import java.util.Arrays;

class Bookc implements Comparable<Bookc> {
	private String title;
	private double price;
	public Bookc(String title,double price) {
		this.title = title;
		this.price = price;
	}
	@Override
	public String toString() {
		return this.title + "-" + this.price;
	}
	@Override
	public int compareTo(Bookc o) { // Arrays.sort()���Զ����ô˷����Ƚ�
		if(this.price > o.price) {
			return 1;
		} else if (this.price < o.price) {
			return -1;
		} else {
			return 0;
		}
	}
}
class BinaryTree {
	private class Node {
		@SuppressWarnings("rawtypes")
		private Comparable data;
		private Node left;
		private Node right;
		@SuppressWarnings({ "rawtypes" })
		public Node(Comparable data) {
			this.data = data;
		}
		@SuppressWarnings("unchecked")
		public void addNode(Node newNode) {
			if (this.data.compareTo(newNode.data) > 0) {
				if (this.left == null) {
					this.left = newNode;
				} else {
					this.left.addNode(newNode);
				}
			} else {
				if (this.right == null) {
					this.right = newNode;
				} else {
					this.right.addNode(newNode);
				}
			}
		}
		public void toArrayNode() {
			if (this.left != null) {
				this.left.toArrayNode();
			}
			BinaryTree.this.retData[BinaryTree.this.foot ++] = this.data;
			if (this.right != null) {
				this.right.toArrayNode();
			}
		}
	}
	private Node root;
	private int count;
	private int foot;
	private Object [] retData;
	@SuppressWarnings("rawtypes")
	public void add(Object obj) {
		Comparable com = (Comparable) obj;
		Node newNode = new Node(com);
		if(this.root == null) {
			this.root = newNode;
		} else {
			this.root.addNode(newNode);
		}
		this.count ++;
	}
	public Object[] toArray() {
		if (this.root == null) {
			return null;
		}
		this.foot = 0;
		this.retData = new Object [this.count];
		this.root.toArrayNode();
		return this.retData;
	}
}
public class BinaryTreeDemo {
	public static void main(String[] args) throws Exception {
		BinaryTree bt = new BinaryTree();
		bt.add(new Bookc("aaa",111));
		bt.add(new Bookc("bbb",22));
		bt.add(new Bookc("ccc",3333));
		bt.add(new Bookc("ddd",4));
		Object obj [] = bt.toArray();
		System.out.println(Arrays.toString(obj));
	}
}

