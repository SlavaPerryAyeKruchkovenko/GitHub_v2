package logic;

import service.VersionController;

import java.io.File;

public class Checkout extends Command {

    public Checkout(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Message execute(String path) {
        File file = new File(path + "\\version controller.dat");
        return new Message(this.revision, "Initialized");
    }
}
