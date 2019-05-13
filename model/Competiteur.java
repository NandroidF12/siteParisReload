package siteParis.model;

public class Competiteur extends Personne {
	
	//paris de competiteur
	private Pari pari;
	 
	public Competiteur(String nom, String motDePasse, String pseudo) {
		super(nom, motDePasse, pseudo);
		// TODO Auto-generated constructor stub
	}

	//getter of the Bet.
	public Pari getPari() {
		return pari;
	}

	//setter of the Bet
	public void setPari(Pari pari) {
		this.pari = pari;
	}
	
}
