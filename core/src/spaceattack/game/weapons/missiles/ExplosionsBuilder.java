package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;

public enum ExplosionsBuilder
{
	INSTANCE;

	public Explosion createFighterExplosion(GameplayStage stage)
	{
		int strength = stage.getCurrentMission() * 2;

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

	public Launchable createMissileExplosion(int strength)
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

	public Explosion createTankExplosion(GameplayStage stage)
	{
		int strength = stage.getCurrentMission() * 2;
		Explosion explosion = new Explosion();

		explosion.setActor(Factories.getActorFactory().create(explosion));
		explosion.setAnimation(Animations.TANK_EX.getAnimation());
		explosion.setDmg(strength * 40);
		explosion.setFireDmg(20 + strength * 4);
		explosion.setFireDuration(4000 + strength * 500);
		explosion.setRadius(130);
		explosion.setSound(Sounds.SMALL_SHIP_EXPLOSION);

		return explosion;
	}

	public Launchable createMineExplosion(int strength) 
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
