package spaceattack.game.stages;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.input.IInputProcessor;

public interface IGameStage
{
	public Stages getType();

	public boolean isCompleted();

	public void act(float delta);

	public void draw();

	public Iterable<IGameActor> getActors();

	public void setType(Stages type);

	public StageResult getResult();

	public void setResult(StageResult result);

	public GameProgress getGameProgress();

	public void setGameProgress(GameProgress gameProgress);

	public IInputProcessor getInputProcessor();

	public void addActor(IGameActor actor);

	public void addActorBeforeGUI(IGameActor actor);

	public void updateViewport(int width,int height,boolean b);
}
