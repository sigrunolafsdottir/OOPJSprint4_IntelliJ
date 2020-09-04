package WildcardDemo;

import java.util.Arrays;
import java.util.List;


public class UnBoundWildcardDemo {
    
    public static void printListUnboundedWildcard(List<?> list) {
        list.forEach(l -> System.out.print(l + " "));
        System.out.println();
    }
    
    public static void printListObject(List<Object> list) {
        list.forEach(l -> System.out.print(l + " "));
        System.out.println();
    }
    
    public static void main(String[] args){
        List<Integer> li = Arrays.asList(1, 2, 3);
        List<String>  ls = Arrays.asList("one", "two", "three");
        List<Object>  lo = Arrays.asList("A", "B", "C");
//        printListUnboundedWildcard(li);
 //       printListUnboundedWildcard(ls);
 //       printListObject(ls);
        printListUnboundedWildcard(lo);
        printListObject(lo);
    }
    
}
