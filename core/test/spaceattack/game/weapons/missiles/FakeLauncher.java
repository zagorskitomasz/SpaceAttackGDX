package spaceattack.game.weapons.missiles;

import java.util.ArrayList;
import java.util.List;

import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.weapons.MissilesLauncher;

public 	class FakeLauncher extends MissilesLauncher
{
	private List<Launchable> launched;
	
	public FakeLauncher(IGameStage stage) 
	{
		super(stage);
		launched = new ArrayList<>();
	}
	
	@Override
	public void launch(Launchable launchable)
	{
		launched.add(launchable);
	}
	
	public List<Launchable> getLaunched()
	{
		return launched;
	}
}