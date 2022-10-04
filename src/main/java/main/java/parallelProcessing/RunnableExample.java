package main.java.parallelProcessing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;



public class RunnableExample {
     private static String path1 = "/Users/rshaika/parallelProcessing/java-concurrency/files/test1.txt";
     private static String path2 = "/Users/rshaika/parallelProcessing/java-concurrency/files/test2.txt";
    public static void main(String[] args) throws IOException {

        //1. Data Integrity check
        String checksumSHA256 = DigestUtils.sha256Hex(new FileInputStream(path1));
        System.out.println("checksumSHA256 : " + checksumSHA256);

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(path2));
        System.out.println("checksumMD5 : " + checksumMD5);

        //2.  create tasks
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

        //3. wait for threads to finish
        try {
            for (int i = 0; i < maxFiles; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //4. collect results
        String content = "";
        for (int i = 0; i< maxFiles; i++) {
            content += tasks.get(i).getContent() + System.lineSeparator();
        }
        System.out.println("Result:");
        System.out.println(content);

        //5. Call compare method
        RunnableExample runnableExample = new RunnableExample();

       if (runnableExample.compareTextFiles(path1, path2)){
           System.out.println("Files are same - Success");
       }
       else {
           System.out.println("Files are different - Error \n");
       }

       //6. Call metaData details method
        RunnableExample metaData = new RunnableExample();
        metaData.metaDataOfFile();

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

    public void metaDataOfFile() throws IOException {

        Path file = Paths.get("/Users/rshaika/parallelProcessing", "java-concurrency", "files", "test1.txt");
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);

        System.out.println("creationTime: " + attr.creationTime());
        System.out.println("lastAccessTime: " + attr.lastAccessTime());
        System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

        System.out.println("isDirectory: " + attr.isDirectory());
        System.out.println("isOther: " + attr.isOther());
        System.out.println("isRegularFile: " + attr.isRegularFile());
        System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
        System.out.println("size: " + attr.size());
    }
}