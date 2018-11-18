package cfrMD5Demo;

import org.apache.commons.codec.digest.DigestUtils;
import sun.awt.CharsetString;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * 原理：严格意义上，MD5不算是加密算法，只是数据完整性校验，不同数据生成的MD5值可能相同，是不可逆的
 * 分析：
 * 一、不同文件格式保存，则加密后结果不同：
 *     如果信息中包含\n，则直接写在字符串中和在写在文件（非UNIX格式保存）中后加密的结果是不同的，因为换行符，
 *     在字符串中是“\n”（对应ASCII编码表十进制是10，缩写是LF），
 *     而在文件中，
 *         如果是windows格式中保存的是CRLF，也就是“\r\n”（\r对应编码表是13，缩写是CR），
 *         如果是mac格式中保存的是CR，
 *         如果是UNIX格式中保存的是LF，只有这种情况从文件中读取才和字符串直接解析结果是一样的
 * 二、如果存在中文，
 *     DigestUtils类的MD5和MD5Hex方法应该是在内部会将字符串从utf-8编码转换成ISO-8859-1编码再处理，因此传入的字符串需要是utf-8编码；
 *     而自定义的方法则需要手动转成ISO-8859-1编码再传入，才能保证两者的结果是相同的
 * 三、文件的字符编码
 *     从结果来看，不管文件是什么编码格式保存，读取时用什么编码格式，只要后面都按读取的编码格式转成字节，再转成相应的iso-8859-1或utf-8编码传递给两种方法，能保证从文件读取加密后的值都相等。遗留：为什么？
 *     而如果文件是utf-8保存，则从文件读取加密，和直接用字符串的加密后的值是相等的，但是如果文件不是utf-8保存，则从文件读取加密，和直接用字符串加密后的值是不等的，为什么？
 *     读取同一个文件，用不同编码格式，为什么读取到的二进制、字节等都是不同的？
 * 结论：
 *      ？
 */
public class App {
    public static void main(String[] args) throws Exception {
        // 加密字符串
        String text = "aaa中文\n";
        String key = "bbb";
        // System.out.println("原始：" + text);
        // System.out.println("key：" + key);

        // 使用java自带的MessageDigest类，结合自己编写方法实现，要使得自定义的方法输出结果与DigestUtils.md5Hex的结果相同，需要转成ISO-8859-1编码
        String inStr = new String((text + key).getBytes(), StandardCharsets.ISO_8859_1); // 或String inStr = new String((text + key).getBytes(), "ISO-8859-1");
        // System.out.println(inStr); // aaaä¸­æ（换行）bbb
        System.out.println("MessageDigest类digest方法加密后：" + MD5(inStr)); // 086c49098656af5c5fa3b65f53b96b2f

        // 使用commons-codec包提供的方法实现加密
        System.out.println("DigestUtils类MD5Hex方法加密后：" + DigestUtils.md5Hex(text + key)); // 086c49098656af5c5fa3b65f53b96b2f

        System.out.println("---------");
        // 加密文件
        // 默认是以utf-8读取，如果文件是以utf-8存储，则读取出来的fileStr是“aaa中文”，而如果文件是以其他编码存储的，比如gbk，则读取出来是错的，变成“aaa����”
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\test.xml"));
        String fileStr = null;
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = fileInputStream.read(buf)) != -1) {
            fileStr = new String(buf, 0, len);
            System.out.println("str:" + fileStr); // aaa中文（换行）
            fileStr = fileStr.replaceAll("\r\n", "\n").replaceAll("\r", "\n"); // 遗留：这样处理有没有什么风险？
        }
        System.out.println(strToBinaryStr(fileStr));
        System.out.println(Arrays.toString(fileStr.getBytes()));

        String fileInStr = new String((fileStr + key).getBytes(), StandardCharsets.ISO_8859_1);
        // System.out.println(fileInStr);
        System.out.println("MessageDigest类digest方法加密后：" + MD5(fileInStr)); // 086c49098656af5c5fa3b65f53b96b2f

        // System.out.println("MD5Hex加密：" + DigestUtils.md5Hex(fileInputStream + key)); // ac6bf291f582405b35f074ee31198b35，因为这样传入参数相当于是fileInputStream.toString() + key = java.io.FileInputStream@18ef96 + key = "java.io.FileInputStream@18ef96bbb"，结果肯定是完全不一样的

        System.out.println("DigestUtils类MD5Hex加密后：" + DigestUtils.md5Hex(fileStr + key)); // 6547436690a26a399603a7096e876a2d
        fileInputStream.close();


        System.out.println("---------");
        // 以指定字符编码格式读取文件，如果文件的保存编码和读取编码不一致，则读取到的信息就不对，但只要后面转对了，结果还是对的
        // 这里这么理解，比如文件中存的是“一”，在存储编码下对应字节为1，文件读取读取到字节1，但是在读取编码下对应的是“二”，所以打印出来和文件中保存不一致，但是后面又用读取编码去转成字节，还是1，后面都是用字节去做处理，所以还是对的
        FileInputStream fileInputStream1 = new FileInputStream(new File("E:\\test.xml"));
        String readCharset = "gbk";
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(fileInputStream1, readCharset));
        StringBuffer stringBuffer1 = new StringBuffer();
        String str;
        while((str = bufferedReader1.readLine()) != null) {
            System.out.println("str:" + str);
            stringBuffer1.append(str + "\n");
        }
        System.out.println(strToBinaryStr(stringBuffer1.toString()));
        System.out.println(Arrays.toString(bufferedReader1.toString().getBytes()));


        String fileInStr1 = new String((new String(stringBuffer1.toString().getBytes(readCharset), "utf-8") + key).getBytes(), StandardCharsets.ISO_8859_1);
        // System.out.println(fileInStr1);
        System.out.println("MessageDigest类digest方法加密后：" + MD5(fileInStr1));
        System.out.println("DigestUtils类MD5Hex加密后：" + DigestUtils.md5Hex(new String(stringBuffer1.toString().getBytes(readCharset), "utf-8") + key));
        bufferedReader1.close();

    }

    // MD5加码。32位
    public static String MD5(String inStr) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        char[] charArray = inStr.toCharArray();
        // System.out.print("charArray：");
        // System.out.println(charArray);
        // for (int i = 0; i < charArray.length; i++) {
        //     int tmp = charArray[i];
        //     System.out.print(tmp);
        //     System.out.print(" ");
        // }
        // System.out.println();

        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        // System.out.println("byteArray：" + Arrays.toString(byteArray));
        // for (int i = 0; i < byteArray.length; i++) {
        //     int tmp = byteArray[i];
        //     System.out.print(tmp);
        //     System.out.print(" ");
        // }
        // System.out.println();


        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    private static String strToBinaryStr(String str) {
        char [] strChar = str.toCharArray();
        String result = "";
        for(int i = 0; i < strChar.length; i ++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }
}
