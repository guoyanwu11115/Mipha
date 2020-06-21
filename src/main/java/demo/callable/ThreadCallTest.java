package demo.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * @author wn:
 * @version 上午16:53:57
 * 类说明
 */
public class ThreadCallTest {
    public static void main(String[]args){
        ExecutorService executor=Executors.newCachedThreadPool();
        Task task=new Task();
        Future<Integer> result=executor.submit(task);
        if (executor != null)
            executor.shutdown();

        try {
            System.out.println("call result"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("over");

    }
}

class Task implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("3.开始 ....");
        Thread.sleep(3000);
        System.out.println("4.结束 ....");
        return 1;
    }
}

