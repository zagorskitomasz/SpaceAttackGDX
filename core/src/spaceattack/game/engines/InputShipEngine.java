package spaceattack.game.engines;

import java.util.function.Predicate;
import java.util.function.Supplier;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.buttons.IAccelerator;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.vector.IVector;

public class InputShipEngine extends AbstractShipEngine {

    private final float maxSpeed;

    private IAccelerator accelerator;

    float currentSpeedHorizontal;
    float currentSpeedVertical;

    private final Supplier<Float> additionalSpeedFactor;
    private final Predicate<Float> energySource;
    private final int sprintFactor;

    public InputShipEngine(final IShip ship, final int engineAttr, final Predicate<Float> energySource,
            final int sprintFactor, final Supplier<Float> additionalSpeedFactor) {

        super(ship);

        maxSpeed = 1 * engineAttr;

        baseSpeed = 0.2f * engineAttr * Sizes.RADIUS_FACTOR;
        acceleration = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
        braking = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
        agility = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;

        this.energySource = energySource;
        this.sprintFactor = sprintFactor;
        this.additionalSpeedFactor = additionalSpeedFactor;
    }

    public void setAccelerator(final IAccelerator accelerator) {

        this.accelerator = accelerator;
    }

    @Override
    public Turn fly() {

        computeHorizontalSpeed();
        computeVerticalSpeed();
        move();

        return calculateTurn();
    }

    private void computeHorizontalSpeed() {

        float targetSpeed = calculateTargetSpeed(accelerator.getHorizontalAcceleration());
        if (targetSpeed > 0) {
            if (isNearRightEdge()) {
                slowDownRight();
            }
            else {
                accelerateRight(targetSpeed);
            }

        }
        else
            if (targetSpeed < 0) {
                if (isNearLeftEdge()) {
                    slowDownLeft();
                }
                else {
                    accelerateLeft(targetSpeed);
                }
            }
            else {
                if (currentSpeedHorizontal > 0) {
                    slowDownRight();
                }
                else
                    if (currentSpeedHorizontal < 0) {
                        slowDownLeft();
                    }
            }
    }

    private boolean isNearRightEdge() {

        return Sizes.GAME_WIDTH - Sizes.SIDE_MARGIN - ship.getX() <= brakingWay(currentSpeedHorizontal);
    }

    private void slowDownRight() {

        currentSpeedHorizontal = Math.max(currentSpeedHorizontal - acceleration, 0);
    }

    private boolean isNearLeftEdge() {

        return ship.getX() - Sizes.SIDE_MARGIN <= brakingWay(-1 * currentSpeedHorizontal);
    }

    private void slowDownLeft() {

        currentSpeedHorizontal = Math.min(currentSpeedHorizontal + acceleration, 0);
    }

    private void accelerateRight(final float targetSpeed) {

        currentSpeedHorizontal = Math.min(currentSpeedHorizontal + acceleration, targetSpeed);
    }

    private void accelerateLeft(final float targetSpeed) {

        currentSpeedHorizontal = Math.max(currentSpeedHorizontal - acceleration, targetSpeed);
    }

    private void computeVerticalSpeed() {

        float targetSpeed = calculateTargetSpeed(accelerator.getVerticalAcceleration());
        if (targetSpeed > 0) {
            if (isNearUpperEdge()) {
                slowDownForward();
            }
            else {
                accelerateForward(targetSpeed);
            }

        }
        else
            if (targetSpeed < 0) {
                if (isNearLowerEdge()) {
                    slowDownBackward();
                }
                else {
                    accelerateBackward(targetSpeed);
                }
            }
            else {
                if (currentSpeedVertical > 0) {
                    slowDownForward();
                }
                else
                    if (currentSpeedVertical < 0) {
                        slowDownBackward();
                    }
            }
    }

    private float calculateTargetSpeed(final float acceleration) {

        return acceleration * maxSpeed / 100;
    }

    private boolean isNearUpperEdge() {

        return Sizes.GAME_HEIGHT - Sizes.UPPER_MARGIN - ship.getY() <= brakingWay(currentSpeedVertical);
    }

    private void slowDownForward() {

        currentSpeedVertical = Math.max(currentSpeedVertical - acceleration, 0);
    }

    private boolean isNearLowerEdge() {

        return ship.getY() - Sizes.DOWN_MARGIN <= brakingWay(-1 * currentSpeedVertical);
    }

    private void slowDownBackward() {

        currentSpeedVertical = Math.min(currentSpeedVertical + acceleration, 0);
    }

    private void accelerateForward(final float targetSpeed) {

        currentSpeedVertical = Math.min(currentSpeedVertical + acceleration, targetSpeed);
    }

    private void accelerateBackward(final float targetSpeed) {

        currentSpeedVertical = Math.max(currentSpeedVertical - acceleration, targetSpeed);
    }

    private Turn calculateTurn() {

        if (currentSpeedHorizontal > Consts.Gameplay.SHIP_TURN_THRESHOLD) {
            return Turn.RIGHT;
        }

        if (currentSpeedHorizontal < -1 * Consts.Gameplay.SHIP_TURN_THRESHOLD) {
            return Turn.LEFT;
        }

        return Turn.FRONT;
    }

    private void move() {

        float oldSpeedVertical = currentSpeedVertical;
        float oldSpeedHorizontal = currentSpeedHorizontal;

        if (!isNearEdge()) {

            speedUp();
            useAdditionalFactor();
        }

        ship.setY(ship.getY() + currentSpeedVertical);
        ship.setX(ship.getX() + currentSpeedHorizontal);

        currentSpeedVertical = oldSpeedVertical;
        currentSpeedHorizontal = oldSpeedHorizontal;
    }

    private void speedUp() {

        if (energySource == null || sprintFactor <= 0) {
            return;
        }
        float factor = 1 + Consts.Pools.SPEED_FACTOR * sprintFactor;
        float energyRequired = (Math.abs(currentSpeedHorizontal) + Math.abs(currentSpeedVertical))
                * Consts.Pools.SPEED_FACTOR
                * sprintFactor / 2;

        if (!energySource.test(energyRequired)) {
            return;
        }

        currentSpeedHorizontal *= factor * 1.5;
        currentSpeedVertical *= factor * 1.5;
    }

    protected boolean isNearEdge() {

        return isNearLowerEdge() || isNearUpperEdge() || isNearLeftEdge() || isNearRightEdge();
    }

    private void useAdditionalFactor() {

        if (additionalSpeedFactor == null) {
            return;
        }

        float factor = additionalSpeedFactor.get();
        currentSpeedHorizontal *= factor;
        currentSpeedVertical *= factor;
    }

    private float brakingWay(final float currentSpeed) {

        float brakingWay = 0;
        float tempSpeed = currentSpeed;

        while (tempSpeed >= 0) {
            brakingWay += tempSpeed;
            tempSpeed -= acceleration;
        }
        return brakingWay;
    }

    @Override
    public boolean isDestinationReached() {

        return currentSpeed == 0;
    }

    @Override
    public void setDestination(final IVector destination) {

        // do nothing
    }

    @Override
    public void forceDestination(final IVector destination) {

        // do nothing
    }
}
