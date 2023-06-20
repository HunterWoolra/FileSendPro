package myproject.file.adapter.configuration;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ConfigLoader {
	   public static String s3_1 = null;
	public static String s3_2 = null;
	public static String s3_3 = null;
	public static String s3_4 = null;

	public static String system1 = null;
	public static String system2 = null;
	public static String system3 = null;
	public static String system4 = null;

	public static String constant1 = null;
	public static String constant2 = null;

	public static String kafka1 = null;
	public static String kafka2 = null;

	   
	   public static String Conffile() throws IOException{
		  // 임의 경로 
	      Path filePath = Paths.get("C:\\Users\\PIN\\IdeaProjects\\FileMain\\src\\main\\java\\Configuration.cfg");
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
		  system4 = cfe.get("data.test.api.System.home");
	      
	      constant1 = cfe.get("data.test.api.Constant.hdfspath");
	      constant2 = cfe.get("data.test.api.Constant.localpath");

		  kafka1 = cfe.get("data.test.api.kafka.topic");
		  kafka2 = cfe.get("data.test.api.kafka.host");
	         
	      return constant1;
	   }


}
