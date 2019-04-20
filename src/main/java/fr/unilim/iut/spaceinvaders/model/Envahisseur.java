package fr.unilim.iut.spaceinvaders.model;

public class Envahisseur extends Sprite {
	private Direction directionActuelle;

	public Envahisseur(Dimension dimension, Position positionOrigine, int vitesse, Direction direction) {
		super(dimension, positionOrigine, vitesse);
		this.directionActuelle=direction;
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
	
}
