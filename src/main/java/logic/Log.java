package logic;

import service.VersionController;

public class Log extends Command {
    public Log(Revision revision, VersionController controller) {
        super(revision, controller);
    }

    @Override
    public String getName() {
        return "log";
    }

    @Override
    public Message execute(String path) {
        return null;
    }
}
