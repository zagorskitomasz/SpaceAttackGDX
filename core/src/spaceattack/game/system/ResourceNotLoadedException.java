package spaceattack.game.system;

public class ResourceNotLoadedException extends RuntimeException
{
	private static final long serialVersionUID = -6170909819372740338L;

	public ResourceNotLoadedException(String name)
	{
		super("Resource: " + name + " not loaded. Use Resources.load() before using any of them.");
	}
}
