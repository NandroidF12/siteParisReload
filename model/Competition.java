package siteParis.model;

import siteParis.DateFrancaise;
import java.util.LinkedList;
import java.util.List;


public class Competition {
	private String nom; //le nom de la competitio
	private DateFrancaise date = null;//la date de création de la competition
	private LinkedList<String> competiteurs = null;

   //constructeur
   public Competition(String nom,DateFrancaise date,LinkedList<String>competiteurs){
      //valeurs de nom et data initialiser
		this.nom = nom;
		this.date = date;
		this.competiteurs = competiteurs;
	} 

	//retrouver nom de Competition

	public String getNom() {
		return nom;
	}
	
	//changer nom de Competition
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//retrouver date de Competition
	public DateFrancaise getDate() {
		return this.date;
	}

	//retrouver les competiteur de Competition
	public LinkedList<String> getCompetiteurs() {
	 	return this.competiteurs;
	}

	//changer les competiteur de Competition
//	public void setCompetiteurs(LinkedList<Competiteur> competiteurs) {
//		this.competiteurs = competiteurs;
//	}


}