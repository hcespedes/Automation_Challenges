import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testSupport.FileManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by heidy.cespedes on 6/22/2016.
 */
public class PageTitle {

   WebDriver driver;
   private Properties props = new Properties();
   private String filePath;

   @BeforeTest
   public void setUpSelenium() {
      driver = new FirefoxDriver();
      try {
         props.load(new FileInputStream("c://Users/heidy.cespedes/IdeaProjects/Challenge_1/src/main/resources/parameters.properties"));
         filePath = props.getProperty("txtFile");
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   //Obtain the url and page title from a txt file and pass them as parameters to the test
   @DataProvider (name = "getTestData")
   public Object [][] getTestData() {
      FileManager fileManager = new FileManager();
      return fileManager.getData(filePath);
   }

   @Test (dataProvider = "getTestData")
   public void verifyTitlePage(String url, String title) {
      String current_title = "";
      driver.get(url);
      current_title = driver.getTitle();
      Assert.assertTrue(title.equals(current_title),"The Page Title is what expected");
   }

   @AfterTest
   public void tearUp() {
      driver.close();
   }

}
