package ÖvnUppg1_Generics;

import GenericDemo.Pair;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;


public class ÖvnUppg1b_Generics {

    public static <T> void printList  (List<T> l){
        l.forEach(e -> System.out.print(e.toString()+" "));
        System.out.println();
        
        //alternativt sätt att skriva for-loopen
        for (T e : l){
            System.out.print(e.toString()+" ");
        }
        System.out.println();
    }
    
    public static void printList2 (List<? extends String> l){
        l.forEach(e -> System.out.print(e.toString()+" "));
        System.out.println();
        
        //alternativt sätt att skriva for-loopen
        for (String e : l){
            System.out.print(e.toString()+" ");
        }
        System.out.println();
    }
    
    
    public static void main(String[] args){
        List<String> stringlist = Arrays.asList("I", "code", "therefore", "I", "am");
        List<Integer> intlist = Arrays.asList(1,2,3);
        List<Object> objlist = Arrays.asList(new JFrame());
        
        printList(stringlist);
        printList(intlist);
        printList(objlist);
        
        //printList2(stringlist);
        //printList2(intlist);
        //printList2(objlist);
    }
    
}
