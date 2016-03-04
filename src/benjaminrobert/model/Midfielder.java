package benjaminrobert.model;

/**
 * @author benjaminrobert
 * Midfielder extends the Player class. 
 * Super calls the parent class of player.
 * Name called Midfielder.
 * The role is equals to the name.
 */

public class Midfielder extends Player {

	public Midfielder(){
		super();
		name = "Midfielder";
		role = name;
	}
}
