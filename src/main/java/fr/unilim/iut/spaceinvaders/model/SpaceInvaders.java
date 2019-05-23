package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.EnvahisseurException;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.*;

import java.util.concurrent.ThreadLocalRandom;

public class SpaceInvaders implements Jeu {
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	List<Missile> missiles;
	List<Envahisseur> envahisseurs;
	List<Missile> missilesEnvahisseurs;
	boolean continuerJeu;
	boolean vaisseauDetruit;
	/**
	 * Permet de savoir à quel moment le dernier missile à été tiré
	 */
	private long timerMissile;
	private int score;
	/**
	 * Permet de savoir à quel moment le dernier missile envahisseur à été tiré
	 */
	private long timerMissileEnvahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
		this.missiles = new ArrayList<Missile>();
		this.missilesEnvahisseurs = new ArrayList<Missile>();
		this.envahisseurs = new ArrayList<Envahisseur>();
		this.timerMissile = 0;
		this.score = 0;
		this.timerMissileEnvahisseur = 0;
		this.continuerJeu = true;
		this.vaisseauDetruit = false;
	}

	private boolean estDansEspaceJeu(int x, int y) {
		return ((x >= 0) && (x < longueur)) && ((y >= 0) && (y < hauteur));
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {
		verificationPositionEspaceJeu(dimension, position);
		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public boolean aUnVaisseau() {
		return null != vaisseau;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}

	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_VAISSEAU;
		} else if (this.aUnMissileQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_MISSILE;
		} else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_ENVAHISSEUR;
		} else if (this.aUnMissileEnvahisseurQuiOccupeLaPosition(x, y)) {
			marque = Constante.MARQUE_MISSILE_ENVAHISSEUR;
		} else {
			marque = Constante.MARQUE_VIDE;
		}
		return marque;
	}

	private boolean aUnMissileEnvahisseurQuiOccupeLaPosition(int x, int y) {
		boolean trouve = false;

		if (this.aUnMissileEnvahisseur()) {
			int i = 0;
			while (!trouve && i < missilesEnvahisseurs.size()) {
				trouve = missilesEnvahisseurs.get(i).occupeLaPosition(x, y);
				i++;
			}
		}
		return trouve;
	}

	public boolean aUnMissileEnvahisseur() {
		return !missilesEnvahisseurs.isEmpty();

	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		boolean trouve = false;

		if (this.aUnMissile()) {
			int i = 0;
			while (!trouve && i < missiles.size()) {
				trouve = missiles.get(i).occupeLaPosition(x, y);
				i++;
			}
		}
		return trouve;
	}

	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		boolean trouve = false;

		if (this.aUnEnvahisseur()) {
			int i = 0;
			while (!trouve && i < envahisseurs.size()) {
				trouve = envahisseurs.get(i).occupeLaPosition(x, y);
				i++;
			}
		}
		return trouve;
	}

	public boolean aUnMissile() {
		return !missiles.isEmpty();

	}

	public boolean aUnEnvahisseur() {
		return null != envahisseurs;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche()) {
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		}
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append(Constante.MARQUE_FIN_LIGNE);
		}
		return espaceDeJeu.toString();
	}

	public void initialiserJeu() {
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 5);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Position positionEnvahisseur = new Position(this.longueur / 5, this.hauteur / 5);
		Position positionEnvahisseur1 = new Position((this.longueur / 5) * 2, this.hauteur / 5);
		Position positionEnvahisseur2 = new Position((this.longueur / 5) * 3, this.hauteur / 5);
		Position positionEnvahisseur3 = new Position((this.longueur / 5) * 4, this.hauteur / 5);
		Position positionEnvahisseur4 = new Position(this.longueur / 5, (this.hauteur / 5) * 2);
		Position positionEnvahisseur5 = new Position((this.longueur / 5) * 2, (this.hauteur / 5) * 2);
		Position positionEnvahisseur6 = new Position((this.longueur / 5) * 3, (this.hauteur / 5) * 2);
		Position positionEnvahisseur7 = new Position((this.longueur / 5) * 4, (this.hauteur / 5) * 2);

		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur1, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur2, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur3, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur4, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur5, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur6, Constante.ENVAHISSEUR_VITESSE);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur7, Constante.ENVAHISSEUR_VITESSE);

	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public List<Envahisseur> recupererEnvahisseurs() {
		return this.envahisseurs;
	}

	private void finirJeu() {
		this.envahisseurs = null;
		this.missiles = null;
		this.continuerJeu = false;
	}

	public void deplacerEnvahisseur() {
		if (this.aUnEnvahisseur()) {
			faireTournerEnvahisseursSiLUnEstSurLesBords();
			// Ici, on est obligé de d'abord vérifier les positions car sinon, si ce n'est
			// pas le premier, les autres ne tourneront pas
			for (int i = 0; i < this.envahisseurs.size(); i++) {
				envahisseurs.get(i).deplacerAutomatiquement();
			}
		}
	}

	private void faireTournerEnvahisseursSiLUnEstSurLesBords() {
		if (envahisseurSurLesBords()) {
			this.tournerEnvahisseurs();
		}
	}

	private boolean envahisseurSurLesBords() {
		for (int i = 0; i < this.envahisseurs.size(); i++) {
			if (envahisseurs.get(i).abscisseLaPlusAGauche() <= 0) {
				return true;
			}
			if (envahisseurs.get(i).abscisseLaPlusADroite() + 1 >= (longueur)) {
				return true;
			}
		}
		return false;
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur) {
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		}

		if (System.currentTimeMillis() > this.timerMissile + Constante.TEMPS_ENTRE_DEUX_MISSILES) {
			missiles.add(this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile));
			this.timerMissile = System.currentTimeMillis();
		}
	}

	public List<Missile> recupererMissiles() {
		return this.missiles;
	}

	public void deplacerMissiles() {
		if (this.aUnMissile()) {
			for (int i = 0; i < missiles.size(); i++) {
				missiles.get(i).deplacerVerticalementVers(Direction.HAUT_ECRAN);
				if (!estDansEspaceJeu(missiles.get(i).abscisseLaPlusAGauche(),
						missiles.get(i).ordonneeLaPlusBasse() - 1)) {
					missiles.remove(i);
				}
			}
		}
	}

	public void tournerEnvahisseurs() {
		for (int i = 0; i < this.envahisseurs.size(); i++) {
			if (null != envahisseurs.get(i)) {
				envahisseurs.get(i).tourner();
				envahisseurs.get(i).deplacerVerticalementVers(Direction.BAS_ECRAN);
			}
		}
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse,
			Direction direction) {
		if (direction != Direction.GAUCHE && direction != Direction.DROITE) {
			throw new EnvahisseurException("La direction de départ de l'envahisseur doit être horizontale");
		}

		verificationPositionEspaceJeu(dimension, position);

		envahisseurs.add(new Envahisseur(dimension, position, vitesse, direction));

	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse) {
		this.positionnerUnNouveauEnvahisseur(dimension, position, vitesse, Direction.GAUCHE);
	}

	// Pour éviter la dupplication de code
	private void verificationPositionEspaceJeu(Dimension dimension, Position position) {
		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y)) {
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");
		}

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y)) {
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		}
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1)) {
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");
		}
	}

	private void deplacerMissilesEnvahisseurs() {
		if (this.aUnMissileEnvahisseur()) {
			for (int i = 0; i < missilesEnvahisseurs.size(); i++) {
				missilesEnvahisseurs.get(i).deplacerVerticalementVers(Direction.BAS_ECRAN);
				if (!estDansEspaceJeu(missilesEnvahisseurs.get(i).abscisseLaPlusAGauche(),
						missilesEnvahisseurs.get(i).ordonneeLaPlusHaute() - 1)) {
					missilesEnvahisseurs.remove(i);
				}
			}
		}
	}

	private void augmenterScore(int difference) {
		if (difference > 0) {
			this.score += difference;
		}
	}

	public int getScore() {
		return this.score;
	}

	public void envahisseurAleatoireTirerMissile(Dimension dimensionMissile, int vitesse) {
		if (System.currentTimeMillis() > this.timerMissileEnvahisseur
				+ Constante.TEMPS_ENTRE_DEUX_MISSILES_ENVAHISSEUR) {

			int i = ThreadLocalRandom.current().nextInt(this.envahisseurs.size());
			this.missilesEnvahisseurs.add(this.envahisseurs.get(i).tirerUnMissile(dimensionMissile, vitesse));
			this.timerMissileEnvahisseur = System.currentTimeMillis();
		}
	}

	public List<Missile> recupererMissilesEnvahisseur() {
		return this.missilesEnvahisseurs;
	}

	public boolean isVaisseauDetruit() {
		return this.vaisseauDetruit;
	}

	private void detecterCollisionMissileVaisseau() {
		int i = 0;
		while (this.continuerJeu && i < missilesEnvahisseurs.size()) {
			if (null != missilesEnvahisseurs.get(i)
					&& Collision.detecterCollision(missilesEnvahisseurs.get(i), this.vaisseau)) {
				this.vaisseauDetruit = true;
				this.finirJeu();
				break;
			}
			i++;
		}
	}

	private void detecterCollisionMissileEnvahisseur() {
		int i = 0;
		int j = 0;
		while (this.continuerJeu && i < missiles.size()) {
			while (this.continuerJeu && j < envahisseurs.size()) {
				if (null != missiles.get(i) && null != envahisseurs.get(j)
						&& Collision.detecterCollision(missiles.get(i), envahisseurs.get(j))) {
					envahisseurs.remove(j);
					missiles.remove(i);
					this.augmenterScore(Constante.GAIN_ENVAHISSEUR_DETRUIT);
					if (envahisseurs.isEmpty()) {
						this.finirJeu();
					}
				}
				j++;
			}
			i++;
		}
	}

	private void evoluerCommandeUtilisateur(Commande commandeUser) {
		if (commandeUser.droite) {
			this.deplacerVaisseauVersLaDroite();
		}

		if (commandeUser.gauche) {
			this.deplacerVaisseauVersLaGauche();
		}

		if (commandeUser.tir && System.currentTimeMillis() > this.timerMissile + 500) {
			this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
					Constante.MISSILE_VITESSE);
			this.timerMissile = System.currentTimeMillis();
		}
	}

	@Override
	public void evoluer(Commande commandeUser) {
		if (this.continuerJeu) {
			if (null != commandeUser) {
				evoluerCommandeUtilisateur(commandeUser);
			}

			if (this.aUnMissile()) {
				this.deplacerMissiles();
			}

			if (this.aUnMissileEnvahisseur()) {
				this.deplacerMissilesEnvahisseurs();
			}

			if (this.aUnEnvahisseur() && this.aUnMissile()) {
				this.detecterCollisionMissileEnvahisseur();
			}

			if (this.aUnEnvahisseur()) {
				this.deplacerEnvahisseur();
				this.envahisseurAleatoireTirerMissile(
						new Dimension(Constante.MISSILE_ENVAHISSEUR_LONGUEUR, Constante.MISSILE_ENVAHISSEUR_HAUTEUR),
						Constante.MISSILE_ENVAHISSEUR_VITESSE);
			}
		}

		if (this.aUnMissileEnvahisseur()) {
			detecterCollisionMissileVaisseau();
		}
	}

	@Override
	public boolean etreFini() {
		return !continuerJeu;
	}
}
