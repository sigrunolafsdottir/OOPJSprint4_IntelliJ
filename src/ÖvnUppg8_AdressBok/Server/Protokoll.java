package ÖvnUppg8_AdressBok.Server;

import ÖvnUppg8_AdressBok.POJOs.Kompis;
import ÖvnUppg8_AdressBok.POJOs.Initiator;
import ÖvnUppg8_AdressBok.POJOs.Response;


public class Protokoll {
    
    //This class now holds all the server logic
    
    private static final int BEFORE_INIT = 0;
    private static final int WAITING_FOR_REQUEST = 1;
    
    private int state = BEFORE_INIT;
    private DAO dao = new DAO();
    Kompis dbResponse;
    
    public Object processInput(String theInput) {
        Object theOutput = null;

        if (state == BEFORE_INIT) {
            theOutput = new Initiator();
            state = WAITING_FOR_REQUEST;
        } else if (state == WAITING_FOR_REQUEST) {
            
             dbResponse = dao.getPersonByName(((String)theInput).trim());
             if (dbResponse == null){
                 theOutput = new Response(false);
             }
             else{
                 theOutput = new Response(true, dbResponse);
             }
        } 
        return theOutput;
    }

}
