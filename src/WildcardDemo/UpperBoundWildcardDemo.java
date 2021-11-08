package WildcardDemo;

import GenericDemo.Pair;

import java.util.Arrays;
import java.util.List;


public class UpperBoundWildcardDemo  {

    
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

        List<Number> n = Arrays.asList(1, 2, 3);
        System.out.println(sumOfListWildcard(n));

        List<Integer> l = Arrays.asList(1, 2, 3);
        System.out.println(sumOfListWildcard(l));

        System.out.println(sumOfList2(n));

        //Will not work as l is a List of Integer, not Number
        //System.out.println(sumOfList2(l));
    }
    
}
