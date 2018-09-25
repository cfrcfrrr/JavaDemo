package chapter.four.regexdemo;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemoMain {
	public static void main(String[] args) {
		// 一、单个字符（数量：1）
		// 字符：表示由一位字符所组成
		System.out.println("x".matches("x")); // true
		// \\：表示转义字符\
		System.out.println("\\".matches("\\\\")); // 在字符串中\需要用\\表示，而正则表达式\又需要用\\表示，所以表示正则的字符串需要四个\
		// \t：表示"\t"符号
		System.out.println("\t".matches("\\t"));
		// \n：表示"\n"符号
		System.out.println("\n".matches("\\n"));
		// 二、字符集（数量：1）
		// [abc]：表示可能是字符a或字符b或字符c中的任意一位
		System.out.println("b".matches("[abc]"));
		// [^abc]：表示不是a、b、c中的任意一位
		System.out.println("d".matches("[^abc]"));
		// [a-z]：任意小写字母
		System.out.println("g".matches("[a-z]"));
		// [A-Z]：任意大写字母
		System.out.println("H".matches("[A-Z]"));
		// [0-9]：任意数字
		System.out.println("1".matches("[0-9]"));
		// 三、简化字符集（数量：1）
		// .：任意一位字符
		System.out.println("abcd".matches("abc.")); // true
		System.out.println("abcde".matches("abc.")); // false
		// \d：等价于[0-9]
		System.out.println("2".matches("\\d")); // 主要字符串"\\d"才表示正则表达式"\d"
		// \D：等价于[^0-9]
		System.out.println("a".matches("\\D")); // true
		System.out.println("2".matches("\\D")); // false
		// \s：表示任意空白字符[\t\n\x0B\f\r ]
		System.out.println(" ".matches("\\s"));
		// \S：表示任意非空白字符
		System.out.println("a".matches("\\S")); // true
		System.out.println("\\t".matches("\\S")); // false
		// \w：[a-zA-Z0-9]
		System.out.println("aA0".matches("\\w\\w\\w")); // true
		// \W:[^a-zA-Z0-9]
		System.out.println("\t".matches("\\W")); // true
		// 四、边界匹配（注意：不要在java中使用，容易出问题，在JavaScript中使用） 遗留：在java中使用会出什么问题？
		// ^：正则开始
		// $：正则结束
		System.out.println("ab".matches("^a.*"));
		System.out.println("ab".matches(".*b$"));
		// 五、数量表达式
		// ？：表示正则出现0次或1次
		System.out.println("a".matches("ab?")); // true
		System.out.println("ab".matches("ab?")); // true
		System.out.println("abb".matches("ab?")); // false
		// + ：表示正则出现1次或1次以上
		System.out.println("a".matches("ab+")); // false
		System.out.println("ab".matches("ab+")); // true
		System.out.println("abb".matches("ab+")); // true		
		// * ：表示正则出现0、1或多次
		System.out.println("a".matches("ab*")); // true
		System.out.println("ab".matches("ab*")); // true
		System.out.println("abb".matches("ab*")); // true
		// {n}：正则出现n次
		System.out.println("ab".matches("ab{2}")); // false
		System.out.println("abb".matches("ab{2}"));	// true
		System.out.println("abbb".matches("ab{2}")); // false
		// {n,}：正则出现n次及以上
		System.out.println("ab".matches("ab{2,}")); // false
		System.out.println("abb".matches("ab{2,}"));	// true
		System.out.println("abbb".matches("ab{2,}")); // true
		// {n,m}：正则出现n-m次
		System.out.println("ab".matches("ab{2,3}")); // false
		System.out.println("abb".matches("ab{2,3}"));	// true
		System.out.println("abbb".matches("ab{2,3}")); // true
		System.out.println("abbbb".matches("ab{2,3}")); // false
		// 六、逻辑表达式
		// 正则表达式1 正则表达式2：且
		System.out.println("abc".matches("[a-z]bc"));
		// 正则表达式1|正则表达式2：或
		System.out.println("a".matches("\\d|a"));
		// (正则表达式s)：将多个正则作为一组，可为这一组单独设置次数
		System.out.println("abcabcabc".matches("(abc){3}"));
		
		// 综合示例
		String regex = "(\\d{1,3}\\.){3}\\d{1,3}";
		System.out.println("192.168.0.1".matches(regex));
		
		// 七、java.util.regex包
		Pattern pattern = Pattern.compile("\\d+");
		String [] result = pattern.split("123a4b5cd6e7");
		System.out.println(Arrays.toString(result)); // [, a, b, cd, e]
		
		Pattern patternB = Pattern.compile("\\d+");
		Matcher matcherB = patternB.matcher("123a");
		System.out.println(matcherB.matches()); // false， 匹配结果
	}
}
