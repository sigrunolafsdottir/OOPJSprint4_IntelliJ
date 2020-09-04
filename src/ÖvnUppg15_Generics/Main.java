package ÖvnUppg1_Generics;

import ÖvnUppg1_Generics.GenericQueue;
import ÖvnUppg1_Generics.GenericNumberQueue;


public class Main {
    
    public static void main (String args[]){
        
        GenericQueue <Integer> genQ = new GenericQueue<>();
        
        genQ.put(5);
        genQ.put(7);
        Integer i = genQ.take();
        System.out.println("size: "+ genQ.size());
        
//        GenericQueue <String> genQ2 = new GenericQueue<>();
//        
//        genQ2.put("hej");
//        genQ2.put("hopp");
//        String s = genQ2.take();
//        System.out.println("size: "+ genQ2.size());
//        
//        GenericNumberQueue <Integer> genQ3 = 
//                new GenericNumberQueue<>();
//        
//        genQ3.put(5);
//        genQ3.put(7);
//        genQ3.put(3);
//        Integer i2 = genQ3.take();
//        System.out.println("size: "+ genQ3.size());
//        System.out.println("sum: "+ genQ3.getValue());
//        
//        GenericNumberQueue <Double> genQ4 = new GenericNumberQueue<>();
//        
//        genQ4.put(5.7);
//        genQ4.put(7.4);
//        genQ4.put(3.3);
//        Double d = genQ4.take();
//        System.out.println("size: "+ genQ4.size());
//        System.out.println("sum: "+ genQ4.getValue());
        
        //Funkar inte
       // GenericNumberQueue <String> genQ4 = new GenericNumberQueue<>();
        

        
    }
    
    

}
