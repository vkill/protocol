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
        String abc = decode("1f8b080000000000000025994996233b0e042f540bce008e4382c1fb1fa1cc95bde8f72b33253130b89b53b5e75eaddcec3ea3b5794a96d3ee5aabbc9ea7f8fcbe2fbdee9bb1ec8eeac7f284bdd7724c8bdade5e25be364bd4396e5bbb8f32cef45beeddf956f765ab7cad7c73bcf7796dadcfcfde2af39d596cee1e6bf1663edb38fbfae02dcbb8ebd4af17bbbeaa67492f63dc5df2ae66b146d83af3cedacb9eabd6b157ef75d6bd3c661fc689bfe29d774a9bbc7df17a2d47be8c8876cef8e2d6088e575efbdaf9e2ab2fdb8831d35a5ae767f9b566f5f317bd65e50d977dbb0dffacd7eb73ddddcee47fdf2aebf3afa83eefbd5a39aa59cf1b7b845b36ce35ec72d2d69bbd7bcfbd37dec8c107dc1667ed3bd73a5edf38b973f463f7568f2ff37d71e25bd7ceebe5dccf0625a96df3af1eb7f9e1914fdf3422a61d3fbbb475e7376dc71cc75698d579b6aff76ef1560bc51fbbf6d3bfc7c7ef97a7ad76dce84b6ddf37fbf3bd68f1884bad6bf77df63ce378b976a994ed3e639e284c84febfbf7e695169cc4d098bed356af2e7e9d1687b7fdf1e2f284b1e062066a18a6fde56dedecffb6cfebe34fbfaf1b55bb471eb9acf198baff3d391df695e662b858fcc9eeb7c9b83946fdc1cfea8ddbb8b07a744c6d1973fdb19f77ce7be6a943dc7eb8d2159fdb4e2a37f51cf3ec97b9b59f8b4f6f5cfdb5b6fb65699ca4dcd3855ee366fbf1ee718c3432596b5761889e7a5be576a9977c57aad57e7158b82515a7e7e6c8f4e9d99ff98ebdbccd230bf6f46efcbc6ec4b051b8f82f67607edcdb38242fb9c2dbc2e8ac119472b54f3b05f7cdacc379e2713db290353623ddee9a3f9d81f3f2bdfbbbeb37d677dac60e143bf7a1765bf9c6d4489f01bd347346bfce9a8d9f762fa3875fd4696c1fcf6a4d4dfd935bcefcfa974b9cc52a15651eb4c16c44fe99da11b9492c1d45c4c867fcd7d98967a32be7bf666b2de46021e1a31b77b79d5ef773509a3ccecdfa22b2572dcd1c7b7ed56631c4fe838b96c338a3cf9e2c396798f1d55a330cfcb55077bc503aedabf3cac382bb8ee3b312f12438590af9b7477efb0b6bfd2f7a9f9e66bde108d4eafc65a77500384abae3c939e16a6ae5654e85b7df0a8cc1562780a1bd4ceea7b0f5e6defbc566a7eee8fed0fca8f966cbd7205effa5eace4b10a433a5981c12a17f7fe78e0d6bcd38ff9de4159e9406de5043b60d4c0076bb8d643f0902714a26bd167c46ffbf65769f1ec76ee62399dedff6caf91128ab9123166e591b43a99acbaa9bad32a147a8dd22ea72cf425574ee6b17ba0b081146b3136ea7737b2b02fbde318d1d8c88b105932e6e7b11d0c795b3350a056639b56b821ce815c49b95e5a5d7b7caf6ed33b735cce8462201f484b6d39a9aba304f550e4d91e4a8e252036a7f15f3c6ceb116867b63b719a8da77434727969d699e54a2d6f9afe157c4094391f15a9bc5530fcccc6a775f539b2eda527f1857276348e098cc522b4d7244b219571149c3d5de7e4b3de66ef781a3e33e2746a76d07b7c8551e22d3e9e2b5ea00577ff5ac86eaeb7cc4abfdd5e679bde6f37540ba6a3a3be1ac4d5991c4fcb3e225e7d13b5d8af22000834ef7966a00058cb7564948f1c1dc7d05cf11015e7e2710e4f3b9e544086d36bd59eb28ec8117bf4a1295336c2999fe36c37dd6ea13dd8066ede1f06968f27db43663207f3f71d043bce3d0789657558325cf7620d45738b4fb1523a1b47b3ca6b366b39edf919b704059a8e21f9c211e7acfe249c169273f9fe570a75a419dd2e4ffac21bbe87455f47bc721620813d0d1e0287eea8e3607bbaac91c6d4664cb4b978026fc0f32fbfc8fe1600c04b7362c20545de8f911dab078bcddfeff8c6e96751a24e7b25758e588eda22dde9790df66b883d8e86e7b3e02d56b6d5b52754e4e69d7ad7ce72f68d3c068765c45d16519eb3fe73b47c25190750016ec94dfdf1b8cbd37fd870c7e5faca8fae8dbd2ff6249fb34064e9121830df34cecce30226830ebd5266bfa2a8577b0693343465e2944bcf81a88e793072acfeb2d9d2e985cf64ce4ce4825cb000404e968d63d917b0126a31dbe4971cbbb3b31d1fecad31ed97f7cb789c0addc8ca6c7d83bf6b68a9a8472e19ec08bd8c8ff79bfc3992c79cee770ff35d037c41e9d9f5b19aa6132d7239128c35ba53096cbca39d38634eb8ae0cc674399bb40bca367d0fc3f5cbd612566afe1af6e410d0f88c279dd4e33526955564976b09e1e3bc148a199504bf86c933f8742a721a724a17eaaae75b0b2d8994462dbf039d06980ce96fe7e2d0950e9c8fbe2e9889568231f995441405b8ac3735a1c5b1c1c53545c4067d311bb243a886c652dc8e0cc5c71251309cbfef4653103f0c1bc7ccf220a03484514df7df76f208a769c2f86f5a34067539bd0627fbea8e8b3db4e9688530eadc6455e145de04d7e1b391161b673456a37d8c21c6b0ce2d089733415bbedad489c35361d47b2f4666347911130ac454b4aeec5aed6e5ae4be80e70faba5e64c3c8a814fc2fd1d310a1005ef63416b1a8540d0d2278431e1e980b4d1a98787c3dae5e1deef01d65237ba0af1b89d221767e7373d984021c27d7112dc99717bcc8ffe4a288d1bd116af240d14450feb03819fc80dd2e8e3ca22796cdc1842b93c916a034721ae96770c43832b6a4d3081202a385b031f615258e80fb6f97053187826ceb29c4fc47c688fb19deccb878df17a5a8e30c26ec8fcf93e04a084c4f93e3408722598240f0b36cc7239b41e04061bac4bf29f382786493331cd0c00734275a86335248437eec80239604f01dd984058a3721b57aa48016c0af876493422aa53b2cae0acafc3542dc815b3ebd2fd4232c282274fe39822a73ae2a0c007d69e1bf303fb44dfd862bd0f3a3b9dba55380eb45af83f9e76ab4c69c2e4f74b18f3cf3b183d200dd6321b84135a017ca1a4cd45d09bb805dcd48fc7075ab7bdca4e57ca451668c41c4290b6cf4f36f416326c12b832c7d47ea1ce4cf0479ac4c110150cfb11344a3f388184a6019ac0e0198cf8e878c8e6dd9fba17625c96e7f141fb137da476ebb72cc1ea84e699a73f5439aa463ce16b94915623683e2595a03500ca4a107398cd6f346009e2c21675a68548c08e91772fbc13aa33b02d291dc3c2a89c1f1f26c52b44807109a78ff484751bf48802da1b21513f0b926ab031a08af60bfb68d08663e640d96bdf201df65f2938396835289b59ad6805bb4e124303714ca691a900fb807442538225790113588b3c8263e236ce673e15825601ff39113146efe3f421d260c14f2c26b17c448b43dc83f8325cb409b17f24519d127547400f0a8c675b6291825b46f17c2cb53877d169f400f0241c8181b007c382d036ed17a90433b8823c2000b1640c89665030f906225002e1476c7741764874a9185c03a95480d96fca41c05d449ebd39a7729e8b25e352866bc41f7890b8e6af4afe0af3c63cc74c946039d576251b7ca4e22c106a2693c42ef1d316b0b1fe6c28a83216d04ea9b483de0f4417fe457017a1959707a9ba26519de02f0f3040023264238443ecc5e32dc6a4e28354b383bc047e07c8598455a272e2fba7ccc402062e8674345acd520cc1207cff353a4b4708541aede0b4d02b9fa446692a486a7ce224ba25074785488358d686ea3633c383423632a48f04b5a9246f8093a3c25005d968540d2ec20b49f43434b2d4b18b44b5a2a650a1ec5f9729a419d6aafea1159874393e8046ba9dc419f3ec1020791ae9ddcd88763803b4c54c75b809705798a2a1441d40f62c765868c0a0e12c9715b84d70e968493622219f074a2dc3dec47485f8732e2b83f0aa7b1b9487a05912160e9fe198414ae4b0c8d86204879eb8622fd0bb6e9a3629b02aabb30c541cb8413e0477cc011a8ad7e24158ca23bcf4aae036d0c9041c60afad18f029608930f0130331b4a08fbf213a55a3d708191f6ebcffd3b6b16bd076d36032c4336511f151a4c9fc24c09c201745072c0094ef2ab9424fd8711ee7400a23181c6b67672d453a2fd819a91704fa960de5f0814f9e8aab2ecad815728227be5826b17e121637704033598dad5e1c1a7da056343881d9259b375e57613776d9884c4b5e914faba90fa36f44bd40e1000339e11408b46e8118e3f26795ad9262591700237f75180b4874748f0c78b4350d3cf0209c2080ec37910223bc2c81063ac9cc8d41c58b183f24999ff0aa56875b236e394cc1dea3d0b1d60013d6c7fc9202090e00eca841d52b8215b73077acadcbb391115e14dfcfb2d959e0ed698396da08bfd3b0cf00247791de2eba27bb51309c51a4db593a1a7c01bc0131bfdf687e602f6cc7f3a0bbfcced92e222d7f4a4ede514d976efe9df39c4a66e045972c04d00eb6d9c51424560005e4225bd7f3e61e8a8db561c0aefb0ff0e87efb9aae4dfb3917615f176f257ad1a6c0f61bf089aacb4888221d7c1f87308093d0dd2da56672dee770a3e88c85c5fa15ef2714d9d940c4e2d10d5768d0ad0b6c79262403c86bb3503c0716f939b151728b6f970f6a215d1d0c15d9be26c9f18a7d60d5ed51a42aa0a63c4453e4633b6ec0a93925907015ee20238574fa87d2534e510f078774400026f9129719773ec4aea91978fed1350a58c1a09296787c838589c9284ab3a33b3b5d6ec10fccfd25b329c2031913f3c5fc994b6dd23e04d80730a35dc48c63a40abc0f19e683ad42ff1b016e492222323584e5a14554c81005d65ff79060c262452fe088863d5a8cc6ec83937fba76c2bc6bea1288182491647bb3fd922f1fcd9061623ea7aea84842d3f835710f45d36f746dbda45b181670bde15ef4f671548589ef7bf40361d8bf750539c23fdd2e75669b79fe987c0f5eb1b0dc2119e02800e0a7ea94c3da32551d7240fc3193373ba95b37611bfd6bf600cb8dec2df87c16dd572b118275a448ac1bcdc43e397882b0ec1d2ef51cd3dc2e70a8e897c1714c0816ac584b7bb34395a0e1c6e21fccb82c3680f8b17fe457b6a1ea633ce7cfaf690accf8e1450a391407a153c610a7ea928c6c4c44c4658570cca0d0091a44fc1e963ea1bc8b09e89f1c19f7340bfa82f6b0425019674b04bd3f10e58a689842655c5dd6f32ae9d25c5835643774dd3a7585f8a17ec9c867db2c1d5c40d77ae2a05d31168f412a1a9b24a2b2d02971295da9f5866a344059154c80919f031fbadd00d5900ceb7e0943f090a09f859052290ef6f9096d16c7753e89a80cee8269642b5883521d289b9a1b06c2241638738c062c305c8804fb83f0b1c2d71ebe8526b1631809b92b26d0077b53411846e734e247d174f019e4bb3a0f08315e7721048044e2d7ad0d4247fb691e62fe24278606026a87b92cb7542bb4ab7e43fac7e6b5e340c3b7ff222a226a10cffd5d2892494a85a8756f543910e50d269ec1ad3cc1e0d319d17603fd21e93015113823c9891d2b3f8476b407626f3424956c890a1405571b085b15c042b26afb67810b66c24e1fe331607b49e1c7393f0c9937cf57e72cccd64add85112978c4f3290a28b51cdd481722136d43d606598238052833ea1c9559c57e17a436889602721213fac4e8b352969341a7352231bc8447a6e489dc6358881ffe4b2bee218aa08f1a56ccf2765d16c047fc2dc38cd8f465c96202f07063117db1994bf7fcbfe75e8717c162fa8e4cf742f3c321b500c0ce51d6802388cef93b4643869733c4413e585a32673e876ea2966e0ac9214813dd241e374c8819288b347f1a4c81ce30b91c905f3294d052c83bda72aa3f7fb1e463958034460eead57dbc51e320149152416e28154ce199e7f6e04fe812f595ae4e031537424aa6e0e3e000988244730941ccda783000d910bd4a1322b13e3d155b341e0cca24ef2607b9c6aceb72f3f59fea62b8835aa5c2264a0005dc474c0148519eab80e1f43c7e619acae3817226a08911aa50938a39ddf958106b3d793846882b53b7228dedfa360814e23959e70efdfb65861058d2f225aa285af177c4a61797d4b775fd660bbdfe806db604c0a090ac2f266a2c666e4003cc87077a13160a3711dd3b2f664efce46d6c7c386111b1db2396eabb45f23c7c895d421f5e07250c5d29b13a8de48e1091a718cea3605960f603d813d4e090c54ed18776f73d741b06e84c085182e52286b096a81bb6ad3b0444ff77ef06a5eae2f5ea4b41faaeaf03de7b84b0d2c8aafb36a09ec610ad25681b6c98daba472b765f00d9655271351a8ba2c112d10ea371f51507cc56d92fdd522fa6de1867e6129956f4be0fb342be9484024648467230e2898820beb728ed2015044284a7f2ba0907b2fbcc51539a163be0b24776c460c1d5fa0a806c0ca9147a02788a890911d389666c971eb991f360c32d75a4828fcd66e74d8610bad6d6854eaf1008c38dd8e89e8fd1bd0c082e0ed35c26e5ea96c1684e004ed8b146253e241d0b2c0de25847a902c36309dca40701f54deaaa6f7608f6c49d322bd95242b5212ee88605de4fec1c9245f412eed217e8bbe06c6c30fecc184dd656f8cb9f16252259cfc41606d99e86dca2c0c3d6f023320e28b80fd02da7ea925f92d6d39520d9e6d3d7296f1f59e22fb241a4c9fee1d92c8e27896661262378656308c4515b51475fb5feae60d760a1745f4229f5752127ad0cac2e7afde99b07ce7ef87b284e633e7523130037808063f152948ecad0e70a00b6c16cebe2f36206b755dc844cabc0d0f8988d8aa1686463040600a2edf8219ba2643c115d220f9018fa3e59a9b42444c0d3d5093a41cefc6220a58b64b64dfb591973e2ead2f740d3a017ddb61ffc7653f3e78371a82805b3209834f92b6ce4acfeb3fd6f60e18b9051e23fdfded1b02f210000");
        System.out.println(abc);


//        System.out.println(decode(abc));


    }
}
