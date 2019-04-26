package spaceattack.game.buttons;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.stages.IStage;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.IUtilsFactory;

public class ExitGameListenerTest {

    @Mock
    private IGameStage stage;

    @Mock
    private IUtils utils;

    @Mock
    private IUtilsFactory factory;

    private ExitGameListener listener;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        doReturn(utils).when(factory).create();
        Factories.setUtilsFactory(factory);

        listener = new ExitGameListener(stage);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void showsDialogWhenClicked() {

        listener.clicked();
        verify(utils).confirmDialog(anyString(), anyString(), nullable(IStage.class), any(Consumer.class));
    }

    @Test
    public void ifConfirmedDoExit() {

        listener.processExitDialogResult(false);
        verify(utils, times(0)).exit();
    }

    @Test
    public void ifNotConfirmedNotExit() {

        listener.processExitDialogResult(true);
        verify(utils).exit();
    }
}
