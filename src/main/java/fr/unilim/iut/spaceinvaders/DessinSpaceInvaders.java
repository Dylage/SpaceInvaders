package fr.unilim.iut.spaceinvaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import fr.unilim.iut.spaceinvaders.moteurjeu.*;
import fr.unilim.iut.spaceinvaders.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.Vaisseau;

public class DessinSpaceInvaders implements DessinJeu {

	/**
	 * lien vers le jeu a afficher
	 */
	private SpaceInvaders spaceInvaders;

	public DessinSpaceInvaders(SpaceInvaders spaceInvaders) {
		this.spaceInvaders = spaceInvaders;
	}

	@Override
	public void dessiner(BufferedImage image) {
		// Pour avoir la position du vaisseau
		if (this.spaceInvaders.aUnVaisseau()) {
			Vaisseau vaisseau = this.spaceInvaders.recupererVaisseau();
			this.dessinerUnVaisseau(vaisseau, image);
		}
	}

	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.gray);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(),
				vaisseau.hauteur());

	}

}
