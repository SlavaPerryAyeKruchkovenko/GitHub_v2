package logic;

public class Commit extends Command {
    private final String message;

    public Commit(Revision revision, String name) {
        super(revision);
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
