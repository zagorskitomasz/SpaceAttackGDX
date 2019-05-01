package spaceattack.game.ai.shooters;

import spaceattack.game.ai.ShooterAI;

public enum ShooterType {
    DIRECT_SHOOTER(DirectShooter.class),
    INSTANT_PRIMARY_DIRECT_SHOOTER(InstantPrimaryDirectShooter.class),
    INSTANT_SHOOTER(InstantShooter.class),
    NOTIFIED_SNIPER(NotifiedSniper.class);

    private Class<? extends ShooterAI> type;

    ShooterType(final Class<? extends ShooterAI> type) {

        this.type = type;
    }

    public ShooterAI create() {

        try {
            return type.newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
