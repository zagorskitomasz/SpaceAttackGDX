package spaceattack.game.ai.shooters;

import spaceattack.game.ai.MoverAI;
import spaceattack.game.factories.Factories;
import spaceattack.game.utils.IUtils;

public class NotifiedSniper extends AbstractShooter {

    private IUtils utils;
    private boolean notified;
    private long notifiedTime;

    public NotifiedSniper() {

        utils = Factories.getUtilsFactory().create();
    }

    @Override
    public ShooterType getType() {

        return ShooterType.NOTIFIED_SNIPER;
    }

    @Override
    public synchronized PossibleAttacks checkShot() {

        canPrimary = true;
        canSecondary = false;

        if (notified) {
            canSecondary = true;

            if (utils.millis() > notifiedTime + 800)
                notified = false;
        }

        return processResult();
    }

    @Override
    public void notify(MoverAI state) {

        notified = true;
        notifiedTime = utils.millis();
    }
}
