package com.zagorskidev.spaceattack.ui.buttons;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.ui.buttons.TextButtonsBuilder.BuilderImpl;

public class TextButtonsBuilderTest
{
	private TextButtonsBuilder builder;
	private TextButton button;

	@Before
	public void setUp()
	{
		BuilderImpl builderImpl = Mockito.mock(BuilderImpl.class);
		Mockito.doCallRealMethod().when(builderImpl).init(ArgumentMatchers.anyString());
		Mockito.doCallRealMethod().when(builderImpl).setPosition(ArgumentMatchers.anyFloat(),
				ArgumentMatchers.anyFloat());
		Mockito.doCallRealMethod().when(builderImpl).setSize(ArgumentMatchers.anyFloat(), ArgumentMatchers.anyFloat());
		Mockito.doCallRealMethod().when(builderImpl).addListener(ArgumentMatchers.any(ClickListener.class));
		Mockito.doCallRealMethod().when(builderImpl).build();

		button = Mockito.mock(TextButton.class);
		Mockito.doReturn(button).when(builderImpl).constructButton("test", Consts.DEFAULT);

		builder = Mockito.spy(TextButtonsBuilder.INSTANCE);
		Mockito.doReturn(builderImpl).when(builder).getBuilder(ArgumentMatchers.any());
	}

	@Test
	public void buttonIsCreated()
	{
		ClickListener listener = Mockito.mock(ClickListener.class);

		//@formatter:off
		TextButton result = builder
				.getBuilder(null)
				.init("test")
				.setPosition(100, 100)
				.setSize(200, 200)
				.addListener(listener)
				.build();
		//@formatter:on

		Mockito.verify(button).setPosition(100, 100);
		Mockito.verify(button).setSize(200, 200);
		Mockito.verify(button).addListener(listener);

		assertEquals(button, result);
	}
}
