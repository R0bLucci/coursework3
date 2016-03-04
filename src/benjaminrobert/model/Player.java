package benjaminrobert.model;

import java.io.File;

import javax.swing.ImageIcon;

public abstract class Player {

	protected String role;
	protected String name;
	protected String imagePath;
	protected int playerID = 0;
	protected ImageIcon image;
	
	private static int ID ;
	protected File file;
	
	static {
		ID = 0;
	}
	
	/**
	 * Constructor - Player.
	 * @author benjaminrobert
	 *  sets this name to null.
	 *  Sets image path to null.
	 *  Sets playerID to ++ID. The ID will be incremented.
	 *  Creates a new file with the location ./src/Minor Piece of Coursework 3 Resources/squad
	 * 
	 */
	public Player(){
		this.name = null;
		imagePath = null;
		playerID = ++ID;
		file = new File("./src/Minor Piece of Coursework 3 Resources/squad");
	}
	
	
	/**
	 * @author benjaminrobert
	 *  This gets the image names.
	 * @return names.
	 * @return null
	 * 
	 */
	public String[] getImagesName(){
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
	 * @author benjaminrobert
	 *  This gets the players name.
	 * @return name.
	 * 
	 */
	public String getPlayerName(){
		return name;
	}
	
	/**
	 * @author benjaminrobert
	 *  This gets the players ID.
	 * @return playerID
	 * 
	 */
	public int getPlayerID(){
		return playerID;
	}
	
	/**
	 * @author benjaminrobert
	 *  This gets the imagePath.
	 * @return imagePath
	 * 
	 */
	public String getImagePath(){
		return imagePath;
	}
	
	
	/**
	 * @author benjaminrobert
	 * Sets the path of the image.. 
	 * @param imagePath  the first paramter to setPath method.
	 * 
	 */
	public void setPath(String imagePath){
		this.imagePath = imagePath;
	}
	
	public void setName(String playerName){
		name += " " + playerName;
	}
	
	/**
	 * @author benjaminrobert
	 *  This gets the players roles.
	 * @return role
	 * 
	 */
	public String getPlayerRole(){
		return role;
	}
	
	/**
	 * @author benjaminrobert
	 *  
	 * @return the name and the playerID
	 * 
	 */
	public String toString(){
		return name + " ID: " + playerID;
	}
}
