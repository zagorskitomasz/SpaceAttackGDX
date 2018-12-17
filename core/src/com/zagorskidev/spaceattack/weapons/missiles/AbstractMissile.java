package com.zagorskidev.spaceattack.weapons.missiles;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public abstract class AbstractMissile extends Actor
{
	private Array<Actor> actors;

	public void addActors(Array<Actor> actors)
	{
		this.actors = actors;
	}

	@Override
	public void act(float delta)
	{
		for (Actor actor : actors)
		{
			if (actor instanceof Vulnerable)
				collision(actor);
		}
	}

	private void collision(Actor actor)
	{
		// TODO Auto-generated method stub
	}
}
