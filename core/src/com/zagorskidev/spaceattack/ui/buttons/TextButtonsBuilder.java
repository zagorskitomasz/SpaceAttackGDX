package com.zagorskidev.spaceattack.ui.buttons;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.zagorskidev.spaceattack.consts.Consts;
import com.zagorskidev.spaceattack.stages.UIStage;

public enum TextButtonsBuilder
{
	INSTANCE;

	public Builder getBuilder(UIStage stage)
	{
		return new BuilderImpl(stage);
	}

	public static class BuilderImpl implements Builder,Initialized,Constructed,Positioned,Sized
	{
		private UIStage stage;
		private TextButton button;

		private BuilderImpl(UIStage stage)
		{
			this.stage = stage;
		}

		@Override
		public Initialized init(String text)
		{
			button = constructButton(text);
			return this;
		}

		TextButton constructButton(String text)
		{
			return new TextButton(text, stage.getSkin(), Consts.DEFAULT);
		}

		@Override
		public Constructed setPosition(float x,float y)
		{
			button.setPosition(x, y);
			return this;
		}

		@Override
		public Positioned setSize(float width,float height)
		{
			button.setSize(width, height);
			return this;
		}

		@Override
		public Sized addListener(ClickListener listener)
		{
			button.addListener(listener);
			return this;
		}

		@Override
		public TextButton build()
		{
			return button;
		}
	}

	public interface Builder
	{
		public Initialized init(String text);
	}

	public interface Initialized
	{
		public Constructed setPosition(float x,float y);
	}

	public interface Constructed
	{
		public Positioned setSize(float width,float height);
	}

	public interface Positioned
	{
		public Sized addListener(ClickListener listener);
	}

	public interface Sized
	{
		public TextButton build();
	}
}
