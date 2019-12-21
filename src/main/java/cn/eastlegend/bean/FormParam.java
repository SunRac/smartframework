package cn.eastlegend.bean;

/**
 * @author java_shj
 * @desc 表单参数
 * @createTime 2019/12/20 21:24
 **/
public class FormParam {
    private String fieldName;
    private Object fieldValue;
    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
