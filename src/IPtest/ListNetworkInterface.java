package IPtest;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

//listar nätverksinterface

public class ListNetworkInterface {
    
    public ListNetworkInterface(){
    try {
        String ip;
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            // filters out 127.0.0.1 and inactive interfaces
            if (iface.isLoopback() || !iface.isUp())
                continue;

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while(addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();
                ip = addr.getHostAddress();
                System.out.println(iface.getDisplayName() + " " + ip);
                System.out.println(iface.getName());
            }
        }
    } catch (SocketException e) {
        e.printStackTrace();
    }
    }
    
    public static void main(String[] args){
        ListNetworkInterface i = new ListNetworkInterface();
    }
}
