package service;

import java.io.File;

public class FileInfo {
    public final String name;
    public final byte[] data;

    public FileInfo(String name, byte[] data) {
        this.name = name;
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() == this.getClass()) {
           FileInfo file = (FileInfo)obj;
           return file.name == this.name;
        }
        return false;
    }
}
