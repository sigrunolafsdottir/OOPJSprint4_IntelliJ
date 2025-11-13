package ÖvnUppg15_Generics.uppg15ab;


public interface IGenericQueue<E> {
    public void put(E e);
    public E take();
    public int size();
}
