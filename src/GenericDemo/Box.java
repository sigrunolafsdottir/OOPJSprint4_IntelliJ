package GenericDemo;

// A small class that showcases generics

import java.util.ArrayList;
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
        
    void main(String[] args){

        List testList = new ArrayList();
        testList.add("hej");
        testList.add(4);

        class X {
            int i = 5;
        }

        testList.add(new X());

        for (Object o : testList){
            System.out.println(o);
        }

        System.out.println();
        Box<Number> b1 = new Box<>();
        b1.set(3);
        System.out.println(b1.get());
        b1.set(3.14);
        System.out.println(b1.get());

        Box<String> b2 = new Box<>();
        b2.set("Meddelande");
        System.out.println(b2.get());


        System.out.println();

        String[] strArr = {"hej", "hopp"};

        Box<String[]> b3 = new Box<>();
        b3.set(strArr);
            
        System.out.println(b3);
        System.out.println(b3.get());

        for (String string : b3.get()) {
            System.out.println(string);
        }

        System.out.println();
        Box<Box<String>> b4 = new Box<>();
        b4.set(b2);
        System.out.println(b4.get().get());

        System.out.println();
        Box<Box<Box<String>>> b5 = new Box<>();
        b5.set(new Box<>());
        b5.get().set(new Box<>());
        b5.get().get().set("just because I can");
        System.out.println(b5.get().get().get());

        System.out.println();

        List<Box> boxlist = new ArrayList<>();
        boxlist.add(b5);
        boxlist.add(b1);
        //boxlist.add(new Object());  //funkar inte
        //boxlist.add("hej");         //funkar inte

        for (Box b : boxlist){
            System.out.println(b.get());
        }

        List<Box<String>> boxStringlist = new ArrayList<>();
        boxStringlist.add(b2);
        //boxStringlist.add(b1);  //funkar inte pga Box<Number>

        for (Box b : boxStringlist){
            System.out.println(b.get());
        }

    }
        
} 

