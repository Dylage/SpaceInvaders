package fr.unilim.iut.spaceinvaders.model;

public class Collision {

	public static boolean detecterCollision(Sprite sp1, Sprite sp2) {
		return collisionAbscisse(sp1, sp2) && collisionOrdonnee(sp1, sp2);
	}

	private static boolean collisionAbscisse(Sprite sp1, Sprite sp2) {
		if (null != sp1 && null != sp2) {
			Sprite grand;
			Sprite petit;
			if (sp1.longueur() > sp2.longueur()) {
				grand = sp1;
				petit = sp2;
			} else {
				grand = sp2;
				petit = sp1;
			}
			return grand.abscisseEstDansCeSprite(petit.abscisseLaPlusAGauche())
					|| grand.abscisseEstDansCeSprite(petit.abscisseLaPlusADroite());
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
		return grand.ordonneEstDansCeSprite(petit.ordonneeLaPlusBasse())
				|| grand.ordonneEstDansCeSprite(petit.ordonneeLaPlusHaute());
	}
}
