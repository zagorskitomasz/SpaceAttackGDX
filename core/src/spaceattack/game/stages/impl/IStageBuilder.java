package spaceattack.game.stages.impl;

import spaceattack.game.GameProgress;
import spaceattack.game.stages.IGameStage;

public interface IStageBuilder
{
	public IGameStage build(GameProgress progress);
}
