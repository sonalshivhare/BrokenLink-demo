package com.brokenlink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class verifylink {
public static void main(String[] args) {
	WebDriver driver=new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	

	driver.get("https://www.google.com");
	
List<WebElement>	linklist=driver.findElements(By.tagName("a"));
System.out.println("full link"+linklist.size());

List<WebElement> activelink=new ArrayList<WebElement>();
System.out.println("active link=="+activelink.size());

for(int i=0;i<linklist.size();i++){
WebElement	ele=linklist.get(i);
String  url=ele.getAttribute("href");
System.out.println(linklist.get(i).getAttribute("href"));
	if(linklist.get(i).getAttribute("href") !=null && (! linklist.get(i).getAttribute("href").contains("javascript"))){
		activelink.add(linklist.get(i));
	}
	

	verifylinkactive(url);
	
}

driver.close();
}
public static void verifylinkactive(String url){
	try {
		HttpURLConnection connection=(HttpURLConnection)new URL(url).openConnection();
		connection.setConnectTimeout(2000);
		connection.connect();
		if(connection.getResponseCode() ==  200){
			System.out.println(connection+"---"+connection.getResponseMessage());
		}
		if(connection.getResponseCode()==connection.HTTP_NOT_FOUND){
			System.out.println(connection.getResponseMessage()+"----"+connection.HTTP_NOT_FOUND);
		}
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
 
}
}
