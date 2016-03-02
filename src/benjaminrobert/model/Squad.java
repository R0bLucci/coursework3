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
	
	public void addPlayer(Player player){
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
