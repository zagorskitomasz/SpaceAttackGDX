package spaceattack.game.buttons.weapon;

import com.zagorskidev.spaceattack.ships.IPool;
import com.zagorskidev.spaceattack.weapons.IWeapon;

public interface IFireButton
{
	public void setWeapon(IWeapon secondaryWeapon);

	public void setEnergyPool(IPool pool);

	public boolean touchDown(int screenX,int screenY);

	public boolean touchUp(int screenX,int screenY);
}
