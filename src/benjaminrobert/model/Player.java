package benjaminrobert.model;

import java.io.File;

import javax.swing.ImageIcon;

/**
 * 
 * @author Benjamin and Robert
 *  Parent Abstract class for a Player
 *  
 */
public abstract class Player {

	protected String role; // class role 
	protected String name; // Player name
	protected String imagePath; // Image path 
	protected int playerID; // unique player ID 
	final static protected String defaultImagePath; // Default value for the image path
	
	private static int ID; // static field to make sure that each player has an unique id 
	protected static File file; //
	
	//initialise the static fields
	static {
		ID = 0;
		file = new File("./src/Minor Piece of Coursework 3 Resources/squad");
		defaultImagePath = "None";
	}
	
	/**
	 *  Player constructor 
	 * 
	 */
	public Player(){
		this.name = null;
		imagePath = defaultImagePath;
		playerID = ++ID;
	}
	
	/**
	 * 
	 * @return
	 * 
	 * return names inside the resource folders without the file extension
	 */
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
	/**
	 * 
	 * @return
	 * 
	 * player name 
	 */
	public String getPlayerName(){
		return name;
	}
	
	/**
	 * 
	 * @return
	 * 
	 * player ID 
	 */
	public int getPlayerID(){
		return playerID;
	}
	
	/**
	 * 
	 * @return
	 * 
	 *  absolute image path
	 */
	public String getImagePath(){
		return imagePath;
	}
	
	/**
	 * 
	 * @param imagePath
	 * 
	 * the path to the player image 
	 */
	public void setPath(String imagePath){
		if(!this.imagePath.equals(imagePath)){
			this.imagePath = imagePath;
		}
	}
	
	/**
	 * 
	 * @param playerName
	 * 
	 * set the name for the player 
	 */
	public void setName(String playerName){
		if(!name.contains(playerName)){
			name += " " + playerName;
		}
	}
	
	/**
	 *  reset the name to its position
	 */
	public void resetName(){
		name = role;
	}
	
	/**
	 *  reset the path to "None"
	 */
	public void resetImagePath(){
		imagePath = defaultImagePath;
	}
	
	/**
	 * 
	 * @return
	 *  the position of the player
	 */
	public String getPlayerRole(){
		return role;
	}
	
	/**
	 * String representation of the player object 
	 */
	public String toString(){
		return name + " ID: " + playerID;
	}
	
	/**
	 * 
	 * @return
	 * 
	 * Static method that returns the default value for the image path "None"
	 */
	public static String getDefaultImagePathValue(){
		return defaultImagePath;
	}
}
