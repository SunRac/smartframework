package cn.eastlegend.bean;

/**
 * @author java_shj
 * @desc 返回的数据对象类，Data类型的数据封装了一个Object类型的模型数据，框架会将该对象写入HttpServletResponse
 * @createTime 2019/12/6 13:55
 **/
public class Data {
    /**
     * 模型数据
     */
    private Object model;
    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
