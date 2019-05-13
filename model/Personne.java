package siteParis.model;

public class Personne {

	private String nom; //nom de personne
	private String motDePasse; //motDePasse de personne
	private String pseudo; //pseudo de personne
	
	//constructeur
	public Personne(String nom, String motDePasse, String pseudo){
		this.nom = nom;
		this.motDePasse = motDePasse;
		this.pseudo = pseudo;
	}
	
	//retouner nom de Personne
	public String getNom(){
		return nom;
	}
	
	//retouner motDePasse de Personne
	public String getMotDePasse(){
		return motDePasse;
	}
	
	//retouner pseudo de Personne
	public String getPseudo(){
		return pseudo;
	}
	
	//changer nom de Personne
	public void  setNom(String personneNom){
		nom = personneNom;
	}
	
	//changer nom de Mdp
	public void setMotDePasse(String personneMotDePasse){
		motDePasse = personneMotDePasse;
	}
	
	//changer pseudo de Personne
	public void setPseudo(String personnePseudo){
		pseudo = personnePseudo;
	}
	
	//consulter Personne
	public String toString(){
		return "C'est la personne " + nom + "avec surnom " + pseudo ;
	}
	
}
