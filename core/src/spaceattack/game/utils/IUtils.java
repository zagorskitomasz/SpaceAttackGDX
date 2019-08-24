package spaceattack.game.utils;

import java.io.InputStream;
import java.util.function.Consumer;

import spaceattack.game.actors.ILabel;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.IStage;
import spaceattack.game.system.IFileHandle;
import spaceattack.game.utils.vector.IVector;

public interface IUtils {

    void clearScreen();

    float getDeltaTime();

    long getCurrentTime();

    <T> T streamToObject(Class<T> clazz, InputStream fileContent);

    IFileHandle loadFile(String save);

    double pow(double value, double exponent);

    double sqrt(double value);

    <T> String objectToString(T object, Class<T> clazz);

    void setInputProcessor(IStage stage);

    void setInputProcessor(IInputProcessor inputProcessor);

    void confirmDialog(String caption, String question, IStage stage, Consumer<Boolean> resultProcessor);

    void exit();

    float atan2(float x, float y);

    float radiansToDegrees();

    long millis();

    ILabel createTimeLabel(String text, int color);

    ILabel createBarLabel();

    ILabel createMenuLabel(String text, float yPos, int color);

    ILabel createSmallMenuLabel(String text, float yPos, int color);

    IVector getTouch();

    void infoDialog(String caption, String info, IStage stage);

    ILabel createDetailerToucher(float yPos, String details, ILabel infoLabel, boolean isCompact);

    void init();
}
