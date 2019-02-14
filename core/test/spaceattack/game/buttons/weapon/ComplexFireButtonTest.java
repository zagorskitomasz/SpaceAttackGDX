package spaceattack.game.buttons.weapon;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import spaceattack.game.buttons.IImageButton;
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

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		Textures.loadForTest();
		button = new ComplexFireButton();
		button.setImageButton(imageButton);
		button.setMainWeapon(mainWeapon);
	}

	@Test
	public void afterSettingSpecialWeaponIsUsedToPerformAttacks()
	{
		button.setSpecialWeapon(specialWeapon, 3);

		button.fire();

		verify(mainWeapon, times(0)).use();
		verify(specialWeapon).use();
	}

	@Test
	public void afterUsingAmmonReturnToMainWeapon()
	{
		button.setSpecialWeapon(specialWeapon, 3);

		button.fire();
		button.fire();
		button.fire();
		button.fire();

		verify(mainWeapon, times(1)).use();
		verify(specialWeapon, times(3)).use();
	}
}
