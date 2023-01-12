package Configuration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ConfigLoader {
	  static String s3_1 = null;
	   static String s3_2 = null;
	   static String s3_3 = null;
	   static String s3_4 = null;
	   
	   static String system1 = null;
	   static String system2 = null;
	   static String system3 = null;
	   
	   static String constant1 = null;
	   static String constant2 = null;
	   
	   public static String Conffile() throws IOException{
		  // 임의 경로 
	      Path filePath = Paths.get("E:/Config/configuration.cfg");
	      Charset charset = Charset.forName("UTF-8");
	      
	      ConfigElement cfe = new ConfigElement(3);
	      try {
	         List<String> lines = Files.readAllLines(filePath,charset);
	         for (String strArr : lines) {
	            if (!strArr.contains("#")) {
	               String[] strSplit = strArr.split("=",-1);
	               cfe.put(strSplit[0].trim(), strSplit[strSplit.length-1].trim());
	            }
	         }
	      }catch ( IOException e ) {
	         e.printStackTrace();
	      }
	      
	      s3_1 = cfe.get("data.test.api.s3.accesskey");
	      s3_2 = cfe.get("data.test.api.s3.secretkey");
	      s3_3 = cfe.get("data.test.api.s3.url");
	      s3_4 = cfe.get("data.test.api.s3.home");
	      
	      system1 = cfe.get("data.test.api.System.mapper");
	      system2 = cfe.get("data.test.api.System.cpu");
	      system3 = cfe.get("data.test.api.System.memory");
	      
	      constant1 = cfe.get("data.test.api.Constant.hdfspath");
	      constant2 = cfe.get("data.test.api.Constant.localpath");
	         
	      return constant1;
	   }


}
