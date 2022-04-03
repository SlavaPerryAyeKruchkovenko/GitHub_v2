package service;

import logic.Command;
import logic.Message;
import logic.Revision;

public class VersionController {
    private final String path;
    private final Revision verse;

    public VersionController(String path) {
        this.path = path;
        this.verse = new Revision(0);
    }

    public String execute(String[] args) {
        Command cmd = Command.getCommand(args, this.verse);
        Message msg = cmd.execute(this.path);
        return msg.getText();
    }
}
