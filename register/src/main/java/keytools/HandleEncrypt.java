package keytools;

import com.mysql.cj.util.Base64Decoder;
import com.space.register.entity.DeviceEntity;
import params.CesJson;
import util.HttpTools.HttpTools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
 *                                                                                             @ClassName HandleEncrypt
 *                                                                                             @Author: space
 *                                                                                             @Description 解决Ces加密， 调用
 *                                                                                             @Date: 2018/10/11 23:29
 **/
public class HandleEncrypt {

    private static final String HOST = "http://192.168.0.108";

    private static final String PORT = "11600";

     /**
      * Ces的加密
      * @param type POST还是GET
      * @param params json字符串
      * @return
      */
    public static byte[] getCes(String type, String params) {
        String url = HOST + ":" + PORT + "/DouYin/Ces";
        Base64.Encoder encoder = Base64.getEncoder();
        String base64params = encoder.encodeToString(params.getBytes());

//        System.out.println(base64params);

        // 发送请求 type=1加密  type=2解密
        String post = HttpTools.post(url, "data=" + base64params + "&type=" + type);

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decode = decoder.decode(post);
        return decode;
    }


    public static void main(String[] args) {
//        DeviceEntity deviceEntity = new DeviceEntity();
//        String s = CesJson.jsonConstruct(deviceEntity);
//        String base64 = "AYoi60AdpEpIHXFbu5x4/Ky+YU4v0llOYZRlvewSjpcMwi3KQoofy6s+qVcpCSUaPnvfhemUtKP1vxY/y0GxvdXSc+0JC6/snSSowj5t5djrvjiT+gHMyMOyYh8jBdckIeUHz61oKA+XdRscyKzYz9KLdhR4YR8FwpnX47Reu64kpPCYvTvwjhB3EBDRKGdovzb6JOLhPHzE38SiJnvPguPrTYLuO4eQhwcJY47OqemoM7795k6s5x94+a/JroUtYbclxzprmnJPH1ov+lh0/ltmNz/VnTBJd79hcCDdavBtOVKAsSruvNCu7RoqOtFrNSeFmo3UH00RFjkpgf7nBD1g5I8lzG+gcoDE1woKaEFk/IbisgJw2cw88Wz16eExvEYal4XFtW2LEhf5TdBcp8zTbJ6912GKj5MEdOqY+jDQ3KgFQdwKwWFTkHXapQrLgcgsy/BR02cFTEV3s5UrWwxltdfXzxS3SepqIFb9KxRLyibR3l3YiNHzDGddhKX/AYFO71IegqwnHwJWy/oaVlmtZ8zBgi2k9Nez4lT9TBeRNxwrfHn8pn8wqDl/gd62Mvt6f8aq9LEnlGIzQOmdUnNTDeL2XQQY9pBukL3NMukXNDvJWr1Dg/kQHpxJgtIRXWXXLTT/tTIyMeP3MD/pMCYiPzQgvGf0pU53MaGdrvP2Cb1/LPLTODFlCHS95pYpA8yCIy2vwvUJZ9HEbDYeE9x+r0T4lcVo9suxDEhqiOkjHBIOK/tS26dEMWdQEJhtC4mkWBBqg+Nzc1IK+dFVl/LkUDRmMFOssMOLMrrGICbfXykhdgP5ls8DlWrKz4Dtc7ATEfdyjbPoUmqqqV+72zTgEg/tv/Q0QV0tm4QM40DgO8TIMSkY8biC2yCQRMWqw7WiNX8CRtWPchf2YE9lxlW3c1TtjBcGpDEI+u7H6ro2ScJX/9tADyBvxqfPQqvMqsQtJCTS5pYLDuy0CaChN+CF7jFjxkZdfkNZ0x6xr/H2sJmMdIyc0ATBN8vqTGJw/r+ItQuzU/CFfCtZYsP+vEsfPJJK6RXsSf+CcoCmKp5YwkKa5hAecPKAgXX6Z3Lq5PFocaCQOp0hlIqHCKTe1f3bMv8ujEsGNFyj15YU0eSnsmPbTIcXiI9z45CllETyj5wZqFsx9+IkIidYt96DzRCXRoO8236gljPJsn98G66b+gkGREJF7NB4BOUi6luZ52uc50uVlTwqCGOrtgFtU0VwUxG9FUtVPe7HCWMLDvV4ovuR+rsq/7g5V3RiOO069GFOoGu4mRNnZC8DkH4DZbkySvK6JaThBicT2Mci4jN9DU6ApprahpiAFjWH1lMWzpPgkZf9KWxHq9GxCgaGpJ2ohr+W1CB1emNLdVX/NFM8WmK9sesuB6LQjJqLF7qRXxH73BuRx8IoqMeRj0nHAplaYXViXgcenIr2vI5Ocob/ZDJXz5MF3puDaq//SLyCBK7J22Yk83jhoXRgk4maLBGTlV7DE6jC7j5GJm3Q9IuDNOVIP6tlblVbk+cDZd9IZBFYcwnbjhqdSG3ODyvLS+tkN7b9uMfbOxk6RMmdwctNCna8Z0tmWZ4ce+tzQ0uehX0=";
//        //下面解密
//        String url = HOST + ":" + PORT + "/DouYin/Ces";
//        String test1 = HttpTools.post(url, "data=" + base64 + "&type=2");
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] decode = decoder.decode(test1);
//        System.out.println(new String(decode));


        Base64.Decoder decoder = Base64.getDecoder();
        String base64 = "eyJwMSI6IiIsInAyIjoiIiwicGtnIjoiY29tLnNzLmFuZHJvaWQudWdjLmF3ZW1lIiwiZnAiOiJYaWFvbWlcL3NhbnRvbmlcL3NhbnRvbmk6Ny4xLjJcL04yRzQ3SFwvOC45LjEzOnVzZXJcL3JlbGVhc2Uta2V5cyIsInZjIjoyNzAsIlZQTiI6MCwid2lmaW1hYyI6ImRjOmZlOjE4OjYyOmYyOmY1IiwibG9jYXRpb24iOiIiLCJhcHBzIjpbXSwiaHciOnsiYnJhbmQiOiJYaWFvbWkiLCJtb2RlbCI6IlJlZG1pIDRYIiwiYm9hcmQiOiJRQ19SZWZlcmVuY2VfUGhvbmUiLCJkZXZpY2UiOiJzYW50b25pIiwicHJvZHVjdCI6InNhbnRvbmkiLCJidCI6InVua25vd24iLCJkaXNwbGF5IjoiNzIwKjEyODAiLCJkcGkiOjMyMCwiYmF0Ijo0MTAwLCJjcHUiOnsiaHciOiJRdWFsY29tbSBUZWNobm9sb2dpZXMsIEluYyBNU004OTQwIiwibWF4IjoiMTQwMTAwMCIsIm1pbiI6Ijk2MDAwMCIsImZ0IjoiaGFsZiB0aHVtYiBmYXN0bXVsdCB2ZnAgZWRzcCBuZW9uIHZmcHYzIHRscyB2ZnB2NCBpZGl2YSBpZGl2dCBscGFlIGV2dHN0cm0gYWVzIHBtdWxsIHNoYTEgc2hhMiBjcmMzMiJ9LCJtZW0iOnsicmFtIjoiMi43NyBHQiIsInJvbSI6IjI0LjA4IEdCIiwic2QiOiIyNC4wOCBHQiJ9fSwiaWQiOnsiaW1laSI6Ijg2NjcwOTAzMzUxMDEzMSIsImltc2kiOiI0NjAwNzgwMTU4MDg5ODgiLCJhZGlkIjoiY2Q1ZGVlZjY3NzA0YTA5ZSIsIm1hYyI6IjAyOjAwOjAwOjAwOjAwOjAwIiwic2VyaWFsIjoiNmQxNmNmYjdkNDQwIn0sImVtdWxhdG9yIjp7InNpZyI6MCwiY2IiOjEwLCJmaWxlIjpbXSwicHJvcCI6W119LCJlbnYiOnsidmVyIjoiMC41LjguMjgiLCJwa2ciOiJjb20uc3MuYW5kcm9pZC51Z2MuYXdlbWUiLCJ1aWQiOjEwMjA2LCJyZWJ1aWxkIjowLCJqZCI6MCwiZGJnIjowLCJ0aWQiOjAsInhwZCI6MCwiaGsiOltdLCJzdSI6MSwic3AiOiJcL3N5c3RlbVwveGJpblwvc3UiLCJyby5zZWN1cmVfcyI6IiIsInJvLmRlYnVnZ2FibGVfcyI6IiIsImNsaWNrIjoiIiwiaHBoIjoiMTkyLjE2OC4wLjEwOCIsImhwcCI6Ijg4ODgiLCJtYyI6MCwiZmMiOjU3NTgwLCJqZXhwIjowLCJ4cG9zZWQiOjAsImN5ZGlhIjowLCJmcmlkYSI6MCwidmFwcCI6IiIsImFwaSI6W119LCJleHRyYSI6ImNvbGRfc3RhcnQiLCJleHRlbnNpb24iOnsibm90aWZ5IjoxMjU4Mzc1MDI0LCJ0cGlkMSI6MCwidGJpbiI6IiJ9fQ==";

        byte[] decode = decoder.decode(base64);
        String result = new String(decode);
        System.out.println(result);
    }


}
