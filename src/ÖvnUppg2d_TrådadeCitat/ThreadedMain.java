package ÖvnUppg2d_TrådadeCitat;

public class ThreadedMain {

    public static void main(String[] args ) throws InterruptedException {
        //Demonstrerar hur alla trådar har tillgång till samma objekt
        String s = "hej";

        Thread t1 = new Thread(new QuoteSenderThreaded(s));
        Thread t2 = new Thread(new QuoteSenderThreaded(s));
        Thread t3 = new Thread(new QuoteSenderThreaded(s));
        Thread t4 = new Thread(new QuoteReceiverThreaded(s));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        Thread.sleep(5000);

        t1.interrupt();


    }


}
