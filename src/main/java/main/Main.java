package main;

import service.VersionController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String dir = System.getProperty("user.dir");
        Scanner in = new Scanner(System.in);
        try {
            VersionController controller = new VersionController(dir, "gayHub");
            System.out.println(controller.execute(args));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
