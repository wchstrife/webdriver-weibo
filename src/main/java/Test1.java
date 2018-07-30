import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1 {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","E:\\ustb\\webdriver\\chromedriver.exe");
        WebDriver test = new ChromeDriver() ;
        test.get("http://manager.5itt.cn");
        test.findElement(By.id("name")).sendKeys("14310000001");
        test.findElement(By.id("pwd")).sendKeys("wl1431");

        try {
            Thread.sleep(5000);
            test.findElement(By.className("btn")).click();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
