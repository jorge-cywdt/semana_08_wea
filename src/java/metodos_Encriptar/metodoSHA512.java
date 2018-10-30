
package metodos_Encriptar;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class metodoSHA512{
 
    public static String[] algoritmos = {"MD2","MD5","SHA-1","SHA-256","SHA-384","SHA-512"};    
    private static String toHexadecimal(byte[] digest){
        String hash = "";
        for(byte aux:digest){
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    
    public static String getHash(String cadena, int tipoAlgoritmo){
        byte[] digest = null;
        byte[] buffer = cadena.getBytes();
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algoritmos[tipoAlgoritmo]);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        }catch (NoSuchAlgorithmException ex){
            System.out.println("Error creando Hash");
        }
        return toHexadecimal(digest);
    }
    
    public static String getMetodoSHA512(String cadena){
        byte[] digest = null;
        byte[] buffer = cadena.getBytes();
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algoritmos[5]);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        }catch (NoSuchAlgorithmException ex){
            System.out.println("Error creando Hash");
        }
        return toHexadecimal(digest);
    }
    
    public static void main(String[] args){
        System.out.println("MD2: "+getHash("admin",0));
        System.out.println("MD5: "+getHash("admin",1));
        System.out.println("SHA-1: "+getHash("admin",2));
        System.out.println("SHA-256: "+getHash("admin",3));
        System.out.println("SHA-384: "+getHash("admin",4));
        System.out.println("SHA-512: "+getHash("admin",5));
        System.out.println("SHA-512: "+getMetodoSHA512("123jorge"));
        System.out.println("SHA-512: "+getMetodoSHA512("123ornu"));
    }
    
}