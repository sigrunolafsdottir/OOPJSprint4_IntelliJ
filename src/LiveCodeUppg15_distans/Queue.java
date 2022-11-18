package LiveCodeUppg15_distans;

import java.util.ArrayList;
import java.util.List;

public class Queue <E> implements QueueInterface<E> {

    List<E> queue = new ArrayList<>();

    @Override
    public void put(E e) {
        queue.add(e);
    }

    @Override
    public E take() {
        E temp = queue.get(0);
        queue.remove(0);
        return temp;
    }

    @Override
    public int size() {
        return queue.size();
    }


}
