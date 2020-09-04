package DatagramCryptoDemo;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DatagramCryptoReceiver  {
    
    public static void main(String[] args) throws SocketException,
            IOException,
            NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException{
        int minPort = 55555;
        DatagramSocket socket = new DatagramSocket(minPort);
        byte[] data = new byte[256];
        
        //String message;
        String encryptionKeyString =  "thisisa128bitkey";
        byte[] encryptionKeyBytes = encryptionKeyString.getBytes();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        
        while(true){
            DatagramPacket packet = new DatagramPacket(data, 
                    data.length);
            socket.receive(packet);
            System.out.println("Meddelande från "+
                    packet.getAddress().getHostAddress() 
                    + " " +LocalDateTime.now());
            
            byte[] origArray = new  byte[packet.getLength()];
            System.out.println("längd på paket: "+packet.getLength());
            
            for (int i = 0; i < packet.getLength(); i++){
                origArray[i]=packet.getData()[i];
            }
            
//            for ( byte b : origArray){
//                    System.out.println(b);
//                }
            
            
            byte[] decryptedMessageBytes = cipher.doFinal(origArray);
            
            String message = new String(decryptedMessageBytes, 0, 
                    decryptedMessageBytes.length);
            System.out.println(message);
        }
    }
}
