package cn.eastlegend.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author java_shj
 * @desc 视图类
 *   由于视图中是可以包含模型数据的，所以在View中包含了视图的路径和该视图所需的模型数据
 * @createTime 2019/12/6 13:48
 **/
public class View {

    /**
     * 视图的路径
     */
    private String path;
    /**
     * 视图的数据
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    /**
     * 增加模型数据
     * @param key
     * @param value
     * @return
     */
    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
