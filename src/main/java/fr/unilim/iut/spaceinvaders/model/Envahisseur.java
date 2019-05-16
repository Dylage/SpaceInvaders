package fr.unilim.iut.spaceinvaders.model;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Envahisseur extends Sprite {
	private Direction directionActuelle;

	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse, Direction direction) {
		super(dimension, positionOrigine, vitesse);
		this.directionActuelle = direction;
	}

	public Direction getDirectionActuelle() {
		return this.directionActuelle;
	}

	public void tourner() {
		if (directionActuelle == Direction.GAUCHE) {
			directionActuelle = Direction.DROITE;
		} else {
			directionActuelle = Direction.GAUCHE;
		}
	}

	public void deplacerAutomatiquement() {
		this.deplacerHorizontalementVers(directionActuelle);
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if (dimensionMissile.longueur() > this.dimension.longueur) {
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le bas de l'espace jeu pour tirer le missile");
		}

		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(dimensionMissile);
		return new Missile(dimensionMissile, positionOrigineMissile, vitesseMissile);
	}

	private Position calculerLaPositionDeTirDuMissile(Dimension dimensionMissile) {
		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeOrigineMissile = this.ordonneeLaPlusHaute() + 1;
		Position positionOrigineMissile = new Position(abscisseOrigineMissile, ordonneeOrigineMissile);
		return positionOrigineMissile;
	}

}
