package by.epam.project.thread;

import java.io.FileWriter;
import java.util.Random;
import java.util.concurrent.Callable;

public class WriteInFileThread implements Callable<String> {
    private final String fileName;

    public WriteInFileThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String call() throws Exception {
        StringBuilder stringBuilder = new StringBuilder();

        try(FileWriter fileWriter = new FileWriter(fileName)) {
            Random random = new Random();

            stringBuilder.append("[").append(Thread.currentThread().getName()).append("][").append(fileName).append("][");
            for (int i = 0; i < 5; i++) {
                int digit = random.nextInt(30);

                fileWriter.write(digit + (i<4?", ":"."));
                stringBuilder.append(digit).append(i < 4 ? "," : "].");
            }
        }
        return stringBuilder.toString();
    }
}
