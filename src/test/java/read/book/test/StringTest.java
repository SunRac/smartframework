package read.book.test;

import java.util.Date;

/**
 * @author java_shj
 * @desc 测试string相关,待续
 * @createTime 2019/12/4 9:17
 **/
public class StringTest {
    public static void main(String[] args) {
        //1、字符串常量
        String s1 = "ab";
        String s2 = "ab";
        System.out.println(s1 == s2);

        //2、使用构造函数创建对象
        String s3 = new String("ab");
        String s4 = new String("ab");
        System.out.println(s3 == s4);
        //TODO

        //获取时间戳，毫秒值，2种方法一样
        System.out.println(System.currentTimeMillis());
        System.out.println(new Date().getTime());
    }
}
