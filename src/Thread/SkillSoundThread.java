package Thread;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;
import main.Main;

public class SkillSoundThread extends Thread{

	Player player;
	boolean loop;
	File file;
	FileInputStream fis;
	BufferedInputStream bis;
	
	public SkillSoundThread(String name, boolean loop) {
		try {
			this.loop=loop;
			file=new File(Main.class.getResource("../Sound/" + name).toURI());
			fis=new FileInputStream(file);
			bis=new BufferedInputStream(fis);
			player=new Player(bis);  
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void finish() {
		loop=false;
		player.close();
		this.interrupt();
	}

	@Override
	public void run() {
		try {
			do {
				player.play();
				fis=new FileInputStream(file);
				bis=new BufferedInputStream(fis);
				player=new Player(bis);   
			}while(loop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
