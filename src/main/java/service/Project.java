package service;

import logic.Revision;

public class Project {
    private Revision currentRevision;

    public Revision getCurrentRevision(){
        return this.currentRevision;
    }
    public void setRevision(Revision newRevision){
        if(newRevision.id > 0)
            this.currentRevision = newRevision;
    }
}
