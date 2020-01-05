package cn.eastlegend.trysm;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author java_shj
 * @desc  一个具有两个实例变量的类：files文件列表   dirs 目录列表
 * @createTime 2020/1/4 16:56
 **/
public class FileAndDirectory implements Iterable<File> {
    public List<File> files = new ArrayList<>();
    public List<File> dirs = new ArrayList<>();
    @Override
    public Iterator<File> iterator() {
        return files.iterator();
    }

    //自定义的添加所有方法
    void addAll(FileAndDirectory other){
        files.addAll(other.files);
        dirs.addAll(other.dirs);

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("当前路径下下文件有：\n");
        for(File item : files) {
            sb.append(item.getName());
            sb.append("\n");
        }
        sb.append("==============================\n当前路径下目录有：\n");
        for(File item : dirs) {
            sb.append(item.getName());
            sb.append("\n");
        }
        return sb.toString();
    }

    //开始递归查找
    public static FileAndDirectory recursionDir(String dirPath, String regex) {
        return recursiveDirs(new File(dirPath), regex);
    }

    public static FileAndDirectory recursionDir(File dir, String regex) {
        return recursiveDirs(dir, regex);
    }

    //默认无正则表达式，则查找所有
    public static FileAndDirectory recursionDir(String dirPath) {
        return recursiveDirs(new File(dirPath), ".*");
    }

    /**
     * 遍历当前目录，获取所有的文件和目录
     * @param startDir
     * @param regex
     * @return
     */
    static FileAndDirectory recursiveDirs(File startDir, String regex) {
        FileAndDirectory result = new FileAndDirectory();
        for(File item : startDir.listFiles()) {
            if(item.isDirectory()) {
                result.dirs.add(item);
            } else if(item.isFile()) {
                result.files.add(item);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(recursionDir("D:\\国创\\h5"));
    }




}
