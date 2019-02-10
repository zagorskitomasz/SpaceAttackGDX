package spaceattack.game.powerup;

import java.util.List;
import java.util.stream.Collectors;

import spaceattack.consts.Consts;
import spaceattack.consts.Sizes;
import spaceattack.game.actors.DrawableActor;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.interfaces.PowerUpConsumer;
import spaceattack.game.factories.Factories;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.system.sound.Sounds;
import spaceattack.game.utils.NumbersUtils;
import spaceattack.game.utils.vector.IVector;

public abstract class AbstractPowerUp extends DrawableActor implements IPowerUp
{
	private Sounds consumeSound;
	private ITexture texture;

	private boolean isToKill;
	private List<PowerUpConsumer> consumers;

	AbstractPowerUp()
	{
		consumeSound = Sounds.POWER_UP;
	}

	@Override
	public void setActors(List<IGameActor> actors)
	{
		consumers = actors //
				.stream() //
				.filter(element->element instanceof PowerUpConsumer) //
				.map(element->(PowerUpConsumer) element) //
				.collect(Collectors.toList());
	}

	@Override
	public void act(float delta)
	{
		setY(getY() - Consts.AI.POWER_UP_SPEED);

		if (consumers == null)
			return;

		for (PowerUpConsumer consumer : consumers)
		{
			if (checkCollision(consumer))
			{
				consumer.consume(this);
				setToKill();
				if (consumeSound != null)
					consumeSound.play();
			}
		}
	}

	private boolean checkCollision(PowerUpConsumer consumer)
	{
		IVector powerUpCenter = Factories.getVectorFactory().create(getX(), getY());
		IVector consumerCenter = Factories.getVectorFactory().create(consumer.getX(), consumer.getY());

		return NumbersUtils.distance(powerUpCenter, consumerCenter) <= Sizes.POWER_UP_RADIUS + consumer.getRadius();
	}

	@Override
	protected ITexture getTexture()
	{
		return texture;
	}

	public void setTexture(Textures texture)
	{
		this.texture = texture.getTexture();
	}

	@Override
	public void setToKill()
	{
		isToKill = true;
	}

	@Override
	public boolean isToKill()
	{
		return isToKill;
	}

	@Override
	public void playSound()
	{
		// do nothing
	}

	@Override
	public void launched()
	{
		// do nothing
	}
}
