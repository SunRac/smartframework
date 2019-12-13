package cn.eastlegend.generator;

import java.util.ArrayList;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/12/13 18:13
 **/
public class BasicGeneratorDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
//        BasicGenerator<String> generator = new BasicGenerator<>(String.class);
        BasicGenerator<String> generator = BasicGenerator.creat(String.class);
        System.out.println(generator.next());
        new ArrayList<Integer>();
//        System.out.println(String.class.newInstance());
    }
}
