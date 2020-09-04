package ÖvnUppg7_Adressbok.Server;

import ÖvnUppg5_Adressbok.Server.Kompis;
import java.io.Serializable;

public class Response implements Serializable {
    
    private boolean success;
    private Kompis person;
    
    public Response(){}
    
    public Response(boolean success){
       this.success = success; 
    }
    
    public Response(boolean success, Kompis person){
       this.success = success; 
       this.person = person;
    }
    
    public void setPerson(Kompis person){
        this.person = person;
    }

    public Kompis getPerson(){
        return person;
    }
    
    public void setSuccess(boolean success){
        this.success = success;
    }

    public boolean getSuccess(){
        return success;
    }
}
