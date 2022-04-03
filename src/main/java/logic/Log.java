package logic;

public class Log extends Command {
    public Log(Revision revision) {
        super(revision);
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
