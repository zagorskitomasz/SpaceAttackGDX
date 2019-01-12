package spaceattack.game.stages.impl;

import spaceattack.game.GameProgress;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.AbstractStage;
import spaceattack.game.system.notifiers.IObserver;

public class GameplayStage extends AbstractStage implements IObserver<GameProgress>
{
	private IInputProcessor inputProcessor;

	void setInputProcessor(IInputProcessor processor)
	{
		inputProcessor = processor;
	}

	@Override
	public IInputProcessor getInputProcessor()
	{
		return inputProcessor;
	}

	@Override
	public void notify(GameProgress state)
	{
		if (state.getLevel() > getProgressBackup().getLevel())
			showLevelUpLabel();
	}

	private void showLevelUpLabel()
	{
		// TODO Auto-generated method stub

	}
}
