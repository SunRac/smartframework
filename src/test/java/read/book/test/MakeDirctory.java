package read.book.test;

import java.io.File;
import java.io.IOException;

/**
 * @author java_shj
 * @desc  文件相关操作
 * @createTime 2020/1/3 12:32
 **/
public class MakeDirctory {

    private static void usage() {
        System.err.println("Usage: MakeDirectories path1 ...\n");
    }

    //File类常用方法
    private static void fileData(File file) {
        System.out.println("getAbsolutePath:" + file.getAbsolutePath() +
                "\n canRead:" + file.canRead()+
                "\n exists:" + file.exists()+
                "\n getParentFile:" + file.getParentFile()+
                "\n canWrite:" + file.canWrite()+
                "\n getName:" + file.getName()+
                "\n getPath:" + file.getPath()+
                "\n getParent:" + file.getParent()+
                "\n length:" + file.length() +
                "\n lastModified:" + file.lastModified() +
                "\n isFile:" + file.isFile() +
                "\n getAbsoluteFile:" + file.getAbsoluteFile() +
                "\n isDirectory:" + file.isDirectory()
        );
    }

    public static void main(String[] args) throws IOException {
//        File file = new File("InterA.java");
//        File file = new File("b.txt");
        File file = new File("D:\\data\\myProject\\d.txt");
        file.createNewFile();
        fileData(file);
    }
}
