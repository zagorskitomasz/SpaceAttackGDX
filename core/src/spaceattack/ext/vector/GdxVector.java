package spaceattack.ext.vector;

import com.badlogic.gdx.math.Vector2;

import spaceattack.game.utils.vector.IVector;

class GdxVector extends Vector2 implements IVector
{
	private static final long serialVersionUID = -627649586112457682L;

	public GdxVector(float x,float y)
	{
		super(x, y);
	}

	@Override
	public float getX()
	{
		return x;
	}

	@Override
	public float getY()
	{
		return y;
	}

	@Override
	public float length()
	{
		return len();
	}

	@Override
	public IVector normalize()
	{
		nor();
		return this;
	}

	@Override
	public IVector copy()
	{
		Vector2 copy = cpy();
		return new GdxVector(copy.x, copy.y);
	}
}
