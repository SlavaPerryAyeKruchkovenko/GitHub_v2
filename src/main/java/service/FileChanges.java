package service;

public class FileChanges {
    public final File file;
    public final State state;

    public FileChanges(File file,State state){
        this.file = file;
        this.state = state;
    }
}
