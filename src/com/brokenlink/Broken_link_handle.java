package com.brokenlink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Broken_link_handle {
public static void main(String[] args) throws MalformedURLException, IOException {
	WebDriver driver=new FirefoxDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.get("https://classic.crmpro.com/index.html?e=2");
	
	driver.findElement(By.xpath("//input[contains(@name,'username')]")).sendKeys("rvgawande");

	driver.findElement(By.xpath("//input[contains(@name,'password')]")).sendKeys("tiger123");
	driver.findElement(By.xpath("//input[@value='Login']")).click();
	
	driver.switchTo().frame("mainpanel");
	
List<WebElement> link=driver.findElements(By.tagName("a"));
link.addAll(driver.findElements(By.tagName("img")));
System.out.println("size of full link and image== "+link.size());

List<WebElement> activelink=new ArrayList<>();
for(int i=0;i<=link.size();i++){
//System.out.println(link.get(i).getAttribute("href"));

	if(link.get(i).getAttribute("href")!= null && (! link.get(i).getAttribute("href").contains("javascript"))){
		
		activelink.add(link.get(i));
	}
}
System.out.println("size of active link and image"+activelink.size());


for(int j=0;j<=activelink.size();j++){
HttpURLConnection   connection= (HttpURLConnection) new URL(activelink.get(j).getAttribute("href")).openConnection();
connection.setConnectTimeout(4000);
connection.connect();
 String  response=connection.getResponseMessage();
 //connection.disconnect();
 System.out.println(activelink.get(j).getAttribute("href")+"--->"+response);


}









}
}
