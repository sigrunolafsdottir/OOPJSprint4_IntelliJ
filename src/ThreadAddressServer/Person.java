package ThreadAddressServer;

//import AddressServer.*;
import java.time.LocalDate;
import java.time.Month;

//only available backend
public class Person {
    
    protected String name;
    String address;
    protected LocalDate dateOfBirth;
    protected String disease;
    protected boolean hospitalEmployee;
    
    public Person(String name, String address, LocalDate dateOfBirth, String disease, boolean hospitalEmployee){
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.disease = disease;
        this.hospitalEmployee = hospitalEmployee;
    }
    
    public boolean isPatient(){
        return !hospitalEmployee;
    }
    
    public boolean isHospitalEmployee(){
        return hospitalEmployee;
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
    
    public void setDisease(String disease){
        this.disease = disease;
    }
    
    public String getDisease(){
        return disease;
    }

}
    
    
    
