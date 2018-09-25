package chapter.three;


class LinkPet {
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
			if (LinkPet.this.foot ++ == index) {
				return this.data;
			} else {
				return this.next.getNode(index);
			}		
		}
		public void setNode(int index, Object data) {
			if (LinkPet.this.foot ++ == index) {
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
			LinkPet.this.dataArray[LinkPet.this.foot ++] = this.data;
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

interface Pet {
	public String getName();
	public int getAge();
}

class Dog implements Pet {
	private String name;
	private int age;
	public Dog(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String toString() {
		return "This is a dog, name is " + this.name + ", age is " + this.age;
	}
	public boolean equals(Object obj) {// 自己实现：public boolean equals(Pet pet) {，修改为使用Object接收对象
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Dog)) {
			return false;
		}
		Dog c = (Dog) obj;
		if (this.name.equals(c.name) && this.age == c.age) {
			return true;
		} else {
			return false;
		}
	}
}

class Cat implements Pet {
	private String name;
	private int age;
	public Cat(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String toString() {
		return "This is a cat, name is " + this.name + ", age is " + this.age;
	}
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Dog)) {
			return false;
		}
		Cat c = (Cat) obj;
		if (this.name.equals(c.name) && this.age == c.age) {
			return true;
		} else {
			return false;
		}
	}
}

class PetStore {
	private LinkPet pets = new LinkPet();
	public void add(Pet pet) {
		this.pets.add(pet);
	}
	public void delete(Pet pet) {
		this.pets.remove(pet);
	}
	public LinkPet search(String keyword) {
		LinkPet result = new LinkPet();
		Object [] obj = this.pets.toArray();
		for (int x = 0; x < obj.length; x ++) {
			Pet p = (Pet) obj[x]; // 注意：只能将每个值取出来单独向下转型，不能直接将整个数组转型
			if (p.getName().contains(keyword)) {
				result.add(p);
			}
		}
		return result;	
	}
}
public class PetStoreDemo {
	public static void main(String[] args) {
		PetStore shop = new PetStore();
		shop.add(new Dog("ww", 3)); //自己实现：Pet wangwang = new Dog("ww", 3);，修改为直接使用匿名对象赋值
		shop.add(new Cat("mm", 6));
		shop.add(new Dog("xbm", 1));
		shop.add(new Cat("tgm",30));
//		Pet miaomiao = new Cat("mm", 6);
//		Pet xiaobai = new Dog("xbm", 1);
//		Pet tiger = new Cat("tgm",30);
//		shop.add(wangwang);
//		shop.add(miaomiao);
//		shop.add(xiaobai);
//		shop.add(tiger);
		Object [] obj = shop.search("m").toArray();
		for(int x = 0; x < obj.length; x ++) {
			Pet tmp = (Pet) obj[x];
			System.out.println(tmp.toString()); //自己实现：System.out.println(tmp.getName() + tmp.getAge());，修改为输出信息，应该通过toString方法 
		}
		System.out.println("--------------------");
//		shop.delete(tiger);
		shop.delete(new Dog("xbm", 1)); // 使用匿名对象删除，因此需要覆写equals方法
		Object [] obj2 = shop.search("m").toArray();
		for(int x = 0; x < obj2.length; x ++) {
			Pet tmp = (Pet) obj2[x];
			System.out.println(tmp.toString());
		}
	}
}
