package siteParis.model;

public class Personne {

	
	private String nom; //nom de personne
	private String mdp; //mdp de personne
	private String pseudo; //pseudo de personne
	
	//constructeur
	public Personne(String nom,String mdp,String pseudo){
		this.nom = nom;
		this.mdp = mdp;
		this.pseudo = pseudo;
	}
	
	//retouner nom de Personne
	public String getNom(){
		return nom;
	}
	
	//retouner mdp de Personne
	public String getMdp(){
		return mdp;
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
	public void setMdp(String n_mdp){
		mdp = n_mdp;
	}
	
	//changer pseudo de Personne
	public void setPseudo(String n_pseudo){
		pseudo = n_pseudo;
	}
	
	//consulter Personne
	public String toString(){
		return "C'est la personne " + nom + "avec surnom " + pseudo ;
	}
	
}
