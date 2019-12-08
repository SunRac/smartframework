package read.book.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author java_shj
 * @desc java正则表达式相关
 * @createTime 2019/12/8 11:15
 **/
public class RegExpTest {
    public static void main(String[] args) {
//        testFind1(args);
//        1、不带索引find()遍历整个被匹配字符串，依次找到匹配项
//        testFind();

//       2、带索引的find(i)  每次调用都是一个新的、从被匹配字符串指定索引位置开始的find()
//        testFindIndex();
//        3、正则表达式的matches   必须匹配整个被匹配字符串
//        testMatches();

//        4、正则表达式的findAt()  必须从头匹配
//        testLookingAt();
//        5、String对象的matches
//        testStringMatch();


    }

    private static void testLookingAt() {
        //        Matcher m1 = Pattern.compile("\\w").matcher(" abcde");// 头部不匹配false
        Matcher m1 = Pattern.compile("\\w+").matcher("1abcde"); // 头部匹配true
        while (m1.lookingAt()){
            System.out.println(m1.start() + "--" + (m1.end()-1));
        }
    }

    private static void testMatches() {
        Matcher m1 = Pattern.compile("\\w+").matcher("abcde");//整个字符串匹配true
//        Matcher m1 = Pattern.compile("\\w").matcher("abcde"); // 只匹配了一个字符false
        if (m1.matches()){
            System.out.println(m1.start() + "--" + (m1.end()-1));
        }
    }

    private static void testStringMatch() {
        System.out.println("12345".matches("\\d"));//false 只匹配一个数字
        System.out.println("12345".matches("\\d+"));//+才会匹配一个以上的数字
    }

    private static void testFindIndex() {
        int i =0;
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is black");
        while (matcher.find(i)) {
            System.out.println(matcher.group() + "--" + i);
            i++;
        }
    }

    private static void testFind() {
        //一次匹配一个字符
//        Matcher matcher = Pattern.compile("\\w").matcher("Evening is sex");
        //一次匹配一个单词
        Matcher matcher = Pattern.compile("\\w+").matcher("Evening is sex");
        while(matcher.find()) {
            System.out.println(matcher.group() + "");
        }
    }


    //简单测试 find()遍历查找
    private static void testFind1(String[] args) {
        //入参    abcabcabcdefabc  "abc+" "(abc)+" "(abc){2,}"
        if(args.length < 2) {
            System.out.println("入参错误");
        }
        System.out.println("Input: \"" + args[0] + "\"");
        for (String arg : args) {
            System.out.println("模式是：\"" + arg + "\"");
            Pattern pattern = Pattern.compile(arg);
            Matcher matcher = pattern.matcher(args[0]);
            while (matcher.find()) {
                System.out.println("匹配到： \"" + matcher.group() + "\" 位于：" + matcher.start() + "--" + (matcher.end()-1));
            }
        }
    }
}
