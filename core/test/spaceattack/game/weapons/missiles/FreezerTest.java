package spaceattack.game.weapons.missiles;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.actors.interfaces.Freezable;
import spaceattack.game.system.FrameController;

public class FreezerTest {

    @Mock
    private Freezable freezable;

    @Mock
    private FrameController controller;

    @InjectMocks
    private Freezer freezer;

    @Before
    public void setUp() {

        freezer = new Freezer();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void unfreezeWhenControllerChecked() {

        doReturn(true).when(controller).check();
        freezer.act(10);

        verify(freezable).unfreeze();
    }

    @Test
    public void stillFrozenWhenControllerNotChecked() {

        doReturn(false).when(controller).check();
        freezer.act(10);

        verify(freezable, never()).unfreeze();
    }
}
