package cn.eastlegend.chapter2.test;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/12/11 13:17
 **/
public class SequenceA implements Sequence {
    private static int number = 0;
    @Override
    public int getNumber() {
        number++;
        return number;
    }

    public static void main(String[] args) {
        SequenceA sequenceA = new SequenceA();
        ClientThread clientThread1 = new ClientThread(sequenceA);
        ClientThread clientThread2 = new ClientThread(sequenceA);
        ClientThread clientThread3 = new ClientThread(sequenceA);
        clientThread1.start();
//        clientThread1.start(); 再启动同一线程报： IllegalThreadStateException
        clientThread2.start();
        clientThread3.start();
    }
}
