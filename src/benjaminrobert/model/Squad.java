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
	
	private void addPlayer(Player player){
		if(index < 15){
			players[index++] = player;
		}
	}
	
	
	public Player[] getSquad(){
		return players;
	}
	
	public Player searchPlayerByID(int id){
		for(int i = 0; i < players.length; i++){
			if(players[i].getPlayerID() == id){
				return players[i];
			}
		}
		return null;
	}
	
	public void setNumberOfDefenders(int qty){
		qty = qty /100;
		if(qty > 0 && qty <= MAX_DEFENDERS){
			formationDefenders = qty;
		}
	}
	
	public int getNumberOfDefenders(){
		return formationDefenders;
	}
	
	public void setNumberOfMidfielders(int qty){
		qty = Integer.parseInt(String.valueOf(qty).substring(1,2));
		if(qty > 0 && qty <= MAX_MIDFIELDERS){
			formationMidfielders = qty;
		}
	}
	
	public int getNumberOfMidfielders(){
		return formationMidfielders;
	}
	
	public void setNumberOfStrikers(int qty){
		qty = Integer.parseInt(String.valueOf(qty).substring(2));
		if(qty > 0 && qty <= MAX_STRIKERS){
			formationStrikers = qty;
		}
	}
	
	public int getNumberOfStrikers(){
		return formationStrikers;
	}
}
