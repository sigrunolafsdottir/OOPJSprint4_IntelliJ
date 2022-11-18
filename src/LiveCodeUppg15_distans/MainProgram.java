package LiveCodeUppg15_distans;

import ÖvnUppg15_Generics.uppg15ab.Main;

public class MainProgram {

    public MainProgram(){
        Queue<String> stringQ = new Queue<>();
        Queue<Integer> intQ = new Queue<>();
        QueueInterface<Integer> interfaceIntQ= new Queue<>();

        stringQ.put("hej");
        stringQ.put("svejs");
        System.out.println(stringQ.take());

        intQ.put(1);
        intQ.put(2);
        System.out.println(intQ.size());

        QueueBounded<Integer> intQB = new QueueBounded<>();
        QueueInterface<Integer> interfaceIntQB= new QueueBounded<>();

        intQB.put(1);
        intQB.put(2);
        System.out.println(intQB.size());

        System.out.println(intQB.calcSum2());


        QueueBounded<Number> numberQB = new QueueBounded<>();
        numberQB.put(3.5);
        numberQB.put(333L);
        numberQB.put(5);

        System.out.println(numberQB.calcSum2());

    }

    public static void main(String[] args) {
        MainProgram mp = new MainProgram();
    }

}
