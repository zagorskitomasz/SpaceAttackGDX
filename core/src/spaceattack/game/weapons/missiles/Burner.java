package spaceattack.game.weapons.missiles;

import spaceattack.game.actors.interfaces.Ignitable;
import spaceattack.game.batch.IBatch;
import spaceattack.game.system.FrameController;
import spaceattack.game.system.graphics.IAnimation;
import spaceattack.game.system.graphics.ITexture;

public class Burner
{
	private Ignitable ignitable;
	private IAnimation burningAnimation;
	private FrameController burningController;
	private boolean isBurning;
	private float burningDPS;

	Burner()
	{
		// do nothing
	}

	public void setBurningAnimation(IAnimation animation)
	{
		burningAnimation = animation;
	}

	public void setBurningController(FrameController controller)
	{
		burningController = controller;
	}

	public void setIgnitable(Ignitable ignitable)
	{
		this.ignitable = ignitable;
	}

	public void burn(float delta)
	{
		if (isBurning)
		{
			if (burningController.check())
			{
				burningDPS = 0;
				isBurning = false;
			}
			else
			{
				ignitable.takeDmg(burningDPS * delta);
			}
		}
	}

	public void draw(IBatch batch)
	{
		if (isBurning)
		{
			ITexture texture = burningAnimation.getFrame();
			batch.draw(texture, 
					ignitable.getDrawingX() + ignitable.getRadius() - texture.getWidth() / 2, 
					ignitable.getDrawingY() + ignitable.getRadius() - texture.getHeight() / 2, 
					texture.getWidth(), texture.getWidth());
		}
	}

	public void ignite(float burningDPS,long fireDuration)
	{
		if (burningDPS > this.burningDPS)
			this.burningDPS = burningDPS;

		burningController.reset(1000 / (float) fireDuration);
		isBurning = true;
	}
}
