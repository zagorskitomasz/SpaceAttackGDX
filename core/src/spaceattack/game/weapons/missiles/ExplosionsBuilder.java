package spaceattack.game.weapons.missiles;

import spaceattack.consts.Consts;
import spaceattack.game.actors.interfaces.Launchable;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.impl.GameplayStage;
import spaceattack.game.system.graphics.Animations;
import spaceattack.game.system.sound.Sounds;

public enum ExplosionsBuilder {
    INSTANCE;

    public Explosion createFighterExplosion(final GameplayStage stage) {

        int strength = stage.getCurrentMission() * 2;

        Explosion explosion = new Explosion();

        explosion.setActor(Factories.getActorFactory().create(explosion));
        explosion.setAnimation(Animations.FIGHTER_EX.getAnimation());
        explosion.setDmg(Consts.Explosions.FIGHTER_EX_DMG * strength);
        explosion.setFireDmg(Consts.Explosions.FIGHTER_EX_FIRE_BASE_DMG
                + strength * Consts.Explosions.FIGHTER_EX_FIRE_DMG_PER_STRENGTH);
        explosion.setFireDuration(
                Consts.Explosions.FIRE_BASE_DURATION);
        explosion.setRadius(Consts.Explosions.FIGHTER_EX_RADIUS);
        explosion.setSound(Sounds.SMALL_SHIP_EXPLOSION);

        return explosion;
    }

    public Explosion createBossExplosion() {

        Explosion explosion = new Explosion();

        explosion.setActor(Factories.getActorFactory().create(explosion));
        explosion.setAnimation(Animations.BOSS_EX.getAnimation());
        explosion.setDmg(Consts.Explosions.BOSS_EX_DMG);
        explosion.setFireDmg(Consts.Explosions.BOSS_EX_FIRE_BASE_DMG);
        explosion.setFireDuration(Consts.Explosions.FIRE_BASE_DURATION);
        explosion.setRadius(Consts.Explosions.BOSS_EX_RADIUS);
        explosion.setSound(Sounds.BOSS_EXPLOSION);

        return explosion;
    }

    public Launchable createMissileExplosion(final float dmg) {

        Explosion explosion = new Explosion();

        explosion.setActor(Factories.getActorFactory().create(explosion));
        explosion.setAnimation(Animations.MISSILE_EX.getAnimation());
        explosion.setDmg(Consts.Explosions.MISSILE_EX_DMG * dmg);
        explosion.setFireDmg(Consts.Explosions.MISSILE_EX_FIRE_DMG * dmg);
        explosion.setFireDuration(
                Consts.Explosions.FIRE_BASE_DURATION + Math.round(dmg) * Consts.Explosions.FIRE_DURATION_PER_DMG);
        explosion.setRadius(Consts.Explosions.MISSILE_EX_RADIUS);
        explosion.setSound(Sounds.MISSILE_EXPLOSION);

        return explosion;
    }

    public Explosion createTankExplosion(final GameplayStage stage) {

        int strength = stage.getCurrentMission() * 2;
        Explosion explosion = new Explosion();

        explosion.setActor(Factories.getActorFactory().create(explosion));
        explosion.setAnimation(Animations.TANK_EX.getAnimation());
        explosion.setDmg(Consts.Explosions.TANK_EX_DMG * strength);
        explosion.setFireDmg(
                Consts.Explosions.TANK_EX_FIRE_BASE_DMG + strength * Consts.Explosions.TANK_EX_FIRE_DMG_PER_STRENGTH);
        explosion.setFireDuration(
                Consts.Explosions.FIRE_BASE_DURATION);
        explosion.setRadius(Consts.Explosions.TANK_EX_RADIUS);
        explosion.setSound(Sounds.SMALL_SHIP_EXPLOSION);

        return explosion;
    }

    public Launchable createMineExplosion(final int dmg) {

        Explosion explosion = new Explosion();

        explosion.setActor(Factories.getActorFactory().create(explosion));
        explosion.setAnimation(Animations.MISSILE_EX.getAnimation());
        explosion.setDmg(Consts.Explosions.MINE_EX_DMG * dmg);
        explosion.setFireDmg(
                Consts.Explosions.MINE_EX_FIRE_DMG * dmg);
        explosion.setFireDuration(
                Consts.Explosions.FIRE_BASE_DURATION + Math.round(dmg) * Consts.Explosions.FIRE_DURATION_PER_DMG);
        explosion.setRadius(Consts.Explosions.MINE_EX_RADIUS);
        explosion.setSound(Sounds.MISSILE_EXPLOSION);

        return explosion;
    }
}
