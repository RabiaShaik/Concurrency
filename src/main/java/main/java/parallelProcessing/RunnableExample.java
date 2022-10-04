package main.java.parallelProcessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RunnableExample {
     private static String path1 = "/Users/rshaika/parallelProcessing/java-concurrency/files/test1.txt";
     private static String path2 = "/Users/rshaika/parallelProcessing/java-concurrency/files2/test1.txt";
    public static void main(String[] args) throws IOException {
        // create tasks
        List<RunnableTask> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        int maxFiles = 2;
        for (int i = 0; i < maxFiles; i++) {
            RunnableTask task = new RunnableTask("test" + (i + 1) + ".txt");
            Thread t = new Thread(task);
            t.start();
            tasks.add(task);
            threads.add(t);
        }

        // wait for threads to finish
        try {
            for (int i = 0; i < maxFiles; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // collect results
        String content = "";
        for (int i = 0; i< maxFiles; i++) {
            content += tasks.get(i).getContent() + System.lineSeparator();
        }
        System.out.println("Result:");
        System.out.println(content);

        RunnableExample runnableExample = new RunnableExample();

       if (runnableExample.compareTextFiles(path1, path2)){
           System.out.println("Files are same");
       }
       else {
           System.out.println("Files are different");
       }
    }

    static boolean compareTextFiles ( String file1, String file2) throws FileNotFoundException, IOException{
        BufferedReader r1 = new BufferedReader(new FileReader(file1));
        BufferedReader r2 = new BufferedReader(new FileReader(file2));
        int c1=0, c2=0;
        while(true){
            c1 = r1.read();
            c2 = r2.read();
            if(c1==-1 && c2==-1)
                return true;
            else if(c1==-1 || c2==-1 || c1!=c2){
                return false;
            }
        }
    }
}