package read.book.test;

/**
 * @author java_shj
 * @desc    验证类的加载过程
 * 1、基类的实例初始化+构造初始化 整体上：先于导出类的实例初始化和构造初始化
 * 2、默认初始化、定义初始化在实例初始化之前，是不是也整体先于导出类定义初始化、实例初始化呢？
 * @createTime 2019/11/26 14:13
 **/
public class Beetle extends  Inset {
    private int i = 6;
    private int k ;
//    private int l = printInit("7、子类定义初始化i=" + i+",k=" + k + ",m=" + m);//illegal forwar reference
    private int l = printInit("7、子类定义初始化i=" + i+",k=" + k + "由于m在下一行才定义，" +
        "所以无法打印m的值；而k为0,说明已经默认初始化");
    private  int m;
    {
        k = 7;
        System.out.println("8、子类实例初始化,i=" + i+",k=" + k + ",l=" +l + "，m=" + m);
    }
    public Beetle() {
        System.out.println("9、子类构造方法中,i=" + i+",k=" + k + ",l=" +l + "，m=" + m);
//        System.out.println("a=" + a);  private 子类访问不到
        m =8;
        System.out.println("9.1、子类构造方法结束,i=" + i+",k=" + k + ",l=" +l + ",m=" + m);
    }

    private static int x2 = printInit("3、子类静态初始化");

    static int printInit(String s) {
        System.out.println(s);
        return 10;
    }
    public static void main(String[] args) {
        System.out.println("4、基类、导出类静态变量初始化之后，准备创建子类对象了--main方法中");
        System.out.println("--------以下为实例化过程");
        Beetle beetle = new Beetle();//注释掉这1行，则不会创建对象
        System.out.println("\n 总结：\n" +
                "以4为分界线，4之前加载class文件到内存中初始化静态变量，4之后开始分配内存，创建对象：\n" +
                "1、静态域初始化，先初始化父类的静态域，再初始化子类静态域 \n" +
                "2、父类对象初始化：，对父类实例变量分别进行：默认初始化（基本类型为0，引用类型为null）、定义初始化（变量赋值）、实例初始化（{}中，不带任何关键字）、构造初始化\n" +
                "3、子类对象初始化：依次执行默认、定义、实例、构造等初始化过程");
    }

}
