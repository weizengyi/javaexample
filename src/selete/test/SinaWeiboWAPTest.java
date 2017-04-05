package selete.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.ConfigUitl;

public class SinaWeiboWAPTest {

	public static void main(String[] args) {

	}

	@Test
	public void test2() {
		System.setProperty("webdriver.chrome.driver", "resources/sedriver/chromedriver");
		WebDriver driver = new ChromeDriver();
		String loginname = "weizengyipp@gmail.com";
		String password = "wzy_8298236";
		SinaWeiboTest.loginSinaWeiBo(driver, loginname, password);
		getFansID(driver,"1638642540");
		driver.quit();
	}
	
	@Test
	public void test1() {
		System.setProperty("webdriver.chrome.driver", "resources/sedriver/chromedriver");
		WebDriver driver = new ChromeDriver();
		testValideUser("weizengyipp@gmail.com", "wzy_8298236", driver, 1638642348 + 1L, 500);
		// testSinaWeiboLoginLogout(driver);
		driver.quit();
	}

	public static void testValideUser(String name, String pwd, WebDriver driver, long startid, int step) {
		Map<String, String> keyValueMap = new TreeMap<String, String>();
		String loginname = name;
		String password = pwd;
		if (!SinaWeiboTest.loginSinaWeiBo(driver, loginname, password))
			return;
		for (long l = startid, i = 0; i < step; l++, i++) {
			try {
				if (isValideUserWAP(driver, l)) {
					log(l + "");
					keyValueMap.put(l + "", "1");
				}
			} catch (NotLoginException e) {
				log("无效登陆状态");
				driver.manage().deleteAllCookies();
				SinaWeiboTest.logoutSinaWeibo(driver);
				if (!SinaWeiboTest.loginSinaWeiBo(driver, loginname, password))
					break;
			}

		}
		String s = keyValueMap.toString();
		for (String d : s.split(", "))
			System.out.println(d);
		driver.close();
	}

	public static void testSinaWeiboLoginLogout(WebDriver driver) {
		String loginname = "weizengyipp@gmail.com";
		String password = "wzy_8298236";
		if (!SinaWeiboTest.loginSinaWeiBo(driver, loginname, password))
			return;
		driver.navigate().to("http://weibo.cn");
		try {
			sendPrivateLetterWAP(driver, "sdf", 3738637747L);
		} catch (NotLoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if(inputTextAreaWAP(driver,"[哈哈][嘻嘻][可怜]喜欢的朋友欢迎关注我，搜索微信号：testetst1099[偷笑][太开心]"))
		// log("微博内容发布成功");
		// logoutSinaWeiboWAP(driver);
	}

	public static void logoutSinaWeiboWAP(WebDriver driver) {
		log("退出新浪微博");
		WebElement we = driver.findElement(By.linkText("退出"));
		we.click();
		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().equalsIgnoreCase("微博广场");
			}
		});
		log("退出新浪微博完成");
	}

	public static boolean loginSinaWeiBoWAP(WebDriver driver, String username, String pwd) {
		log("正访问新浪微博");
		driver.get("https://weibo.cn/login/");
		(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return d.getTitle().toLowerCase().equalsIgnoreCase("微博");
			}
		});
		log("已进入新浪微博WAP首页");
		WebElement loginname = driver.findElement(By.name("mobile"));
		loginname.click();
		loginname.clear();
		loginname.sendKeys(username);
		log("设置用户名");
		List<WebElement> inputs = driver.findElements(By.tagName("input"));
		for (WebElement we : inputs) {
			if (we.getAttribute("type") != null && we.getAttribute("type").equalsIgnoreCase("password")) {
				we.click();
				we.clear();
				we.sendKeys(pwd);
			}
		}
		log("设置密码");
		WebElement code = driver.findElement(By.name("code"));
		if (code != null) {
			log("请填写验证码");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		WebElement login_form_savestate = driver.findElement(By.name("remember"));

		if (login_form_savestate.isSelected())
			login_form_savestate.click();

		WebElement loginbtn = driver.findElement(By.name("submit"));
		log("提交用户名密码，正在登陆");
		loginbtn.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		driver.navigate().refresh();
		try {
			(new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.getTitle().toLowerCase().equalsIgnoreCase("我的首页");
				}
			});
		} catch (TimeoutException e1) {
			return false;
		}
		log("已登陆新浪微博，当前用户：" + username);
		return true;
	}

	public static void log(String log) {
		System.out.println(log);
	}

	public static boolean sendPrivateLetterWAP(WebDriver driver, String content, long id) throws NotLoginException {
		WebElement plLink = driver.findElement(By.linkText("私信"));
		plLink.click();
		WebElement textArea = driver.findElement(By.tagName("textarea"));
		textArea.click();
		textArea.sendKeys(content);
		textArea.submit();
		return true;
	}

	public static boolean isValideUserWAP(WebDriver driver, long id) throws NotLoginException {
		String userURL = "http://weibo.cn/" + id;
		log(userURL);
		driver.navigate().to(userURL);
		Random r = new Random();
		try {
			Thread.sleep(5000L + r.nextInt(3000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		try {
			if (driver.getTitle().contains("我的首页"))
				return false;
			if (driver.getTitle().length() == 0)
				throw new NotLoginException();
		} catch (TimeoutException e) {
			log("超时:" + id);
			return false;
		}
		try {
			WebElement we = driver.findElement(By.className("me"));
			if (we != null && (we.getText().equalsIgnoreCase("User does not exists!") || we.getText().contains("抱歉")))
				return false;
		} catch (NoSuchElementException e) {
		}
		return isSendMsgRecently(driver);
	}

	public static boolean inputTextAreaWAP(WebDriver driver, String content) {
		log("发布微博内容：" + content);
		WebElement textArea = driver.findElement(By.tagName("textarea"));
		textArea.click();
		textArea.sendKeys(content);
		List<WebElement> MAYBEfabuBtn = driver.findElements(By.tagName("input"));
		for (WebElement we : MAYBEfabuBtn) {
			if ((we.getAttribute("value") != null) && we.getAttribute("value").equalsIgnoreCase("发布")) {
				we.click();
				return true;
			}
		}
		return false;
	}

	public static boolean isSendMsgRecently(WebDriver driver) {
		boolean b = false;
		List<WebElement> wes = driver.findElements(By.tagName("span"));
		if (wes == null)
			return false;
		for (WebElement we : wes) {
			if (we.getAttribute("class") == null)
				continue;
			if (we.getAttribute("class").equalsIgnoreCase("ct")) {
				if ((we.getText().contains("月") && we.getText().contains("日")) || we.getText().contains("分钟前")
						|| we.getText().contains("今天")) {
					b = true;
					break;
				}
			}
		}
		return b;
	}

	public static int dataCount(WebDriver driver) {
		List<WebElement> wes = driver.findElements(By.tagName("a"));
		int guanzhu = 0;
		int fengsi = 0;
		int weibo = 0;
		for (WebElement we : wes) {
			if (we.getText().contains("关注"))
				guanzhu = getCount(we.getText());
			if (we.getText().contains("粉丝"))
				fengsi = getCount(we.getText());
			if (we.getText().contains("微博"))
				weibo = getCount(we.getText());
		}
		return 0;
	}

	private static int getCount(String s) {
		if (s.contains("[") && s.contains("]")) {
			int start = s.indexOf("[") + 1;
			int end = s.indexOf("]") - 1;
			String countString = s.substring(start, end);
			return Integer.parseInt(countString);
		}
		return 0;
	}

	public static void getFansID(WebDriver driver, String targetID) {
		driver.navigate().to("http://weibo.cn/"+ targetID+"/fans");
		try {
			(new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d) {
					return d.getTitle().contains("粉丝");
				}
			});
		} catch (TimeoutException e1) {
			System.out.println("进入粉丝页失败");
			return ;
		}
		List<String> IDList = new ArrayList<String>();
		log("收集粉丝ID");
		boolean b = true;
		while(b){
			List<WebElement> weList = driver.findElements(By.tagName("a"));
			for (WebElement we : weList) {
				if (we.getAttribute("href") == null || !we.getAttribute("href").contains("http://weibo.cn/u/"))
					continue;
				String id = we.getAttribute("href").substring(18, we.getAttribute("href").length());
//				log(id);
				IDList.add(id);
			}
			List<WebElement> inputList = driver.findElements(By.tagName("input"));
			for (WebElement we : inputList) {
				    if(we.getAttribute("value") != null ){
				    	if(we.getAttribute("value").equalsIgnoreCase("跳页")){
				    		String t = we.getText();
				    		int  flag = t.indexOf("/");
				    		int flagpage = t.indexOf("页") ;
				    		String s1 = t.substring(0,flag);
				    		String s2 = t.substring(flag+1,flagpage);
				    		if(s1.equalsIgnoreCase(s2)){
				    			b=false;
								break;
				    		}
				    	}
				    		
				    }
					
			}
			if(b){
			    WebElement nextPage = driver.findElement(By.linkText("下页"));
				waitInThread(3);
				nextPage.click();
				try {
					(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return d.getTitle().contains("粉丝");
						}
					});
				} catch (TimeoutException e1) {
					System.out.println(IDList);
				}
			}
			else {
				for(String s :IDList)
				System.out.println(s);
			}
		}
		
	}
	
	  private static void waitInThread(long t){
	    	try {
				Thread.sleep(t*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
  
}