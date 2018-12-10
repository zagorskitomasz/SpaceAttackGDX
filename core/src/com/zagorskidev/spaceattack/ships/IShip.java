package com.zagorskidev.spaceattack.ships;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public interface IShip
{
	public Actor getActor();

	public void draw(Batch batch,float alpha);
}
