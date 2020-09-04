package ÖvnUppg1_Generics;


public interface IGenericQueue<T> {
    public void put(T t);
    public T take();
    public int size();
}
