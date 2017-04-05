package selete.test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SinaWeiboTest {

	public static void main(String[] args) {
		// 创建一个 Chrome 的浏览器实例
		System.setProperty("webdriver.chrome.driver", "resources/sedriver/chromedriver");
		WebDriver driver = new ChromeDriver();
		// testValideUser(driver);
		testSinaWeiboLoginLogout(driver);
		// driver.quit();
	}

	public static void testValideUser(WebDriver driver) {
		log("正访问新浪微博");
		driver.get("http://weibo.com/");
		if ("Sina Visitor System".equalsIgnoreCase(driver.getTitle()))
			log("已连接新浪微博");
		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().equalsIgnoreCase("微博-随时随地发现新鲜事");
			}
		});
		log("已进入新浪微博首页");
		for (long l = 3738637743L, i = 0; i < 500; l++, i++) {
			if (isValideUser(driver, l))
				log(l + "");
		}

	}

	public static void testSinaWeiboLoginLogout(WebDriver driver) {
		String loginname = "weizengyipp@gmail.com";
		String password = "wzy_8298236";
		if (!loginSinaWeiBo(driver, loginname, password))
			return;
		sendPrivateLetter(driver, "sdf", 3738637747L);
		// if(inputTextArea(driver,"[哈哈][嘻嘻][可怜]喜欢的朋友欢迎关注我，搜索微信号：testetst1099[偷笑][太开心]"))
		// log("微博内容发布成功");
		// logoutSinaWeibo(driver);
	}

	public static void logoutSinaWeibo(WebDriver driver) {
		log("退出新浪微博");
		driver.navigate().to("http://weibo.com/logout.php?backurl=%2F");
		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().equalsIgnoreCase("微博-随时随地发现新鲜事");
			}
		});
		log("退出新浪微博完成");
	}

	public static boolean loginSinaWeiBo(WebDriver driver, String username, String pwd) {
		log("正访问新浪微博");
		driver.get("http://weibo.com/");
		if ("Sina Visitor System".equalsIgnoreCase(driver.getTitle()))
			log("已连接新浪微博");
		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().equalsIgnoreCase("微博-随时随地发现新鲜事");
			}
		});
		log("已进入新浪微博首页");
		WebElement loginname = driver.findElement(By.id("loginname"));
		loginname.click();
		loginname.clear();
		loginname.sendKeys(username);
		log("设置用户名");
		WebElement password = driver.findElement(By.name("password"));
		password.click();
		password.clear();
		password.sendKeys(pwd);
		log("设置密码");
		WebElement login_form_savestate = driver.findElement(By.id("login_form_savestate"));

		if (login_form_savestate.isSelected())
			login_form_savestate.click();

		WebElement loginbtn = driver.findElement(By.cssSelector(".W_unlogin_v2 .W_login_form .login_btn"));
		log("提交用户名密码，正在登陆");
		loginbtn.click();
		try {
			(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.getTitle().toLowerCase().equalsIgnoreCase("我的首页 微博-随时随地发现新鲜事");
				}
			});
		} catch (TimeoutException e1) {
			log("提交超时");
			loginbtn = driver.findElement(By.cssSelector(".W_unlogin_v2 .W_login_form .login_btn"));
			if (loginbtn != null) {
				loginbtn.click();
				log("已提交用户名密码，正在登陆");
				try {
					(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return d.getTitle().toLowerCase().equalsIgnoreCase("我的首页 微博-随时随地发现新鲜事");
						}
					});
				} catch (TimeoutException e2) {
					log("二次提交超时，请手动查看");
					return false;
				}
			}else{
				driver.navigate().refresh();
			}
		}
		log("已登陆新浪微博，当前用户：" + username);
		return true;
	}

	public static void log(String log) {
		System.out.println(log);
	}

	public static boolean sendPrivateLetter(WebDriver driver, String content, long id) {
		if (!isValideUser(driver, id))
			return false;
		List<WebElement> MAYBEfabuBtn = driver.findElements(By.tagName("a"));
		for (WebElement we : MAYBEfabuBtn) {
			if ((we.getAttribute("suda-uatrack") != null) && (!we.getAttribute("suda-uatrack").isEmpty())
					&& we.getAttribute("suda-uatrack").contains("private_letter")) {
				we.click();
			}
		}
		List<WebElement> textAreas = driver.findElements(By.tagName("textarea"));
		for (WebElement textArea : textAreas) {
			if (textArea.getAttribute("placeholder").equalsIgnoreCase("按回车发送私信")) {
				textArea.click();
				textArea.sendKeys(content);
				textArea.sendKeys(Keys.RETURN);
				return true;
			}
		}

		return false;
	}

	public static boolean isValideUser(WebDriver driver, long id) {
		String userURL = "http://weibo.com/" + id;
		log("userURL:" + userURL);
		driver.navigate().to(userURL);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if (driver.getTitle().toLowerCase().contains("404") || driver.getTitle().toLowerCase().contains("随时随地发现新鲜事"))
			return false;
		while ("Sina Visitor System".equalsIgnoreCase(driver.getTitle())) {
			driver.navigate().refresh();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			if (driver.getTitle().toLowerCase().contains("404")
					|| driver.getTitle().toLowerCase().contains("随时随地发现新鲜事"))
				return false;
		}

		return true;
	}

	public static boolean inputTextArea(WebDriver driver, String content) {
		log("发布微博内容：" + content);
		WebElement textArea = driver.findElement(By.tagName("textarea"));
		textArea.click();
		textArea.sendKeys(content);
		List<WebElement> MAYBEfabuBtn = driver.findElements(By.tagName("a"));
		for (WebElement we : MAYBEfabuBtn) {
			if ((!we.getAttribute("title").isEmpty()) && we.getAttribute("title").equalsIgnoreCase("发布微博按钮")) {
				we.click();
				return true;
			}
		}
		return false;
	}

}