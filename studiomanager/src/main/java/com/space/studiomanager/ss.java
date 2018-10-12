package com.space.studiomanager;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Base64;

/**
 *           .]]]]]]`.            .]]]]`           .]]]]].            .,]]]]]`        .]]]]`
 *         ,@@@@@@@@@@^    @@@@./@@@@@@@@@^    =@@@@@@@@@@@@.      ]@@@@@@@@@@@^   ,@@@@@@@@@@`
 *        .@@@@`    .[`    @@@@@@@[`..[@@@@@   =@/`    .\@@@@    ,@@@@@[.    ,[.  /@@@/.  .\@@@\
 *        =@@@\            @@@@/.       @@@@^            @@@@   ,@@@@/           /@@@^      =@@@^
 *         \@@@@@]`        @@@@.        =@@@@        ...]@@@@   =@@@@           .@@@@]]]]]]]]@@@@
 *          ,\@@@@@@@]     @@@@.        .@@@@   ,@@@@@@@@@@@@   @@@@^           =@@@@@@@@@@@@@@@@
 *              ,\@@@@@`   @@@@.        =@@@@ ,@@@@/.    @@@@   =@@@@           .@@@@
 *                 =@@@@   @@@@.        /@@@^ @@@@.      @@@@   ,@@@@^           \@@@\
 *        =].      =@@@/   @@@@@]     ./@@@/  @@@@\    ,/@@@@`   ,@@@@@`      ,`  \@@@@`       .`
 *        =@@@@@@@@@@@/    @@@@@@@@@@@@@@@`   .@@@@@@@@@@/@@@@@^  .\@@@@@@@@@@@^   ,@@@@@@@@@@@@@
 *         ,[\@@@@@[`      @@@@..[\@@@@[.       .[@@@@[.  ,\@@@[     ,[@@@@@/[`.      ,[@@@@@/[`.
 *                         @@@@.
 *                         @@@@.
 *                         @@@@.
 *                                                                                             @Author: space
 *                                                                                             @Date: 2018/10/11 23:29
 **/
public class ss {
    public static void main(String[] args) throws IOException {
        String str = "{\"p1\":\"\",\"p2\":\"\",\"pkg\":\"com.ss.android.ugc.aweme\",\"fp\":\"Xiaomi\\/bxr0q\\/bxr0q:7.1\\/XWJZXZ\\/:user\\/release-keys;\",\"hw\":{\"brand\":\"Xiaomi\",\"model\":\"HM Note3\",\"board\":\"bxr0q\",\"device\":\"bxr0q\",\"product\":\"bxr0q\",\"bt\":\"XWJZXZ\",\"display\":\"1080*1920\",\"dpi\":400,\"bat\":3600,\"cpu\":{\"hw\":\"\",\"max\":\"1586000\",\"min\":\"442000\",\"ft\":\"half thumb fastmult vfp edsp neon vfpv3 tls vfpv4 idiva idivt lpae evtstrm aes pmull sha1 sha2 crc32\"},\"mem\":{\"ram\":\"3.47 GB\",\"rom\":\"24.76 GB\",\"sd\":\"24.74 GB\"}},\"id\":{\"imei\":\"\",\"imsi\":\"\",\"adid\":\"356a706243746b75\",\"mac\":\"6b:d9:27:43:d5:a3\",\"serial\":\"796f77704e764a70\"},\"emulator\":{\"sig\":0,\"cb\":10,\"file\":[],\"prop\":[]},\"env\":{\"ver\":\"0.5.5.29\",\"pkg\":\"com.ss.android.ugc.aweme\",\"uid\":10243,\"rebuild\":1,\"jd\":0,\"dbg\":0,\"tid\":0,\"xpd\":0,\"hk\":[],\"su\":0,\"sp\":\"\",\"ro.secure_s\":\"\",\"ro.debuggable_s\":\"\",\"click\":\"\",\"hph\":\"\",\"hpp\":\"\",\"mc\":0,\"fc\":19067,\"jexp\":0,\"xposed\":0,\"cydia\":0,\"frida\":0,\"vapp\":\"\",\"api\":[]},\"extra\":\"cold_start\"}";
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] bytes = str.getBytes();
        String base64 = encoder.encode(bytes);
        System.out.println(base64);

        String data = "AJl9KTK4XcbQFVCOAbMBJ35S2h5XP9yT1awx/XwgXoIu3GMXA43gSPXlk+bYLEz46S9PhjhYlE38JVdMDt9L+iWZ01KCq6CSl9ScJ1jrDldlWOQWWJu5DtQVYukOD+BhwkeHM6UYa8LtA18Dm/wOZamCHV5AbrN0MAA45v5VLDETed+OgTlcZDH/TE/vcaBlrEXFjSLIFZ/dciDx/vigV7pGIQ9SFjqeEBh6PxIG4oz38UAug+oIvXbzGe+Hxk/zYa+nB4Bn90BSJVhGvT8W0NQ0Dm6CZnJzvwxumugYebrMONzsinzfIMLaexjm/EyqJgcwGwgl+EybbKUQkHn/ShfWPy/KtSe3Ql79zE4Day6Iucuwir23magKmJ2ILMJaUbC+M1xb4z6OxvRfHs4IqmV5psmwjdOm2J3L3HYtRb6uHARFxMoAl0j77Bwyayy1uIVDws0hW6oT2oLIauHxztqhDe64Fyt50x7TKLmsWEvqK0LuxGQeTQkFN9jNn4QHy3oCnoxo6NdIF18TM8mRtEJz2g1aPIIyJ5CvoKwSOxHmb5pm7icz6UH31NdIv4im3xj2udjd7TJRformNEr/9tDhaOgO5j8nkiV9g/ahRYZmpXO3F51zNZI4XNc+T3ZxmJznpY45rPL3zV+BhenMDmdPjrKb+qenkGzILBYsMZdNMRgF1gIneVC6RnTvqQpxmhoRWcgtfxvIdKynNRkUVBuDgJNiUIsbUDoE7KtJTg/gNK+vsXbhC7GSCWOoE4CAclfZg5/T4q5X+U759eYghBWwTBrk7tT7mxOHvJRl4atwEpVaWs3q1ffdNpzorWtZsH1rIFFwhIk5ts3vNlJq/SB0FWP8j/deQjIkR6fVNgTirwn6n9cuGUjkuCi4eE3/SUmF8EZUrOJGYGMCxBGn4Hw3e8VaJHAqaA6XAq6JWCTHAM/tytWRKJJLNfyPHatmUL3ltYYMsC1IFw5Yzrp1E4CQZJdaL1vlSEUY4QYtTxYW5Gj8otgoLUKQK441Tj2x7ZkU01MIfN7Tfwnc1RCFU1gU2PYPHwK8V33jAii9Hfp+nCI3KU/Yp5CcQ8EEg96IkUwc9xDcJIwI+tiiWjDnVFQDTxF20PDwUJOl0xDCgSIU3pBz0B6b91Djac3CyKO3Ij3eThEuZ0GeMcuw/QaYtwrvuEAKOqe+k9gyZazGh9tp1lEoGy05E1R7m5pWFugWmM+cHBPlYhsTd9KpNVrHSB++RC5Ohi7O+g==";
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(data);
        String s = new String(bytes1);

        System.out.println(s);

    }
}
