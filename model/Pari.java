package siteParis.model;

public class Pari {
	private long jetons;
	private String vainqueur_envisage;
	
	public Pari(long jetons, String vainqueur_envisage){
		this.setJetons(jetons);
		this.setVainqueur_envisage(vainqueur_envisage);
	}

	public String getVainqueur_envisage() {
		return vainqueur_envisage;
	}

	public void setVainqueur_envisage(String vainqueur_envisaqe) {
		this.vainqueur_envisage = vainqueur_envisaqe;
	}

	public long getJetons() {
		return jetons;
	}

	public void setJetons(long jetons) {
		this.jetons = jetons;
	}

	private Competiteur competiteur;

	public Competiteur getCompetiteur() {
		return competiteur;
	}

	public void setCompetiteur(Competiteur competiteur) {
		this.competiteur = competiteur;
	}

	private Joueur joueur;

	public Joueur getJoueur() {
		return joueur;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	private Competition competition;

	public Competition getCompetition() { return competition; }

	public void setCompetition(Competition competition) { this.competition = competition; }
}