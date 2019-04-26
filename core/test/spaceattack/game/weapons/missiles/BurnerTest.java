package spaceattack.game.weapons.missiles;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;

public class BurnerTest {

    @Mock
    private ITexture frame;

    @Mock
    private IAnimation animation;

    @Mock
    private Ignitable ignitable;

    @Mock
    private IBatch batch;

    private FrameController controller;

    private Burner burner;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        controller = new FrameController(ExtUtilsFactory.INSTANCE.create(), 1);

        burner = new Burner();
        burner.setIgnitable(ignitable);
        burner.setBurningController(controller);
        burner.setBurningAnimation(animation);

        doReturn(frame).when(animation).getFrame();
    }

    @Test
    public void takesDamageWhenIgnited() {

        burner.ignite(10, 1000);
        burner.burn(1);

        verify(ignitable).takeDmg(anyFloat());
    }

    @Test
    public void damageIsTakenPartialy() {

        burner.ignite(30, 1000);
        burner.burn(0.5f);

        verify(ignitable).takeDmg(15);
    }

    @Test
    public void drawnWhenBurning() {

        burner.ignite(10, 1000);
        burner.draw(batch);

        verify(batch).draw(frame, 0, 0, 0, 0);
    }

    @Test
    public void notDrawWhenNotBurning() {

        burner.draw(batch);

        verify(batch, times(0)).draw(frame, 0, 0);
    }

    @Test
    public void afterDurationNotBurningAnymore() throws InterruptedException {

        burner.ignite(10, 100);
        Thread.sleep(101);

        burner.burn(10);

        verify(ignitable, times(0)).takeDmg(anyFloat());
    }
}
