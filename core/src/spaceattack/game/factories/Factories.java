package spaceattack.game.factories;

import spaceattack.game.actors.IActorFactory;
import spaceattack.game.stages.IStageFactory;
import spaceattack.game.system.graphics.ITextureFactory;
import spaceattack.game.system.sound.ISoundFactory;
import spaceattack.game.utils.IUtilsFactory;
import spaceattack.game.utils.vector.IVectorFactory;

public class Factories
{
	private static ISoundFactory soundFactory;
	private static ITextureFactory textureFactory;
	private static IUtilsFactory utilsFactory;
	private static IStageFactory stageFactory;
	private static IActorFactory actorFactory;
	private static IVectorFactory vectorFactory;

	public static void setSoundFactory(ISoundFactory factory)
	{
		soundFactory = factory;
	}

	public static ISoundFactory getSoundFactory()
	{
		return soundFactory;
	}

	public static void setTextureFactory(ITextureFactory factory)
	{
		textureFactory = factory;
	}

	public static ITextureFactory getTextureFactory()
	{
		return textureFactory;
	}

	public static void setUtilsFactory(IUtilsFactory factory)
	{
		utilsFactory = factory;
	}

	public static IUtilsFactory getUtilsFactory()
	{
		return utilsFactory;
	}

	public static IStageFactory getStageFactory()
	{
		return stageFactory;
	}

	public static void setStageFactory(IStageFactory factory)
	{
		stageFactory = factory;
	}

	public static IActorFactory getActorFactory()
	{
		return actorFactory;
	}

	public static void setActorFactory(IActorFactory factory)
	{
		actorFactory = factory;
	}

	public static IVectorFactory getVectorFactory()
	{
		return vectorFactory;
	}

	public static void setVectorFactory(IVectorFactory factory)
	{
		vectorFactory = factory;
	}
}
