package siteParis.model;

public class Competiteur extends Personne {
	
	//paris de competiteur
	private Pari pari;
	 
	public Competiteur(String nom, String mdp, String pseudo) {
		super(nom, mdp, pseudo);
		// TODO Auto-generated constructor stub
	}

	//retourner les Paris
	public Pari getPari() {
		return pari;
	}

	//changer le Paris
	public void setPari(Pari pari) {
		this.pari = pari;
	}
	
}
