package chapter.three;
interface USB {
	public void start();
	public void stop();
}
class Computer{
	public void plugin(USB usb) {
		usb.start();
		usb.stop();
	}
}
class Flash implements USB {

	public void start() {
		System.out.println("U�̿�ʼʹ��");
	}

	public void stop() {
		System.out.println("U�̽���ʹ��");
	}
}
class Printer implements USB{

	public void start() {
		System.out.println("��ӡ����ʼʹ��");
	}

	public void stop() {
		System.out.println("��ӡ������ʹ��");
	}
	
}
public class InterfaceDemo {

	public static void main(String[] args) {
		Computer com = new Computer();
		com.plugin(new Flash());
		com.plugin(new Printer());
	}

}
