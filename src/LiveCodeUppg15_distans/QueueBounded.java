package LiveCodeUppg15_distans;

import java.util.ArrayList;
import java.util.List;

public class QueueBounded<E extends Number> implements QueueInterface<E> {

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

    public E calcSum2(){

        Double sum =0.0;
        for (E q: queue) {

            try {
                if ( q.getClass() == Byte.class||q.getClass() == Integer.class|| q.getClass() == Short.class||
                        q.getClass() == Long.class|| q.getClass() == Float.class|| q.getClass() == Double.class){
                    sum+= q.doubleValue();
                }
            }catch (Exception e){
                e.printStackTrace();
                Integer ajdå = 0;
                return (E)ajdå;
            }
        }return (E) sum;
    }
}
