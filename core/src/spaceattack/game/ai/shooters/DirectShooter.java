package spaceattack.game.ai.shooters;

import spaceattack.game.utils.vector.IVector;

public class DirectShooter extends AbstractShooter
{
	boolean canPrimary;
	boolean canSecondary;

	IVector playerPosition;
	IVector primaryWeaponPlacement;
	IVector secondaryWeaponPlacement;

	@Override
	public ShooterType getType()
	{
		return ShooterType.DIRECT_SHOOTER;
	}

	@Override
	public synchronized PossibleAttacks checkShot()
	{
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
		return Math.abs(playerPosition.getX() - ownerX) < playerShip.getRadius() + weaponRadius;
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
		return weaponPlacementY + weaponRadius > playerShip.getY() - playerShip.getRadius();
	}

	private PossibleAttacks processResult()
	{
		if (canPrimary && canSecondary)
			return PossibleAttacks.BOTH;

		if (canPrimary)
			return PossibleAttacks.PRIMARY;

		if (canSecondary)
			return PossibleAttacks.SECONDARY;

		return PossibleAttacks.NONE;
	}
}
