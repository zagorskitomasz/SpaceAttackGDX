package spaceattack.game.ships.enemy;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.ships.Ship;
import spaceattack.game.weapons.IWeaponController;

public class Fighter extends Ship implements IEnemyShip
{
	@SuppressWarnings("unused")
	private RadarVisible playerShip;

	private MoverAI mover;
	private ShooterAI shooter;
	private IWeaponController controller;

	@Override
	public void act(float delta)
	{
		super.act(delta);
		mover.updateDirection();
		performAttack();
	}

	void performAttack()
	{
		PossibleAttacks possibleAttack = shooter.checkShot();
		if (!PossibleAttacks.NONE.equals(possibleAttack))
			controller.performAttack(possibleAttack);
	}

	@Override
	public void setPlayerShip(RadarVisible playerShip)
	{
		this.playerShip = playerShip;
	}

	@Override
	public void setMover(MoverAI mover)
	{
		this.mover = mover;
	}

	@Override
	public void setShooter(ShooterAI shooter)
	{
		this.shooter = shooter;
	}

	@Override
	public IWeaponController getWeaponController()
	{
		return controller;
	}

	@Override
	public boolean isMoving()
	{
		return !engine.isDestinationReached();
	}

	@Override
	public void setWeaponController(IWeaponController controller)
	{
		this.controller = controller;
	}
}
