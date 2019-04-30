package spaceattack.game.ai.shooters;

public class InstantShooter extends AbstractShooter {

    @Override
    public ShooterType getType() {

        return ShooterType.INSTANT_SHOOTER;
    }

    @Override
    public synchronized PossibleAttacks checkShot() {

        if (playerShip == null || owner == null) {
            return PossibleAttacks.NONE;
        }

        canPrimary = true;
        canSecondary = true;

        return processResult();
    }
}
