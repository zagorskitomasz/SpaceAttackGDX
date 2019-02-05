package spaceattack.game.weapons.missiles;

import spaceattack.game.factories.Factories;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;

public enum ExplosionsBuilder
{
	INSTANCE;

	public Explosion createFighterExplosion(GameplayStage stage)
	{
		int strength = stage.getGameProgress().getMission();

		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setAnimation(Animations.FIGHTER_EX.getAnimation());
		explosion.setDmg(strength * 10);
		explosion.setFireDmg(strength * 2);
		explosion.setFireDuration(strength * 500);
		explosion.setRadius(1500);
		explosion.setSound(Sounds.SMALL_SHIP_EXPLOSION);
		explosion.setActors(stage.getActors());

		return explosion;
	}

	public Explosion createBossExplosion(GameplayStage stage)
	{
		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setAnimation(Animations.BOSS_EX.getAnimation());
		explosion.setDmg(0);
		explosion.setFireDmg(0);
		explosion.setFireDuration(0);
		explosion.setRadius(0);
		explosion.setSound(Sounds.BOSS_EXPLOSION);
		explosion.setActors(stage.getActors());

		return explosion;
	}
}
