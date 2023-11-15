package CarObjectDemo.Server;


import java.io.Serializable;

//only available backend
public class Car implements Serializable {
    
    protected String regnr;
    protected String owner;
    protected String model;
    protected String color;
    
    public Car(String regnr, String owner, String model, 
            String color){
        this.regnr = regnr;
        this.owner = owner;
        this.model = model;
        this.color = color;
    }

    public Car(){
    }
    
    public String getRegNr(){
        return regnr;
    }
    
    public String getData(){
        return "Regnummer: "+regnr+", Owner: "+owner
                +", Modell: "+model +", Färg: "+color;
    }

}
    
    
    
