package com.zagorskidev.spaceattack.graphics;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class StaticImageTest
{
	@Test
	public void drawsTextureOnBatchWithCoords()
	{
		StaticImage image = spy(StaticImage.class);

		Batch batch = mock(Batch.class);
		Texture texture = mock(Texture.class);
		doReturn(700f).when(image).gameHeight();
		doReturn(200).when(texture).getHeight();

		image.init(texture, 10, 20);
		image.draw(batch, 0);

		verify(batch).draw(texture, 10, 480);
	}
}
