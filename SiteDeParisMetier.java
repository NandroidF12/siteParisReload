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
 * La classe qui contient toutes les méthodes "Métier" de la gestion du site de parisListe.
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
	private String passwordGestionnaire;

	//la liste des competitons.
	private LinkedList<Competition> competitionsListe;
	
	// la liste des joueursListe.
	private LinkedList<Joueur> joueursListe;
	
	// la liste des competiteursListe.
	private LinkedList<Competiteur> competiteursListe;

    // la liste des parisListe.
    private LinkedList<Pari> parisListe;

	/**
	 * constructeur de <code>SiteDeParisMetier</code>.
	 *
	 * @param passwordGestionnaire   le password du gestionnaire.
	 *
	 * @throws MetierException  levée
	 * si le <code>passwordGestionnaire</code>  est invalide
	 */
	public SiteDeParisMetier(String passwordGestionnaire) throws MetierException {
		this.passwordGestionnaire = passwordGestionnaire;
		this.validitePasswordGestionnaire(passwordGestionnaire);

        //la liste des competitionsListe.
        this.competitionsListe = new LinkedList<Competition>();

        // la liste des joueursListe.
        this.joueursListe = new LinkedList<Joueur>();

        // la liste des competiteursListe.
        this.competiteursListe = new LinkedList<Competiteur>();

        // la liste des parisListe.
        this.parisListe = new LinkedList<Pari>();
    }


	/*
	 * 				Les méthodes du gestionnaire (avec mot de passe gestionnaire)
 	 */


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

		validitePasswordGestionnaire(passwordGestionnaire);
		validiteParametresJoueur(nom, prenom, pseudo);

		//création du mot de passe de Joueur
		String joueurMotDePasse = null;
        boolean joueurExistantFlag = false;
        
        RandomAlphanumeric joueur_mdp = new RandomAlphanumeric();
        joueurMotDePasse = joueur_mdp.generateRandomAlphanumeric(10);

        // Création d'un nouveau Joueur.
        Joueur joueurNouveuau = new Joueur();
        joueurNouveuau.setNom(nom);
        joueurNouveuau.setPreNom(prenom);
        joueurNouveuau.setPseudo(pseudo);
        joueurNouveuau.setMotDePasse(joueurMotDePasse);

        // Les drapeaux pour valider chaque exception.
        boolean joueurExistant = false;
        boolean joueurNomPrenomExistant = false;
        boolean joueurPseudoExistant = false;

        // Cherche s'il y a un joueur avec le même paramètres.
		for(Joueur joueur : this.joueursListe) {
			if(joueur.equals(joueurNouveuau)) {
				joueurExistant = true;
			}
			 
			if((joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom))) {
				joueurNomPrenomExistant = true;
			} 
			if((joueur.getPseudo().equals(pseudo))) {
				joueurPseudoExistant = true;
			} 
			
		}

		// Faire les exceptions pour chaque cas proposé.
		if(joueurExistant)throw new JoueurExistantException("Joueur existant");
		if(joueurNomPrenomExistant)throw new JoueurExistantException("Joueur avec les mêmes noms et prénoms existant ");
		if(joueurPseudoExistant)throw new JoueurExistantException("joueur avec un pseudo existant ");
		this.joueursListe.add(joueurNouveuau);
			
		return "Le nom de cette Joueur est : " + joueurMotDePasse;

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
	 * si le joueur a des parisListe en cours,
	 * si <code>nom</code>, <code>prenom</code>, <code>pseudo</code> sont invalides.
	 *
	 * @return le nombre de jetons à rembourser  au joueur qui vient d'être désinscrit.
	 *
	 */
	
	public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurInexistantException, JoueurException {

		// Validation des paramètres du Gestionnaire et du Joueur.
		validitePasswordGestionnaire(passwordGestionnaire);
		validiteParametresJoueur(nom, prenom, pseudo);

		// Nombre du jetons à rembourse.
		long jetonRembourse = 20;

		// Les drapeaux pour valider chaque exception.
		boolean joueurInexistant = true;
		boolean joueurDejaRetire = false;

		// Cherche s'il y a un joueur avec le même paramètres.
		for(Joueur joueur : this.joueursListe) {

			for (int i = 0; i < this.joueursListe.size(); i++) {

				if ((joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom)) && joueur.getPseudo().equals(pseudo)) {
					joueurInexistant = false;
					joueurDejaRetire = true;
					this.joueursListe.remove(i);
				}
			}
		}

		// Faire les exceptions pour chaque cas proposé.
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
	 * moins de 2 compétiteurs, si un des competiteursListe n'est pas instancié,
	 * si deux compétiteurs ont le même nom, si la date de clôture
	 * n'est pas instanciée ou est dépassée.
	 */

	public void ajouterCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs, String passwordGestionnaire) throws MetierException, CompetitionExistanteException, CompetitionException  {

		validitePasswordGestionnaire(passwordGestionnaire);
		validiteCompetition(competition,dateCloture,competiteurs);

		// Prototype of before():	public boolean before(Date when)
		if(dateCloture.estDansLePasse() ) throw new CompetitionException("compétition sans date de début.");

		// Ajouter competiteur dans la competition.
		for(int i = 0; i < competiteurs.length; i++ ) {

			// Faire les exceptions pour chaque cas proposé.
			if(competiteurs[i]== null) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].contains(" ")) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].contains("*")) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].contains("|")) throw new  CompetitionException("nom de compétiteur contient un space");
			if(competiteurs[i].length() < 4) throw new  CompetitionException("nom de compétiteur invalide (moins de 4 caracteres)");
			if( competiteurs.length == 1) throw new CompetitionException("avec un seul compétiteur.");

			if(!competiteurExisits(this.competiteursListe,competiteurs[i]) == true) {
				ajouterCompetiteur(competiteurs[i]);
			}
			
			for(int j = i + 1; j < competiteurs.length; j++ ) {
				if(competiteurs[i].equals(competiteurs[j])) throw new CompetitionException("deux compétiteurs de même nom");
			}
		}

		if(this.competiteursListe == null) throw new  CompetitionException("liste de competiteur vide");

		if(dateCloture == null) throw new  CompetitionException("compétition avec nom qui contient un *");
		
		//parcours les competiteursListe fourni par le fonction
		for(int i = 0; i < competiteurs.length; i++ ) {

		}
		
		for(Competition competitionObj: this.competitionsListe) {
		
			if(competitionObj.getNom().equals(competition)) throw new CompetitionExistanteException();
			
		}
		//creer la propre liste competiteur pour la competition
		LinkedList<String> competiteursDeCompetition = creerListeDeCompetiton(competiteurs);

		//creer l'instance de Competition
		Competition competitionNouveau = new Competition(competition, dateCloture, competiteursDeCompetition);

		
		//ajouter la competition dans la liste de competiton
		this.competitionsListe.add(competitionNouveau);
		
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
	 * bon compétiteur, des jetons sont crédités aux joueursListe ayant
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

		// Validation des paramètres du Gestionnaire et de la compétition à solder.
		validitePasswordGestionnaire(passwordGestionnaire);
		validiteCompetitionSolder(competition);

		// déclaration des classes pour la competition et le vainqueur à trouver.
		Competition competitionTrouve = null;
		Competiteur vainqueurTrouve = null;

		// déclaration des variables pour les montants.
		long montantTotalCompetition = 0;
		long montantTotalVainqueur = 0;

		// declaration des listes pour le paris dans à compétition et pour les vainqueurs.
		LinkedList<Pari> competitionListeParis = new LinkedList<Pari>();
		LinkedList<Pari> vainqueursListeParis = new LinkedList<Pari>();

		// chercher du competition et faire les exceptions pour chaque cas proposé.
		competitionTrouve = competitionChercher(competition);
		if (competitionTrouve == null) throw new CompetitionInexistanteException();
		if (!competitionTrouve.getDateCreationCompetition().estDansLePasse()) throw new CompetitionException();

		// chercher du competiteur et faire les exceptions pour chaque cas proposé.
		vainqueurTrouve = competiteurChercher(vainqueur, competitionTrouve);
		if (vainqueurTrouve == null) throw new CompetitionException();

		// Addition dans une compétition et le vainqueur du compétition correspondant.
		for (Pari pari : parisListe) {
			if (pari.getCompetition().equals(competitionTrouve)) {
				montantTotalCompetition += pari.getJetons();
				competitionListeParis.add(pari);

				if (pari.getCompetiteur().equals(vainqueurTrouve)) {
					montantTotalVainqueur += pari.getJetons();
					vainqueursListeParis.add(pari);
				}
			}
		}

		// Cas où il n'y a pas de/s vainqueurs.
		boolean sansVainqueurs = vainqueursListeParis.size() == 0 && competitionListeParis.size() > 0;
		if (sansVainqueurs) {
			vainqueurCrediter(competitionListeParis, 1, 1);
		} else {
			vainqueurCrediter(vainqueursListeParis, montantTotalCompetition, montantTotalVainqueur);
		}
		totalJetons(competitionListeParis);

		// retrait de la compétition trouvé.
		competitionsListe.remove(competitionTrouve);
	}


	/**
	 * créditer le compte en jetons d'un joueur du site de parisListe.
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

		// Validation des paramètres du Gestionnaire et du Joueur.
		validitePasswordGestionnaire(passwordGestionnaire);
		validiteParametresJoueur(nom, prenom, pseudo);

		// Les drapeaux pour valider chaque exception.
		boolean joueurTrouve = false;
		boolean joueurNomPseudo = false;

		if(sommeEnJetons < 0) throw new MetierException("Somme en jetons est negative.");

		// Cherche s'il y a un joueur avec le même paramètres.
		for(Joueur joueur: this.joueursListe)    {
            if(joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom) && joueur.getPseudo().equals(pseudo))   {
                joueur.ajoutJetons(sommeEnJetons);
				joueurTrouve = true;
            }
        }

		// Faire les exceptions pour chaque cas proposé.
		if(!joueurTrouve) throw new JoueurInexistantException();
	}


	/**
	 * débiter le compte en jetons d'un joueur du site de parisListe.
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

		// Validation des paramètres du Gestionnaire et du Joueur.
		validitePasswordGestionnaire(passwordGestionnaire);
        validiteParametresJoueur(nom, prenom, pseudo);

		// Les drapeaux pour valider chaque exception.
        boolean joueurTrouve = false;
        boolean joueurNomPseudo = false;

        if(sommeEnJetons < 0) throw new MetierException("Somme en jetons est negatives.");

		// Cherche s'il y a un joueur avec le même paramètres.
        for(Joueur joueur: this.joueursListe)    {
            if(joueur.getNom().equals(nom) && joueur.getPreNom().equals(prenom) && joueur.getPseudo().equals(pseudo))   {
                joueurTrouve = true;
                joueur.remiseJetons(sommeEnJetons);
            }
        }

		// Faire les exceptions pour chaque cas proposé.
        if(!joueurTrouve)  throw new JoueurInexistantException("Prenom is wrong but the Nom and the pseudo are right");
    }


	/**
	 * consulter les joueursListe.
	 *
	 * @param passwordGestionnaire  le password du gestionnaire du site de parisListe

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

		// Validation des paramètres du Gestionnaire.
		validitePasswordGestionnaire(passwordGestionnaire);

		// initialisation de la chaîne de la liste du joueurs.
		LinkedList listJouers = new LinkedList<LinkedList<String>>();

		//obtention des paramètres de chaque joueur de la liste
		for(Joueur joueur : this.joueursListe)	{
			listJouers.add(joueur.getJoueurParametres());
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

		// déclaration d'un nouveau pari pour réaliser la miser du vainqueur.
		Pari pari = new Pari(miseEnJetons, vainqueurEnvisage);

		// Les drapeaux pour valider chaque exception.
		boolean vainqueurFlag = false;
	    boolean competitionflag = false;
	    boolean joueurFlag = false;
		boolean joueurPseudoFlag = false;
		boolean joueurpariflag = false;

		//verifier que le competition existe
		for(Competition competitionObj: this.competitionsListe) {

			// Fouiller du Competition
			if (competitionObj.getNom().equals(competition)) {
				competitionflag = true;

				LinkedList<String> competiteursListeauxiliaire = competitionObj.getCompetiteursListe();

				for(String competiteur : competiteursListeauxiliaire)	{
					if (competiteur.equals(vainqueurEnvisage)) {
						vainqueurFlag = true;
					}
				}
			}
		}


		// déclaration d'un compétiteur vainqueur.
        Competiteur competiteurVainqueur = new Competiteur(vainqueurEnvisage, passwordJoueur, pseudo);
        competiteurVainqueur.setNom(vainqueurEnvisage);

        // Dans la classe Joueur on a faire le debit sur le attribute Pari
        for(Joueur joueurPari : this.joueursListe) {

            if(joueurPari.getPseudo().equals(pseudo) && !joueurPari.getMotDePasse().equals(passwordJoueur)) {
                joueurFlag = true;
            }

            if(joueurPari.getPseudo().equals(pseudo)) {
                pari.setJoueur(joueurPari);

                String nomJoueur = joueurPari.getNom();
                String prenomJoueur = joueurPari.getPreNom();
				joueurPseudoFlag = true;

                if(joueurPari.getJetons() > miseEnJetons) {
                        joueurpariflag = true;
                        debiterJoueur(nomJoueur, prenomJoueur, pseudo, miseEnJetons, passwordGestionnaire);
                }
            }
        }

		// Faire les exceptions pour chaque cas proposé.
		if(miseEnJetons < 0) throw new MetierException("la somme en jetons est négative");
		if(pseudo == null || passwordJoueur == null) throw new JoueurException("pseudo ou passwordJoueur est invalide");
		if(competition == null || vainqueurEnvisage ==null) throw new CompetitionException("competition ou vainqueurEnvisage est invalide");
		if(!vainqueurFlag) throw new CompetitionException("Nom du vainqueur n'est pas exist.");
		if(!competitionflag) throw new CompetitionInexistanteException("il n'existe pas de compétition de même nom");
		if(!joueurFlag) throw new JoueurException("il n'y a pas de joueur avec les mêmes pseudos et password");
		if(!joueurpariflag) throw new JoueurException("Le joueur n'est peut mise une pari.");

	}



	/*
					Fonctions correspondant à les Compétitions.
	 */

	/**
	 * valider les parametres du Compétition.
	 *
	 * @param competition   le nom du competition.
	 * @param dateCloture   le date de creation du competition.
	 * @param competiteurs  la liste du competiteurs.
	 *
	 * @throws CompetitionException
	 * @throws MetierException
	 */
	protected void validiteCompetition(String competition, DateFrancaise dateCloture, String [] competiteurs) throws CompetitionException, MetierException{

		if (competition == null) throw new CompetitionException("compétition avec nom de compétiteur contient un space");

		if (competition.contains(" ")) throw new CompetitionException("compétition avec nom qui contient un space");

		if (competition.contains("|")) throw new CompetitionException("compétition avec nom qui contient un |");

		if (competition.length() < 4)
			throw new CompetitionException("compétition avec nom invalide (moins de 4 caracteres)");

		if (competiteurs == null) throw new MetierException("compétition avec nom invalide (moins de 4 caracteres)");

		if (competiteurs.length <= 1)
			throw new CompetitionException("compétition avec nom invalide (moins de 4 caracteres)");

		if (dateCloture == null) throw new CompetitionException("compétition sans date cloture.");
	}

	protected static void validiteCompetitionSolder(String nomCompetition) throws CompetitionException {
		if (nomCompetition == null) {
			throw new CompetitionException();
		} else if (!nomCompetition.matches("[0-9A-Za-z-_.]{4,}")) {
			throw new CompetitionException();
		}
	}

	public Competition competitionChercher(String nomCompetition){
		for (Competition comp : competitionsListe) {
			if (comp.getNom().equals(nomCompetition))
			{return comp;}

		}
		return null;
	}

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
		LinkedList <String> competitionsInformation = new LinkedList <String>();
		
		for(Competition competition : this.competitionsListe) {
			competitionsInformation.add(competition.getNom());
			competitionsInformation.add(competition.getDateCreationCompetition().toString());
			competitons.add(competitionsInformation);
		}
		
		return competitons;
	}

	public void vainqueurCrediter(LinkedList<Pari> competitionBetsList, long montantTotalCompetition, long montantTotalVainqueur){
		for (Pari pari : competitionBetsList) {
			pari.getJoueur().ajoutJetons(pari.getJetons()*montantTotalCompetition/montantTotalVainqueur);
		}
	}

	public void totalJetons(LinkedList<Pari> competitionBetsList){
		for (Pari pari : competitionBetsList) {
			pari.getJoueur().remiseJetons(pari.getJetons());
		}
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

	/**
	 * créé liste de competiteur contenant le nom competiteur.
	 *
	 * @param competiteurs   la table de nom de competiteursListe
	 *
	 *
	 *  * @throws CompetitionException levée si le nom de competiteur exist déjà
	 */

	private LinkedList<String> creerListeDeCompetiton(String [] competiteurs) {

		LinkedList<String> competiteursDeCompetition = new LinkedList<>();
		for(int i = 0; i < competiteurs.length; i++ ) {
			competiteursDeCompetition.add(competiteurs[i]);
		}

		return competiteursDeCompetition;

	}

	/*
					Fonctions correspondant à les Competiteurs
	 */

	public LinkedList <String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
		
		LinkedList <String> competiteursListeAuxiliaires = new LinkedList <String>();

		boolean competitionflag = false;

		// Cherche s'il y a un compétetion avec un nom invalide.
		for(Competiteur competiteur : this.competiteursListe) {
			if(competiteur== null) throw new  CompetitionException("nom de compétiteur invalide");
		}

		// Cherche s'il y a un compétetion avec le même nom et deposer la liste de competiteurs.
		for(Competition competitionObj : this.competitionsListe) {

			if(competitionObj.getNom().equals(competition) ) {
				competitionflag = true;

				//retrouver la liste de competiteur de cette competition
				competiteursListeAuxiliaires = competitionObj.getCompetiteursListe();
			}
		}


		// Faire les exceptions pour chaque cas proposé.
		if(competition== null) throw new  CompetitionException("nom de la compétition est invalide");
		if(competition.length() < 4) throw new  CompetitionException("nom de la compétition invalide (moins de 4 caracteres)");
		if(!competitionflag)throw new CompetitionInexistanteException("il n'existe pas de compétition de même nom");

		return competiteursListeAuxiliaires;
	}

	public Competiteur competiteurChercher(String lookedForCompetiteur, Competition inCompetition){

		//	Competiteur foundVainqueur
		Competiteur foundVainqueur = null;

		for(Competiteur competiteur : this.competiteursListe)	{
			if (competiteur.getNom().equals(lookedForCompetiteur)){
				foundVainqueur = competiteur;
			}
		}
		return foundVainqueur;
	}

	/**
	 * ajouter un nouveau  competiteur dans la liste de competiteursListe.
	 *
	 * @param nom   la nom de competiteur
	 *
	 *
	 *  * @throws CompetitionException levée si le nom de competiteur exist déjà
	 */

	private void ajouterCompetiteur(String nom) {
		Competiteur competiteur = new Competiteur(nom,nom,nom);
		this.competiteursListe.add(competiteur);
	}

	/**
	 * verifier l'existance d'un competiteur.
	 *
	 * @param listecompetiteurs la liste de competiteursListe
	 * @param competiteur   le nom de competiteur à verifier
	 *
	 *
	 *  * @throws CompetitionException levée si le nom de competiteur exist déjà
	 */

	private boolean competiteurExisits(LinkedList<Competiteur> listecompetiteurs, String competiteur) {
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
		if(!passwordGestionnaire.equals(this.passwordGestionnaire)) throw new MetierException("passwordGestionnaire incorrecte");
	}


	/*
					Fonctions correspondant à les Joueurs.
	 */

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
}

