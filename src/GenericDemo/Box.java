package GenericDemo;

// A small class that showcases generics

import java.util.Arrays;
import java.util.List;



public class Box<T> { // T stands for "Type" 
	private T t; 
	
        public void set(T t) { 
		this.t = t; 
	} 
	public T get() { 
		return t; 
	} 
        
        
        public static void main(String[] args){
//            Box<Number> b1 = new Box<>();
//            b1.set(3);
//            b1.set(3.14);
//            System.out.println(b1.get());
            
            Box<String> b2 = new Box<>();
            b2.set("Meddelande");
//            System.out.println(b2.get());

            String[] strArr = {"hej", "hopp"};
            
            Box<String[]> b3 = new Box<>();
            b3.set(strArr);
            
//            System.out.println(b3);
//            System.out.println(b3.get());
//            
//            for (String string : b3.get()) {
//                System.out.println(string);
//            }
            
            Box<Box<String>> b4 = new Box<>();
            b4.set(b2);
           // System.out.println(b4.get().get());
            
            Box<Box<Box<String>>> b5 = new Box<>();
            b5.set(new Box<>());
            b5.get().set(new Box<>());
            b5.get().get().set("just because I can");
            System.out.println(b5.get().get().get());
        
        }
        
} 

