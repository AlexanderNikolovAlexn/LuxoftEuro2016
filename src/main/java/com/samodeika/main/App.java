package com.samodeika.main;

import com.samodeika.utils.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alexander Nikolov on 13.6.2016 ?..
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Run main!");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Directory: ");
        String directory;
        directory = scanner.nextLine();
        if(directory == null || directory.isEmpty()) {
            directory = "C:\\dev\\jsons";
        }
        final File folder = new File(directory);
        final List<String> files;
        if(FileUtils.isValidDirectory(folder)) {
            files = FileUtils.listFilesFromFolder(folder);
        }
    }



}
