package spaceattack.game.stages;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import spaceattack.game.GameProgress;
import spaceattack.game.StageResult;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.buttons.IButton;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.system.graphics.StaticImage;

public interface IGameStage
{
	public Stages getType();

	public boolean isCompleted();

	public void act(float delta);

	public void draw();

	public List<IGameActor> getActors();

	public void setType(Stages type);

	public StageResult getResult();

	public void setResult(StageResult result);

	public GameProgress getGameProgress();

	public void setGameProgress(GameProgress gameProgress);

	public IInputProcessor getInputProcessor();

	public void addActor(IGameActor actor);

	public void addActorBeforeGUI(IGameActor actor);

	public void updateViewport(int width,int height,boolean b);

	public IStage getStage();

	public void updateControls();

	public void addButtonsEnabledPredicate(IButton button,Predicate<IButton> predicate);

	public void addButtonsVisiblePredicate(IButton button,Predicate<IButton> predicate);

	public void addButtonsTextFunction(IButton button,Function<IButton, String> predicate);

	public void addBackground(StaticImage background);
}
