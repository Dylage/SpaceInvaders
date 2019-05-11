package fr.unilim.iut.spaceinvaders.model;

public class Constante {

	// Caracteres du pour l'affichage ASCII
	public static final char MARQUE_FIN_LIGNE = '\n';
	public static final char MARQUE_VIDE = '.';
	public static final char MARQUE_VAISSEAU = 'V';
	public static final char MARQUE_MISSILE = 'M';
	public static final char MARQUE_ENVAHISSEUR = 'X';

	// Dimensions du jeu
	public static final int ESPACEJEU_LONGUEUR = 300;
	public static final int ESPACEJEU_HAUTEUR = 200;

	// Dimensions et vitesse du Vaisseau du joueur
	public static final int VAISSEAU_LONGUEUR = 30;
	public static final int VAISSEAU_HAUTEUR = 15;
	public static final int VAISSEAU_VITESSE = 10;

	// Dimensions et vitesse des missiles tirés
	public static final int MISSILE_LONGUEUR = 5;
	public static final int MISSILE_HAUTEUR = 10;
	public static final int MISSILE_VITESSE = 15;

	// Dimensions et vitesse de l'envahisseur
	public static final int ENVAHISSEUR_LONGUEUR = 20;
	public static final int ENVAHISSEUR_HAUTEUR = 7;
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

}
