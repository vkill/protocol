package platform.tv;

/**
 * @program: protocol
 * @description: 暂时的注册方法
 * @author: gaoxiang
 * @create: 2018-08-14 11:51
 **/
import database.AccountInfoGetterImpl;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AndroidKeyCode;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import platform.email.EmailGetter;
import po.AccountPo;
import po.PhonePo;
import tools.RandomName;

public class HundredOneDay {

    public AppiumDriver appiumDriver;
    public EmailGetter emailGetter;
    public EmailGetter space;
    public AccountInfoGetterImpl accountInfoGetter;
    public HundredOneDay(){
        appiumDriver =initAppiumTest();
        emailGetter = new EmailGetter();
        emailGetter.loginIT("api_qianbaiwan_rrgr","qianbaiwan");
        space = new EmailGetter();
        space.loginIT();
        accountInfoGetter = new AccountInfoGetterImpl();
    }
    /**
     * AppiumDriver的初始化逻辑
     * @return
     */
    public AppiumDriver initAppiumTest() {

        AppiumDriver driver=null;
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "web\\apk");
        File app = new File(appDir, "com.ss.android.ugc.aweme.apk");

        //设置自动化相关参数
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("appPackage", "com.ss.android.ugc.aweme");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("noSign", "true");

        //设置apk路径
        capabilities.setCapability("app", app.getAbsolutePath());

        //设置使用unicode键盘，支持输入中文和特殊字符
        capabilities.setCapability("unicodeKeyboard","true");
        //设置用例执行完成后重置键盘
        capabilities.setCapability("resetKeyboard","true");
        //初始化
        try {
            driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return driver;
    }

    public void getOneDay(){
        PhonePo phonePo = null;
        PhonePo passwordPhone = null;
        AccountPo accountPo = new AccountPo();
        try {
            Thread.sleep(6000);		//等待6s，待应用完全启动
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("开始注册");
        appiumDriver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS); //设置尝试定位控件的最长时间为8s,也就是最多尝试8s

        appiumDriver.findElementByXPath("//android.widget.TextView[@text='我']").click();

        appiumDriver.findElementByXPath("//android.widget.TextView[@text='我']").click();
        appiumDriver.tap(1,656,1254,100);
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/a60").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/jm").sendKeys("泰国");
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/jn").click();
        appiumDriver.findElementByXPath("//android.widget.RelativeLayout[@index='1']").click();
        //调用短信系统获取验证码—— 等待添加
        phonePo = emailGetter.getPhoneNumber();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/a62").sendKeys(phonePo.getPhone_Num());
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/a2w").click();
        String buff = emailGetter.getIdentCode(phonePo.getP_ID());
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/a6j").sendKeys(buff);
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b_i").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/kw").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/kv").click();
        appiumDriver.findElementByXPath("//android.widget.Button[@text='确定']").click();
        String name = RandomName.getName();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/kq").sendKeys(name);
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/ko").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b7w").click();
        appiumDriver.findElementByClassName("android.widget.LinearLayout").click();
        //appiumDriver.tap();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appiumDriver.findElementById("com.android.gallery:id/save").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/ks").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/aga").click();
        appiumDriver.findElementByXPath("//android.widget.TextView[@text='我']").click();

        //appiumDriver.findElementByXPath("//android.widget.TextView[@text='我']").click();
        System.out.println("点击Index了");
        appiumDriver.tap(1,656,1254,100);
        //appiumDriver.findElementByXPath("//android.widget.RelativeLayout[2]/android.widget.ImageView[@index='0']").click();
        appiumDriver.tap(2,670,70,100);
        appiumDriver.findElementByXPath("//android.widget.TextView[@text='账号与安全']").click();
        appiumDriver.findElementByXPath("//android.widget.TextView[@text='抖音密码']").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b_h").sendKeys("pigbrother233");
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b_i").click();
        passwordPhone = emailGetter.getPhoneNumber(phonePo.getPhone_Num());
        buff = emailGetter.getIdentCode(passwordPhone.getP_ID());
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b_g").sendKeys(buff);
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b_i").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/ih").click();
        accountInfoGetter.insertOneInfo(phonePo.changToAccount(name));
        Dimension size = appiumDriver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        //SwipeDown(appiumDriver);
        //appiumDriver.swipe(1/2*width, 9/10*height, 1/2*width, 1/10*height, 1000);
        //appiumDriver.swipe(1/2*width, 1/7*height, 1/2*width, 6/7*height, 200);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appiumDriver.findElementByXPath("//android.widget.TextView[@text='退出登录'").click();
        appiumDriver.findElementById("com.ss.android.ugc.aweme:id/b6g").click();

    }
    /**
    public void SwipeDown(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int height = size.height;
        int width = size.width;
        new TouchAction(driver)
                .longPress(PointOption.point(width / 2, height - 100))
                .moveTo(PointOption.point(width / 2, 100)).release().perform();
    }
     **/
    public static void main(String[]args){
        HundredOneDay hundredOneDay = new HundredOneDay();
        hundredOneDay.getOneDay();
        while (true){
            try {

            }catch (Exception e){
                System.out.println("继续");
            }
        }

    }
}
