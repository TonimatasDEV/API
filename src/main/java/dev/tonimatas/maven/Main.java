package dev.tonimatas.maven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static String id;

    public static void main(String[] args) {
        new File("1201").mkdir();

        try {
            Scanner scanner = new Scanner(new File("id.txt"));
            id = scanner.next();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error on load id file.");
        }

        SpringApplication.run(Main.class, args);
    }
}
