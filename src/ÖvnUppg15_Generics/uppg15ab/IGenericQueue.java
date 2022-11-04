package ÖvnUppg15_Generics.uppg15ab;


public interface IGenericQueue<T> {
    public void put(T t);
    public T take();
    public int size();
}
