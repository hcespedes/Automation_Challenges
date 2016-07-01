import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by heidy.cespedes on 6/30/2016.
 */
public class MainMenu {
   WebDriver driver;
   private Properties props = new Properties();
   private String filePath;

   @BeforeTest
   public void setUpSelenium() {
      driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      driver.get("https://www.skiutah.com");
   }

   @Test
   public void challenge_2() {
      List<WebElement> menu = new ArrayList<WebElement>();
      // Find out how many elements there are in the main menu
      menu = driver.findElements(By.xpath("//ul[@class='SuperfishMegaMenu sf-js-enabled sf-arrows']//li//a[contains(@class,'SuperfishMegaMenu-topLink')]"));
      int size = menu.size();

      // It will store the title of each element in the menu
      String [] titles = new String[size];

      for (int i = 0; i < size; i++) {
         // The element needs to be found one by one because everytime the page refreshes, the element is removed from the DOM
         WebElement element = driver.findElement(By.xpath("//ul[@class='SuperfishMegaMenu sf-js-enabled sf-arrows']//li[" + (i + 1) + "]//a[contains(@class,'SuperfishMegaMenu-topLink')]"));
         titles[i] = element.getAttribute("title");
         Assert.assertTrue(goToPage(element), titles[i] + " has not been launched correctly");
         driver.navigate().back();
      }
   }

   public boolean goToPage(WebElement element){
      element.click();
      if (driver.getTitle().contains("- Ski Utah")) {
         return true;
      } else {
         return false;
      }
   }

   @AfterTest
   public void tearUp() {
      driver.close();
   }
}
