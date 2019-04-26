package spaceattack.game.utils.vector;

public interface IVector {

    public float getX();

    public float getY();

    public float length();

    public IVector normalize();

    public IVector copy();

    public void setY(float y);
}
