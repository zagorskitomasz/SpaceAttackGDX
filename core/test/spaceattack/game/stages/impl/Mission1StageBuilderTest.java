package spaceattack.game.stages.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.factories.Factories;
import spaceattack.game.utils.IUtilsFactory;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.multiRedLaser.DoubleRedLaser;
import spaceattack.game.weapons.multiRedLaser.MassiveRedLaser;
import spaceattack.game.weapons.redLaser.RedLaser;

public class Mission1StageBuilderTest {

    private Mission1StageBuilder builder;

    @Mock
    private IUtilsFactory utilsFactory;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Factories.setUtilsFactory(utilsFactory);

        builder = new Mission1StageBuilder();
    }

    @Test
    public void simpleRedLaserIsPrimaryWeaponWithoutMastery() {

        IWeapon weapon = builder.createPrimaryWeapon(10, 0);

        assertTrue(weapon instanceof RedLaser);
    }

    @Test
    public void simpleRedLaserIsPrimaryWeaponWithLowMastery() {

        IWeapon weapon = builder.createPrimaryWeapon(10, 3);

        assertTrue(weapon instanceof RedLaser);
    }

    @Test
    public void doubleRedLaserIsPrimaryWeaponWithMediumMastery() {

        IWeapon weapon = builder.createPrimaryWeapon(10, 6);

        assertTrue(weapon instanceof DoubleRedLaser);
    }

    @Test
    public void massiveRedLaserIsPrimaryWeaponWithHighMastery() {

        IWeapon weapon = builder.createPrimaryWeapon(10, 9);

        assertTrue(weapon instanceof MassiveRedLaser);
    }
}
