package benjaminrobert.model;

/**
 * @author benjaminrobert
 * Defender extends the Player class. 
 * Super calls the parent class of player. 
 * Name called Defender.
 * The role is equals to the name.
 */

public class Defender extends Player {

	public Defender(){
		super();
		name = "Defender";
		role = name;
	}
	
}