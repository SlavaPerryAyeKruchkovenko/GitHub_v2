package logic;

import service.VersionController;

public class Status extends Command {

    public Status(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return "status";
    }

    @Override
    public Message execute(String path) {
        return null;
    }
}
