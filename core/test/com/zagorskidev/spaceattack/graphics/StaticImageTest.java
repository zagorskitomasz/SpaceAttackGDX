package com.zagorskidev.spaceattack.graphics;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class StaticImageTest
{
	@Test
	public void drawsTextureOnBatchWithCoords()
	{
		Batch batch = mock(Batch.class);
		Texture texture = mock(Texture.class);
		StaticImage image = mock(StaticImage.class);
		doCallRealMethod().when(image).draw(any(Batch.class), anyFloat());
		doCallRealMethod().when(image).init(anyString(), anyFloat(), anyFloat());
		doReturn(texture).when(image).createTexture("path");
		doReturn(700f).when(image).gameHeight();
		doReturn(200).when(texture).getHeight();

		image.init("path", 10, 20);
		image.draw(batch, 0);

		verify(batch).draw(texture, 10, 480);
	}
}
