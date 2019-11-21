/**
 * @author java_shj
 * @desc    测试构造初始化
 * @createTime 2019/11/21 9:09
 **/
public class Demo1121 {
    //基本类型会默认初始化，String及引用类型不会
    String a;
    Integer b;
    int c;
    String d = "abcd";
    public Demo1121(String aValue){
        a = aValue;
    }
    void testConstructor(){
        System.out.println("----testConstructor----");
        System.out.println(a);
        System.out.println(d);
    }
}
