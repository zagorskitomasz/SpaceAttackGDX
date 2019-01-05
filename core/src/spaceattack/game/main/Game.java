package spaceattack.game.main;

import com.zagorskidev.spaceattack.graphics.Textures;
import com.zagorskidev.spaceattack.sound.Sounds;
import com.zagorskidev.spaceattack.stages.IStage;
import com.zagorskidev.spaceattack.stages.StageResult;
import com.zagorskidev.spaceattack.stages.Stages;
import com.zagorskidev.spaceattack.system.GameLoader;
import com.zagorskidev.spaceattack.system.GameProgress;

import spaceattack.game.stages.StageBuilder;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.ExtUtils;

class Game implements SpaceAttackGame
{
	private boolean isTest;

	private ExtUtils extUtils;
	private GameLoader gameLoader;
	private StageBuilder stageBuilder;
	private FrameController frameController;

	private IStage stage;

	public Game(boolean isTest)
	{
		this.isTest = isTest;
	}

	public void setExtUtils(ExtUtils extUtils)
	{
		this.extUtils = extUtils;
	}

	public void setGameLoader(GameLoader gameLoader)
	{
		this.gameLoader = gameLoader;
	}

	public void setStageBuilder(StageBuilder stageBuilder)
	{
		this.stageBuilder = stageBuilder;
	}

	public void setFrameController(FrameController frameController)
	{
		this.frameController = frameController;
	}

	@Override
	public void create()
	{
		loadMedia();
		StageResult defaultResult = prepareInitialResult();
		stage = stageBuilder.getStage(defaultResult);
	}

	private void loadMedia()
	{
		if (isTest)
		{
			Textures.loadForTest();
			Sounds.loadForTest();
		}
		else
		{
			Sounds.load();
			Textures.load();
		}
	}

	private StageResult prepareInitialResult()
	{
		StageResult defaultResult = new StageResult();
		GameProgress progress = gameLoader.load();
		defaultResult.setGameProgress(progress);

		return defaultResult;
	}

	@Override
	public void render()
	{
		extUtils.clearScreen();

		if (checkFrame())
			stage.act(extUtils.getDeltaTime());

		stage.draw();

		checkStageSwitch();
	}

	private boolean checkFrame()
	{
		return frameController.check();
	}

	private void checkStageSwitch()
	{
		if (stage.isCompleted())
		{
			StageResult result = stage.getResult();
			stage = stageBuilder.getStage(result);
		}
	}

	@Override
	public void resize(int width,int height)
	{
		if (stage == null)
			return;

		stage.updateViewport(width, height, true);
	}

	Stages getCurrentStage()
	{
		return stage.getType();
	}
}
