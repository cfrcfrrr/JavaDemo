package cfrIOCodeDemo;

import java.io.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        File file = new File("E:\\tmp.txt");
        BufferedReader brGBK = new BufferedReader(new InputStreamReader(new FileInputStream(file), "gbk"));
        printBinary(brGBK);

        BufferedReader brUTF8 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
        printBinary(brUTF8);

        brGBK.close();
        brUTF8.close();

    }
    public static void printBinary(BufferedReader bufferedReader) throws Exception {
        int i;
        while((i = bufferedReader.read()) != -1) {
            System.out.print(Integer.toBinaryString(i));
            System.out.print(" ");
        }
        System.out.println();
    }
}
