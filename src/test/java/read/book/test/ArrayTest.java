package read.book.test;


import java.util.Arrays;
import java.util.List;

/**
 * @author java_shj
 * @desc    测试数组相关
 * @createTime 2019/12/3 9:08
 **/
public class ArrayTest {
    public static void main(String[] args) {
        //1、对象数组调用Arrays.asList方法直接返回一个list
        Integer[] arr = new Integer[]{1, 2, 3};
        System.out.println(arr);
        List<Integer> ints = Arrays.asList(arr);
        List<Integer> arr1 = Arrays.asList(2, 6, 3, 3);
        //2、基本类型数组调用Arrays.asList方法返回一个List<int[]>，没什么用
        int[] arr2 = new int[]{1,2,3};
        List<int[]> ints1 = Arrays.asList(arr2);
        System.out.println(ints1);
    }
}
