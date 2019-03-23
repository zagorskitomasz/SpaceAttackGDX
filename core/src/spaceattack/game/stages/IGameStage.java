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
	Stages getType();

	boolean isCompleted();

	void act(float delta);

	void draw();

	List<IGameActor> getActors();

	void setType(Stages type);

	StageResult getResult();

	void setResult(StageResult result);

	GameProgress getGameProgress();

	void setGameProgress(GameProgress gameProgress);

	IInputProcessor getInputProcessor();

	void addActor(IGameActor actor);

	void addActorBeforeGUI(IGameActor actor);

	void updateViewport(int width,int height,boolean b);

	IStage getStage();

	void updateControls();

	void addButtonsEnabledPredicate(IButton button,Predicate<IButton> predicate);

	void addButtonsVisiblePredicate(IButton button,Predicate<IButton> predicate);

	void addButtonsTextFunction(IButton button,Function<IButton, String> predicate);

	void addBackground(StaticImage background);
}
