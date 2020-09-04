package ÖvnUppg1_Generics;

import java.util.LinkedList;
import java.util.List;


public class GenericQueue <T> implements IGenericQueue<T> {
    
    private List<T> innerList = new LinkedList<>();
    
    public GenericQueue (){}
    
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

}
