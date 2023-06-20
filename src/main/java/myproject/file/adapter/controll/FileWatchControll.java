package myproject.file.adapter.controll;

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

import myproject.file.adapter.configuration.ConfigLoader;


public class FileWatchControll {
	private String projectPath = null;
	private WatchKey watchkey;
	boolean watchFlag = true;
	public Path paths = null;
	public File files = null;
	public void init() throws IOException {

		ConfigLoader cfl = new ConfigLoader();
		try {cfl.Conffile();} catch (IOException e) {e.printStackTrace();}
		projectPath = cfl.system4;

		//watchservice 생성
		WatchService watchService = FileSystems.getDefault().newWatchService();
		//Path
		Path path = Paths.get(projectPath);
		System.out.println("===== File Home : " + path + "=====" );
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

					//Path
					paths = (Path)event.context();

					files = new File( projectPath + paths.toString());

					System.out.println("===== File Absolute Path : " + files.getAbsolutePath() + "=====");
					if ( kind.equals(StandardWatchEventKinds.ENTRY_CREATE) ) {
						System.out.println("create Directory : " +  files.getAbsolutePath());
					}else if( kind.equals(StandardWatchEventKinds.ENTRY_DELETE )) {
						System.out.println("delete Directory : ");
					}else if( kind.equals(StandardWatchEventKinds.ENTRY_MODIFY )) {
						System.out.println("modify Directory : " +  files.getAbsolutePath());
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
		try{
			System.out.println("===== output : " + files.getAbsolutePath() + "=====");
			return  files.getAbsolutePath();
		}catch (NullPointerException e) {
			System.out.println("NullPointerException...watch");
		}
		return null;
	}

}
