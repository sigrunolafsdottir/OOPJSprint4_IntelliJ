package ÖvnUppg6_Adressbok.POJOs;

import java.io.Serializable;
import java.time.LocalDate;

public class Kompis implements Serializable{
    
    protected String name;
    String address;
    protected LocalDate dateOfBirth;
    
    public Kompis(String name, String address, LocalDate dateOfBirth){
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public void setBirthday(int year, int month, int day){
        dateOfBirth = LocalDate.of(year, month-1, day);
    }
    
    public LocalDate getBirthday(){
        return dateOfBirth;
    }

}
    
    
    
