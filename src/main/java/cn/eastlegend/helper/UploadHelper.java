package cn.eastlegend.helper;

import cn.eastlegend.bean.FileParam;
import cn.eastlegend.bean.FormParam;
import cn.eastlegend.bean.Param;
import cn.eastlegend.util.CollectionUtil;
import cn.eastlegend.util.FileUtil;
import cn.eastlegend.util.StreamUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author java_shj
 * @desc 文件上传助手
 * @createTime 2019/12/20 20:57
 **/
public class UploadHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadHelper.class);

    /**
     * Apach commons-fileupLoad提供的Servlet文件上传对象
     */
    private static ServletFileUpload servletFileUpload;
    /**
     * 初始化
     */
    public static void init(ServletContext servletContext) {
        //获取临时保存文件的路径
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //创建文件上传对象，设置临时保存在内存中的阈值
        servletFileUpload = new ServletFileUpload(new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository));
        int uploadLimit = ConfigHelper.getUploadLimit();
        //设置允许上传的文件最大值
        if(uploadLimit != 0) {
            servletFileUpload.setFileSizeMax(uploadLimit *1024 *1024);
        }
    }

    /**
     * 判断请求是否为Mutipart类型
     */
    public static boolean isMutipart(HttpServletRequest request) {
        return servletFileUpload.isMultipartContent(request);
    }

    /**
     * 从request中创建请求对象Param
     */
    public static Param createParam(HttpServletRequest request) {
        List<FileParam> fileParamList = new ArrayList<>();
        List<FormParam> formParamList = new ArrayList<>();
        try {
            //从request中解析请求中的参数名和参数值键值对，可能有多个同名参数，所以是list
            Map<String, List<FileItem>> fieldListMap = servletFileUpload.parseParameterMap(request);
            if(CollectionUtil.isMapNotEmpty(fieldListMap)) {
                for (Map.Entry<String, List<FileItem>> fieldListEntry : fieldListMap.entrySet()) {
                    //获取字段名
                    String fieldName = fieldListEntry.getKey();
                    List<FileItem> fieldValueList = fieldListEntry.getValue();
                    if(CollectionUtil.isNotEmpty(fieldValueList)) {
                        for (FileItem fileItem : fieldValueList) {
                            if(fileItem.isFormField()) {
                                String fieldValue = fileItem.getString("UTF-8");
                                formParamList.add(new FormParam(fieldName, fieldValue));
                            } else {
                                String realFileName = FileUtil.getRealFileName(new String(fileItem.getName().getBytes(), "UTF-8"));
                                if(StringUtils.isNotEmpty(realFileName)) {
                                    long fileSize = fileItem.getSize();
                                    String contentType = fileItem.getContentType();
                                    InputStream inputStream = fileItem.getInputStream();
                                    fileParamList.add(new FileParam(fieldName, realFileName, fileSize, contentType, inputStream));
                                }

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("文件上传获取参数异常：", e);
        }

        return new Param(formParamList, fileParamList);

    }

    /**
     * 上传文件
     */
    public static void uploadFile(String basePath, FileParam fileParam) {
        if(fileParam != null) {
            String filePath = basePath + fileParam.getFieldName();
            File file = FileUtil.createFile(filePath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream((fileParam.getInputStream()));
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                StreamUtil.copySteam(bufferedInputStream, bufferedOutputStream);
            } catch (FileNotFoundException e) {
                LOGGER.error("上传文件异常：", e);
            }

        }
    }

    /**
     * 批量上传文件
     */

    public static void uploadFiles(String basePath, List<FileParam> fileParamList) {
        if(CollectionUtil.isNotEmpty(fileParamList)) {
            for (FileParam fileParam : fileParamList) {
                uploadFile(basePath, fileParam);
            }
        }
    }

}
