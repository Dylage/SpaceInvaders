package fr.unilim.iut.spaceinvaders.model;

public abstract class Sprite {

	protected Position position;
	protected Dimension dimension;
	protected int vitesse;

	public Sprite(Dimension dimension, Position origine, int vitesse) {
		super();
		this.dimension = dimension;
		this.position = origine;
		this.vitesse = vitesse;
	}

	public Sprite() {
		super();
	}

	public int abscisseLaPlusAGauche() {
		return this.position.abscisse();
	}

	public boolean occupeLaPosition(int x, int y) {
		return (estAbscisseCouverte(x) && estOrdonneeCouverte(y));
	}

	private boolean estOrdonneeCouverte(int y) {
		return ordonneeLaPlusBasse(y) && ordonneeLaPlusHaute(y);
	}

	private boolean ordonneeLaPlusHaute(int y) {
		return y <= this.position.ordonnee();
	}

	private boolean ordonneeLaPlusBasse(int y) {
		return this.position.ordonnee() - this.dimension.hauteur() + 1 <= y;
	}

	public int ordonneeLaPlusBasse() {
		return this.position.ordonnee() - this.dimension.hauteur() + 1;
	}

	public int ordonneeLaPlusHaute() {
		return this.position.ordonnee();
	}

	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.position.abscisse() + this.dimension.longueur() - 1;
	}

	public void deplacerHorizontalementVers(Direction direction) {
		this.position.changerAbscisse(this.position.abscisse() + direction.valeur() * vitesse);
	}

	public void deplacerVerticalementVers(Direction direction) {
		this.position.changerOrdonnee(this.position.ordonnee() + direction.valeur() * vitesse);
	}

	public void positionner(int x, int y) {
		this.position.changerAbscisse(x);
		this.position.changerOrdonnee(y);
	}

	public int longueur() {
		return this.dimension.longueur();
	}

	public int hauteur() {
		return this.dimension.hauteur();
	}

	public boolean abscisseEstDansCeSprite(int abscisse) {
		if (abscisse >= this.abscisseLaPlusAGauche() && abscisse <= this.abscisseLaPlusADroite()) {
			return true;
		}
		return false;
	}
	
	public boolean ordonneEstDansCeSprite(int ordonnee) {
		if (ordonnee >= this.ordonneeLaPlusBasse() && ordonnee <= this.ordonneeLaPlusHaute()) {
			return true;
		}
		return false;
	}
}