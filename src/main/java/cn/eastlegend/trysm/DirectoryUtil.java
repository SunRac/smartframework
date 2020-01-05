package cn.eastlegend.trysm;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @author java_shj
 * @desc 目录工具类
 * @createTime 2020/1/4 16:46
 **/
public class DirectoryUtil {

    /**
     * 获取指定目录下，文件名满足指定正则表达式的文件
     * @dir
     * @regex
     */
    public static File[] getFileFromDirByName(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    /**
     * 重载方法，先根据文件路径生成文件
     * @param filePath
     * @param regex
     * @return
     */
    public static File[] getFileFromDirByName(String filePath, String regex) {
        return getFileFromDirByName(new File(filePath), regex);
    }


}
