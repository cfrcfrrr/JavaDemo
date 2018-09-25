package chapter.three;
enum Color {
	RED,GREEN,BLUE ;
}
public class EnumDemo {
	public static void main(String[] args) {
		Color c = Color.RED ;
		switch(c) {
			case RED :
				System.out.println("这是红色");
				break ;
			case GREEN :
				System.out.println("这是绿色");
				break ;
			case BLUE :
				System.out.println("这是蓝色");
		}
	}
}