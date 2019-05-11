package fr.unilim.iut.spaceinvaders.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.model.Vaisseau;
import fr.unilim.iut.spaceinvaders.moteurjeu.*;

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
		if (this.spaceInvaders.aUnMissile()) {
			List<Missile> missiles = this.spaceInvaders.recupererMissiles();
			for (int i = 0; i < missiles.size(); i++) {
				this.dessinerUnMissile(missiles.get(i), image);
			}
		}
		if (this.spaceInvaders.aUnEnvahisseur()) {
			List<Envahisseur> envahisseurs = this.spaceInvaders.recupererEnvahisseurs();
			for (int i = 0; i < envahisseurs.size(); i++) {
				this.dessinerUnEnvahisseur(envahisseurs.get(i), image);
			}
		}
		this.dessinerLeScore(spaceInvaders.getScore(), image);
	}

	private void dessinerUnEnvahisseur(Envahisseur envahisseur, BufferedImage image) {
		Graphics2D crayon = (Graphics2D) image.getGraphics();

		crayon.setColor(Color.red);
		crayon.fillRect(envahisseur.abscisseLaPlusAGauche(), envahisseur.ordonneeLaPlusBasse(), envahisseur.longueur(),
				envahisseur.hauteur());
	}

	private void dessinerUnVaisseau(Vaisseau vaisseau, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.gray);
		crayon.fillRect(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusBasse(), vaisseau.longueur(),
				vaisseau.hauteur());

	}

	private void dessinerUnMissile(Missile missile, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.gray);
		crayon.fillRect(missile.abscisseLaPlusAGauche(), missile.ordonneeLaPlusBasse(), missile.longueur(),
				missile.hauteur());

	}

	private void dessinerLeScore(int score, BufferedImage im) {
		Graphics2D crayon = (Graphics2D) im.getGraphics();

		crayon.setColor(Color.pink);
		crayon.drawString(Integer.toString(spaceInvaders.getScore()), Constante.SCORE_POSITION_X,
				Constante.SCORE_POSITION_Y);
	}

}
