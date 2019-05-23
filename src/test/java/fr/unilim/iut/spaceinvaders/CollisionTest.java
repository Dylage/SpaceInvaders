package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Collision;
import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class CollisionTest {
	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_CollisionEntreMissileEtEnvahisseur() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(5, 2), new Position(7, 9), 1);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5, 2), new Position(7, 2), 1);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 1);
		for (int i = 0; i < 4; i++) {
			spaceinvaders.deplacerMissiles();
		}
		assertEquals(true, Collision.detecterCollision(spaceinvaders.recupererMissiles().get(0),
				spaceinvaders.recupererEnvahisseurs().get(0)));
	}

	@Test
	public void test_CollisionEntreMissileEtEnvahisseurDansLAutreSens() {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(5, 2), new Position(7, 9), 1);
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(5, 2), new Position(7, 2), 1);
		spaceinvaders.tirerUnMissile(new Dimension(3, 2), 1);
		for (int i = 0; i < 4; i++) {
			spaceinvaders.deplacerMissiles();
		}
		assertEquals(true, Collision.detecterCollision(spaceinvaders.recupererEnvahisseurs().get(0),
				spaceinvaders.recupererMissiles().get(0)));
	}
}
