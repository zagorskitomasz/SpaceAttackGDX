package spaceattack.game.system;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.GameProgress;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.utils.IUtils;

public class GameSaverTest {

    private IUtils utils;

    private static final String CONTENT = "{savedProgress:{class:java.util.HashMap,2:{mission:16,level:40,experience:163900,playerName:PlayerOne}}}";

    @Mock
    private IFileHandle file;

    private GameSaver saver;

    @Before
    public void setUp() {

        utils = spy(ExtUtilsFactory.INSTANCE.create());
        saver = new GameSaver();
        saver.setUtils(utils);
        initMocks(this);

        doReturn(new ByteArrayInputStream(CONTENT.getBytes())).when(file).read();
        doReturn(file).when(utils).loadFile(anyString());
    }

    @Test
    public void savingExistingPlayer() {

        GameProgress progress = new GameProgress();
        progress.setExperience(999888777666l);
        progress.setLevel(5);
        progress.setMission(3);
        progress.setPlayerName("PlayerOne");
        progress.setSlot(2);

        saver.save(progress);
        verify(file).writeString(
                "{savedProgress:{2:{mission:3,level:5,experience:999888777666,playerName:PlayerOne,attributes:{}}}}",
                false);
    }

    @Test
    public void savingNewPlayer() {

        GameProgress progress = new GameProgress();
        progress.setExperience(999888777666l);
        progress.setLevel(5);
        progress.setMission(3);
        progress.setPlayerName("PlayerTwo");
        progress.setSlot(3);

        saver.save(progress);
        verify(file).writeString(
                "{savedProgress:{2:{mission:16,level:40,experience:163900,playerName:PlayerOne,attributes:{}},3:{mission:3,level:5,experience:999888777666,playerName:PlayerTwo,attributes:{}}}}",
                false);
    }

    @Test
    public void clearing() {

        saver.clear();
        verify(file).writeString("{}", false);
    }

    @Test
    public void deleting() {

        String content = "{savedProgress:{2:{mission:16,level:40,experience:163900,playerName:PlayerOne,attributes:{}},3:{mission:3,level:5,experience:999888777666,playerName:PlayerTwo,attributes:{}}}}";
        doReturn(new ByteArrayInputStream(content.getBytes())).when(file).read();

        saver.delete(2);
        verify(file).writeString(
                "{savedProgress:{3:{mission:3,level:5,experience:999888777666,playerName:PlayerTwo,attributes:{}}}}",
                false);
    }

    @Test
    public void attributesAreSaved() {

        GameProgress progress = new GameProgress();
        progress.setExperience(999888777666l);
        progress.setLevel(5);
        progress.setMission(3);
        progress.setPlayerName("PlayerOne");
        progress.setSlot(2);
        progress.getAttributes().addFreePoints(5);
        progress.getAttributes().increase(Attribute.ARMORY);
        progress.getAttributes().increase(Attribute.ARMORY);
        progress.getAttributes().increase(Attribute.BATTERY);

        saver.save(progress);
        verify(file).writeString(
                "{savedProgress:{2:{mission:3,level:5,experience:999888777666,playerName:PlayerOne,attributes:{attributes:{BATTERY:9,ARMORY:10,ENGINE:8,SHIELDS:8},freePoints:2}}}}",
                false);
    }
}
