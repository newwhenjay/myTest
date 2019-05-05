package enc.myTest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;

/**
 * @description: //TODO
 * @author: lwj
 * @create: 2019-03-11 14:39
 **/
public class AESUtil {


    //生成AES秘钥，然后Base64编码
    public static String genKeyAES() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        //测试
        SecretKey key = keyGen.generateKey();
        String base64Str = byte2Base64(key.getEncoded());
        return base64Str;

    }

    //将Base64编码后的AES秘钥转换成SecretKey对象
    public static SecretKey loadKeyAES(String base64Key) throws Exception{
        byte[] bytes = base642Byte(base64Key);
        SecretKeySpec key = new SecretKeySpec(bytes, "AES");
        return key;
    }

    //加密
    public static byte[] encryptAES(byte[] source, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(source);
    }

    //解密
    public static byte[] decryptAES(byte[] source, SecretKey key) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(source);
    }

    //字节数组转Base64编码
    public static String byte2Base64(byte[] bytes){
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    //Base64编码转字节数组
    public static byte[] base642Byte(String base64Key) throws IOException{
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(base64Key);
    }

    public static void main(String[] args) throws Exception {
        String s = "hello,您好";

        System.out.println("s:" + s);

        byte[] s1 = AESUtil.encryptAES(s.getBytes(), loadKeyAES("1234"));
        System.out.println("s1:" + s1);

        System.out.println("s2:"+ AESUtil.decryptAES(s1, loadKeyAES("1234")));


    }
}