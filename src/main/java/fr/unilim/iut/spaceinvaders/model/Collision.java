package fr.unilim.iut.spaceinvaders.model;

import java.util.List;

public class Collision {

	public static boolean detecterCollision(Sprite sp1, Sprite sp2) {
		return collisionAbscisse(sp1, sp2) && collisionOrdonnee(sp1, sp2);
	}
	
	public static boolean detecterCollision(List<Missile> missiles, Sprite sp) {
		for (int i =0; i<missiles.size(); i++) {
			if (collisionAbscisse(missiles.get(i), sp) && collisionOrdonnee(missiles.get(i), sp)){
				return true;
			}
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
		return grand.abscisseEstDansCeSprite(petit.abscisseLaPlusAGauche())
				|| grand.abscisseEstDansCeSprite(petit.abscisseLaPlusADroite());
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
