package fr.unilim.iut.spaceinvaders;

public class SpaceInvaders {
	int longueur;
	int hauteur;
	Vaisseau vaisseau;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public void positionnerUnNouveauVaisseau(int x, int y) {
		this.vaisseau = new Vaisseau(x, y);

	}

	private boolean aUnVaisseau() {
		return null != vaisseau;
	}
	
	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {
		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);
	}
	
	@Override
	public String toString() {
		StringBuilder espaceDeJeu = new StringBuilder();
		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				if (aUnVaisseauQuiOccupeLaPosition(x, y)) {
					espaceDeJeu.append('V');
				} else {
					espaceDeJeu.append('.');
				}
			}
			espaceDeJeu.append('\n');
		}
		return espaceDeJeu.toString();
	}

}
