package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Direction;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;

public class EnvahisseurTest {
	
	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	@Test
	public void test_LEnvahisseurTourneCorrectement() {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3,2), new Position(7,2), 1);
		spaceinvaders.tournerEnvahisseur();
		assertEquals(Direction.DROITE, spaceinvaders.recupererEnvahisseur().getDirectionActuelle());
	}
}
