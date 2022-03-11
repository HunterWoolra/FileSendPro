package FileAdapter;

import java.io.IOException;
import java.text.ParseException;

import NewFileGenerate.FileGenerate;

public class AdapterMain {

	public static void main(String[] args) throws IOException, ParseException, InterruptedException {
		// TODO Auto-generated method stub
		
		FileWatchControll filewatch = new FileWatchControll();
		filewatch.init();
		filewatch.output();
		
		FileGenerate filecreate = new FileGenerate();
		filecreate.createFile();

	}

}
