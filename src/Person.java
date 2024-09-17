import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.*;

public class Person implements Serializable {
    private String name;
    private String gender;
    private String mobilenumber;
    private String email;
    private String adress;

    Person(){

    }


    Person(String n,String g, String m,String e,String a){
        this.name=n;
        this.gender=g;
        this.mobilenumber=m;
        this.email=e;
        this.adress=a;

    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
        return("\nName :"+getName()+"\nGender:"+getGender()+"\nMobile Number:"+getMobilenumber()+
                "\nEmail Adress:"+getEmail()+  "\nAdress:"+getAdress());
    }
}

