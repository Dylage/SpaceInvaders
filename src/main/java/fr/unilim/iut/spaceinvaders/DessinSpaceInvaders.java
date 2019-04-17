package fr.unilim.iut.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.*;
import fr.unilim.iut.spaceinvaders.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.Vaisseau;;;

public class DessinSpaceInvaders implements DessinJeu {

	/**
	 * lien vers le jeu a afficher
	 */
	private SpaceInvaders jeu;

	@Override
	public void dessiner(BufferedImage image) {
		SpaceInvaders j = (SpaceInvaders) this.jeu;
		Position position = null;
		// Pour avoir la position du vaisseau
		for (int y = 0; y < j.hauteur; y++) {
			for (int x = 0; x < j.longueur; x++) {
				if (j.recupererEspaceJeuDansChaineASCII().equals(Character.toString(SpaceInvaders.MARQUE_VAISSEAU))) {
					position = new Position(x, y);
				}
			}
		}
		Graphics2D crayon = (Graphics2D) image.getGraphics();

		crayon.setColor(Color.black);
		if (null != position) {
			crayon.fillOval(position.abscisse(), position.ordonnee(), 1, 1);
		}
	}

}
