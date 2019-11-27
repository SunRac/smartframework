package read.book.test;


public class Inset {
    private int a =31;
    protected int b;
    protected int c;
    protected int d = printInit("5.0、基类定义初始化，在4之后，5以前");

    {
        System.out.println("5、基类实例初始化，a=" +a + "，b=" +b + "，c=" +c);
        b= 32;
        System.out.println("5.1、基类实例初始化结束，a=" +a + "，b=" +b + "，c=" +c);
    }

    Inset() {
        System.out.println("6、基类构造初始化");
        c = 33;
        System.out.println("6.1、基类构造初始化结束，a=" +a + ",b=" +b + ",c=" +c);
    }

    private  static int x = printInit("1、基类静态变量 --X 初始化");

    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }
    static {
        System.out.println("2、基类静态代码块初始化（定义顺序在1之后，所以初始化在1之后）");
        y =88;
    }
    static int y;
}
