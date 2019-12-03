package cn.eastlegend.chapter2.test;

import read.book.test.InterA;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/12/3 11:39
 **/
public class InterAImpl implements InterA {
    @Override
    public  void mA() {
        System.out.println(a);
    }

    @Override
    public  void mB() {
        //接口中变量是final的
        //Cannot assign a value to final variable 'a'
//        a = 4;
    }

    public static void main(String[] args) {

        InterAImpl interA = new InterAImpl();
        interA.mA();
        //接口中变量是静态
        System.out.println(InterA.a);
    }
}
