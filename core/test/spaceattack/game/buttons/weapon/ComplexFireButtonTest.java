package spaceattack.game.buttons.weapon;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.batch.IBatch;
import spaceattack.game.buttons.IImageButton;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.weapons.IWeapon;

public class ComplexFireButtonTest
{
	private ComplexFireButton button;

	@Mock
	private IWeapon mainWeapon;

	@Mock
	private IWeapon specialWeapon;

	@Mock
	private IImageButton imageButton;

	@Mock
	private ITexture texture;

	@Mock
	private IBatch batch;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		Textures.loadForTest();
		button = new ComplexFireButton();
		button.setImageButton(imageButton);
		button.setMainWeapon(mainWeapon);
		doReturn(true).when(mainWeapon).use();
		doReturn(true).when(specialWeapon).use();
	}

	@Test
	public void afterSettingSpecialWeaponIsUsedToPerformAttacks()
	{
		button.setSpecialWeapon(specialWeapon, 3, texture);

		button.fire();

		verify(mainWeapon, times(0)).use();
		verify(specialWeapon).use();
	}

	@Test
	public void afterUsingAmmonReturnToMainWeapon()
	{
		button.setSpecialWeapon(specialWeapon, 3, texture);

		button.fire();
		button.fire();
		button.fire();
		button.fire();

		verify(mainWeapon, times(1)).use();
		verify(specialWeapon, times(3)).use();
	}

	@Test
	public void weaponIconIsDrawn()
	{
		button.draw(batch, 0);
		verify(batch, times(0)).draw(eq(texture), anyFloat(), anyFloat());

		button.setSpecialWeapon(specialWeapon, 3, texture);

		button.draw(batch, 0);
		verify(batch, times(3)).draw(eq(texture), anyFloat(), anyFloat());

		button.fire();
		button.fire();
		button.fire();

		button.draw(batch, 0);
		verify(batch, times(3)).draw(eq(texture), anyFloat(), anyFloat());
	}
}
