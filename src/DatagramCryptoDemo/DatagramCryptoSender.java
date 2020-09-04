package DatagramCryptoDemo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DatagramCryptoSender {
    public static void main(String[] args) throws 
            UnknownHostException, SocketException, IOException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        
        InetAddress toAdr = InetAddress.getLocalHost();
        int toPort = 55555;
        DatagramSocket socket = new DatagramSocket();
        
        String message;String encryptionKeyString =  "thisisa128bitkey";
        byte[] encryptionKeyBytes = encryptionKeyString.getBytes();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        
        System.out.println("Vad har du på hjärtat? ");
        while((message = in.readLine()) != null){
            if (message.equals("bye"))  System.exit(0);
            byte[] data = message.getBytes(Charset.forName("UTF-8"));
            
//            for ( byte b : data){
//                System.out.println(b);
//            }
            
            byte[] cipherText =  cipher.doFinal(data);
//            System.out.println("krypterat");
//            for ( byte b : cipherText){
//                    System.out.println(b);
//            }
            
            DatagramPacket packet = new DatagramPacket(cipherText, 
                    cipherText.length, toAdr, toPort);
            socket.send(packet);
            System.out.println("Vad har du på hjärtat?  ");
        } System.exit(0);
    }
}
