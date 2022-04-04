package main;

import service.VersionController;

public class Main {
    public static void main(String[] args) {
        final String dir = System.getProperty("user.dir");
        try {
            VersionController controller = new VersionController(dir,"gayHub");
            System.out.println(controller.execute(args));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
