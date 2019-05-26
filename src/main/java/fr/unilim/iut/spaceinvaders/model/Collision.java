package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public static boolean detecterCollision(Sprite sp1, Sprite sp2) {
		if (null != sp1 && null != sp2) {
			return collisionAbscisse(sp1, sp2) && collisionOrdonnee(sp1, sp2);
		}
		return false;
	}

	private static boolean collisionAbscisse(Sprite sp1, Sprite sp2) {
		Sprite grand;
		Sprite petit;
		if (sp1.longueur() > sp2.longueur()) {
			grand = sp1;
			petit = sp2;
		} else {
			grand = sp2;
			petit = sp1;
		}
		// On teste pour l'abscisse la plus à gauche, la plus à droite, ou celle du
		// milieu
		if (grand.abscisseEstDansCeSprite(petit.abscisseLaPlusAGauche())
				|| grand.abscisseEstDansCeSprite(petit.abscisseLaPlusADroite())
				|| grand.abscisseEstDansCeSprite(petit.abscisseLaPlusAGauche() + (petit.longueur() / 2))) {
			return true;
		}
		return false;

	}

	private static boolean collisionOrdonnee(Sprite sp1, Sprite sp2) {
		Sprite grand;
		Sprite petit;
		if (sp1.hauteur() > sp2.hauteur()) {
			grand = sp1;
			petit = sp2;
		} else {
			grand = sp2;
			petit = sp1;
		}
		// On teste pour l'ordonnée la plus basse, la plus haute, ou celle du milieu
		if (grand.ordonneEstDansCeSprite(petit.ordonneeLaPlusBasse())
				|| grand.ordonneEstDansCeSprite(petit.ordonneeLaPlusHaute())
				|| grand.ordonneEstDansCeSprite(petit.ordonneeLaPlusHaute() + (petit.hauteur() / 2))) {
			return true;
		}
		return false;

	}
}
