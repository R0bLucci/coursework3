package benjaminrobert.model;


/**
 * @author benjaminrobert
 * Striker extends the Player class. 
 * Super calls the parent class of player. 
 * Name called Striker.
 * The role is equals to the name.
 */
public class Striker extends Player {

	public Striker(){
		super();
		name = "Striker";
		role = name;
	}
}
