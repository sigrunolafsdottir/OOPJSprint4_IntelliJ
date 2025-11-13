package ÖvnUppg15_Generics.uppg15ab;

import java.util.LinkedList;
import java.util.List;


public class GenericQueue <E> implements IGenericQueue<E> {
    
    private List<E> innerList = new LinkedList<>();
    
    public GenericQueue (){}
    
    public void put(E e){
        innerList.add(e);
    }
    
    public E take(){
        E temp = innerList.get(0);
        innerList.remove(0);
        return temp;
    }
    
    public int size(){
        return innerList.size();
    }

}
