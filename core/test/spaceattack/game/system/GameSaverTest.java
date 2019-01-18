package spaceattack.game.system;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.GameProgress;
import spaceattack.game.utils.IUtils;

public class GameSaverTest
{
	private IUtils utils;

	private String fileContent;

	@Mock
	private IFileHandle file;

	private GameSaver saver;

	@Before
	public void setUp()
	{
		utils = spy(ExtUtilsFactory.INSTANCE.create());
		saver = new GameSaver();
		saver.setUtils(utils);
		initMocks(this);

		fileContent = "{mission:3,level:5,experience:999888777666}";
		doReturn(file).when(utils).loadFile(anyString());
	}

	@Test
	public void gameProgressIsParsedProperly()
	{
		GameProgress progress = new GameProgress();
		progress.setExperience(999888777666l);
		progress.setLevel(5);
		progress.setMission(3);

		saver.save(progress);
		verify(file).writeString(fileContent, false);
	}
}
