package platform.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import po.PhonePo;

import java.io.IOException;

/**
 * @program: protocol
 * @description: 获取短信验证码操作类
 * @author: gaoxiang
 * @create: 2018-08-02 18:04
 **/

public class EmailGetter {
    /**
     * 变量依次为：账号、密码、项目类型
     */
    //api_qianbaiwan_rrgr,qianbaiwan
    private String userName ="api_qianbaiwan_o1o";
    private String password ="qianbaiwan";
    private String projectID ="1003";
    private String projectPasswordID = "1004";
    private String Login_url ;
    private String Usertoken ="5eb9541386e913d9d0bfccbd212e081b";
    private String errorStr = "0";
    private String successStr = "1";
    /**
     * 登陆平台初始化方法
     */
    public void loginIT(){
        loginIT(userName,password);
    }

    public void loginIT(String user,String password){
        this.userName = user;
        this.password = password;
        String tag = errorStr;
        int errTime =0;
        String[] buffers = null;
        //http://api0.wmisms.com/yhapi.ashx?act=login&ApiName=api_yh001_0fk&PassWord=yh001
        Login_url = "http://api0.wmisms.com/yhapi.ashx?act=login&ApiName="+userName+"&PassWord="+password;

        try {

            while (tag.equals(errorStr)&&errTime<3){
                Document document = Jsoup.connect(Login_url).get();
                // System.out.println(document.body().text());
                String buff=document.body().text();
                buffers = buff.split("\\|");
                tag = buffers[0];
                if(tag.equals(successStr)){
                    System.out.println("登录成功");
                    break;
                }
                System.out.println("尝试登陆错误"+errTime+"次");
                errTime++;
                //因为感觉这方面错误只有网络延迟的可能，所以就120秒吧
                Thread.sleep(120000);
            }
            Usertoken =buffers[1];
            if(errTime==3){
                System.out.println("账号可能存在问题，还是别进行下一步操作了");
            }else{
                System.out.println("用户标识Token为："+Usertoken);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 提取手机号方法实现,注意提取之前要去主动发送短信
     * 返回包含手机号码信息的po类型
     */
    public PhonePo getPhoneNumber(){
        return getPhoneNumber("随机");
    }

    /**
     *
     * @param phoneNum 指定要获取的手机号
     * @return 存储号码对象的po
     */
    public PhonePo getPhoneNumber(String phoneNum){
        String tag =errorStr;
        String phone_url;
        if(phoneNum.equals("随机")){
            //http://api0.wmisms.com/yhapi.ashx?act=getPhone&token=ad718214bdf8e7ad80344bf9743ec307&iid=1001&did=&operator=&city=&mobile=
            phone_url="http://api0.wmisms.com/yhapi.ashx?act=getPhone&token="+Usertoken+"&iid="+projectID+"&did=&operator=&city=&mobile=";

        }else{
            phone_url="http://api0.wmisms.com/yhapi.ashx?act=getPhone&token="+Usertoken+"&iid="+projectPasswordID+"&did=&operator=&city=&mobile="+phoneNum;
        }
        Document document = null;
        String[] buffers = null;
        int worryTime =0;
        while(tag.equals(errorStr)&&worryTime<2){
            try {
                document = Jsoup.connect(phone_url).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String buff=document.body().text();
            buffers = buff.split("\\|");
            tag =buffers[0];
            if(tag.equals(successStr)){
                System.out.println("成功获取手机号: "+buffers[4]+" "+buffers[1]+" "+buffers[6]);
                break;
            }else{
                System.out.print("发生错误，错误信息为: "+buff);
            }
            worryTime++;
        }
        if(worryTime==2){
            System.out.println("获取手机号码失败，将返回空值");
            return null;
        }else{
            if(buffers.length==7){
                PhonePo phonePo = new PhonePo(buffers[1],buffers[2],buffers[3],buffers[4],buffers[6]);
                return phonePo;
            }else{
                System.out.println("格式出现错误，请进行更正");
            }

            return null;
        }

    }
    /**
     *
     * @param P_ID 监听验证码的参数
     * @return 获取的验证码
     */
    public String getIdentCode(String P_ID){
        //              http://api0.wmisms.com/yhapi.ashx?act=getPhoneCode&token=ad718214bdf8e7ad80344bf9743ec307&pid=100118456007026
        String infoUrl ="http://api0.wmisms.com/yhapi.ashx?act=getPhoneCode&token="+Usertoken+"&pid="+P_ID;
        Document document =null;
        String buff = null;
        String[] buffers;
        String tag =errorStr;
        int buffer_Num =0;
        while(tag.equals(errorStr)&buffer_Num<10){
            try {
                Thread.sleep(5000);
                document =Jsoup.connect(infoUrl).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            buff = document.body().text();
            buffers = buff.split("\\|");
            tag = buffers[0];
            if(tag.equals(successStr)){
                return buffers[2].substring(3,7);
            }
            else{
                System.out.println("出现错误："+buffers[1] +" -3 意味着等待验证码");
            }
            buffer_Num++;
        }
        System.out.print("获取验证码失败");
        return "请求超时";
    }


    public static void main(String[] args) {

        EmailGetter emailGetter =new EmailGetter();
        emailGetter.loginIT();
        while(true){ ;
            PhonePo phonePo =emailGetter.getPhoneNumber();
            System.out.print(emailGetter.getIdentCode(phonePo.getP_ID()));
            System.out.println(emailGetter.getIdentCode(phonePo.getP_ID()));
        }

    }
}
