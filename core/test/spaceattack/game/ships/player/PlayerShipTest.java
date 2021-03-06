package spaceattack.game.ships.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.engines.IEngine;
import spaceattack.game.factories.Factories;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.rpg.Improvements;
import spaceattack.game.ships.pools.HpPool;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.Burner;

public class PlayerShipTest {

    private PlayerShip ship;

    @Mock
    private IPool energyPool;

    @Mock
    private IPool hpPool;

    @Mock
    private IEngine engine;

    @Mock
    private IWeapon weapon;

    @Mock
    private ITexture texture;

    @Mock
    private Launchable explosion;

    @Mock
    private MissilesLauncher launcher;

    @Mock
    private IActor explosionActor;

    @Mock
    private IBatch batch;

    @Mock
    private Burner burner;

    @Before
    public void setUp() {

        Textures.loadForTest();
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        MockitoAnnotations.initMocks(this);

        explosionActor = new FakeActor();
        explosionActor.setX(800);
        explosionActor.setY(200);

        doReturn(100).when(texture).getWidth();
        doReturn(200).when(texture).getHeight();
        doReturn(explosionActor).when(explosion).getActor();

        ship = new PlayerShip();
        ship.setActor(new FakeActor());
        ship.setShipEngine(engine);
        ship.setHpPool(hpPool);
        ship.setEnergyPool(energyPool);
        ship.addWeapon(weapon);
        ship.setTexture(texture);
        ship.setExplosion(explosion);
        ship.setMissilesLauncher(launcher);
        ship.setBurner(burner);

        Improvements imps = new Improvements();
        imps.addFreePoints(10);
        ship.setImprovements(imps);
    }

    @Test
    public void isSettingCenterCoords() {

        IBatch batch = mock(IBatch.class);

        ship.draw(batch, 0);

        verify(batch).draw(texture, 310, 412, 100, 200);
    }

    @Test
    public void radius() {

        doReturn(100).when(texture).getWidth();
        doReturn(100).when(texture).getHeight();

        assertEquals(50f, ship.getRadius(), 0);
    }

    @Test
    public void afterDmgHigherThanHpPoolIsToKill() {

        IPool hpPool = new HpPool(10);

        ship.setHpPool(hpPool);
        ship.takeDmg(100);

        assertTrue(ship.isToKill());
    }

    @Test
    public void actingIsUpdatingShipsConponents() {

        ship.act(0);

        verify(engine).fly();
        verify(energyPool).update();
        verify(hpPool).update();
    }

    @Test
    public void explodeWhenDestroyed() {

        ship.setToKill();

        verify(launcher).launch(explosion);
    }

    @Test
    public void wontExplodeOutOfScreen() {

        ship.setX(-800);
        ship.setToKill();

        verify(launcher, times(0)).launch(explosion);
    }

    @Test
    public void explosionHasShipCoords() {

        ship.setToKill();

        assertEquals(360, explosionActor.getX(), 0);
        assertEquals(512, explosionActor.getY(), 0);
    }

    @Test
    public void burnIsDrawn() {

        ship.draw(batch, 0);

        verify(burner).draw(batch);
    }

    @Test
    public void burningWhenActing() {

        ship.act(0);

        verify(burner).burn(0);
    }

    @Test
    public void additionalSpeedFactorFromAdrenalineWhenLowHp() {

        doReturn(10f).when(hpPool).getAmount();
        doReturn(30f).when(hpPool).getMaxAmount();

        ship.getImprovements().increase(Improvement.ADRENALINE);
        ship.getImprovements().increase(Improvement.ADRENALINE);

        float factor = ship.getAdditionalSpeedFactor();

        assertEquals(1.4f, factor, 0);
    }

    @Test
    public void noAdditionalSpeedFactorFromAdrenalineWhenHighHp() {

        doReturn(20f).when(hpPool).getAmount();
        doReturn(30f).when(hpPool).getMaxAmount();

        ship.getImprovements().increase(Improvement.ADRENALINE);
        ship.getImprovements().increase(Improvement.ADRENALINE);

        float factor = ship.getAdditionalSpeedFactor();

        assertEquals(1f, factor, 0);
    }

    @Test
    public void noAdditionalLowHpSpeedFactorWhenNoAdrenaline() {

        doReturn(10f).when(hpPool).getAmount();
        doReturn(30f).when(hpPool).getMaxAmount();

        float factor = ship.getAdditionalSpeedFactor();

        assertEquals(1f, factor, 0);
    }

    @Test
    public void gainingBerserkLevel() {

        ship.getImprovements().increase(Improvement.BERSERKER);
        ship.gainBerserk();
        assertEquals(1, ship.getBerserkerLevel());
    }

    @Test
    public void berserkLevelIsLimited() {

        ship.getImprovements().increase(Improvement.BERSERKER);
        ship.gainBerserk();
        ship.gainBerserk();
        ship.gainBerserk();
        ship.gainBerserk();
        ship.gainBerserk();

        assertEquals(3, ship.getBerserkerLevel());
    }

    @Test
    public void actingWithIntervalCancelsBerserk() throws InterruptedException {

        ship.gainBerserk();
        ship.gainBerserk();

        Thread.sleep(2200);
        ship.act(0);

        assertEquals(0, ship.getBerserkerLevel());
    }

    @Test
    public void berserkIncreasesSpeedFactor() {

        ship.getImprovements().increase(Improvement.BERSERKER);
        ship.gainBerserk();
        ship.gainBerserk();

        float factor = ship.getAdditionalSpeedFactor();

        assertEquals(1.8f, factor, 0);
    }
}
