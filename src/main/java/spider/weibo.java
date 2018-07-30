package spider;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Random;

public class weibo {

    /**
     * 初始化一个WebDriver
     * @param url
     * @return
     */
    public static WebDriver getDriver(String url){
        try {

            // 等待数据加载的时间
            // 为了防止服务器封锁，这里的时间要模拟人的行为，随机且不能太短
            long waitLoadBaseTime = 5000;

            int waitLoadRandomTime = 2000;

            Random random = new Random(System.currentTimeMillis());

            ChromeOptions options = new ChromeOptions();
            // 通过配置参数禁止data;的出现

            options.addArguments(

                    "--user-data-dir=C:/Users/Administrator/AppData/Local/Google/Chrome/User Data/Default");

            // 通过配置参数删除“您使用的是不受支持的命令行标记：--ignore-certificate-errors。稳定性和安全性会有所下降。”提示

            options.addArguments("--start-maximized", "allow-running-insecure-content", "--test-type");

            options.addArguments("--profile-directory=Default");

            // userdata 设置使用chrome的默认参数

            options.addArguments("--user-data-dir=C:/Temp/ChromeProfile");



            System.setProperty("webdriver.chrome.driver","E:\\ustb\\webdriver\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get(url);

            // 等待页面动态加载完毕

            Thread.sleep(waitLoadBaseTime+random.nextInt(waitLoadRandomTime));

            return driver;

        }catch (Exception e){
            e.printStackTrace();

            return null;
        }
    }

    /**
     * 封装根据类名查Data的方法
     * @param driver
     * @param className
     * @return
     */
    public static String getPagerDataByClassName(WebDriver driver, String className) {

        return driver.findElement(By.className(className)).getText();

    }

    /**
     * 获取微博用户关注、粉丝、微博 有先后顺序
     *
     * @param driver
     * @return
     */

    public static String[] getWeiboCount(WebDriver driver) {

        String result = getPagerDataByClassName(driver, "tb_counter");

        String[] array = new String[3];

        int gz = result.indexOf("关注");

        int fs = result.indexOf("粉丝");

        int wb = result.indexOf("微博");

        array[0] = result.substring(0, gz);

        array[1] = result.substring(gz + 2, fs);

        array[2] = result.substring(fs + 2, wb);

// 去除换行效果

        array[0] = array[0].substring(0, array[0].length() - 1);

        array[1] = array[1].substring(1, array[1].length() - 1);

        array[2] = array[2].substring(1, array[2].length() - 1);

        return array;

    }

    public static void getWeiboInfo(String url){
        System.out.println("---weibo url:" + url);
        WebDriver webDriver =getDriver(url);
        System.out.println("---weibo webdriver is :" + (null == webDriver ? "null" : "not null"));
        System.out.println("---weibo 名称 :" + getPagerDataByClassName(webDriver, "username"));
        System.out.println("---weibo 签名 :" + getPagerDataByClassName(webDriver, "pf_intro"));

        String[] array = getWeiboCount(webDriver);

        System.out.println("---weibo 关注数 " + array[0]);
        System.out.println("---weibo 粉丝数 " + array[1]);
        System.out.println("---weibo 微博数 " + array[2]);

        webDriver.quit();
    }

    public static void main(String[] args) {
        getWeiboInfo("https://weibo.com/731078971");
    }

}
