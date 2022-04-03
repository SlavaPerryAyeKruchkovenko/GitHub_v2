package logic;

public class Message {
    private final Revision revision;
    private final String text;

    public Message(Revision revision, String text) {
        this.revision = revision;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public Revision getRevision() {
        return this.revision;
    }
}
