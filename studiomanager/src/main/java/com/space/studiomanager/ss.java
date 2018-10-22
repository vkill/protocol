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
        String str = "a";
        BASE64Encoder encoder = new BASE64Encoder();
        byte[] bytes = str.getBytes();
        String base64 = encoder.encode(bytes);
        System.out.println(base64);

        String data = "3uc/1HwY12qKQ3beMgxLHWUw7fA55Ag+FYC5uALzjQvOphAqO5IvOyEqvNGp6a62TqbdDYkKIkRI4VYNVYjhwLBuYCdv+UHXeuzGLh0MitoZiQpDTTUSeHjpowiYArrnlMzX7+Dyio+lg7I9j8hi0z5IPRdQvOzS3NhgpaiC5RtSblxU5q7HSM/B9ZvzvDiXhxi7akB5Iqk+eUTADw2S12ahUAJi1vsU9V43IjywwVPr+GTLWA6I9b19mUzWAK28VEJTX7pexXH0q5XV8HhV9hZExFlBmhI4qf9LUbe0ofTUwmuNXlLJi5P/ECnVFGWnkq5tPa5ZSBnk3Lm+OMQ/y/aCadoQwuFwaCBzkKGCtu7ajH2NcygCaoFOQg5vdvne+U+cvdX+NK5j02CszR9Fot+hsie0kz/BkQy8b9hOaxj9/47651LNt2wdwwFXzwbvYUyj/e7uGwP2vrg5Jgk4urYuDZdtaZqsdqp7bkNItt8HgIuFCoIrweBYm7u1Ou+dvBwKMT6mAtL1cNZIJFqUcj89rVgo3mGCdJ4jGngou+H/SKjg1wjUcpjF2yjbaMuwP4D2XORqndwuvxIBDhTnVnuc4AtFnJKB/zbkMChm/Xpm7r/QM0GpRTIOjT0uXhOXediaud3ZIEWV8rzKGR6dvZRYTwcwQcWI6FTqpkkWdmTuKEJ60wCSbGE3utgmSscnIQrsUnKQmIqBOdIMoiTGVww8c+HdWyXH0qZO/HUyEiVTAnT90oJXCursIQuZK7/MJIq0wIxbTupmBE+08x5tShPcjUg0BvBzZhLQNsPt1m8aDo1rxoiLUTHOCC40t9mV7IACfVngO4DYSLrf0R1uIEpp/FmGj8d5atD7f/kEl18lm6xk0SaQ+jPMhe8wI/XYNrdaxavkwKDSAH5TQGC54TPP3qpjlM+nToVbr2ZOdHeHNmybkzPVIEsgtE+AhwS3kEPp/mQVGXgSJjNd6W3KeBRMz1A+mH9ar2ETo+TyLBeAZ5IgkxS9jvXFJRR3niy0+c+ri84DDBEjzivXQuuuWPSPA3FPohHc+79HFd4E7OTAyaSaS7aR7wY/xOKk7Dj++FInUfsDdQ0kY4WD+zA8r8bT8IBtBU1wx9ZuV4UTLSBT1K7b3kmHdjCdu3P/IJek7PpH2nkav5lLSryIA90+O7R4iK+tceQGEbxfutD5V3lFjmbi/KAmpSTnDCzt/KHMl/ZgRI2JoIZO5qZ635AVsIR5aASJ+tI8TTzy0L5NvjsGQpcwGZck+V7WSf6N3W8Q6jyFEdmaW8YszwGTYXgNBRrjv+hXxekzlxFsif1Js2zNBrHEikgQGardLSX4z0sAUxgOSSY2B0BjXf5JiApGPxBmOzkpRZ5mSFL4xm98V2T8og2vsgNDN2QnqATIRBkex4WbWxtg7TpPrfdpjyRrrK3wHeTFOYynkMaEC63tBEvWUTavHlyM9XQWFW9P6q0j697XUfVGWM/VuS4JKdJcGI0Ja+t/2n+hin57+xTGkkKTNmJP4/BLZI9M0y3ykc35S7CLCwZ4WA/J6kUdDqJoDkWqE+H+t7ydo1Ilqoo6M0h56+eNoaTxBVDplE3R+A/psgyHwymuhdOEnoXpt/pcoZpoXZ1oEFOOVSg+WV53CMzK89VR/m4eQPhC0ZNvD75E";

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes1 = decoder.decodeBuffer(data);
//        String s = new String(bytes1);
//        System.out.println(s);
        for (int i=0; i < bytes1.length; i++) {
            System.out.print(bytes1[i]+" ");
        }

    }
}
