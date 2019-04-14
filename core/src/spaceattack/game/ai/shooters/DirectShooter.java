package spaceattack.game.ai.shooters;

import spaceattack.consts.Sizes;
import spaceattack.game.utils.vector.IVector;

public class DirectShooter extends AbstractShooter
{
	private IVector playerPosition;
	private IVector primaryWeaponPlacement;
	private IVector secondaryWeaponPlacement;
	
	@Override
	public ShooterType getType()
	{
		return ShooterType.DIRECT_SHOOTER;
	}

	@Override
	public synchronized PossibleAttacks checkShot()
	{
		if (playerShip == null || owner == null)
			return PossibleAttacks.NONE;

		canPrimary = false;
		canSecondary = false;

		playerPosition = vectors.create(playerShip.getX(), playerShip.getY());
		primaryWeaponPlacement = controller.getPrimaryWeaponUsePlacement();
		secondaryWeaponPlacement = controller.getSecondaryWeaponUsePlacement();

		checkHorizontal();
		checkVertical();

		return processResult();
	}

	private void checkHorizontal()
	{
		if (inHorizontalRange(primaryWeaponPlacement.getX(), controller.getPrimaryWeaponRadius()))
			canPrimary = true;

		if (inHorizontalRange(secondaryWeaponPlacement.getX(), controller.getSecondaryWeaponRadius()))
			canSecondary = true;
	}

	private boolean inHorizontalRange(float ownerX,float weaponRadius)
	{
		return isTargetBelow(ownerX, weaponRadius) && !isFriendBetweenOwnerAndTarget(ownerX, weaponRadius);
	}

	private boolean isTargetBelow(float ownerX, float weaponRadius) 
	{
		return Math.abs(playerPosition.getX() - ownerX) < playerShip.getRadius() + weaponRadius;
	}

	private boolean isFriendBetweenOwnerAndTarget(float ownerX,float weaponRadius) 
	{
		return friends //
				.stream() //
				.filter(friend -> Math.abs(ownerX - friend.getX()) <= friend.getRadius() + weaponRadius) //
				.filter(friend -> friend.getY() < owner.getY()) //
				.filter(friend -> friend.getY() > playerShip.getY()) //
				.findAny() //
				.isPresent();
	}

	private void checkVertical()
	{
		if (!isAbove(controller.getPrimaryWeaponUsePlacement().getY(), controller.getPrimaryWeaponRadius()))
			canPrimary = false;

		if (!isAbove(controller.getSecondaryWeaponUsePlacement().getY(), controller.getSecondaryWeaponRadius()))
			canSecondary = false;
	}

	private boolean isAbove(float weaponPlacementY,float weaponRadius)
	{
		return weaponPlacementY + weaponRadius  * Sizes.X_FACTOR > playerShip.getY() - playerShip.getRadius();
	}
}
