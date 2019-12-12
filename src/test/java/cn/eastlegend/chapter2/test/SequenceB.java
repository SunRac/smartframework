package cn.eastlegend.chapter2.test;


/**
 * @author java_shj
 * @desc
 * @createTime 2019/12/11 13:24
 **/
public class SequenceB implements Sequence {
   /* private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };*/
   private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>(){
       @Override
       protected Integer initialValue() {
           return 0;
       }
   };

    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        SequenceB sequenceB = new SequenceB();
        ClientThread clientThread1 = new ClientThread(sequenceB);
        ClientThread clientThread2 = new ClientThread(sequenceB);
        ClientThread clientThread3 = new ClientThread(sequenceB);
        clientThread1.start();
//        clientThread1.start(); 再启动同一线程报： IllegalThreadStateException
        clientThread2.start();
        clientThread3.start();
    }
}
