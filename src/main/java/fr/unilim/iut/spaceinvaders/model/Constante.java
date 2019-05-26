package fr.unilim.iut.spaceinvaders.model;

public class Constante {

	// Caracteres du pour l'affichage ASCII
	public static final char MARQUE_FIN_LIGNE = '\n';
	public static final char MARQUE_VIDE = '.';
	public static final char MARQUE_VAISSEAU = 'V';
	public static final char MARQUE_MISSILE = 'M';
	public static final char MARQUE_ENVAHISSEUR = 'X';
	public static final char MARQUE_MISSILE_ENVAHISSEUR = 'I';

	// Dimensions du jeu
	public static final int ESPACEJEU_LONGUEUR = 500;
	public static final int ESPACEJEU_HAUTEUR = 700;

	// Dimensions et vitesse du Vaisseau du joueur
	public static final int VAISSEAU_LONGUEUR = 30;
	public static final int VAISSEAU_HAUTEUR = 15;
	public static final int VAISSEAU_VITESSE = 10;

	// Dimensions et vitesse des missiles tirés
	public static final int MISSILE_LONGUEUR = 7;
	public static final int MISSILE_HAUTEUR = 20;
	public static final int MISSILE_VITESSE = 20;

	// Dimensions et vitesse de l'envahisseur
	public static final int ENVAHISSEUR_LONGUEUR = 30;
	public static final int ENVAHISSEUR_HAUTEUR = 20;
	public static final int ENVAHISSEUR_VITESSE = 6;

	/**
	 * Temps entre deux tirs de missiles, en millisecondes
	 * 
	 */
	public static final long TEMPS_ENTRE_DEUX_MISSILES = 500;

	/**
	 * Gain de score pour la destruction d'un envahisseur
	 */
	public static final int GAIN_ENVAHISSEUR_DETRUIT = 1;

	/**
	 * Position du score dans le GUI
	 */
	public static final int SCORE_POSITION_X = 20;
	public static final int SCORE_POSITION_Y = 9;

	// Constantes relatives aux missile ENVAHISSEUR

	public static final int TEMPS_ENTRE_DEUX_MISSILES_ENVAHISSEUR = 900;
	public static final int MISSILE_ENVAHISSEUR_LONGUEUR = 5;
	public static final int MISSILE_ENVAHISSEUR_HAUTEUR = 9;
	public static final int MISSILE_ENVAHISSEUR_VITESSE = 10;
	
	//Messages affichés à la fin du jeu
	public static final String MESSAGE_DE_DEFAITE = "Dommage... !";
	public static final String MESSAGE_DE_VICTOIRE = "Félicitations !";

}
