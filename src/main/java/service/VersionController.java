package service;

import logic.Command;
import logic.Message;
import logic.Revision;

public class VersionController {
    private final String path;
    private final Revision verse;
    private final String name;

    public VersionController(String path, String name) {
        this.path = path;
        this.verse = new Revision(0);
        this.name = name;
    }

    public String execute(String[] args) {
        Command cmd = Command.getCommand(args, this.verse);
        Message msg = cmd.execute(this.path);
        return msg.getText();
    }
}
