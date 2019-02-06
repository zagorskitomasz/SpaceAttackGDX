package spaceattack.game.ships.enemy;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.ai.MoverAI;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.ai.movers.MoverType;
import spaceattack.game.ai.shooters.PossibleAttacks;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.Ship;
import spaceattack.game.weapons.IWeaponController;

public class BaseEnemyShip extends Ship implements IEnemyShip
{
	@SuppressWarnings("unused")
	private RadarVisible playerShip;

	private MoverAI mover;
	private ShooterAI shooter;
	private IWeaponController controller;

	private EnemyBar bar;

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

	@Override
	public void setActor(IActor actor)
	{
		super.setActor(actor);
		getActor().setPosition((float) (Math.random() * Sizes.GAME_WIDTH), Sizes.GAME_HEIGHT);
	}

	@Override
	public void setBar(EnemyBar bar)
	{
		this.bar = bar;
	}

	@Override
	public MoverType getMoverType()
	{
		return mover.getType();
	}

	@Override
	public void draw(IBatch batch,float alpha)
	{
		super.draw(batch, alpha);

		if (bar != null)
			bar.draw(batch);
	}
}
