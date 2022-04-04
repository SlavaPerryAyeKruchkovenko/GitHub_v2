package main;

import service.VersionController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String dir = System.getProperty("user.dir");
        Scanner in = new Scanner(System.in);
        try {
            VersionController controller = new VersionController(dir,"gayHub");
            String[] value = in.nextLine().split(" ");
            System.out.println(controller.execute(value));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
