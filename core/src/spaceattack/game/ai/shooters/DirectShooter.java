package spaceattack.game.ai.shooters;

public class DirectShooter extends AbstractShooter
{
	@Override
	public ShooterType getType()
	{
		return ShooterType.DIRECT_SHOOTER;
	}
}
