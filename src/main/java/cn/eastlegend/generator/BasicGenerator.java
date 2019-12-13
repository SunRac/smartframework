package cn.eastlegend.generator;

/**
 * @author java_shj
 * @desc 基本生成器  类上的泛型与方法上的泛型
 * @createTime 2019/12/13 17:57
 **/
public class BasicGenerator<T> implements Generator<T>{

    private Class<T> type;
    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    //不想每次都调用new BasicGenerator
    //静态方法如果用到泛型特性，必须要声明为泛型方法
    public static <T> BasicGenerator<T> creat(Class<T> type) {
        return new BasicGenerator<>(type);
    }

    @Override
    public  T next() {
        try {

            System.out.println(type.getName());
            System.out.println(type.getSimpleName());
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
