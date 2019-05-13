package siteParis.model;

public class Pari {
	private long jetons;
	private String vainqueurEnvisage;
	
	public Pari(long jetons, String vainqueurEnvisage){
		this.setJetons(jetons);
		this.setVainqueurEnvisage(vainqueurEnvisage);
	}

	public String getVainqueurEnvisage() {
		return vainqueurEnvisage;
	}

	// setter du vainqueur
	public void setVainqueurEnvisage(String vainqueurEnvisaqe) {
		this.vainqueurEnvisage = vainqueurEnvisaqe;
	}

	// getter des jetons
	public long getJetons() {
		return jetons;
	}

	// setter des jetons
	public void setJetons(long jetons) {
		this.jetons = jetons;
	}

	private Competiteur competiteur;

	// getter du competiteur.
	public Competiteur getCompetiteur() {
		return competiteur;
	}

	// setter du competiteur.
	public void setCompetiteur(Competiteur competiteur) {
		this.competiteur = competiteur;
	}

	private Joueur joueur;

	// getter du joueur.
	public Joueur getJoueur() {
		return joueur;
	}

	// setter du joueur.
	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	private Competition competition;

	// getter du competition.
	public Competition getCompetition() { return competition; }

	// setter du competition.
	public void setCompetition(Competition competition) { this.competition = competition; }
}