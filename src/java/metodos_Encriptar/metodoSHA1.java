
package metodos_Encriptar;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class metodoSHA1 {
    
    private static final char[] CONSTS_HEX = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};    
    public static String encriptaEnSHA1(String stringAEncriptar){
        try{
            MessageDigest msgd = MessageDigest.getInstance("SHA1");
            byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for(int i=0;i<bytes.length;i++){
                int bajo = (int)(bytes[i] & 0x0f);
                int alto = (int)((bytes[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString();
        }catch(NoSuchAlgorithmException e){
            return null;
        }
    }
 
    public static void main(String args[])
    {
        System.out.println("Encriptacion en SHA1 de admin: '"+encriptaEnSHA1("admin")+"'");
        System.out.println("Encriptacion en SHA1 de usuario: '"+encriptaEnSHA1("usuario")+"'");
    }
    
}