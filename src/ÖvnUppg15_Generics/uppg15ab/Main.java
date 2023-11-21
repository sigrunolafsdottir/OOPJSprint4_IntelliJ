package ÖvnUppg15_Generics.uppg15ab;


public class Main {
    
    public static void main (String args[]){
        
        GenericQueue<Integer> genQ = new GenericQueue<>();
        
        genQ.put(5);
        genQ.put(7);
        Integer i = genQ.take();
        System.out.println("size: "+ genQ.size());

        GenericQueue <String> genQ2 = new GenericQueue<>();
//        
        genQ2.put("hej");
        genQ2.put("hopp");
        String s = genQ2.take();
        System.out.println("size: "+ genQ2.size()+" "+s);
//
/*       GenericNumberQueue<Integer> genQ3 =
                new GenericNumberQueue<>();
//        
        genQ3.put(5);
        genQ3.put(7);
        genQ3.put(3);
        Integer i2 = genQ3.take();
        System.out.println("size: "+ genQ3.size());
        System.out.println("sum: "+ genQ3.getValue2());

        if (genQ3.getValue2().getClass() == Integer.class) {
            System.out.println("yes");
        };


        GenericNumberQueue <Long> genQ4 = new GenericNumberQueue<>();

        genQ4.put(432543589765473343L);
        genQ4.put(234L);
        genQ4.put(12L);
        System.out.println("genQ4 size: "+ genQ4.size());
        System.out.println("genQ4 sum: "+ genQ4.getValue2());

        System.out.println(genQ4.getValueInDoubleFormat());

        GenericNumberQueue <Number> genQ5 = new GenericNumberQueue<>();
        genQ5.put(43273343L);
        genQ5.put(0.5);
        genQ5.put(12);

        System.out.println("genQ5 sum: "+ genQ5.getValue2());
        System.out.println(genQ5.getValueInDoubleFormat());
        
        //Funkar inte
       // GenericNumberQueue <String> genQ4 = new GenericNumberQueue<>();
        
*/

    }
    
    

}
