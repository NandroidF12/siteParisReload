package siteParis;

import siteParis.model.Competiteur;
import siteParis.model.Competition;
import siteParis.model.Joueur;
import siteParis.model.Pari;

import java.util.LinkedList;

// Package for the random password
import java.util.Random;

/**
 *
 * @author Bernard Prou et Julien Mallet
 * <br><br>
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de paris.
 * <br><br>
 * Dans toutes les méthodes :
 * <ul>
 * <li>un paramètre de type <code>String</code> est invalide si il n'est pas instancié.</li>
 *  <li>pour la validité d'un password de gestionnaire et d'un password de joueur :
 * <ul>
 * <li>       lettres et chiffres sont les seuls caractères autorisés </li>
 * <li>       il doit avoir une longueur d'au moins 8 caractères </li>
 * </ul></li>
 * <li>pour la validité d'un pseudo de joueur  :
 * <ul>
 * <li>        lettres et chiffres sont les seuls caractères autorisés  </li>
 * <li>       il doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>
 * <li>pour la validité d'un prénom de joueur et d'un nom de joueur :
 *  <ul>
 *  <li>       lettres et tiret sont les seuls caractères autorisés  </li>
 *  <li>      il  doit avoir une longueur d'au moins 1 caractère </li>
 * </ul></li>
 * <li>pour la validité d'une compétition  :
 *  <ul>
 *  <li>       lettres, chiffres, point, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      elle  doit avoir une longueur d'au moins 4 caractères</li>
 * </ul></li>
 * <li>pour la validité d'un compétiteur  :
 *  <ul>
 *  <li>       lettres, chiffres, trait d'union et souligné sont les seuls caractères autorisés </li>
 *  <li>      il doit avoir une longueur d'au moins 4 caractères.</li>
 * </ul></li></ul>
 */

public class SiteDeParisMetier {

	//mot de passe de gestionnaire
	private String password_Gestionnaire;

	//la liste des competitons.
	private LinkedList<Competition> competitions;
	
	// la liste des joueurs.
	private LinkedList<Joueur> joueurs;
	
	// la liste des competiteurs.
	private LinkedList<Competiteur> competiteurs;

    // la liste des paris.
    private LinkedList<Pari> paris;

	/**
	 * constructeur de <code>SiteDeParisMetier</code>.
	 *
	 * @param passwordGestionnaire   le password du gestionnaire.
	 *
	 * @throws MetierException  levée
	 * si le <code>passwordGestionnaire</code>  est invalide
	 */
	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException {
		password_Gestionnaire = passwordGestionnaire;
		this.validitePasswordGestionnaire(passwordGestionnaire);

        //la liste des competitions.
        this.competitions = new LinkedList<Competition>();

        // la liste des joueurs.
        this.joueurs= new LinkedList<Joueur>();

        // la liste des competiteurs.
        this.competiteurs = new LinkedList<Competiteur>();

        this.paris = new LinkedList<Pari>();
    }

	/**
	 * vérifier la validité du password du gestionnaire.
	 *
	 * @param passwordGestionnaire   le password du gestionnaire à vérifier
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code> est invalide.
	 */
	protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException {
		
		if (passwordGestionnaire==null) throw new MetierException("passwordGestionnaire est invalide");
		if (!passwordGestionnaire.matches("[0-9A-Za-z]{8,}")) throw new MetierException();
		if(passwordGestionnaire == null) throw new MetierException("passwordGestionnaire est vide");
		if(passwordGestionnaire.length() < 8) throw new MetierException("passwordGestionnaire est moins de 8");
		if(passwordGestionnaire.contains(" ")) throw new MetierException("passwordGestionnaire contient un space");
		if(passwordGestionnaire.contains("-")) throw new MetierException("passwordGestionnaire contient un -");
		if(!passwordGestionnaire.equals(password_Gestionnaire)) throw new MetierException("passwordGestionnaire incorrecte");
	}

	// Les méthodes du gestionnaire (avec mot de passe gestionnaire)
	/**
	 * inscrire un joueur.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code> proposé est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurExistantException   levée si un joueur existe avec les mêmes noms et prénoms ou le même pseudo.
	 * @throws JoueurException levée si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 *
	 * @return le mot de passe (déterminé par le site) du nouveau joueur inscrit.
	 */
	public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurExistantException, JoueurInexistantException,JoueurException {
 		this.validitePasswordGestionnaire(passwordGestionnaire);
		this.validiteParametresJoueur(nom, prenom, pseudo);

		//création de Mdp de Joueur
		String j_mdp = null;
        boolean joueurexistflag = false;

        RandomAlphanumeric joueur_mdp = new RandomAlphanumeric();
        j_mdp = joueur_mdp.generateRandomAlphanumeric(10);

        Joueur newjoueur = new Joueur();
        newjoueur.setNom(nom);
        newjoueur.setPreNom(prenom);
        newjoueur.setPseudo(pseudo);
        newjoueur.setPassword(j_mdp);

        boolean joueurexistant = false;
        boolean joueurnomprenomexistant = false;
        boolean joueurpseudoexistant = false;

		for(Joueur joueur : this.joueurs) {
			if(joueur.equals(newjoueur)) {
				joueurexistant = true;
			}
			 
			if((joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom))) {
				joueurnomprenomexistant = true;
			} 
			if((joueur.getPseudo().equals(pseudo))) {
				joueurpseudoexistant = true;
			} 
			
		}
		
		if(joueurexistant)throw new JoueurExistantException("Joueur existant");
		if(joueurnomprenomexistant)throw new JoueurExistantException("Joueur avec les mêmes noms et prénoms existant ");
		if(joueurpseudoexistant)throw new JoueurExistantException("joueur avec un pseudo existant ");
		this.joueurs.add(newjoueur);
			
		return "Le nom de cette Joueur est : " + j_mdp;

	}

	/**
	 * supprimer un joueur.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec le même <code>nom</code>, <code>prenom</code>  et <code>pseudo</code>.
	 * @throws JoueurException levée
	 * si le joueur a des paris en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 *
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 *
	 */
	
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {


		long jetonRembourse = 20;
		this.validitePasswordGestionnaire(passwordGestionnaire);
		this.validiteParametresJoueur(nom, prenom, pseudo);

		boolean joueurInexistant = true;
		boolean joueurDejaRetire = false;

		for(Joueur joueur : this.joueurs) {

			for (int i = 0; i < this.joueurs.size(); i++) {

				if ((joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom)) && joueur.getPseudo().equals(pseudo)) {
					joueurInexistant = false;
					joueurDejaRetire = true;
					this.joueurs.remove(i);
				}
			}
		}

		if(joueurInexistant) throw new JoueurInexistantException("Joueur inexistant");
		if(!joueurDejaRetire) throw new JoueurInexistantException("Joueur déjà retiré");

		return jetonRembourse;

	}



	/**
	 * ajouter une compétition.
	 *
	 * @param competition le nom de la compétition
	 * @param dateCloture   la date à partir de laquelle il ne sera plus possible de miser
	 * @param competiteurs   les noms des différents compétiteurs de la compétition
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException levée si le tableau des
	 * compétiteurs n'est pas instancié, si le
	 * <code>passwordGestionnaire</code> est invalide, si le
	 * <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionExistanteException levée si une compétition existe avec le même nom.
	 * @throws CompetitionException levée si le nom de la
	 * compétition ou des compétiteurs sont invalides, si il y a
	 * moins de 2 compétiteurs, si un des competiteurs n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture
	 * n'est pas instanciée ou est dépassée.
	 */

	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {
		this.validitePasswordGestionnaire(passwordGestionnaire);

		if(competition== null) throw new  CompetitionException("compétition avec nom de compétiteur contient un space");

		if(competition.contains(" ")) throw new  CompetitionException("compétition avec nom qui contient un space");

		if(competition.contains("|")) throw new  CompetitionException("compétition avec nom qui contient un |");

		if(competition.length() < 4) throw new  CompetitionException("compétition avec nom invalide (moins de 4 caracteres)");

		if(competiteurs ==null) throw new  MetierException("compétition avec nom invalide (moins de 4 caracteres)");

		if(competiteurs.length <= 1) throw new  CompetitionException("compétition avec nom invalide (moins de 4 caracteres)");

		if(dateCloture == null) throw new CompetitionException("compétition sans date cloture.");

		// Prototype of before():	public boolean before(Date when)
		if(dateCloture.estDansLePasse() ) throw new CompetitionException("compétition sans date de début.");



		for(int i = 0; i < competiteurs.length; i++ ) {

			if(competiteurs[i]== null) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].contains(" ")) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].contains("*")) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].contains("|")) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].length() < 4) throw new  CompetitionException("nom de compétiteur invalide (moins de 4 caracteres)");
//			if( i == 1) throw new CompetitionException("avec un seul compétiteur.");

			if(!competiteurexisits(this.competiteurs,competiteurs[i]) == true) {
				ajoutercompetiteur(competiteurs[i]);
			}
			
			for(int j = i + 1; j < competiteurs.length; j++ ) {
				if(competiteurs[i].equals(competiteurs[j])) throw new CompetitionException("deux compétiteurs de même nom");
			}
		}

		if(this.competiteurs == null) throw new  CompetitionException("liste de competiteur vide");

		if(dateCloture == null) throw new  CompetitionException("compétition avec nom qui contient un *");
		
		//parcours les competiteurs fourni par le fonction
		for(int i = 0; i < competiteurs.length; i++ ) {

		}
		
		for(Competition competitionobj: this.competitions) {
		
			if(competitionobj.getNom().equals(competition)) throw new CompetitionExistanteException();
			
		}
		//creer la propre liste competiteur pour la competition
		LinkedList<String> competiteursdecompetition = creerlistdecompetiton(competiteurs);

		//creer l'instance de Competition
		Competition newcompetition = new Competition(competition, dateCloture, competiteursdecompetition);

		
		//ajouter la competition dans la liste de competiton
		this.competitions.add(newcompetition);
		
	}


	/**
	 * ajouter un nouveau  competiteur dans la liste de competiteurs.
	 *
	 * @param nom   la nom de competiteur
	 *
	 *
	 *  * @throws CompetitionException levée si le nom de competiteur exist déjà
	 */

	private void ajoutercompetiteur(String nom) {
		Competiteur competiteur = new Competiteur(nom,nom,nom);
		this.competiteurs.add(competiteur);
	}

	/**
	 * créé liste de competiteur contenant le nom competiteur.
	 *
	 * @param competiteurs   la table de nom de competiteurs
	 *
	 *
	 *  * @throws CompetitionException levée si le nom de competiteur exist déjà
	 */

	private LinkedList<String> creerlistdecompetiton(String [] competiteurs) {
		LinkedList<String> competiteursdecompetition = new LinkedList<>();
		for(int i = 0; i < competiteurs.length; i++ ) {
			competiteursdecompetition.add(competiteurs[i]);
		}

		return competiteursdecompetition;

	}

	/**
	 * verifier l'existance d'un competiteur.
	 *
	 * @param listecompetiteurs la liste de competiteurs
	 * @param competiteur   le nom de competiteur à verifier
	 *
	 *
	 *  * @throws CompetitionException levée si le nom de competiteur exist déjà
	 */

	private boolean competiteurexisits(LinkedList<Competiteur> listecompetiteurs,String competiteur) {
		boolean competiteurexist = false;

		for(int i = 0; i < listecompetiteurs.size(); i++ ) {

			String nom_competiteur = listecompetiteurs.get(i).getNom();

			if(competiteur.equals(nom_competiteur)) {
				competiteurexist = true;
			}

		}

		return competiteurexist;
	}


	/**
	 * solder une compétition vainqueur (compétition avec vainqueur).
	 *
	 * Chaque joueur ayant misé sur cette compétition
	 * en choisissant ce compétiteur est crédité d'un nombre de
	 * jetons égal à :
	 *
	 * (le montant de sa mise * la somme des
	 * jetons misés pour cette compétition) / la somme des jetons
	 * misés sur ce compétiteur.
	 *
	 * Si aucun joueur n'a trouvé le
	 * bon compétiteur, des jetons sont crédités aux joueurs ayant
	 * misé sur cette compétition (conformément au montant de
	 * leurs mises). La compétition est "supprimée" si il ne reste
	 * plus de mises suite à ce solde.
	 *
	 * @param competition   le nom de la compétition
	 * @param vainqueur   le nom du vainqueur de la compétition
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée
	 * si le nom de la compétition ou du vainqueur est invalide,
	 * si il n'existe pas de compétiteur du nom du vainqueur dans la compétition,
	 * si la date de clôture de la compétition est dans le futur.
	 *
	 */
	public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException,  CompetitionInexistanteException, CompetitionException {

		validitePasswordGestionnaire(passwordGestionnaire);
		validiteCompetition(competition);

		Competition foundCompetition = null;
		Competiteur foundVainqueur = null;

		long montantTotalCompetition = 0;
		long montantTotalVainqueur = 0;

		LinkedList<Pari> competitionBetsList = new LinkedList<Pari>();
		LinkedList<Pari> winningBetsList = new LinkedList<Pari>();

		foundCompetition = findCompetition(competition);
		if (foundCompetition == null) throw new CompetitionInexistanteException();
		if (!foundCompetition.getDate().estDansLePasse()) throw new CompetitionException();

		foundVainqueur = findCompetiteur(vainqueur, foundCompetition);
		if (foundVainqueur == null) throw new CompetitionException();

		for (Pari pari : paris) {
			if (pari.getCompetition().equals(foundCompetition)) {
				montantTotalCompetition += pari.getJetons();
				competitionBetsList.add(pari);

				if (pari.getCompetiteur().equals(foundVainqueur)) {
					montantTotalVainqueur += pari.getJetons();
					winningBetsList.add(pari);
				}
			}
		}

		boolean noWinners = winningBetsList.size() == 0 && competitionBetsList.size() > 0;
		if (noWinners) {
			crediterWinners(competitionBetsList, 1, 1);
		} else {
			crediterWinners(winningBetsList, montantTotalCompetition, montantTotalVainqueur);
		}
		updateTotalJetonsEngage(competitionBetsList);

		competitions.remove(foundCompetition);
	}


	/**
	 * créditer le compte en jetons d'un joueur du site de paris.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param sommeEnJetons   la somme en jetons à créditer
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides.
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 */
	public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException {
		this.validitePasswordGestionnaire(passwordGestionnaire);
		this.validiteParametresJoueur(nom, prenom, pseudo);

		boolean joueurTrouve = false;
		boolean joueurNomPseudo = false;

		if(sommeEnJetons < 0) throw new MetierException("Somme en jetons est negatives.");

		for(Joueur joueur: this.joueurs)    {
            if(joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom) && joueur.getPseudo().equals(pseudo))   {
            	joueurTrouve = true;
                joueur.ajoutJetons(sommeEnJetons);
            }
        }

		if(!joueurTrouve) throw new JoueurInexistantException();
	}


	/**
	 * débiter le compte en jetons d'un joueur du site de paris.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prénom du joueur
	 * @param pseudo   le pseudo du joueur
	 * @param sommeEnJetons   la somme en jetons à débiter
	 * @param passwordGestionnaire  le password du gestionnaire du site
	 *
	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect,
	 * si la somme en jetons est négative.
	 * @throws JoueurException levée
	 * si <code>nom</code>, <code>prenom</code>,  <code>pseudo</code> sont invalides
	 * si le compte en jetons du joueur est insuffisant (il deviendrait négatif).
	 * @throws JoueurInexistantException   levée si il n'y a pas de joueur  avec les mêmes nom,  prénom et pseudo.
	 *
	 */

	public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws  MetierException, JoueurInexistantException, JoueurException {
        this.validitePasswordGestionnaire(passwordGestionnaire);
        this.validiteParametresJoueur(nom, prenom, pseudo);

        boolean joueurTrouve = false;
        boolean joueurNomPseudo = false;

        if(sommeEnJetons < 0) throw new MetierException("Somme en jetons est negatives.");

        for(Joueur joueur: this.joueurs)    {

            if(joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom) && joueur.getPseudo().equals(pseudo))   {
                joueurTrouve = true;
                joueur.remiseJetons(sommeEnJetons);
            }
        }

        if(!joueurTrouve)  throw new JoueurInexistantException("Prenom is wrong but the Nom and the pseudo are right");
    }




	/**
	 * consulter les  joueurs.
	 *
	 * @param passwordGestionnaire  le password du gestionnaire du site de paris

	 * @throws MetierException   levée
	 * si le <code>passwordGestionnaire</code>  est invalide,
	 * si le <code>passwordGestionnaire</code> est incorrect.
	 *
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent un joueur avec dans l'ordre :
	 *  <ul>
	 *  <li>       le nom du joueur  </li>
	 *  <li>       le prénom du joueur </li>
	 *  <li>       le pseudo du joueur  </li>
	 *  <li>       son compte en jetons restant disponibles </li>
	 *  <li>       le total de jetons engagés dans ses mises en cours. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterJoueurs(String passwordGestionnaire) throws MetierException {

		this.validitePasswordGestionnaire(passwordGestionnaire);

		// intialise the String List of Players.
		LinkedList<LinkedList<String>> listJouers = new LinkedList<LinkedList<String>>();
		LinkedList<String> joueur_auxiliar = new LinkedList<String>();

		for(Joueur j : this.joueurs){
			System.out.println("Nom de joueur : " + j.getNom());
			joueur_auxiliar.add(j.getNom());
			joueur_auxiliar.add(j.getPreNom());
			joueur_auxiliar.add(j.getPseudo());
			listJouers.add(joueur_auxiliar);
		}
		
		return listJouers;
	}





	// Les méthodes avec mot de passe utilisateur



	/**
	 * mFiserVainqueur  (parier sur une compétition, en désignant un vainqueur).
	 * Le compte du joueur est débité du nombre de jetons misés.
	 *
	 * @param pseudo   le pseudo du joueur
	 * @param passwordJoueur  le password du joueur
	 * @param miseEnJetons   la somme en jetons à miser
	 * @param competition   le nom de la compétition  relative au pari effectué
	 * @param vainqueurEnvisage   le nom du compétiteur  sur lequel le joueur mise comme étant le  vainqueur de la compétition
	 *
	 * @throws MetierException levée si la somme en jetons est négative.
	 * @throws JoueurInexistantException levée si il n'y a pas de
	 * joueur avec les mêmes pseudos et password.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 * @throws CompetitionException levée
	 * si <code>competition</code> ou <code>vainqueurEnvisage</code> sont invalides,
	 * si il n'existe pas un compétiteur de  nom <code>vainqueurEnvisage</code> dans la compétition,
	 * si la compétition n'est plus ouverte (la date de clôture est dans le passé).
	 * @throws JoueurException   levée
	 * si <code>pseudo</code> ou <code>password</code> sont invalides,
	 * si le <code>compteEnJetons</code> du joueur est insuffisant (il deviendrait négatif).
	 */
	public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons, String competition, String vainqueurEnvisage) throws MetierException, JoueurInexistantException, CompetitionInexistanteException, CompetitionException, JoueurException  {

		Pari pari = new Pari(miseEnJetons, vainqueurEnvisage);

		// Integer to iterate the list of Competitors in the Competition to bet.
		int i;

		boolean vainqueurFlag = false;
	    boolean competitionflag = false;
		boolean competiteurflag = false;
		boolean joueurpseudoflag = false;
		boolean joueurjetonsflag = false;
		boolean joueurpariflag = false;

		if(miseEnJetons < 0) throw new MetierException("la somme en jetons est négative");
		if(pseudo == null || passwordJoueur ==null) throw new JoueurException("pseudo ou passwordJoueur est invalide");
		if(competition == null || vainqueurEnvisage ==null) throw new CompetitionException("competition ou vainqueurEnvisage est invalide");

		//verifier que le competition existe
		for(Competition competitionobj: this.competitions) {

			// Fouiller du Competition
			if (competitionobj.getNom().equals(competition)) {
				competitionflag = true;

				LinkedList<String> competiteurs_liste = competitionobj.getCompetiteurs();

				for (i = 0; i < competiteurs_liste.size(); i++) {
					if (competiteurs_liste.get(i).equals(vainqueurEnvisage)) {
						vainqueurFlag = true;
					}
				}
			}
		}

		if(!vainqueurFlag) throw new CompetitionException("Nom du vainqueur n'est pas exist.");
        Competiteur competiteur_vainqueur = new Competiteur(vainqueurEnvisage, passwordJoueur, pseudo);
        competiteur_vainqueur.setNom(vainqueurEnvisage);

        // Sur la class Joueur on a faire le debit sur le attribute Pari
        for(Joueur joueur_pari : this.joueurs) {

            if(!(joueur_pari.getPseudo().equals(pseudo) && joueur_pari.getPassword().equals(passwordJoueur))) {
                joueurpseudoflag = true;
            }

            if(joueur_pari.getPseudo().equals(pseudo)) {
                pari.setJoueur(joueur_pari);
                String nom_j = joueur_pari.getNom();
                String prenom_j = joueur_pari.getPreNom();

                if(joueur_pari.getJetons() > miseEnJetons) {
                        joueurpariflag = true;
                        debiterJoueur(nom_j, prenom_j, pseudo, miseEnJetons, password_Gestionnaire);
                }
            }
        }

		if(!competitionflag) throw new CompetitionInexistanteException("il n'existe pas de compétition de même nom");
		if(!joueurpseudoflag) throw new JoueurInexistantException("il n'y a pas de joueur avec les mêmes pseudos et password");
		if(!joueurpariflag) throw new JoueurException("Le joueur n'est peut mise une pari.");

	}

	//	if(!joueurpseudoflag) throw new JoueurInexistantException("il n'y a pas de joueur avec les mêmes pseudos et password");


	// Les méthodes sans mot de passe

	/**
	 * connaître les compétitions en cours.
	 *
	 * @return une liste de liste dont les éléments (de type <code>String</code>) représentent une compétition avec dans l'ordre :
	 *  <ul>
	 *  <li>       le nom de la compétition,  </li>
	 *  <li>       la date de clôture de la compétition. </li>
	 *  </ul>
	 */
	public LinkedList <LinkedList <String>> consulterCompetitions(){
		
		LinkedList <LinkedList <String>> competitons = new LinkedList <LinkedList <String>>();
		LinkedList <String> competitionsinfo = new LinkedList <String>();
		
		for(Competition competition : this.competitions) {
			
			competitionsinfo.add(competition.getNom());
		//	competitionsinfo.add(competition.getDate());
			competitons.add(competitionsinfo);
		}
		
		return competitons;
	}


	/**
	 * connaître  la liste des noms des compétiteurs d'une compétition.
	 *
	 * @param competition   le nom de la compétition
	 *
	 * @throws CompetitionException   levée
	 * si le nom de la compétition est invalide.
	 * @throws CompetitionInexistanteException   levée si il n'existe pas de compétition de même nom.
	 *
	 * @return la liste des compétiteurs de la  compétition.
	 */


	public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
		
		LinkedList <String> listedecompetiteur = new LinkedList <String>();

		if(competition== null) throw new  CompetitionException("nom de la compétition est invalide");
		if(competition.length() < 4) throw new  CompetitionException("nom de la compétition invalide (moins de 4 caracteres)");
		
		for(Competiteur competiteur : this.competiteurs) {
			if(competiteur== null) throw new  CompetitionException("nom de compétiteur invalide");
			
		}

		boolean competitionflag = false;
		for(Competition competitionobj : this.competitions) {
			
			//verifier qu'il existe de compétition de même nom
			//System.out.println("Nom de competition : " + competitionobj.getNom() + " Nombre de competiteurs : " + competitionobj.consulterCompetiteurs().size());
			
			if(competitionobj.getNom().equals(competition) ) {
				competitionflag = true;
				//retrouver la liste de competiteur de cette competition
				listedecompetiteur = competitionobj.getCompetiteurs();
			}
		}
		if(!competitionflag)throw new CompetitionInexistanteException("il n'existe pas de compétition de même nom");
		return listedecompetiteur;
	}



	/**
	 * valider les parametres du Joueur.
	 *
	 * @param nom   le nom du joueur
	 * @param prenom   le prenom du joueur
	 * @param pseudo   le pseudo du joueur
	 *
	 * @throws JoueurInexistantException   levée
	 * si le nom, prenom ,pseudo de la compétition est invalide
	 */
	
	protected void validiteParametresJoueur(String nom, String prenom, String pseudo) throws JoueurInexistantException, JoueurException {
		int i = 0;

		if(nom == null || prenom == null || pseudo ==null) throw new JoueurException("nom ou prenom ou pseudo ode joueur est invalide");

		if(nom.contains(" ") ||nom.contains("-") ||nom.contains("'") || nom.length() < 4 ) throw new JoueurException("nom invalide");

		if(prenom.contains(" ") || prenom.contains("-") || prenom.contains(".") || prenom.length() < 4) throw new JoueurException("prenom invalide");

		if(pseudo.contains(" ") || pseudo.contains("-") || pseudo.contains(".") || pseudo.length() < 4) throw new JoueurException("pseudo invalide");

	}

	protected static void validiteCompetition(String nomCompetition) throws CompetitionException {
		if (nomCompetition == null) {
			throw new CompetitionException();
		} else if (!nomCompetition.matches("[0-9A-Za-z-_.]{4,}")) {
			throw new CompetitionException();
		}
	}

	public Competition findCompetition(String nomCompetition){
		for (Competition comp : competitions) {
			if (comp.getNom().equals(nomCompetition))
			{return comp;}

		}
		return null;
	}

	public Competiteur findCompetiteur(String lookedForCompetiteur, Competition inCompetition){

	//	Competiteur foundVainqueur
		Competiteur foundVainqueur = null;

		for(Competiteur competiteur : this.competiteurs)	{
			if (competiteur.getNom().equals(lookedForCompetiteur)){
				foundVainqueur = competiteur;
			}
		}
		return foundVainqueur;
	}

	public void crediterWinners(LinkedList<Pari> competitionBetsList, long montantTotalCompetition, long montantTotalVainqueur){
		for (Pari pari : competitionBetsList) {
			pari.getJoueur().ajoutJetons(pari.getJetons()*montantTotalCompetition/montantTotalVainqueur);
		}
	}

	public void updateTotalJetonsEngage(LinkedList<Pari> competitionBetsList){
		for (Pari pari : competitionBetsList) {
			pari.getJoueur().remiseJetons(pari.getJetons());
		}
	}

	/*******************************************************************************************************************/
	public class RandomAlphanumeric	{
		int i;

		private final String lowers = "abcdefghijklmnopqrstuvxyz";
		private final String uppers = lowers.toUpperCase();
		private final String numbers = "0123456789";
		private final char[] concatenation = (lowers + uppers + numbers).toCharArray();

		public String generateRandomAlphanumeric(int length)	{
			StringBuilder joueurMotDePasse = new StringBuilder();

			for(i=0; i <length; i++)	{
				joueurMotDePasse.append(concatenation[new Random().nextInt(concatenation.length)]);
			}

			return concatenation.toString();
		}
	}


    // ################################################################################################################



}

