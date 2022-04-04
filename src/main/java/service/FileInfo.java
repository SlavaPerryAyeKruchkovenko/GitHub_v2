package service;

public class FileInfo {
    public final String name;
    public final byte[] data;

    public FileInfo(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }
}
