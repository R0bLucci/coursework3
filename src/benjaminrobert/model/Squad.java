package benjaminrobert.model;
/**
 * 
 * @author Benjamin and Robert
 * 
 * The Squad is the only class that instantiate players
 * 
 */
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
	 * Constructor to the squad class 
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
	
	/**
	 * Method to initialise:
	 * 2 Goal Keepers
	 * 5 Defenders
	 * 5 Mid fielders
	 * 3 Strikers
	 */
	public void initSquad(){
		for(int i = 0; i < players.length; i++){
			if(i < 2){
				Goalkeeper goalkeeper = new Goalkeeper();
				addPlayer(goalkeeper);
			}else if(i < 7){
				Defender defender = new Defender();
				addPlayer(defender);
			}else if(i < 12){
				Midfielder midfielder = new Midfielder();
				addPlayer(midfielder);
			}else{
				Striker striker = new Striker();
				addPlayer(striker);
			}
		}
	}
	
	
	// add player to the squad
	private void addPlayer(Player player){
		if(index < 15){
			players[index++] = player;
		}
	}
	
	/**
	 * 
	 * @return
	 * 
	 * return the squad 
	 */
	public Player[] getSquad(){
		return players;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * 
	 * Search player by its ID
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
	 * 
	 * @param qty
	 * 
	 *  set number of defenders 
	 */
	public void setNumberOfDefenders(int qty){
		qty = qty /100;
		if(qty > 0 && qty <= MAX_DEFENDERS){
			formationDefenders = qty;
		}
	}
	
	/**
	 * 
	 * @return
	 * 
	 * get the number of defenders based of the current formation 
	 */
	public int getNumberOfDefenders(){
		return formationDefenders;
	}
	
	/**
	 * 
	 * @param qty
	 * 
	 * set number of mid fielders
	 */
	public void setNumberOfMidfielders(int qty){
		qty = Integer.parseInt(String.valueOf(qty).substring(1,2));
		if(qty > 0 && qty <= MAX_MIDFIELDERS){
			formationMidfielders = qty;
		}
	}
	
	/**
	 * 
	 * @return
	 * 
	 *  get number of mid fielders based of current formation
	 */
	public int getNumberOfMidfielders(){
		return formationMidfielders;
	}
	
	
	/**
	 * 
	 * @param qty
	 * 
	 * set number of mid striker
	 */
	public void setNumberOfStrikers(int qty){
		qty = Integer.parseInt(String.valueOf(qty).substring(2));
		if(qty > 0 && qty <= MAX_STRIKERS){
			formationStrikers = qty;
		}
	}
	
	/**
	 * 
	 * @return
	 * 
	 * get the number of strikers based of current formation 
	 */
	public int getNumberOfStrikers(){
		return formationStrikers;
	}
}
