package read.book.test;

/**
 * @author java_shj
 * @desc    验证类的加载过程
 * @createTime 2019/11/26 14:13
 **/
public class Beetle extends  Inset {
    private int k ;
//    private int k = printInit("子类初始化");
    public Beetle() {
        System.out.println("子类构造方法中,k=" + k);
//        System.out.println("i=" + i);  private 子类访问不到
        System.out.println("子类构造方法中,j=" + j);
    }

    private static int x2 = printInit("子类静态方法");
    public static void main(String[] args) {
        System.out.println("准备创建子类对象");
        Beetle beetle = new Beetle();
    }

}
