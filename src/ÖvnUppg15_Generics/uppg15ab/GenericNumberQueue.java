package ÖvnUppg15_Generics.uppg15ab;

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


    //It works to convert all numbers to double, but in this way, we cannot have a generic result
    public double getValueInDoubleFormat(){
        double sum = 0.0;
        //Number sum = 0;

        for (T t : innerList){
            sum += t.doubleValue();
        }
        return sum;
    }

    //Här har vi ett generiskt resultat, men notera att vi kan ha en lista av typ List<Number> och
    //då kan vi ha olika type på våra tal, jag kollar bara det första, så då riskerar det att bli fel pga det
    public T getValue2(){

       // Detta funkar inte, vi måste plocka ut ett element och kolla klass på det
       // if (innerList instanceof List<Integer>)
       // if (innerList<T> instanceof List<Integer>)

        try {

            if (innerList.get(0).getClass() == Integer.class) {
                Integer sum = 0;
                for (T t : innerList) {
                    sum += t.intValue();
                }
                return (T) sum;
            }
            if (innerList.get(0).getClass() == Long.class) {
                Long sum = 0L;
                for (T t : innerList) {
                    sum += t.longValue();
                }
                return (T) sum;
            }
            if (innerList.get(0).getClass() == Double.class) {
                Double sum = 0.0;
                for (T t : innerList) {
                    sum += t.doubleValue();
                }
                return (T) sum;
            }
        }
        catch (IndexOutOfBoundsException e){
            //Listan var tom
            Integer ajdå = 0;
            return (T) ajdå;
        }

        //Hit kommer vi om våra tal var av annan typ, t.ex float, BigInteger
        Integer ajdå = 0;
        return (T) ajdå;

    }



}
