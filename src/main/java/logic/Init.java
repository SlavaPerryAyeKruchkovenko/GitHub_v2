package logic;

import java.io.File;
import java.io.IOException;

public class Init extends Command{

    public Init(Revision revision) {
        super(revision);
    }

    @Override
    public String getName() {
        return "init";
    }

    @Override
    public Message execute(String path) {
        File file = new File(path);
        if(file.exists()){
            throw new RuntimeException("file exist");
        }
        else if(!file.isFile()){
            throw new RuntimeException("inccorect file");
        }
        else{
            try {
                if(file.createNewFile()){
                    return new Message(new Revision(0),"Initialized");
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
