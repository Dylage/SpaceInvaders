package fr.unilim.iut.spaceinvaders;

import org.junit.Before;
import org.junit.Test;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class VaisseauTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}

	@Test(expected = MissileException.class)
	public void test_PasAssezDePlacePourTirerUnMissile_UneExceptionEstLevee() throws Exception {
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(7, 2), new Position(5, 9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(7, 9), 1);
	}

	@Test(expected = MissileException.class)
	public void test_LongueurMissileSuperieureALongueurVaisseau_UneExceptionEstLevee() throws Exception {
		Vaisseau vaisseau = new Vaisseau(new Dimension(5, 2), new Position(5, 9), 1);
		vaisseau.tirerUnMissile(new Dimension(7, 2), 1);
	}

}