package GenericDemo;

import java.util.List;


public class Util {
	
    public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) { 
		return p1.getKey().equals(p2.getKey()) 		
                        && p1.getValue().equals(p2.getValue()); 
	}
    
    
    
    public static void main(String[] args){
        
        OrderedPair<String, Integer> p1 = new OrderedPair<>("Even", 8); 
        Pair<String, String> p2 = new OrderedPair<>("Kalle", 
                "Drottninggatan 12");
        OrderedPair<String, Box<Integer>> p3 = new OrderedPair<>("A box", 
                new Box<>());
        OrderedPair<String, String> p4 = new OrderedPair<>("Mimmi", 
                "Drottninggatan 3"); 

       // System.out.println(compare(p2, p2));
       // System.out.println(compare(p1, p2));   //otillåtet eftersom p1 och p2:s typer inte överensstämmer
        System.out.println(compare(p2, p4));
        
    }
    
 } 


