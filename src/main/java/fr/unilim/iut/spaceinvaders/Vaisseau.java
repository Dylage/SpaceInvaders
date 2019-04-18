package fr.unilim.iut.spaceinvaders;

public class Vaisseau {

	Position origine;
	Dimension dimension;
	private int vitesse;

	public Vaisseau(int longueur, int hauteur, int x, int y) {
		this(new Dimension(longueur, hauteur), new Position(x, y));
	}

	public Vaisseau(int longueur, int hauteur) {
		this(longueur, hauteur, 0, 0);
	}

	public Vaisseau(Dimension dimension, Position positionOrigine) {
		this(dimension, positionOrigine, 1);
	}
	
	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		this.dimension = dimension;
		this.origine = positionOrigine;
		this.vitesse = vitesse;
	}

	public int abscisseLaPlusAGauche() {
		return this.origine.abscisse();
	}

	public boolean occupeLaPosition(int x, int y) {
		return (estAbscisseCouverte(x) && estOrdonneeCouverte(y));
	}

	private boolean estOrdonneeCouverte(int y) {
		return ordonneeLaPlusBasse(y) && ordonneeLaPlusHaute(y);
	}

	private boolean ordonneeLaPlusHaute(int y) {
		return y <= this.origine.ordonnee();
	}

	private boolean ordonneeLaPlusBasse(int y) {
		return this.origine.ordonnee() - this.dimension.hauteur() + 1 <= y;
	}

	public int ordonneeLaPlusBasse() {
		return this.origine.ordonnee() - this.dimension.hauteur() + 1;
	}
	
	public int ordonneeLaPlusHaute() {
		return this.origine.ordonnee();
	}


	private boolean estAbscisseCouverte(int x) {
		return (abscisseLaPlusAGauche() <= x) && (x <= abscisseLaPlusADroite());
	}

	public int abscisseLaPlusADroite() {
		return this.origine.abscisse() + this.dimension.longueur() - 1;
	}

	public void seDeplacerVersLaDroite() {
		this.origine.changerAbscisse(this.origine.abscisse() + vitesse);
	}

	public void seDeplacerVersLaGauche() {
		this.origine.changerAbscisse(this.origine.abscisse() - vitesse);
	}

	public void positionner(int x, int y) {
		this.origine.changerAbscisse(x);
		this.origine.changerOrdonnee(y);
	}

	public int longueur() {
		return this.dimension.longueur();
	}

	public int hauteur() {
		return this.dimension.hauteur();
	}
}
