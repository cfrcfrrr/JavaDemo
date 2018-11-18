package reflectdemo;

public class MyClassFactory {
	public static MyClass getInstance(String className) {
		MyClass myClass = null;
		try {
			myClass = (MyClass) Class.forName(className).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myClass;
	}

}
