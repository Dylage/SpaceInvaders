package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.*;
import fr.unilim.iut.spaceinvaders.moteurjeu.*;

/**
 * lancement du moteur avec le jeu
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		

		// creation du jeu particulier et de son afficheur
		SpaceInvaders spaceInvaders = new SpaceInvaders(Constante.ESPACEJEU_LONGUEUR,Constante.ESPACEJEU_HAUTEUR);
		spaceInvaders.initialiserJeu();
		DessinSpaceInvaders aff = new DessinSpaceInvaders(spaceInvaders);

		// classe qui lance le moteur de jeu generique
		MoteurGraphique moteur = new MoteurGraphique(spaceInvaders, aff);
		moteur.lancerJeu(Constante.ESPACEJEU_LONGUEUR,Constante.ESPACEJEU_HAUTEUR);
	}

}
