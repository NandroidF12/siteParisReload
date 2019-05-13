package siteParis.model;

public class Compte {
	
	private Joueur joueur;

	// Constructeur pour la compte du Joueur
	public Compte(Joueur joueur){
		this.joueur = joueur;
	}

	// Getter du Joueur.
	public Joueur getJoueur(){
		return joueur;
	}

	// Setter du Joueur.
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
