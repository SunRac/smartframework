/**
 * @author java_shj
 * @desc
 * @createTime 2019/11/21 9:12
 **/
public class TestClass {
    public static void main(String[] args) {
        Demo1121 demo1121 = new Demo1121("avalue");
        demo1121.d = "ddd";
        System.out.println(demo1121.a);//null
        System.out.println(demo1121.b);//null
        System.out.println(demo1121.c);//0
        System.out.println(demo1121.d);//0
        demo1121.testConstructor();
    }
}
