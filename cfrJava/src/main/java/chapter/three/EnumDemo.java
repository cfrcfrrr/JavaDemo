package chapter.three;
enum Color {
	RED,GREEN,BLUE ;
}
public class EnumDemo {
	public static void main(String[] args) {
		Color c = Color.RED ;
		switch(c) {
			case RED :
				System.out.println("���Ǻ�ɫ");
				break ;
			case GREEN :
				System.out.println("������ɫ");
				break ;
			case BLUE :
				System.out.println("������ɫ");
		}
	}
}