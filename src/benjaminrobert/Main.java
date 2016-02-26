package benjaminrobert;
import benjaminrobert.model.Defender;
import benjaminrobert.model.Goalkeeper;
import benjaminrobert.model.Midfielder;
import benjaminrobert.model.Player;


public class Main {

	public static void main(String[] args){
		
		Defender d = new Defender("Paul");
		Goalkeeper goalkeeper = new Goalkeeper("Fiil");
		Midfielder midfielder = new Midfielder("ok");
		
		System.out.println(d);
		System.out.println(goalkeeper);
		System.out.println(midfielder);
		
		if(Player.getImagesName() != null){
			for(String s : Player.getImagesName()){
				System.out.println(s);
			}
		}
		

	}
}
