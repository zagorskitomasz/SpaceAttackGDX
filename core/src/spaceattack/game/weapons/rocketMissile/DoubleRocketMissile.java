package spaceattack.game.weapons.rocketMissile;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.factories.Factories;
import spaceattack.game.weapons.missiles.Missile;

public class DoubleRocketMissile extends RocketMissile {

    protected DoubleRocketMissile(final int armory) {

        super(armory);

        dmg = Consts.Weapons.DROCKET_DMG_PER_ATTR * armory;
        speed = Consts.Weapons.DROCKET_SPEED_PER_ATTR * armory;
        energyCost = Consts.Weapons.DROCKET_COST_PER_ATTR * armory;
    }

    @Override
    public boolean use() {

        if (!frameController.check()) {
            return false;
        }

        if (!controller.takeEnergy(getShotCost())) {
            return false;
        }

        launchMissiles();
        return true;
    }

    private void launchMissiles() {

        Missile left = buildMissile();
        left.setPosition(Factories.getVectorFactory()
                .create(left.getPosition().getX() - Sizes.MULTI_MISSILES_X_DISTANCE, left.getPosition().getY()));

        Missile right = buildMissile();
        right.setSound(null);
        right.setPosition(Factories.getVectorFactory()
                .create(left.getPosition().getX() + Sizes.MULTI_MISSILES_X_DISTANCE, left.getPosition().getY()));

        launcher.launch(left);
        launcher.launch(right);
    }
}
