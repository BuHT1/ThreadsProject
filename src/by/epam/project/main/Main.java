package by.epam.project.main;

import by.epam.project.thread.WriteInFileThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String...args){
        List<WriteInFileThread> writeInFileThreads=new ArrayList<>();
        for(int i=0;i<7;i++){
            writeInFileThreads.add(new WriteInFileThread(i+".txt"));
        }

        ExecutorService executorService= Executors.newFixedThreadPool(4);
        List<Future<String>> resultList;
        try {
            resultList=executorService.invokeAll(writeInFileThreads);

            for (Future<String> stringFuture : resultList) {
                System.out.println(stringFuture.get());
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
