package ÖvnUppg1_Generics;

import java.util.LinkedList;
import java.util.List;


public class GenericNumberQueue <T extends Number> 
        implements IGenericQueue<T> {
    
    private List<T> innerList = new LinkedList<>();
    
    public GenericNumberQueue (){}
    
    public void put(T t){
        innerList.add(t);
    }
    
    public T take(){
        T temp = innerList.get(0);
        innerList.remove(0);
        return temp;
    }
    
    public int size(){
        return innerList.size();
    }
    
//    public Number getValue(){
//        Double sum = 0.0;
//        for (Number t : innerList){
//            sum += t.doubleValue();
//        }
//        return (Number) sum;
//    }
    
    public T getValue(){
        Double sum = 0.0;
        for (T t : innerList){
            sum += t.doubleValue();
        }
        return (T) sum;
    }

}
