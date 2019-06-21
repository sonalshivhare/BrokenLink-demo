package com.brokenlink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

public class handle_links {
public static void main(String[] args) throws MalformedURLException, IOException {
	//System.setProperty("Webdriver.chrome.driver", "G:\\selenium workspace 1\\BrokenLink\\driver\\chromedriver.exe");
WebDriver driver=new FirefoxDriver();
driver.manage().window().maximize();
driver.manage().deleteAllCookies();
driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
driver.get("https://www.javabykiran.com");

 List<WebElement> linklist=driver.findElements(By.tagName("a"));
 linklist.addAll(driver.findElements(By.tagName("img")));
 
 List<WebElement>  activelink =new ArrayList<WebElement>();
 System.out.println("size of full link and images=="+activelink.size());
 for(int i=0;i<linklist.size();i++){
	// System.out.println(linklist.get(i).getAttribute("href"));
	 if(linklist.get(i).getAttribute("href")!= null &&(! linklist.get(i).getAttribute("href").contains("javascript"))){
		 activelink.add(linklist.get(i));
	 }
 }
 System.out.println("size of active link and images=="+activelink.size());
 
 for(int j=0;j<activelink.size();j++){
HttpURLConnection	 connection=(HttpURLConnection)new URL(activelink.get(j).getAttribute("href")).openConnection();
connection.connect();
 String response=connection.getResponseMessage();
 System.out.println(activelink.get(j).getAttribute("href")+"--->"+response);
 }
}
}
