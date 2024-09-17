import java.io.Serializable;

public class Appointment implements Serializable {
    private String date;
    private String time;
    private String doctorid;
    private String doctorname;
    private String typeofdoctor;
    Appointment(){}
    Appointment(String d,String t, String di,String dn,String tod){
        this.date=d;
        this.time=t;
        this.doctorid=di;
        this.doctorname=dn;
        this.typeofdoctor=tod;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getTypeofdoctor() {
        return typeofdoctor;
    }

    public void setTypeofdoctor(String typeofdoctor) {
        this.typeofdoctor = typeofdoctor;
    }
}