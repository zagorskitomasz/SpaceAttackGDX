package spaceattack.game.weapons.missiles;

import spaceattack.game.factories.Factories;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.sound.Sounds;

public enum ExplosionsBuilder
{
	INSTANCE;

	public Explosion createShipExplosion(GameplayStage stage,int strength)
	{
		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setDmg(strength * 10);
		explosion.setFireDmg(strength * 2);
		explosion.setFireDuration(strength * 500);
		explosion.setRadius(1500);
		explosion.setSound(Sounds.SMALL_SHIP_EXPLOSION);
		explosion.setUtils(Factories.getUtilsFactory().create());
		explosion.setActors(stage.getActors());

		return explosion;
	}
}
