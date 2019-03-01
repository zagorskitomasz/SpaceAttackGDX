package spaceattack.game.utils;

import java.io.InputStream;
import java.util.function.Consumer;

import spaceattack.game.actors.ILabel;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.IStage;
import spaceattack.game.system.IFileHandle;
import spaceattack.game.utils.vector.IVector;

public interface IUtils
{
	public void clearScreen();

	public float getDeltaTime();

	public long getCurrentTime();

	public <T> T streamToObject(Class<T> clazz,InputStream fileContent);

	public IFileHandle loadFile(String save);

	public double pow(double value,double exponent);

	public double sqrt(double value);

	public <T> String objectToString(T object,Class<T> clazz);

	public void setInputProcessor(IStage stage);

	public void setInputProcessor(IInputProcessor inputProcessor);

	public void confirmDialog(String caption,String question,IStage stage,Consumer<Boolean> resultProcessor);

	public void exit();

	public float atan2(float x,float y);

	public float radiansToDegrees();

	public long millis();

	public ILabel createTimeLabel(String text,int color);

	public ILabel createBarLabel();

	IVector getTouch();
}
