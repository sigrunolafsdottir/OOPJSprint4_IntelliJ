package WildcardDemo;

import java.util.Arrays;
import java.util.List;


public class UpperBoundWildcardDemo {
    
    public static int sumOfListWildcard(List<? extends Number> list) { 
	int s = 0; 
	for (Number n : list) 
		s += n.intValue(); 
	return s; 
    } 
    
    public static int sumOfList2(List<Number> list) { 
	int s = 0; 
	for (Number n : list) 
		s += n.intValue(); 
	return s; 
    } 
    
    public static void main(String[] args){
        List<Integer> l = Arrays.asList(1, 2, 3);
        System.out.println(sumOfListWildcard(l));
        //System.out.println(sumOfList2(l));
        //Number n = new Number();
    }
    
}
