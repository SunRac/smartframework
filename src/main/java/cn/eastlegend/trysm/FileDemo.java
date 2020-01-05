package cn.eastlegend.trysm;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @author java_shj
 * @desc 文件相关操作
 * @createTime 2020/1/4 16:25
 **/
public class FileDemo {

    public static void main(String[] args) {
        File path = new File(".");
//        System.out.println(path.getAbsolutePath());
        String[] list = path.list(new MyFileFilter("^.+txt$"));
//        String[] list = path.list();
        for (String s : list) {
            System.out.println(s);
        }

    }
}

//根据文件名过滤
class MyFileFilter implements FilenameFilter {
    private Pattern pattern;

    public MyFileFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
