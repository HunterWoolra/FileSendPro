package FileAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class FileWatchControll {
	
	//private static final String projectPath = System.getProperty("D:\\File_home");
	private static final String projectPath = "D:\\File_home";
	
	private WatchKey watchkey;
	
	boolean watchFlag = true;
	
	public void init() throws IOException {
		
		//watchservice 생성
		WatchService watchService = FileSystems.getDefault().newWatchService();
		//경로
		Path path = Paths.get(projectPath);
		//생성 삭제 시간 이벤트 
		path.register(watchService,StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.OVERFLOW);
		
		Thread thread = new Thread(()-> {
			while ( watchFlag ) {
				try {
					watchkey = watchService.take();
				}catch (InterruptedException e){
					e.printStackTrace();
				}
				//List 생성해서 이벤트가 배열이면 events
				List<WatchEvent<?>> events = watchkey.pollEvents();
				for ( WatchEvent<?> event : events ) {
					Kind<?> kind = event.kind();
					
					//경로
					Path paths = (Path)event.context();
					
					System.out.println(paths.toAbsolutePath());
					if ( kind.equals(StandardWatchEventKinds.ENTRY_CREATE) ) {
						System.out.println("create Directory : ");
					}else if( kind.equals(StandardWatchEventKinds.ENTRY_DELETE )) {
						System.out.println("delete Directory : ");
					}else if( kind.equals(StandardWatchEventKinds.ENTRY_MODIFY )) {
						System.out.println("create Directory : ");
					}else {
						System.out.println("");
					}
					
				}
				if (!watchkey.reset()) {
					try {
						watchService.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		thread.start();
		
	}
	public String output() {
		System.out.println(projectPath);
		return "파일감지!!";
	}
	
	
	

}
