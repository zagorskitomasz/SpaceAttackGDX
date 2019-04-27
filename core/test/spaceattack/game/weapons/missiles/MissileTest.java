package spaceattack.game.weapons.missiles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.buttons.weapon.IFireButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.ships.player.PlayerShip;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.vector.IVectorFactory;

public class MissileTest {

    private IVectorFactory factory;

    private Missile missile;

    @Mock
    private ITexture texture;

    @Mock
    private PlayerShip ship;

    @Mock
    private IFireButton button;

    private List<IGameActor> actors;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        factory = ExtVectorFactory.INSTANCE;
        Factories.setVectorFactory(factory);
        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);

        missile = new Missile();
        missile.setActor(new FakeActor());
        missile.setTexture(texture);
        missile.setDmg(10);
        missile.setSpeed(10);
        missile.setAcceleration(3);
        missile.setMovement(factory.create(1, 0));
        missile.setPosition(factory.create(200, 200));
        missile.setSound(Sounds.RED_LASER);

        actors = new ArrayList<>();
        actors.add(ship);
        actors.add(button);

        missile.setActors(actors);

        doReturn(factory.create(100, 100)).when(ship).getPosition();
        doReturn(10f).when(ship).getRadius();
        doReturn(20).when(texture).getHeight();
    }

    @Test
    public void missileIsMoving() {

        missile.act(0);
        missile.act(0);
        missile.act(0);

        assertEquals(factory.create(239, 200), missile.getPosition());
    }

    @Test
    public void missileIsHittingVulnerableTarget() {

        doReturn(factory.create(200, 200)).when(ship).getPosition();

        missile.act(0);

        verify(ship).takeDmg(10f);
    }

    @Test
    public void isNotHittingOutOfRange() {

        missile.act(0);

        verify(ship, times(0)).takeDmg(10f);
    }

    @Test
    public void missileIsDissapearingAfterHit() {

        doReturn(factory.create(200, 200)).when(ship).getPosition();

        missile.act(0);

        assertTrue(missile.isToKill());
    }
}
