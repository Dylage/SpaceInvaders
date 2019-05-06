package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.model.Dimension;
import fr.unilim.iut.spaceinvaders.model.Direction;
import fr.unilim.iut.spaceinvaders.model.Position;
import fr.unilim.iut.spaceinvaders.model.SpaceInvaders;
import fr.unilim.iut.spaceinvaders.utils.EnvahisseurException;

public class EnvahisseurTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test
	public void test_LEnvahisseurTourneCorrectement() {
		spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 2), 1);
		spaceinvaders.tournerEnvahisseurs();
		assertEquals(Direction.DROITE, spaceinvaders.recupererEnvahisseurs().get(0).getDirectionActuelle());
	}

	@Test
	public void test_DirectionDeLEnvahisseurNonConforme() throws Exception {
		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 2), 1, Direction.BAS);
			fail("Erreur de direction de l'envahisseur (BAS): devrait déclencher une exception EnvahisseurException");
		} catch (final EnvahisseurException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 2), 1, Direction.HAUT);
			fail("Erreur de direction de l'envahisseur (HAUT) : devrait déclencher une exception EnvahisseurException");
		} catch (final EnvahisseurException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 2), 1,
					Direction.BAS_ECRAN);
			fail("Erreur de direction de l'envahisseur (BAS_ECRAN) : devrait déclencher une exception EnvahisseurException");
		} catch (final EnvahisseurException e) {
		}

		try {
			spaceinvaders.positionnerUnNouveauEnvahisseur(new Dimension(3, 2), new Position(7, 2), 1,
					Direction.BAS_ECRAN);
			fail("Erreur de direction de l'envahisseur (HAUT_ECRAN) : devrait déclencher une exception EnvahisseurException");
		} catch (final EnvahisseurException e) {
		}
	}
}
