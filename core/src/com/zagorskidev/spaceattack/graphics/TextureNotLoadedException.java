package com.zagorskidev.spaceattack.graphics;

public class TextureNotLoadedException extends RuntimeException
{
	private static final long serialVersionUID = -6170909819372740338L;

	public TextureNotLoadedException(String name)
	{
		super("Texture: " + name + " not loaded. Use Textures.load() before playing any of them.");
	}
}
