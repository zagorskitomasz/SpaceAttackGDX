package spaceattack.game.ai.shooters;

import java.util.List;

import spaceattack.game.actors.interfaces.RadarVisible;
import spaceattack.game.utils.vector.IVector;

public class InstantPrimaryDirectShooter extends AbstractShooter
{
	private boolean canPrimary;
	private boolean canSecondary;

	private IVector playerPosition;
	private IVector secondaryWeaponPlacement;
	
	private List<? extends RadarVisible> friends;

	@Override
	public void setFriends(List<? extends RadarVisible> friends)
	{
		this.friends = friends;
	}
	
	@Override
	public ShooterType getType()
	{
		return ShooterType.INSTANT_PRIMARY_DIRECT_SHOOTER;
	}

	@Override
	public synchronized PossibleAttacks checkShot()
	{
		if (playerShip == null || owner == null)
			return PossibleAttacks.NONE;

		canPrimary = true;
		canSecondary = false;

		playerPosition = vectors.create(playerShip.getX(), playerShip.getY());
		secondaryWeaponPlacement = controller.getSecondaryWeaponUsePlacement();

		checkHorizontal();
		checkVertical();

		return processResult();
	}

	private void checkHorizontal()
	{
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