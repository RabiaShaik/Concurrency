package main.java.parallelProcessing;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;


public class RunnableExample {

    public static String HOME_DIR = System.getProperty("user.dir");
    public static String FILE_PATH_LOCATION = "/java-concurrency/files/";
    public static String path1 = HOME_DIR + FILE_PATH_LOCATION + "test1.txt";
    public static String path2 = HOME_DIR + FILE_PATH_LOCATION + "test2.txt";

    public static void main(String[] args) throws IOException {

        //1.  create tasks
        List<RunnableTask> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        int maxFiles = 4;
        for (int i = 0; i < maxFiles; i++) {
            RunnableTask task = new RunnableTask("test" + (i + 1) + ".txt");
            Thread t = new Thread(task);
            t.start();
            tasks.add(task);
            threads.add(t);
        }

        //2. wait for threads to finish
        try {
            for (int i = 0; i < maxFiles; i++) {
                threads.get(i).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //3. collect results
        String content = "";
        for (int i = 0; i < maxFiles; i++) {
            content += tasks.get(i).getContent() + System.lineSeparator();
        }
        System.out.println("Result:");
        System.out.println(content);

        //4. Call compare method
        RunnableExample runnableExample = new RunnableExample();

        if (runnableExample.compareTextFiles(path1, path2)) {
            System.out.println("Files are same - Success");
        } else {
            System.out.println("Files are different - Error \n");
        }

        //5. Call metaData details method
        RunnableExample metaData = new RunnableExample();
        metaData.metaDataOfFileList();

        //6. Data Integrity check
        RunnableExample dataIntegrity = new RunnableExample();
        dataIntegrity.dataIntegrityCheck();

    }

    /**
     * The pupose of the method is to compare two files using buffer reader
     *
     * @param file1
     * @param file2
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    static boolean compareTextFiles(String file1, String file2) throws FileNotFoundException, IOException {

        BufferedReader r1 = new BufferedReader(new FileReader(file1));
        BufferedReader r2 = new BufferedReader(new FileReader(file2));
        int c1 = 0, c2 = 0;
        while (true) {
            c1 = r1.read();
            c2 = r2.read();
            if (c1 == -1 && c2 == -1)
                return true;
            else if (c1 == -1 || c2 == -1 || c1 != c2) {
                return false;
            }
        }
    }

    /**
     * The purpose of this method is to retrieve the meta data of the files
     *
     * @throws IOException
     */
    public void metaDataOfFileList() throws IOException {

        try {
            File dir = new File(HOME_DIR + FILE_PATH_LOCATION);
            File[] files = dir.listFiles();
            for (File file : files) {
                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                System.out.println("creationTime: " + attr.creationTime());
                System.out.println("lastAccessTime: " + attr.lastAccessTime());
                System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
                System.out.println("isDirectory: " + attr.isDirectory());
                System.out.println("isOther: " + attr.isOther());
                System.out.println("isRegularFile: " + attr.isRegularFile());
                System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
                System.out.println("size: " + attr.size());
                System.out.println("-----------------------");
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    /**
     * The purpose of the method is to check the data integrity of given files
     *
     * @throws IOException
     */
    public void dataIntegrityCheck() throws IOException {
        String checksumSHA256 = DigestUtils.sha256Hex(new FileInputStream(path1));
        System.out.println("checksumSHA256 : " + checksumSHA256);

        String checksumMD5 = DigestUtils.md5Hex(new FileInputStream(path1));
        System.out.println("checksumMD5 : " + checksumMD5);
        System.out.println("----------------------");
    }
}