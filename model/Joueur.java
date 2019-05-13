package siteParis.model;

import java.util.LinkedList;
import java.util.List;

public class Joueur {
	
	private String prenom; //prenom de Joueur
	private Long jetons; //prenom de Joueur
	private Pari pari; //pari de Joueur
	private String nom; //nom de personne
	private String password; //password de personne
	private String pseudo; //pseudo de personne

	public Joueur(String nom,String password,String pseudo){
		this.nom = nom;
		this.password = password;
		this.pseudo = pseudo;
		this.prenom = prenom;
	
	}

	public Joueur(){
		jetons = (long) 0;
		pari = null;
	}
	
	//retouner nom de Personne
	public String getNom(){
			return nom;
	}
		
		//retouner password de Personne
	public String getPassword(){
			return password;
	}
		
		//retouner pseudo de Personne
	public String getPseudo(){
			return pseudo;
	}
		
		//changer nom de Personne
	public void  setNom(String n_nom){
			nom = n_nom;		
	}
		
		//changer nom de Mdp
	public void setPassword(String n_mdp){
			password = n_mdp;
	}
		
		//changer pseudo de Personne
	public void setPseudo(String n_pseudo){
			pseudo = n_pseudo;
	}
		
	
	//retouner prenom de Personne
	public String getPreNom() {
		return prenom;
	}
	
	//changer prenom de Personne
	public void setPreNom(String prenom) {
		this.prenom = prenom;
	}

	//ajouter des jetons
	public void ajoutJetons ( long sommeEnJetons){
		this.jetons += sommeEnJetons;
	}
	
	//soustraire des jetons
	public void remiseJetons ( long sommeEnJetons){
		this.jetons -= sommeEnJetons;
	}
	
	//retourner jetons de Joueur
	public long getJetons () {
			return this.jetons;
		}
	// changer la quantité des Jetons.
	public void setJetons(long jetons) { this.jetons = jetons; }

	//retourner les paris
	public Pari getPari() {
			return this.pari;
		}
	//changer les paris
	public void setPari (Pari pari){
			this.pari = pari;
		}

	// get the parameters of the player for the method consulterJoueurs
	public List<String> getJoueurParametres() {

		List<String> joueurParametres = new LinkedList();
		joueurParametres.add(this.nom);
		joueurParametres.add(this.prenom);
		joueurParametres.add(String.valueOf(this.getJetons()));
		joueurParametres.add(this.pseudo);

		return joueurParametres;
	}

}