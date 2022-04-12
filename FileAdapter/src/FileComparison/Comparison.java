package FileComparison;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Comparison {

	public static void main(String[] args) throws IOException {
		
		// ���ϴٰ� log �м��ϴµ� 20����¥�� File List �� �ϳ��ϳ� ���Ҽ� ��� ������� (���� ����� �ڵ�� ȸ��PC��...)
		
		BufferedReader read1  = new BufferedReader(new FileReader("D:\\File_home/text1.txt"));
		
		BufferedReader read2  = new BufferedReader(new FileReader("D:\\File_home/text2.txt"));
		
		List lineList = new ArrayList();
		
		String fsFile;
		String s3File;
		while ( (fsFile = read1.readLine()) != null ) {
			
			lineList.add(fsFile);
			
			
		}
		
		while ( (s3File = read2.readLine()) != null ) {
			
			if ( lineList.contains(s3File) ) {
				System.out.println("=== SUCC : " + s3File + "===" );
			}else {
				System.out.println(" FAIL : " + s3File );
				
			}
			
		}
		
		System.out.println("===== END FILE LIST =====");
		
		
		
		
		
		

	}

}
