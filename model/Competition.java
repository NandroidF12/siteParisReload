package siteParis.model;

import siteParis.DateFrancaise;
import java.util.LinkedList;


public class Competition {
	private String nom; //le nom de la competitio
	private DateFrancaise dateCreationCompetition = null;//la dateCreationCompetition de création de la competition
	private LinkedList<String> competiteursListe = null;

   //constructeur
   public Competition(String nom, DateFrancaise dateCreationCompetition, LinkedList<String> competiteursListe){
      //valeurs de nom et data initialiser
		this.nom = nom;
		this.dateCreationCompetition = dateCreationCompetition;
		this.competiteursListe = competiteursListe;
	} 

	//retrouver nom de Competition
	public String getNom() {
		return nom;
	}
	
	//changer nom de Competition
	public void setNom(String nomCompetition) {
		this.nom = nomCompetition;
	}
	
	//retrouver dateCreationCompetition de Competition
	public DateFrancaise getDateCreationCompetition() {
		return this.dateCreationCompetition;
	}

	//retrouver les competiteur de Competition
	public LinkedList<String> getCompetiteursListe() {
	 	return this.competiteursListe;
	}
}