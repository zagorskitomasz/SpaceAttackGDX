package spaceattack.game.weapons.missiles;

import spaceattack.game.factories.Factories;
import spaceattack.game.stages.IGameStage;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;

public enum ExplosionsBuilder
{
	INSTANCE;

	public Explosion createFighterExplosion(IGameStage stage)
	{
		int strength = stage.getGameProgress().getMission();

		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setAnimation(Animations.FIGHTER_EX.getAnimation());
		explosion.setDmg(strength * 20);
		explosion.setFireDmg(10 + strength * 2);
		explosion.setFireDuration(4000 + strength * 500);
		explosion.setRadius(75);
		explosion.setSound(Sounds.SMALL_SHIP_EXPLOSION);

		return explosion;
	}

	public Explosion createBossExplosion()
	{
		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setAnimation(Animations.BOSS_EX.getAnimation());
		explosion.setDmg(0);
		explosion.setFireDmg(0);
		explosion.setFireDuration(1000);
		explosion.setRadius(0);
		explosion.setSound(Sounds.BOSS_EXPLOSION);

		return explosion;
	}

	public Explosion createMissileExplosion(int strength)
	{
		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setAnimation(Animations.MISSILE_EX.getAnimation());
		explosion.setDmg(strength * 20);
		explosion.setFireDmg(10 + strength * 2);
		explosion.setFireDuration(4000 + strength * 500);
		explosion.setRadius(100);
		explosion.setSound(Sounds.MISSILE_EXPLOSION);

		return explosion;
	}
}
