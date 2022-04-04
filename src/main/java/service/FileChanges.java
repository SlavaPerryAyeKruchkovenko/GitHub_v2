package service;

public class FileChanges {
    public final FileInfo file;
    public final State state;

    public FileChanges(FileInfo file,State state){
        this.file = file;
        this.state = state;
    }
}
