package spaceattack.game.actors;

public class FakeActor implements IActor {

    private float x;
    private float y;

    @Override
    public float getX() {

        return x;
    }

    @Override
    public float getY() {

        return y;
    }

    @Override
    public void setX(float x) {

        this.x = x;
    }

    @Override
    public void setY(float y) {

        this.y = y;
    }

    @Override
    public float getHeight() {

        return 0;
    }

    @Override
    public float getWidth() {

        return 0;
    }

    @Override
    public void setPosition(float x, float y) {

        this.x = x;
        this.y = y;
    }
}
