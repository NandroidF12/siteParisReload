package siteParis.model;

public class Compte {
	
	private Joueur joueur;
	
	public Compte(Joueur joueur){
		this.joueur = joueur;
	}
	
	public Joueur getJoueur(){
		return joueur;
	}
	public void crediterJoueur(){
		
	}
	public void dediterJoueur(){
		
	}
	
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}
}
