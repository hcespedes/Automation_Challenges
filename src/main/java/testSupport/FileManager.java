package testSupport;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;

/**
 * Created by heidy.cespedes on 6/22/2016.
 */
public class FileManager {
   private Object[][] data = new Object[][]{new String[]{"", ""}};

   public Object[][] getData (String fileName) {

      FileReader fileReader = null;
      try {
         fileReader = new FileReader(fileName);
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      }
      String line = "";
      String [] parts;
      int i = 0;
      int j = 0;
      BufferedReader bufferReader = new BufferedReader(fileReader);

      try {
         while ((line = bufferReader.readLine()) != null) {
            parts = line.split(";");
            data [i][j++] = parts[0];
            data [i++][j] = parts[1];
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

      return data;
   }
}
