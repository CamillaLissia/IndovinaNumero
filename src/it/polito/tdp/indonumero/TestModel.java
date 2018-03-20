package it.polito.tdp.indonumero;

public class TestModel {

	public static void main(String[] args) {
		
		Model model= new Model();
		model.newGame();
		
		int min=1;
		int max=model.getNMAX();
		
		//la gioco in modo stupido
		
		/*while(model.isInGame()) {
			int ris=model.tentativo(7);
			System.out.println(ris);
		}
		*/
		
		while(model.isInGame()) {
			int t= (min+max)/2;
			System.out.format("tra %d e %d provo %d\n", min, max, t);
			int ris=model.tentativo(t);
			if(ris>0)
				max=t-1;
			else
				min=t+1;
			System.out.println(ris);
		
		}

	}

}
