package com.samodeika.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static void readFile() {
        File file = new File("C:\\dev\\jsons\\albania-players.json");
        readFile(file);
    }

    public static String readFile(File file) {
        String result = null;
        try (FileInputStream in = new FileInputStream(file))
        {
            result = getFileContent(in);
            //System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isValidDirectory(File dir) {
        return dir.exists();
    }

    public static List<String> listFilesFromFolder(File folder) {
        System.out.println("List files:");
        List<String> files = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesFromFolder(fileEntry);
            } else {
                files.add(fileEntry.getAbsolutePath());
            }
        }
        return files;
    }

    public static String getFileContent(InputStream in) {
        byte[] data;
        try {
            data = new byte[in.available()];
            int bytesRead = in.read(data);
            return new String(data, 0, bytesRead, "UTF-8");
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static File writeToFile(String fileName, String data) {
        File newFile = new File(fileName);
        try {
            FileWriter fw = new FileWriter(newFile);
            fw.write(data);
            fw.close();
            return newFile;
        } catch (IOException iox) {
            //do stuff with exception
            iox.printStackTrace();
        }
        return newFile;
    }


}
