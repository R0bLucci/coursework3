package benjaminrobert.model;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * 
 * @author Robert
 * Abstract class for a Player
 */
public abstract class Player {

	protected String role;
	protected String name;
	protected String imagePath;
	protected int playerID;
	protected ImageIcon image;

	private static int ID;
	protected static File file;
	
	static {
		ID = 0;
		file = new File("./src/Minor Piece of Coursework 3 Resources/squad");
	}
	
	public Player(){
		this.name = null;
		imagePath = "None";
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
	
	public String getImagePath(){
		return imagePath;
	}
	
	public void setPath(String imagePath){
		if(!this.imagePath.equals(imagePath)){
			this.imagePath = imagePath;
		}
	}
	
	public void setName(String playerName){
		if(!name.contains(playerName)){
			name += " " + playerName;
		}
	}
	
	public void resetName(){
		name = role;
	}
	
	
	public void resetImagePath(){
		imagePath = "None";
	}
	
	public String getPlayerRole(){
		return role;
	}
	
	public String toString(){
		return name + " ID: " + playerID;
	}
}
