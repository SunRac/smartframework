package read.book.test;


public class Inset {
    private int i =9;
    protected int j;

    {
        System.out.println("实例初始化");
        System.out.println("第一，j=" +j + "i=" +i);
        j = 33;
        System.out.println("第二，j=" +j + "i=" +i);
    }

    Inset() {
        System.out.println("构造初始化");
        System.out.println("i=" + i + ",j=" + j);
        j = 39;
    }

    private  static int x1 = printInit("static Insect.x1 initialized");

    static int printInit(String s) {
        System.out.println(s);
        return 47;
    }
    static {
        System.out.println("静态初始化");
        x =66;
        y =88;
    }
    static int x;
    static int y;
}
