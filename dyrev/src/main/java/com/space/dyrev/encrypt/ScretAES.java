package com.space.dyrev.encrypt;


import com.space.dyrev.testpackage.OutPutUtil;
import com.space.dyrev.util.formatutil.GzipGetteer;
import com.space.dyrev.util.formatutil.ScaleTrans;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.zip.CRC32;

/**
 * @program: protool
 * @description: 对aes加密算法进行操作的工具类
 * @author: Mr.gao
 * @create: 2018-09-08 11:48
 **/
public class ScretAES  {

    public static String scretKey = "eagleye_9fd&fwfl";
    public static String ariougms = "AES";
    public static byte[] bytes = {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte)15};
    public static byte[] byteTwo = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] encryptCodeWithCFB(String plainText){
        Key secretKey = new SecretKeySpec(scretKey.getBytes(),ariougms);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(byteTwo);
        try {
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey,ivParameterSpec);
            byte[] p = plainText.getBytes("UTF-8");
            byte[] result = cipher.doFinal(p);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
            //return null;
        }

    }

    public static byte[] decryptCodeWithCFB(byte[] plainText){
        Key secretKeySpec = new SecretKeySpec(scretKey.getBytes(), ariougms);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(byteTwo);
        Cipher instance = null;
        try {
            instance = Cipher.getInstance("AES/CFB/NoPadding");
            instance.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] result = instance.doFinal(plainText);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

//    public static String bytesToHexFun(byte[] bytes) {
//        // 一个byte为8位，可用两个十六进制位标识
//        char[] buf = new char[bytes.length * 2];
//        int a = 0;
//        int index = 0;
//        for(byte b : bytes) { // 使用除与取余进行转换
//            if(b < 0) {
//                a = 256 + b;
//            } else {
//                a = b;
//            }
//
//            buf[index++] = HEX_CHAR[a / 16];
//            buf[index++] = HEX_CHAR[a % 16];
//        }
//
//        return new String(buf);
//    }

    /**
     * 加密部分
     * @param encode 加密的字段，Json
     * @return 返回加密后的，直接发送请求
     */
    public static String encode(String encode) {
        byte[] bytes = encryptCodeWithCFB(encode);
        String sixCFB = ScaleTrans.bytesToHexFun(bytes);
        CRC32 crc32 = new CRC32();
        crc32.update(bytes);
        long value = crc32.getValue();
        String result = sixCFB + "," + value;
        byte[] compress = GzipGetteer.compress(result);
        result = ScaleTrans.bytesToHexFun(compress);

        return result;
    }

    /**
     * 解密数据
     * @param password
     * @return
     */
    public static String decode(String password) {
        String youCanBelieve = password;
        String line = GzipGetteer.uncompressToString (hexStringToBytes(youCanBelieve));

        String line1 = line.split(",")[0];
        String line2 = line.split(",")[1];

//        System.out.println(line1);
        byte[] result = hexStringToBytes(line1);
        result = decryptCodeWithCFB(result);
        return new String(result);
    }

    public static void main(String[]args){
//
        String abc = encode("abc");
        System.out.println(abc);


        System.out.println(decode(abc));


    }
}
