package LiveCodeUppg15_distans;

public interface QueueInterface<E> {

    public void put(E e);
    public E take();
    public int size();

}
