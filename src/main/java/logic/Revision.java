package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Revision {
    private int id;
    private Date date = new Date();

    public Revision() {

    }

    public Revision(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public Date getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "Revision " + this.id + "\nDate: " +
                new SimpleDateFormat("hh:mm:ss dd.mm.yyyy").format(this.date);
    }
}
