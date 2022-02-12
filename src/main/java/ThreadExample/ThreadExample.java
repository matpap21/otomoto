package ThreadExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExample {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool (2);
        executorService.submit (ThreadExample::elements);
        executorService.submit (ThreadExample::elements);
        executorService.shutdown ();






    }
    private static void elements() {
        for (int i = 0; i < 100; i++) {
            System.out.println (Thread.currentThread ( ).getName ( ) + "" + i);
        }
    }

}
