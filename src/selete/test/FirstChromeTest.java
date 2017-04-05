package selete.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstChromeTest {

	    public static void main(String[] args) {
	    	// 创建一个 Chrome 的浏览器实例
	    	System.setProperty("webdriver.chrome.driver", "resources/sedriver/chromedriver");
	        WebDriver driver = new ChromeDriver();
	        
	        
	     // 创建一个 htmlUnit 的浏览器实例
//	        WebDriver driver = new HtmlUnitDriver();
	        
	     // TODO 创建一个 firefox 的浏览器实例 崩溃
//	    	System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox");
//	    	System.setProperty("webdriver.gecko.driver", "/Users/yp-tc-m-2933/Desktop/selejar/geckodriver");
//	    	WebDriver driver = new FirefoxDriver();
	        
	        // 让浏览器访问 Baidu
	        driver.get("http://www.baidu.com");
	        // 用下面代码也可以实现
	        // driver.navigate().to("http://www.baidu.com");
	        // 获取 网页的 title
	        System.out.println("1 Page title is: " + driver.getTitle());
	        // 通过 id 找到 input 的 DOM
	        WebElement element = driver.findElement(By.id("kw"));
	        // 输入关键字
	        element.sendKeys("zTree");
	        // 提交 input 所在的  form
	        element.submit();
	        // 通过判断 title 内容等待搜索页面加载完毕，Timeout 设置10秒
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.getTitle().toLowerCase().endsWith("ztree");
	            }
	        });
	        // 显示搜索结果页面的 title
	        System.out.println("2 Page title is: " + driver.getTitle());
	        //关闭浏览器
	        driver.quit();
	    }
}
