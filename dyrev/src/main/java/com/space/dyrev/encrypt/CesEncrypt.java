package com.space.dyrev.encrypt;

import com.space.dyrev.commonentity.RequestEntity;
import com.space.dyrev.enumeration.RequestEnum;
import com.space.dyrev.util.formatutil.ScaleTrans;
import com.space.dyrev.util.httputil.OkHttpTool;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 *        @Author: space
 *        @Date: 2018/10/15 13:54
 *        @Description: xlog请求头发送的
 **/
public class CesEncrypt {

    private static final String HOST = "192.168.0.100";

    private static final String PORT = "11600";

    public enum CesEnum{
        ENCODE(1),
        DECODE(2);

        private int index;

        CesEnum(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    /**
     * 对xlog的加密数据进行加密解密
     * @param cesType ENCODE是加密，DECODE是解密
     * @param bytes 参数字节流
     * @return 结果字节流
     */
    public static byte[] cesEncrypt(CesEnum cesType, byte[] bytes) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Map<String, String> header = new HashMap();
        Map<String, String> body = new HashMap();

        String url = "http://" + HOST + ":" + PORT + "/DouYin/Ces";
        String base64Str = ScaleTrans.bytesToBase64String(bytes);
        body.put("type", String.valueOf(cesType.getIndex()));
        body.put("data", base64Str);

        RequestEntity requestEntity = new RequestEntity(RequestEnum.POST_FORM);
        requestEntity.setUrl(url);
        requestEntity.setRequestEnum(RequestEnum.POST_FORM);
        requestEntity.setOkHttpClient(okHttpClient);
        requestEntity.setHeaders(header);
        requestEntity.setBody(body);


        Response response = OkHttpTool.handleHttpReq(requestEntity);

        return ScaleTrans.base64StringTobytes(response.body().string());
    }


    public static void main(String[] args) {
        String base64 = "AdxNFIhfjnLzGoibgpQXdKTopByoB9VctzqqB8MbM/EZ44scBaWXrNQNs7jHGQRFQqXZkSwHMWNU8uw974zsVabSc+0JC6/snSSowj5t5djru2vDPDd0W8V/inwf3QhGYXXzNwD1dwSz/MqTQ2sFbP6i0VLoYqE7vQgBc9xnJgRZF2WqG824lTC2o4E2GXd+Or1dUgRt/DVVLV44zT0iWxhCkWxj20NTnyRv0AFD3TE2zxrQbo3DHfBuD1NgOD44+RcDP3arzPhCxpeC5Y4GKziiNE+BMBC2OF/YRIkvel9AUXUc0xW5mMeaUtpnfHZTlCw9AyeC5rBffErxW09knY3fo/vkNJiGKfQNXL9zb/xVLNI5POvnMEmI2M5bixXDevQC7196sjsCHb6uhivcprNaYVnqGdQM5j1HSQ5aWomk31Ki2vh+H41E1zMJ4ZICJQ09F5w/DIBwIusB/Z+Csmq59kzYGzTUZkNOu7l8lbCdSJulcrpI+O5EZDXhD0RjRC3DhHCfw1ER0bg6VgJKvC9zfks84NKEuj3BZQDQRBlKCUHRJ1YhIKq7scvd80N6DlzlLyauXXpHuAJFBHuJU3Aw+olCYLCQ4FbsSZ4ceHCxdDiMuX/5+niY1/j1RvuFCP/rLdkYwpE46YMWYMvLpsRi6OyHQLwv5uV2wPXBd3Dxd+//Mxa09+aoWmope7jkbD9tyMmxGEHNxF12hZsKJs6IsK8CDC95KvMF1iVqd+q0sRshTqabORyVcIhf+ocb4MhbLWcG8oVJqf2sZvKHYz2e56tFV+FDgQw/7TLfr4F20tnlhSDhJ1bNuGd0w6ZFz98qXkZdWbK9kVTNHKyYAZ9trLutDSJSreAU6X2Xs3lyJRbjaI759yRLDBdRydh0KY/1OTzcy9zO62S7hOBnf/hQ/yyjNnao6KmndC4VtFmMr/1+7wUqg9oMTj5kO10+8zLaZi+7zsUTRlw7wxGaqWTSsTmAZrBnZu26D3gEIKj+GrSqkpPxoaEt2GR7zEIexOzmTTzsZhRJePTJrqRRhfob6ALSrdbVhaqFguvG0kOetuxDlE2lEKAbr3k/Yt49MX6JenU4FYTIaTM/gXFQGGLTuhX24mZwbY6Pqrc+AnI35NAcwGhfa0WyfnreYi5gfEae1j0PZAlqtl8Vl9/6reQTP5Q0NK+j5GpYUvdHYqDJ6movgo2QJObRAnF7IovGqhUBNYEmTy1rXOB3JhPaEw4eClxD7pwXjetwzW7giy2FtFjXonQQj10bxXyl2ArRcy/G85P/owIt133O8nAxZcT9VBHlzRl45tYfEQOVbFqnjyJ/MkIkhDrMY73nQKVZgpFmciOai9kJfTzYQjtsLrh5mcz6/sM18yLbCCv0PvxJneirLvLIKCF+kiITPBcpTziqAnDu4eY2Tv3DP9XiwGoi3JE6IvVHTIHtVzN35v/qvr5ERFxEdpZGLkQ0";
        byte[] bytes = ScaleTrans.base64StringTobytes(base64);
        try {
            byte[] bytes1 = cesEncrypt(CesEnum.DECODE, bytes);
            String result = new String(bytes1);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new String(bytes);
        System.out.println(result);
    }
}
