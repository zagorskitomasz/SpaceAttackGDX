package com.zagorskidev.spaceattack.graphics;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.badlogic.gdx.graphics.Texture;

public class StaticImageFactoryTest
{
	@Mock
	private StaticImageFactory factory;

	@Mock
	private StaticImage image;

	@Mock
	private Texture texture;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		doReturn(image).when(factory).instantiateImage();
		doCallRealMethod().when(factory).create(any(Texture.class), anyFloat(), anyFloat());
	}

	@Test
	public void factoryIsInitializingImageObject()
	{
		factory.create(texture, 100, 500);

		verify(image).init(texture, 100, 500);
	}

	@Test
	public void factoryIsReturningCreatedObject()
	{
		StaticImage imageCreated = factory.create(texture, 100, 500);

		assertEquals(image, imageCreated);
	}
}
