package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;
import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.EnvahisseurException;

import java.util.ArrayList;
import java.util.List;

import fr.unilim.iut.spaceinvaders.moteurjeu.*;

public class SpaceInvaders implements Jeu {
	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	List<Missile> missiles;
	Envahisseur envahisseur;
	boolean continuerJeu = true;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
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
		} else {
			marque = Constante.MARQUE_VIDE;
		}
		return marque;

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
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	public boolean aUnMissile() {
		if (null != missiles) {
			return !missiles.isEmpty();

		}
		return false;
	}

	public boolean aUnEnvahisseur() {
		return null != envahisseur;
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
		Position positionVaisseau = new Position(this.longueur / 2, this.hauteur - 1);
		Dimension dimensionVaisseau = new Dimension(Constante.VAISSEAU_LONGUEUR, Constante.VAISSEAU_HAUTEUR);
		positionnerUnNouveauVaisseau(dimensionVaisseau, positionVaisseau, Constante.VAISSEAU_VITESSE);

		Position positionEnvahisseur = new Position(this.longueur / 2, 30);
		Dimension dimensionEnvahisseur = new Dimension(Constante.ENVAHISSEUR_LONGUEUR, Constante.ENVAHISSEUR_HAUTEUR);
		positionnerUnNouveauEnvahisseur(dimensionEnvahisseur, positionEnvahisseur, Constante.ENVAHISSEUR_VITESSE,
				Direction.GAUCHE);
	}

	public Vaisseau recupererVaisseau() {
		return this.vaisseau;
	}

	public Envahisseur recupererEnvahisseur() {
		return this.envahisseur;
	}

	private void finirJeu() {
		this.continuerJeu = false;
	}

	public void deplacerEnvahisseur() {
		if (this.aUnEnvahisseur()) {
			if (envahisseur.abscisseLaPlusAGauche() <= 0) {
				envahisseur.tourner();
			}
			if (envahisseur.abscisseLaPlusADroite() + 1 >= (longueur)) {
				envahisseur.tourner();
			}
			envahisseur.deplacerAutomatiquement();
		}
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if(null==missiles) {
			this.missiles = new ArrayList<Missile>();
		}
		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur) {
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");
		}
		missiles.add(this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile));
	}

	public List<Missile> recupererMissiles() {
		return this.missiles;
	}

	public void deplacerMissiles() {
		if (this.aUnMissile()) {
			for (int i = 0; i < missiles.size(); i++) {
				missiles.get(i).deplacerVerticalementVers(Direction.HAUT_ECRAN);
				if (!estDansEspaceJeu(missiles.get(i).abscisseLaPlusAGauche(),
						missiles.get(i).ordonneeLaPlusHaute() - 1)) {
					missiles.remove(i);
				}
			}
		}
	}

	public void tournerEnvahisseur() {
		envahisseur.tourner();
	}

	public void positionnerUnNouveauEnvahisseur(Dimension dimension, Position position, int vitesse,
			Direction direction) {
		if (direction != Direction.GAUCHE && direction != Direction.DROITE) {
			throw new EnvahisseurException("La direction de départ de l'envahisseur doit être horizontale");
		}

		verificationPositionEspaceJeu(dimension, position);

		envahisseur = new Envahisseur(dimension, position, vitesse, direction);
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

	@Override
	public void evoluer(Commande commandeUser) {
		if (null != commandeUser) {
			if (commandeUser.droite) {
				this.deplacerVaisseauVersLaDroite();
			}

			if (commandeUser.gauche) {
				this.deplacerVaisseauVersLaGauche();
			}

			if (commandeUser.tir) {
				this.tirerUnMissile(new Dimension(Constante.MISSILE_LONGUEUR, Constante.MISSILE_HAUTEUR),
						Constante.MISSILE_VITESSE);
			}
		}

		if (this.aUnMissile()) {
			this.deplacerMissiles();
		}

		if (this.aUnEnvahisseur()) {
			this.deplacerEnvahisseur();
		}

		if (this.aUnEnvahisseur() && this.aUnMissile()) {
			for (int i = 0; i < missiles.size(); i++) {
				if (Collision.detecterCollision(missiles.get(i), envahisseur)) {
					finirJeu();
				}
			}
		}
	}

	@Override
	public boolean etreFini() {
		return !continuerJeu;
	}

}