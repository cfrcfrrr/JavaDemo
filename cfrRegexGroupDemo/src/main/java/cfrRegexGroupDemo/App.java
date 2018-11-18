package cfrRegexGroupDemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // // 捕获组动态修改：使用matcher.group取出匹配数据后再进行修改
        // String str = "aaa_bbb_ccc";
        // String regex = "_([a-z])";
        // // System.out.println(str.replaceAll(regex, "$1".toUpperCase())); // aaabbbccc，这种方式不行
        //
        // Pattern pattern = Pattern.compile(regex);
        // Matcher matcher = pattern.matcher(str);
        // while (matcher.find()) {
        //     str = str.replaceAll(matcher.group(), matcher.group(1).toUpperCase());
        // }
        // System.out.println(str); // aaaBbbCcc
        //
        // // 反向引用, 比如\\1表示匹配第一个分组
        // System.out.println("aa12bb12345".replaceAll("(\\d{2}).+?\\1", "")); // aa345
        //
        // // 捕获组编号：以(开始编号
        // Matcher matcher2 = Pattern.compile("((a+)_(b+))_(c+)").matcher("aaa_bbb_ccc");
        // if (matcher2.find()) {
        //     System.out.println("group(0) " + matcher2.group(0)); // aaa_bbb_ccc
        //     System.out.println("group(1) " + matcher2.group(1)); // aaa_bbb
        //     System.out.println("group(2) " + matcher2.group(2)); // aaa
        //     System.out.println("group(3) " + matcher2.group(3)); // bbb
        //     System.out.println("group(4) " + matcher2.group(4)); // ccc
        // }
        //
        // // 非捕获组，(?:<Expression>)表示这个组进行匹配，但不进行捕获，也就是不会占用捕获组编号，不会保存到内存中，可以节约内存
        // // 遗留：有的还有说到(?=<Expression>)、(?!<Expression>)、(?<=<Expression>)、(?<!<Expression>)四个零宽度断言，还有(?igmsxe<Expression>)等模式修正符，还有(?>Pattern)侵占模式，但是断言的试了下好像没用，不知道是不是java不支持，后面有需要再了解
        // Matcher matcher3 = Pattern.compile("((?:a+)_(b+))_(c+)").matcher("aaa_bbb_ccc");
        // if (matcher3.find()) {
        //     System.out.println("group(0) " + matcher3.group(0)); // aaa_bbb_ccc
        //     System.out.println("group(1) " + matcher3.group(1)); // aaa_bbb
        //     System.out.println("group(2) " + matcher3.group(2)); // aaa
        //     System.out.println("group(3) " + matcher3.group(3)); // bbb
        // }


        // 断言
        // 比如匹配不包含某部分（如字符串aaa）
        // (?=X )：零宽度正先行断言。仅当子表达式 X 在 此位置的右侧匹配时才继续匹配。例如，\w+(?=\d) 与后跟数字的单词匹配，而不与该数字匹配。此构造不会回溯。
        String regexAssertPositiveAhead = "(?=a).*";
        Matcher matcherAssertPositiveAhead = Pattern.compile(regexAssertPositiveAhead).matcher("babc");
        if(matcherAssertPositiveAhead.find()) { // find只要求部分字符串匹配（！！！）
            // 必须要调完find()才能调group()
            System.out.println(matcherAssertPositiveAhead.group()); // abc，遗留问题：不是说断言的不参与匹配吗，那不是应该输出bc吗？
        }
        System.out.println(Pattern.matches(regexAssertPositiveAhead, "babc")); // false，因为matches要求整个字符串匹配（！！！）

        // (?!X)：零宽度负先行断言。仅当子表达式 X 不在 此位置的右侧匹配时才继续匹配。例如，例如，\w+(?!\d) 与后不跟数字的单词匹配，而不与该数字匹配 。
        System.out.println(Pattern.compile("^(?!a).*").matcher("abc").find()); // false，不以a开头
        String regexAssertNegativeAhead = "^((?!a).)*$"; // 整个表达式中不包含a
        System.out.println(Pattern.compile(regexAssertNegativeAhead).matcher("abc").find()); // false
        System.out.println(Pattern.compile(regexAssertNegativeAhead).matcher("bac").find()); // false
        System.out.println(Pattern.compile(regexAssertNegativeAhead).matcher("a").find()); // false
        System.out.println(Pattern.compile(regexAssertNegativeAhead).matcher("bca").find()); // false，遗留问题：这个不是以a开头，为什么也是false？
        System.out.println(Pattern.compile(regexAssertNegativeAhead).matcher("b").find()); // true

        // (?<=X)：零宽度正后发断言。仅当子表达式 X 在 此位置的左侧匹配时才继续匹配。例如，(?<=19)99 与跟在 19 后面的 99 的实例匹配。此构造不会回溯。
        System.out.println(Pattern.compile(".*(?<=a)").matcher("ba").find()); // true

        // (?<!X)：零宽度负后发断言。仅当子表达式 X 不在此位置的左侧匹配时才继续匹配。例如，(?<!19)99 与不跟在 19 后面的 99 的实例匹配
        System.out.println(Pattern.compile(".*(?<!c)$").matcher("abc").find()); // false，不以c结尾，遗留问题：为什么这里使用.*(?<c)$不行？必须要用后发断言？再理解下
    }
}
