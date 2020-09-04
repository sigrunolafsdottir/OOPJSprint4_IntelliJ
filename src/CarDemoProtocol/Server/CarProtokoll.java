package CarDemoProtocol.Server;

import CarDemo.Server.Database;



public class CarProtokoll {
    
    //This class now holds all the server logic
    
    private static final int BEFORE_INIT = 0;
    private static final int WAITING_FOR_REQUEST = 1;
    
    private int state = BEFORE_INIT;
    Database dao = new Database();
    String dbResponse;
    
    public Object processInput(String theInput) {
        Object theOutput = null;

        if (state == BEFORE_INIT) {
            theOutput = "Vilken bil vill du slå upp?";
            state = WAITING_FOR_REQUEST;
        } else if (state == WAITING_FOR_REQUEST) {
             dbResponse = dao.getCarData(((String)theInput).trim());
             if (dbResponse == null){
                 theOutput = "Bilen kunde inte hittas";
             }
             else{
                 theOutput = dbResponse;
             }
        } 
        return theOutput;
    }

}
