package spaceattack.game.weapons.rocketMissile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.actors.FakeActor;
import spaceattack.game.actors.IActorFactory;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeaponController;
import spaceattack.game.weapons.MissilesLauncher;
import spaceattack.game.weapons.missiles.ExplosiveMissile;

public class RocketMissileTest {

    @Mock
    private MissilesLauncher launcher;

    @Mock
    private FrameController checker;

    @Mock
    private IWeaponController controller;

    @Mock
    private IActorFactory actorFactory;

    @InjectMocks
    private RocketMissile missile;

    @Before
    public void setUp() {

        Textures.loadForTest();
        Animations.loadForTest();

        missile = new RocketMissile(10);
        MockitoAnnotations.initMocks(this);

        Factories.setActorFactory(actorFactory);
        doReturn(true).when(checker).check();
        doReturn(true).when(controller).takeEnergy(anyFloat());
        doReturn(new FakeActor()).when(actorFactory).create(any(IGameActor.class));
        doReturn(ExtVectorFactory.INSTANCE.create(10, 10)).when(controller).getSecondaryWeaponUsePlacement();
    }

    @Test
    public void explosiveMissileIsShot() {

        missile.use();
        verify(launcher).launch(any(ExplosiveMissile.class));
    }
}
