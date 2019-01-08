package spaceattack.game.stages.builders;

import spaceattack.game.GameProgress;
import spaceattack.game.stages.IGameStage;

public interface IStageBuilder
{
	public IGameStage build(GameProgress progress);
}
