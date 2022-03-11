package NewFileGenerate;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import FileAdapter.FileWatchControll;

import java.io.File;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.Calendar;

public class FileGenerate {
	
	public void createFile() throws ParseException, IOException, InterruptedException {

		boolean flag = true; 
		while(flag) {
			SimpleDateFormat  simpleDate1 = new SimpleDateFormat("yyyyMMddHHmm");
			Date time = new Date();
			SimpleDateFormat  simpleDate2 = new SimpleDateFormat("yyyyMMddHH");
			Date time2 = new Date();
			
			String today = simpleDate1.format(time);
			String today2 = simpleDate2.format(time2);
			Date selectDate = simpleDate1.parse(today);
			Date selectDate2 = simpleDate2.parse(today2);
			GregorianCalendar cal = new GregorianCalendar(Locale.KOREA);
			GregorianCalendar cal2 = new GregorianCalendar(Locale.KOREA);
			cal.setTime(selectDate);
			cal2.setTime(selectDate2);
			today = simpleDate1.format(cal.getTime());
			today2 = simpleDate2.format(cal2.getTime());
			
			
			System.out.println(today);
			
			String FileName = "NewFile_" + today + ".txt" ;
			File DateFile = new File("D:\\File_home\\"+ today2);
			//�ش� Directory �� ������� ����
			if (!DateFile.exists()) {
				try {
					DateFile.mkdir();
					System.out.println( DateFile + "�� �����Ǿ����ϴ�.");
				}
				catch (Exception e) {
					e.getStackTrace();
				}
			}else {
				System.out.println( DateFile + "�� �����մϴ�...");
			}
			
			File FilePath = new File( DateFile +"\\"+ FileName);
			System.out.println(FilePath);
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(FilePath, false));
			
			String txt = "aaaa";
			writer.write(txt);
 			writer.flush();
 			writer.close();
			
 		//	Thread.sleep(1000 * 60);
 			Thread.sleep(1000);
 			
 			FileDelete fd = new FileDelete();
 			
 			File[] list = fd.path.listFiles();
 			
 			
 			for(int i = 0;i<list.length;i++)
 			{
 				fd.fileDate = new Date(list[i].lastModified());
 				System.out.println(fd.fileDate);
 				
 				fd.fileCal.setTime(fd.fileDate);
 				System.out.println(fd.fileDate);
 				long delMil = fd.todayMil - fd.fileCal.getTimeInMillis();
 				
 				int delDay = (int) (delMil / fd.oneDayMil);
 				
 				if (delDay > 1 && list[i].exists()) {
 					list[i].delete();
 					System.out.println(list[i].getName() + ": ������ ���� �߽��ϴ�.");
 				}
 				
 			}
 			 
 			
		}
	}

		
//		if ( flag = false ) break;
		
		
		//1�ð����� ������ �����Ѵ�.... 1�ð����� ���� �������� �����ϰ� 10�и��� ���� �������� ���濹��.... File ������ Ư������� text�� �о ����ϵ����Ѵ�
		
	
}//end class

class FileDelete {
	Calendar calen = Calendar.getInstance();
	
	long todayMil = calen.getTimeInMillis();
	long oneDayMil = 24 * 60 * 60 * 1000;
	
	Calendar fileCal = Calendar.getInstance();
	Date fileDate = null;
	
	File path = new File("D:\\File_home\\");
	
	
	
}//end class
