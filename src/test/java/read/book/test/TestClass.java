package read.book.test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/11/21 9:12
 **/
public class TestClass {
    public static void main(String[] args) {
//        testInit();
//        System.out.println(String.valueOf(2));
//        listOutOfBund();
        testMd5();
//        positionMoveDemo();
//        positionTest();
    }

    public static void positionTest(){
        /**
         * 左移   高位(左侧)溢出抛弃，低位（右侧）补0
         * 右移   正数高位补0  负数补1
         * 无符号右移   高位补0（左侧补0）
         */
        int i = 2;
        System.out.println(Integer.toBinaryString(i) + "---原始二进制");
        System.out.println(Integer.toBinaryString(i << 6));
        System.out.println(Integer.toBinaryString(i >> 6));
        System.out.println(Integer.toBinaryString(i >>> 6));
        System.out.println("下边是负数位移：");
         i = -2;
        System.out.println(Integer.toBinaryString(i) + "---原始二进制");
        System.out.println(Integer.toBinaryString(i << 6)+ "---负数显示的是补码：");
        System.out.println(Integer.toBinaryString(i >> 6));
        System.out.println(Integer.toBinaryString(i >>> 6));
    }

    private static void positionMoveDemo() {
        byte b = -26;
        System.out.println(Integer.toBinaryString(b));
        //111010
        System.out.println(Integer.toBinaryString(b >>>2));
        System.out.println(b >>>4 & 0xf);
    }

    private static void listOutOfBund() {
        List<Map<String,Object>> list = new ArrayList<>();
        //将会抛出一个indexOutOfBoundException
        System.out.println(list.get(0));
    }

    private static void testInit() {
        Demo1121 demo1121 = new Demo1121("avalue");
        demo1121.d = "ddd";
        System.out.println(demo1121.a);//null
        System.out.println(demo1121.b);//null
        System.out.println(demo1121.c);//0
        System.out.println(demo1121.d);//0
        demo1121.testConstructor();
    }

    public static void testMd5(){
        Random random = new Random();
        String s = String.valueOf(random.nextInt());
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            //java自带的生产散列算法工具类
            MessageDigest digester = MessageDigest.getInstance("MD5");
            try {
                byte[] bytes  = s.getBytes("UTF-8");
                System.out.println("字节数组bytes：" + new String(bytes));
                digester.update(bytes);
                //得到一个长度为16的字节数组
                byte[] digestBytes = digester.digest();
                int j = digestBytes.length;
                System.out.println("新字节数组长度：" + j);
                char[] charArray = new char[j*2];
                int k =0;
                for (int i = 0; i <j; i++) {
                    byte thisByte = digestBytes[i];
                    System.out.println(thisByte);
                    charArray[k++] = hexDigits[thisByte >>>4 & 0xf];
                    charArray[k++] = hexDigits[thisByte & 0xf];
                }
                String resultStr = new String(charArray).toUpperCase();
                System.out.println(resultStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
