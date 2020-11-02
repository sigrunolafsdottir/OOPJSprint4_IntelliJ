package ÖvnUppg2c_TrådadeCitat;

public class ThreadedMain {

    public static void main(String[] args ){
        Thread t1 = new Thread(new QuoteSenderThreaded());
        Thread t2 = new Thread(new QuoteSenderThreaded());
        Thread t3 = new Thread(new QuoteSenderThreaded());
        Thread t4 = new Thread(new QuoteReceiverThreaded());

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }


}
