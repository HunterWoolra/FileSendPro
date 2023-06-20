package myproject.file.adapter.controll;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import myproject.file.adapter.kafka.FileToBy;
import myproject.file.adapter.kafka.Producer;

public class AdapterMain {
	private static boolean isEmpty = false;

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		FileWatchControll filewatch = new FileWatchControll();
		filewatch.init();

		while (true) {
			try {
				if (filewatch.output() != null) {
					isEmpty = true;
					System.out.println("파일 감지");
				}
			}catch(NullPointerException e) {
				System.out.println("NullPointerException ...main");
			}
			if (isEmpty){
				System.out.println("===== isEmpty : true =====");
				String strFileName = filewatch.output();

				FileToBy fy = new FileToBy();
				Producer prod = new Producer();

				File file = new File(strFileName);
				fy.fileToBinary(file);
				System.out.println(fy.fileToBinary(file));
				prod.kafkaProd(fy.fileToBinary(file));
			}
			Thread.sleep(10000);
		}


//		FileGenerate filecreate = new FileGenerate();
//		filecreate.createFile();
	}

}
