package spaceattack.game.stages;

import java.util.List;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.system.GameSaver;

public abstract class AbstractStage implements IGameStage
{
	protected IStage stage;

	private GameSaver gameSaver;
	private Stages type;
	private StageResult result;
	private GameProgress gameProgress;
	private GameProgress progressBackup;

	void setStage(IStage stage)
	{
		this.stage = stage;
	}

	void setGameSaver(GameSaver saver)
	{
		gameSaver = saver;
	}

	@Override
	public boolean isCompleted()
	{
		return result != null;
	}

	@Override
	public StageResult getResult()
	{
		return result;
	}

	@Override
	public void setResult(StageResult result)
	{
		this.result = result;

		if (result.getGameProgress() != null && !result.getGameProgress().equals(progressBackup))
			gameSaver.save(result.getGameProgress());
	}

	@Override
	public Stages getType()
	{
		return type;
	}

	@Override
	public void setType(Stages type)
	{
		this.type = type;
	}

	@Override
	public GameProgress getGameProgress()
	{
		return gameProgress;
	}

	@Override
	public void setGameProgress(GameProgress gameProgress)
	{
		this.gameProgress = gameProgress;

		if (progressBackup == null)
			progressBackup = gameProgress.clone();
	}

	@Override
	public void addActorBeforeGUI(IGameActor newActor)
	{
		stage.addActorAtBegining(newActor);
	}

	@Override
	public void addActor(IGameActor actor)
	{
		stage.addActor(actor);
	}

	protected GameProgress getProgressBackup()
	{
		return progressBackup;
	}

	@Override
	public void act(float delta)
	{
		stage.act(delta);
	}

	@Override
	public void draw()
	{
		stage.draw();
	}

	@Override
	public List<IGameActor> getActors()
	{
		return stage.getGameActors();
	}

	@Override
	public void updateViewport(int width,int height,boolean centerCamera)
	{
		stage.updateViewport(width, height, centerCamera);
	}

	@Override
	public IStage getStage()
	{
		return stage;
	}
}
