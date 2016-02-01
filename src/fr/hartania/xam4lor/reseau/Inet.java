package fr.hartania.xam4lor.reseau;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet {

   public Inet() {
      try {
         InetAddress address = InetAddress.getLocalHost();
         showInformations(address, "H�te local");
         
         address = InetAddress.getByAddress(
               new byte[]{(byte) 192, (byte) 168, 2, 44}
         );
         showInformations(address, "192.168.2.44");
         
         address = InetAddress.getByName("localhost");
         showInformations(address, "locahost");
         
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
   }
   
   public static void showInformations(InetAddress address, String name){
      System.out.println("-----------------------------------------------");
      System.out.println("INFORMATIONS DE " + name);
      System.out.println("-----------------------------------------------");
      System.out.println("Nom  : " + address.getHostName());
      System.out.println("Adresse : " + address.getHostAddress());
      System.out.println("Nom canonique : " + address.getCanonicalHostName());
      //Cette m�thode nous retourne un tableau de byte
      byte[] bAddress = address.getAddress();
      String ip = "";
      for(byte b : bAddress)
           ip +=(b & 0xFF) + ".";//L'instruction & 0xFF permet d'avoir la valeur non sign�e

      System.out.println("Adresse IP depuis tableau de byte : " + ip);
      System.out.println("");
   }
}
