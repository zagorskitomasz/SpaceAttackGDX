package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.IActor;
import spaceattack.game.actors.interfaces.Explosive;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.weapons.MissilesLauncher;

public class ExplosiveMissile extends Missile implements Explosive
{
	private Launchable explosion;
	private MissilesLauncher launcher;

	@Override
	public void setExplosion(Launchable explosion)
	{
		this.explosion = explosion;
	}

	@Override
	public void setMissilesLauncher(MissilesLauncher launcher)
	{
		this.launcher = launcher;
	}

	@Override
	public void explode()
	{
		if (launcher == null || explosion == null)
			return;

		IActor actor = getActor();
		explosion.getActor().setPosition(actor.getX(), actor.getY());
		launcher.launch(explosion);
	}

	@Override
	public void setToKill()
	{
		if (!isToKill() && !isOutOfScreen())
			explode();

		super.setToKill();
	}
}
