package spaceattack.game.engines;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import spaceattack.consts.Sizes;
import spaceattack.game.ships.IShip;
import spaceattack.game.ships.IShip.Turn;
import spaceattack.game.utils.IUtils;
import spaceattack.game.utils.vector.IVector;

public class DestinationShipEngine extends AbstractShipEngine {

    private final IUtils utils;

    protected IVector destination;
    private IVector nextDestination;

    private boolean isTurning;
    protected boolean fixedDestination = false;

    private final Lock lock;

    DestinationShipEngine(final IShip ship, final IUtils utils, final int engineAttr) {

        super(ship);
        this.utils = utils;
        lock = new ReentrantLock();

        baseSpeed = 0.2f * engineAttr * Sizes.RADIUS_FACTOR;
        acceleration = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
        braking = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
        agility = 0.1f * engineAttr * Sizes.RADIUS_FACTOR;
    }

    @Override
    public void setDestination(final IVector destination) {

        try {
            lock.lock();

            if (fixedDestination) {
                return;
            }

            if (this.destination == null || isDestinationReached()) {
                this.destination = destination;
            }
            else {
                isTurning = true;
                nextDestination = destination;
            }
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isDestinationReached() {

        return ship != null && destination != null && ship.getX() == destination.getX()
                && ship.getY() == destination.getY();
    }

    @Override
    public Turn fly() {

        try {
            lock.lock();

            if (destination == null) {
                return IShip.Turn.FRONT;
            }

            if (isDestinationReached()) {
                if (isTurning) {
                    doTurn();
                }

                return IShip.Turn.FRONT;
            }

            computeSpeed();
            return moveShip();
        }
        finally {
            lock.unlock();
        }
    }

    private void doTurn() {

        destination = nextDestination;
        nextDestination = null;
        isTurning = false;
    }

    private Turn moveShip() {

        IVector movement = computeMovement();

        if (movement.length() <= currentSpeed || movement.length() <= baseSpeed) {
            ship.setX(destination.getX());
            ship.setY(destination.getY());

            return IShip.Turn.FRONT;
        }
        else {
            movement = movement.normalize();
            ship.setX(ship.getX() + movement.getX() * currentSpeed);
            ship.setY(ship.getY() + movement.getY() * currentSpeed);

            return calculateShipTurning(movement);
        }
    }

    private IVector computeMovement() {

        return vectorFactory.create(destination.getX() - ship.getX(), destination.getY() - ship.getY());
    }

    private Turn calculateShipTurning(final IVector movement) {

        if (movement.getX() >= 0.3) {
            return Turn.RIGHT;
        }

        if (movement.getX() <= -0.3) {
            return Turn.LEFT;
        }

        return Turn.FRONT;
    }

    private void computeSpeed() {

        IVector movement = computeMovement();

        if (isTurning) {
            if (canTurnWithThisSpeed(movement.copy().normalize())) {
                doTurn();
            }
            else {
                brake();
            }
        }
        else {
            if (movement.length() > brakingWay()) {
                accelerate();
            }
            else {
                brake();
            }

            if (currentSpeed <= baseSpeed) {
                currentSpeed = baseSpeed;
            }
        }
    }

    private void accelerate() {

        currentSpeed += acceleration;
    }

    private void brake() {

        currentSpeed -= braking;
    }

    private boolean canTurnWithThisSpeed(final IVector currentDirection) {

        IVector newDirection = vectorFactory
                .create(nextDestination.getX() - ship.getX(), nextDestination.getY() - ship.getY()).normalize();

        float angle = computeAngle(currentDirection, newDirection);

        return currentSpeed <= baseSpeed || (currentSpeed * currentSpeed / agility) < angle;
    }

    private float computeAngle(final IVector first, final IVector second) {

        float angle = (utils.atan2(first.getX(), first.getY()) - utils.atan2(second.getX(), second.getY()));

        angle *= utils.radiansToDegrees();
        angle = Math.abs(Math.abs(angle) - 180);

        return angle;
    }

    private float brakingWay() {

        float brakingWay = 0;
        float tempSpeed = currentSpeed - 1;

        while (tempSpeed >= baseSpeed) {
            brakingWay += tempSpeed;
            tempSpeed -= braking;
        }
        return brakingWay;
    }

    IVector getDestination() {

        return destination;
    }

    IVector getNextDestination() {

        return nextDestination;
    }

    float getCurrentSpeed() {

        return currentSpeed;
    }

    @Override
    public void forceDestination(final IVector destination) {

        try {
            lock.lock();
            this.destination = destination;
            isTurning = false;
            nextDestination = null;
            fixedDestination = true;
        }
        finally {
            lock.unlock();
        }
    }
}
