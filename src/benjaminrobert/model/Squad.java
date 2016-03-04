package benjaminrobert.model;

public class Squad {

	private Player[] players;
	private int index;
	private final int MAX_GOALKEEPERS;
	private final int MAX_DEFENDERS;
	private final int MAX_MIDFIELDERS;
	private final int MAX_STRIKERS;
	private int formationDefenders;
	private int formationMidfielders;
	private int formationStrikers;
	
	/**
	 * Constructor
	 * @author benjaminrobert
	 * Creates a new instance of player array of 15.
	 * Sets the index to 0.
	 * Sets the MAX_GOALKEEPER to 2.
	 * Sets the MAX_DEFENDER to 5.
	 * Sets the MAX_MEDFIELDERS to 5
	 * Sets the MAX_STRIKER to 3
	 * 
	 * Sets the formationDefenders to 0.
	 * Sets the formationMidfielders to 0.
	 * Sets the formationStrikers to 0.
	 */
	public Squad(){
		players = new Player[15];
		index = 0;
		MAX_GOALKEEPERS = 2;
		MAX_DEFENDERS = 5;
		MAX_MIDFIELDERS = 5;
		MAX_STRIKERS = 3;
		
		formationDefenders = 0;
		formationMidfielders = 0;
		formationStrikers = 0;
		
	}
	

	public void initSquad(){
			Goalkeeper gk1 = new Goalkeeper();
			Goalkeeper gk2 = new Goalkeeper();
			Defender d1 = new Defender();
			Defender d2 = new Defender();
			Defender d3 = new Defender();
			Defender d4 = new Defender();
			Defender d5 = new Defender();
			Midfielder mf1 = new Midfielder();
			Midfielder mf2 = new Midfielder();
			Midfielder mf3 = new Midfielder();
			Midfielder mf4 = new Midfielder();
			Midfielder mf5 = new Midfielder();
			Striker s1 = new Striker();
			Striker s2 = new Striker();
			Striker s3 = new Striker();
			addPlayer(gk1);
			addPlayer(gk2);
			addPlayer(d1);
			addPlayer(d2);
			addPlayer(d3);
			addPlayer(d4);
			addPlayer(d5);
			addPlayer(mf1);
			addPlayer(mf2);
			addPlayer(mf3);
			addPlayer(mf4);
			addPlayer(mf5);
			addPlayer(s1);
			addPlayer(s2);
			addPlayer(s3);
	}
	
	
	/**
	 * @author benjaminrobert
	 * Adds players. 
	 * If statement used to to create a new player until get to 15 players in a squad.
	 * @param player  the first paramter to setNumberOfMidfielders method.
	 * 
	 */
	public void addPlayer(Player player){
		if(index < 15){
			players[index++] = player;
		}
	}
	
	/**
	 * @author benjaminrobert
	 * Array of players and the method called getSquad.. 
	 * 
	 * @return players.
	 */
	public Player[] getSquad(){
		return players;
	}
	
	
	/**
	 * @author benjaminrobert
	 * Search for players by ID. 
	 * @param id  the first paramter to searchPlayerByID method.
	 * @return an array of players.
	 * @return null when the if statement is false.
	 */
	public Player searchPlayerByID(int id){
		for(int i = 0; i < players.length; i++){
			if(players[i].getPlayerID() == id){
				return players[i];
			}
		}
		return null;
	}
	
	/**
	 * @author benjaminrobert
	 * Sets number of defenders. 
	 * @param qty  the first paramter to setNumberOfMidfielders method.
	 * 
	 */
	public void setNumberOfDefenders(int qty){
		qty = qty /100;
		if(qty > 0 && qty <= MAX_DEFENDERS){
			formationDefenders = qty;
		}
	}
	
	/**
	 * @author benjaminrobert
	 * Gets the number of Defenders. 
	 * @param none.
	 * @return the formationDefenders.
	 */
	public int getNumberOfDefenders(){
		return formationDefenders;
	}
	
	
	/**
	 * @author benjaminrobert
	 * Sets number of midfields. 
	 * @param qty  the first paramter to setNumberOfMidfielders method.
	 * 
	 */
	public void setNumberOfMidfielders(int qty){
		qty = Integer.parseInt(String.valueOf(qty).substring(1,2));
		if(qty > 0 && qty <= MAX_MIDFIELDERS){
			formationMidfielders = qty;
		}
	}
	
	/**
	 * @author benjaminrobert
	 * Gets the number of midfielders. 
	 * @param none.
	 * @return the formationMidfielders.
	 */
	public int getNumberOfMidfielders(){
		return formationMidfielders;
	}
	
	/**
	 * @author benjaminrobert
	 * This method sets number of Strikers. 
	 * @param qty  the first paramter to setNumberOfStrikers method.
	 * 
	 */
	public void setNumberOfStrikers(int qty){
		qty = Integer.parseInt(String.valueOf(qty).substring(2));
		if(qty > 0 && qty <= MAX_STRIKERS){
			formationStrikers = qty;
		}
	}
	
	
	/**
	 * @author benjaminrobert
	 * This method gets the number of Strikers. 
	 * @param none.
	 * @return the formationStrikers.
	 */
	public int getNumberOfStrikers(){
		return formationStrikers;
	}
}
