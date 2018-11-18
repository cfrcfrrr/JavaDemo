package internationalizationdemo;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 国际化：根据当前语言环境读取指定语言资源文件
 * 资源文件：*.properties，以“key=value”格式保存内容（例info=hello），且文件命名标准与java一样，也就是每个单词首字母大写
 * 如果定义在包内，则资源文件内要声明包，否则就需要定义在根目录
 * @author cfr
 */
public class App {
	public static void main(String[] args) {
		// 根据当前语言环境取出：ResourceBundle类 public static final ResourceBundle getBundle(String baseName)
		// 只需要传入文件名，不需要加“.properties”
		// 本语言资源文件>无语言资源文件，也就是即使没有设置语言，如果存在本语言资源文件，也会优先访问，没有时才会访问无语言的资源文件		
		ResourceBundle rb = ResourceBundle.getBundle("InternationlizationDemo");
		// 简单读取 ResourceBundle类 public final String getString(String key);
		System.out.println(rb.getString("info1"));
		//	格式化文本 MessageFormat类 public static String format(String pattern, Object .. arguments)
		String str2 = rb.getString("info2");
		System.out.println(MessageFormat.format(str2, "China", "!!!"));
		
		// 设置指定语言环境取出：ResourceBundle类 public static final ResourceBundle getBundle(String baseName, Local local)
		// Local类保存区域和语言编码，通过IE->Internet选项->语言->中国：zh-CN -> zh_CN（把中划线变为下划线）
		Locale locale = new Locale("zh", "CN");
		ResourceBundle rbzhcn = ResourceBundle.getBundle("InternationlizationDemo", locale);
		System.out.println(rbzhcn.getString("info1"));
	}
}
