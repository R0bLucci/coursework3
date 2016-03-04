package benjaminrobert.model;

/**
 * @author benjaminrobert
 * Goalkeeper extends the Player class. 
 * Super calls the parent class of player. 
 * Name called Goalkeeper.
 * The role is equals to the name.
 */

public class Goalkeeper extends Player {

	public Goalkeeper(){
		super();
		name = "Goalkeeper";
		role = name;
	}
}
