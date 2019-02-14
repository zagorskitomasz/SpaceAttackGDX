package spaceattack.ext.button.image;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import spaceattack.ext.texture.GdxTexture;
import spaceattack.ext.vector.ExtVectorFactory;
import spaceattack.game.buttons.IImageButton;
import spaceattack.game.input.InputType;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.utils.vector.IVector;

public class GdxImageButton extends Button implements IImageButton
{
	public GdxImageButton(Drawable drawableUp,Drawable drawableDown,Drawable drawableDisabled)
	{
		super(drawableUp, drawableDown);
		getStyle().disabled = drawableDisabled;
	}

	@Override
	public void fire(InputType type)
	{
		InputEvent.Type gdxType = null;

		switch (type)
		{
			case TOUCH_DOWN:
				gdxType = InputEvent.Type.touchDown;
				break;
			case TOUCH_UP:
				gdxType = InputEvent.Type.touchUp;
				break;
		}

		InputEvent event = new InputEvent();
		event.setType(gdxType);

		fire(event);
	}

	@Override
	public IVector screenToStageCoordinates(IVector touch)
	{
		Vector2 vector = new Vector2(touch.getX(), touch.getY());
		Vector2 result = getStage().screenToStageCoordinates(vector);

		return ExtVectorFactory.INSTANCE.create(result.x, result.y);
	}

	@Override
	public void setEnabled(boolean enabled)
	{
		setDisabled(!enabled);
		setTouchable(enabled ? Touchable.enabled : Touchable.disabled);
	}

	@Override
	public void setDown(ITexture texture)
	{
		getStyle().down = createDrawable(texture);
	}

	@Override
	public void setUp(ITexture texture)
	{
		getStyle().up = createDrawable(texture);
	}

	private Drawable createDrawable(ITexture texture)
	{
		TextureRegion region = new TextureRegion((GdxTexture) texture);
		return new TextureRegionDrawable(region);
	}
}
