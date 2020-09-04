package CarDemo.Server;



//only available backend
public class Car {
    
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
    
    public String getRegNr(){
        return regnr;
    }
    
    public String getData(){
        return "Regnummer: "+regnr+", Owner: "+owner
                +", Modell: "+model +", Färg: "+color;
    }

}
    
    
    
