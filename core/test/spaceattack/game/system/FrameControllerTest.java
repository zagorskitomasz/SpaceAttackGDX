package spaceattack.game.system;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.consts.Consts;
import spaceattack.game.utils.IUtils;

public class FrameControllerTest {

    @Mock
    private IUtils extUtils;

    private FrameController frameController;

    @Before
    public void setUp() {

        initMocks(this);
        frameController = new FrameController(extUtils, Consts.Metagame.FPS);
    }

    @Test
    public void ifIntervalGreaterThanThresholdReturnTrue() {

        doReturn(60000l).when(extUtils).getCurrentTime();
        frameController.check();
        doReturn(60000l + 1000 / Consts.Metagame.FPS + 1).when(extUtils).getCurrentTime();

        assertTrue(frameController.check());
    }

    @Test
    public void ifIntervalLesserThanThresholdReturnFalse() {

        doReturn(60000l).when(extUtils).getCurrentTime();
        frameController.check();
        doReturn(60000l + 1000 / Consts.Metagame.FPS - 1).when(extUtils).getCurrentTime();

        assertFalse(frameController.check());
    }
}
