package benjaminrobert.model;

import java.io.File;

import javax.swing.ImageIcon;

public abstract class Player {

	protected String role;
	protected String name;
	protected int playerID = 0;
	protected ImageIcon image;
	
	private static int ID ;
	protected static  File file;
	
	static {
		file = new File("./src/Minor Piece of Coursework 3 Resources/squad");
		ID = 0;
	}
	
	public Player(String name){
		role = null;
		this.name = name;
		playerID = ++ID;
	}
	
	public static String[] getImagesName(){
		String[] jpgName = file.list();
		if(file != null && jpgName != null){
			String ext = ".jpg";
			int i = 0;
			String[] names = new String[jpgName.length];
			for(String name: jpgName){
				if(name.contains(ext)){
					name = name.replaceAll(ext, "");
					names[i++] = name; 
				}
			}
			return names;
		}
		return null;
	}
	
	public String getPlayerName(){
		return name;
	}
	
	public int getPlayerID(){
		return playerID;
	}
	
	public String getPlayerRole(){
		return role;
	}
	
	public String toString(){
		role = "";
		if(this instanceof Goalkeeper){
			role = "Goalkeeper";
		}else if(this instanceof Defender){
			role = "Defender";
		}else if(this instanceof Midfielder){
			role = "Midfielder";
		}else if(this instanceof Striker){
			role = "Striker";
		}
		return role + " " + name + " ID: " + playerID;
	}
}
