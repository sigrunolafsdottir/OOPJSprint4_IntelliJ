package InetAddressDemo;

import java.net.*; 
class InetAddressDemo 
{ 
    public static void main(String args[]) throws UnknownHostException { 
        //Mitt eget IP 
        InetAddress Address = InetAddress.getLocalHost(); 
//        System.out.println(Address); 
//        System.out.println(Address.getHostAddress()); 
//        System.out.println(Address.getHostName()); 
//        System.out.println(Address.isMulticastAddress());
////
////        //Loopback, pekar alltid på den egna datorn
//        InetAddress loopback = InetAddress.getLoopbackAddress(); 
//        System.out.println(loopback); 
//
//        //IP till dn.se
//        Address = InetAddress.getByName("dn.se"); 
//        System.out.println(Address); 
//
//        //Ett datornamn kan ha flera IP
//        InetAddress SW[] = InetAddress.getAllByName("www.hm.se"); 
//
//        for (int i=0; i<SW.length; i++) {
//            System.out.println(SW[i]); 
//        } 
    }
} 

