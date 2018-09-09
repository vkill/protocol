package keytools;


import jsonreader.tools.GzipGetteer;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @program: protool
 * @description: 对aes加密算法进行操作的工具类
 * @author: Mr.gao
 * @create: 2018-09-08 11:48
 **/
public class ScretAES  {

    public static String scretKey = "eagleye_9fd&fwfl";
    public static String ariougms = "AES";
    public static byte[] bytes = {(byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, KeyParam.k, KeyParam.l, KeyParam.m};
    public static byte[] byteTwo = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
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

    /**
     *
     * @param data 需要加密的数据流
     * @return crc校验码
     */
    public static String Make_CRC(byte[] data) {
        byte[] buf = new byte[data.length];// 存储需要产生校验码的数据
        for (int i = 0; i < data.length; i++) {
            buf[i] = data[i];
        }
        int len = buf.length;
        int crc = 0xFFFF;//16位
        for (int pos = 0; pos < len; pos++) {
            if (buf[pos] < 0) {
                crc ^= (int) buf[pos] + 256; // XOR byte into least sig. byte of
                // crc
            } else {
                crc ^= (int) buf[pos]; // XOR byte into least sig. byte of crc
            }
            for (int i = 8; i != 0; i--) { // Loop over each bit
                if ((crc & 0x0001) != 0) { // If the LSB is set
                    crc >>= 1; // Shift right and XOR 0xA001
                    crc ^= 0xA001;
                } else
                    // Else LSB is not set
                    crc >>= 1; // Just shift right
            }
        }
        String c = Integer.toHexString(crc);
        if (c.length() == 4) {
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 3) {
            c = "0" + c;
            c = c.substring(2, 4) + c.substring(0, 2);
        } else if (c.length() == 2) {
            c = "0" + c.substring(1, 2) + "0" + c.substring(0, 1);
        }
        return c;
    }

    public static void main(String[]args){
        String youCanBelieve ="1f8b08000000000000001d97598e25290c4537941f1803c6cbc118ef7f0979e2b5aaa5d2ab08f070a710bd67f59657f7f4de67b4dba2e75aab95de687bbe7abee7e9d7d7ca292fdeddae35fa1bd35cf67e72dbba9d5f97f25bee5b6f190fb661b3ef774fed7eeaa8b4bedacbb69abadb2c1eb4a8e6c695b7fa8dab9a3756afd8c3255bebfd8e795f3b9c667dbed724763b96dde60a2fdd46c56bcfec633eeda2ed6db5dbf3ae6eea7d8f7bee887567c68ee6b9bab6aa8a79728e2be5f6beceac47bdb335f69508d31db6179db91eda18933ba2e5714d716fcdaf57d790ba343cce1b6f5a93de8a0af2a4e57a7dbd921c5b7b1bfd98cfbddd4ef53553f29d79d52d45dace5de935f679a22a5973c930a3203f215ac665cfb6d5ec83da99cbaed39eb6d56f33fef731cf38daab6594d1149b79faa4e265dc5fe35dc6baeb30064d1f4f24b3d5641f9631ac8fd77bdbb7371914c81bfcb04b87b62ba2a79f3656d6abed03586c1dfb46dd36de70b0c3a5ebcd1a3981ce657655fe9522a6d185b9ac7966e808f3fdeaf671ac5b6c6997c746a48bc71dfe567048f31976b2ed7142cde37801138fc72332bdda2e130b5bd21e67a7eaabb5fdb4bed7795470f44e60b84d40acae65dc46a5a2e090ee84dabb6bd70ce56c919ef976684f8f214778caa852536594c479bbdd3b6c51028d31147e043deb6677aea3da71d945636da08efbb5b22595bf73dd95ce4a5a4c265b4f237279c8d077d4b836c1554fc60cb8ce835c4dd65917fae41e165021e28d9b7ab6f42900da8a05b1bfb72b2c7d531b000643b3b566d4de6e9540dc7e5e7b95a05135eef7e48acdb57da4fbcef56dd5153a819f2d6a8d13f2dc7372c8adc6fcaf03412bf6cc91a10fb0b17e5ef09876f4e84aa6eeebf494a7802819db186a83590c799db1b08a808f20791a40d39ca068f52d56b0b01df5f5a15d8a5d06b3cf999591b10eabd3b9e0e74bddce4bf01fae6a1423ccb0fb09111c359d8712a9796f7a5410aeae23afdffd01aedac7bd073379107dd967e6d4eef28db87bcf4651d917ea318d15d46608bb968d87f830a5d00d44d664efe053654d07d5c1f875501142d74138f49940ff23827c809e01b5a14cb28715dd673f470a2d7c638b73495d5bc7e463857cd09db6a079cb66880d25df23fd4d383b7b67c2afcfbda636d9b279e499fed478b4d16c35aa40a497fb3808d5d5fe717326035ae8c58b3eecd2d568d003ccbfd0e50feab3bc642ab56d844cc00325f46b6950ccf401949dff60c3dc174df195304bcb6525484d9d8256b4021bb1e4b1c75b170400b556106f855c76c1b60e3343d54e0ac2db116b8455b8cc106f7803f456bed917720b81ed382c84b8b5c784665b867b7f6d142fe69995acdd4fc7a602005db075c67e948de889b344a869c3296d2992021b6443acf3902ab3b244bcc56d4f55a6559503a398a8f8d800b90076bdcd9ec363adb7998e25e620c6d01e0a38bf52e4eeda73f162646f73bfb61e377607bcc5eec23f1a816b53652fe7eccf18cd5135fe6d8c1aa00a9c4a2d0c091cbdf96c71307a46919bd9eecf07e787a4cf041ec3048c239e2c50d570863940d1b3388000674cec150a8c6b684a556ffc05b381bda851f87bc604f4306de196bda112e807480c530d7e485bacbe30e507e9ef82b2be9069663a8a91031094d239b847a23bdafa647bd10a012bbac6b70ce9e9d301cb92f54100b06120020b33811966ab1d3c85f4326fdc87c00cf423575d790b4d5e80fb847bb48994a2289f4221d65c8ea18d05ef83ad9322caf712db824fb145647ceb053030dcb1d6579d65a8e253090bdba7c88cb19db69822f86b0066342c3a8dae190eccced93f0783530797300c00fb34c83be01210199a803b20a6936350680e3f0291081fb33fbb5f53830b41a27dce01e68810741b7716f6fe64a3f905aaf045a8839230daeaf6f609de7a38797e92f553e7c5609c0bc4d8dadaf7e4652a3a477b8fc2b87604b1e0c14070fe21399c3f77cfed2de87d7db87cfa39c0441a05bbbf1815446a901641c08d75217a89e6226ce08518588c16750246767038072620f32d14f736c48da93d463cf1a95a83217116ba86109de54816e1a8196a54df281c8294c06c8ebf28dcb953773aaa4f0f4403c552403123032350041be50d3ca6215cee39a9e704806d449d6e744c7ac2f018725c503f0f5a571fbbc83af74b02f73019ec6cb0c5e897bc25095f13c93ef1a50e2371ddd1be4c49aa42496600152431d6ae6a448b0eb5be9848b40d6a5b1beca1f1d07ee4261f0099e01a623047e1b320a4c04610184961efca46f4567ec0fdca25878dc2521521826e452a6072c01fe138c0157b44dae76d8a284c2740129f60c225a9158816320b41ca88d9098d23499913691a2483fcb2f0175e0f5aca5988436bbac7ea9f49cbfe482d042a9cb97913d99806fe7fc5daa3d5f9590791b5069440021111e84480ee0819424752dbe8af7f9182b02e858413576b7e1bb74fdfe5be0faa6bc0075b5f9825cac4e215b4fcb1e90baab63d222ff6d189acf83774aeef39fdf43c964fd44321245f096a42d93ec85a04829b1fc4c8ec1bd031c0032380deb179089cadce7bd251abc1820c5d466188fa8bf0c26a26ebe56b8338effcda02b680deef8302900d258121d01d448d4095d9ca6568abcd47919d10c9fdb0f3ddcf32e6e74be3e21db221c327aae42934be098da4adcf6e3ef33b4504000d13f9cc9fe3809383e110f409e08729734e62054befcdfc625c01b486029072c82764587152d0176d06119ad8a01d2002cc695458482bd3433647a005e3cb267c298dcff50899a0ef76d6dc018c4507184152156c49f8961be1449e4baafc44523f1f2089e13fec957cd4de1c058b771f5f739e387f6f08035a8669e56422159f23df5e8812df8ca09b50f9517735b4ebadef33064341f7830f9e29bfe8c2d4b10bfde33b6729ce61f60fc69b78f97f0e0000";
        //byte[] ouye = Base64.decodeBase64(youCanBelieve);
        //byte[] ouye= decryptCodeWithCFB(Base64.decodeBase64(youCanBelieve));
        //System.out.println(GzipGetteer.uncompressToString(youCanBelieve.getBytes()));
        //System.out.println(GzipGetteer.uncompressToString(Base64.decodeBase64(youCanBelieve)));
        //System.out.println(Base64.encodeBase64String(ouye));
        byte[] buff = hexStringToBytes(youCanBelieve);
        buff = GzipGetteer.uncompress(buff);
        String kao = new String(buff);
        String[] hehe = kao.split(",");
        System.out.println(hehe[0]);
        byte[] ouyes = hexStringToBytes(hehe[0]);
        buff = decryptCodeWithCFB(buff);
        System.out.println(new String(buff));
        System.out.println(Base64.encodeBase64String(buff));
        System.out.println(GzipGetteer.uncompressToString(buff));
//        System.out.println(Base64.encodeBase64String(encryptCodeWithCFB("1f8b0800000000000000")));
//        System.out.println(Base64.encodeBase64String(encryptCodeWithCFB("1f8bdqwdwq0010000000")));
//        System.out.println(Base64.encodeBase64String(encryptCodeWithCFB("1f8b080000cdce000003")));
//        System.out.println(Base64.encodeBase64String(encryptCodeWithCFB("1f8b0400000000000000")));
//        System.out.println(Base64.encodeBase64String(decryptCodeWithCFB("1f8b080000000000wdqe".getBytes())));
//        System.out.println(Base64.encodeBase64String(decryptCodeWithCFB("1f8b080000000000ceqs".getBytes())));

//        System.out.println(new String(buff));
//        //byte[] result = decryptCodeWithCFB(buff);
//        //String kao = Base64.encodeBase64String(result);
//        System.out.println(new String(buff));
//        System.out.println(Base64.encodeBase64String(buff));
//        //System.out.println(Cipher.);
//        byte[] ouye = encryptCodeWithCFB("呵呵哒哒，你能成功吗");
//        String buff = Base64.encodeBase64String(ouye);
//        System.out.println(buff);
//        byte[] result = decryptCodeWithCFB(ouye);
//        String resultString = new String(result);
//        System.out.println(Base64.encodeBase64String(result));
//        System.out.println(resultString);
    }
}
