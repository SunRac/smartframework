package read.book.test;

/**
 * @author java_shj
 * @desc 测试string类相关方法
 * @createTime 2019/12/6 11:30
 **/
public class StringTest {
    public static void main(String[] args) {
        InfiniteRecursion infiniteRecursion = new InfiniteRecursion();
        System.out.println(infiniteRecursion);
    }

}

class InfiniteRecursion {
    public String toString() {
        //字符串+非字符串会导致调用this.toString(）方法会导致递归调用
//        return "无线递归：" + this ;
//        return "无线递归：" + super.toString() ;
        return  super.toString() ;
    }
}
