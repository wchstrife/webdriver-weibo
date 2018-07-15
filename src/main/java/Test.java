import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","E:\\ustb\\webdriver\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.baidu.com");
        webDriver.findElement(By.id("kw")).sendKeys("Java");
        webDriver.findElement(By.id("su")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.quit();
    }
}
