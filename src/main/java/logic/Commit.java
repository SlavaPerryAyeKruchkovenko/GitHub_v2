package logic;

import service.VersionController;

public class Commit extends Command {
    private final String message;

    public Commit(Revision revision, String name, VersionController controller) {
        super(revision,controller);
        this.message = name;
    }

    @Override
    public String getName() {
        return "command";
    }

    @Override
    public Message execute(String path) {
        return null;
    }
}
