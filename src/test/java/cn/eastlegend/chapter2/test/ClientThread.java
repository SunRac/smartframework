package cn.eastlegend.chapter2.test;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/12/11 13:15
 **/
public class ClientThread extends Thread {
    private Sequence sequence;
    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() +  "==>" + sequence.getNumber());
        }
    }
}
