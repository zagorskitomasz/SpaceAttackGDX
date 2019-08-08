package spaceattack.ext.utils;

import java.io.InputStream;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;

import spaceattack.consts.Paths;
import spaceattack.consts.Sizes;
import spaceattack.ext.actor.GdxLabel;
import spaceattack.ext.stage.GdxStage;
import spaceattack.game.actors.ILabel;
import spaceattack.game.factories.Factories;
import spaceattack.game.input.IInputProcessor;
import spaceattack.game.stages.IStage;
import spaceattack.game.system.IFileHandle;
import spaceattack.game.utils.vector.IVector;

enum GdxUtils implements IGdxUtils {
    INSTANCE;

    @Override
    public void clearScreen() {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public float getDeltaTime() {

        return Gdx.graphics.getDeltaTime();
    }

    @Override
    public long getCurrentTime() {

        return TimeUtils.millis();
    }

    @Override
    public <T> T streamToObject(final Class<T> clazz, final InputStream fileContent) {

        Json json = new Json();
        return json.fromJson(clazz, fileContent);
    }

    @Override
    public IFileHandle loadFile(final String save) {

        return new GdxFileHandle(Gdx.files.local(Paths.SAVE));
    }

    @Override
    public double pow(final double value, final double exponent) {

        return Math.pow(value, exponent);
    }

    @Override
    public double sqrt(final double value) {

        return Math.sqrt(value);
    }

    @Override
    public <T> String objectToString(final T object, final Class<T> clazz) {

        Json json = new Json();
        return json.toJson(object, clazz);
    }

    @Override
    public void setInputProcessor(final IStage stage) {

        Gdx.input.setInputProcessor((GdxStage) stage);
    }

    @Override
    public void setInputProcessor(final IInputProcessor inputProcessor) {

        Gdx.input.setInputProcessor(new GdxInput(inputProcessor));
    }

    @Override
    public IVector getTouch() {

        if (!Gdx.input.isTouched()) {
            return null;
        }

        return Factories.getVectorFactory().create(Gdx.input.getX(), Gdx.input.getY());
    }

    @Override
    public Skin getUiSkin() {

        return new Skin(Gdx.files.internal(Paths.UI_STYLE));
    }

    @Override
    public void confirmDialog(final String caption, final String question, final IStage stage,
            final Consumer<Boolean> resultProcessor) {

        GdxDialog dialog = new GdxDialog(caption);

        TextButton yesButton = new TextButton("Yes", getUiSkin().get(TextButton.TextButtonStyle.class));
        TextButton noButton = new TextButton("No", getUiSkin().get(TextButton.TextButtonStyle.class));
        Label questionLabel = new Label(question, getUiSkin().get(Label.LabelStyle.class));

        yesButton.getLabel().setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);
        noButton.getLabel().setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);
        questionLabel.setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);

        dialog.text(questionLabel);
        dialog.button(yesButton, true);
        dialog.button(noButton, false);
        dialog.key(Keys.ENTER, true);
        dialog.setResultProcessor(resultProcessor);

        dialog.getTitleLabel().setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);

        dialog.show((GdxStage) stage);
    }

    @Override
    public void infoDialog(final String caption, final String info, final IStage stage) {

        GdxDialog dialog = new GdxDialog(caption);

        TextButton okButton = new TextButton("Ok", getUiSkin().get(TextButton.TextButtonStyle.class));
        Label questionLabel = new Label(info, getUiSkin().get(Label.LabelStyle.class));

        okButton.getLabel().setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);
        questionLabel.setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);

        dialog.text(questionLabel);
        dialog.button(okButton, true);
        dialog.key(Keys.ENTER, true);

        dialog.getTitleLabel().setFontScale(Sizes.X_FACTOR, Sizes.Y_FACTOR);
        dialog.show((GdxStage) stage);
    }

    @Override
    public void exit() {

        Gdx.app.exit();
    }

    @Override
    public float atan2(final float x, final float y) {

        return MathUtils.atan2(y, x);
    }

    @Override
    public float radiansToDegrees() {

        return MathUtils.radiansToDegrees;
    }

    @Override
    public long millis() {

        return TimeUtils.millis();
    }

    @Override
    public ILabel createTimeLabel(final String text, final int color) {

        BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.TIME_LABELS_FONT));
        Label.LabelStyle style = new Label.LabelStyle(font, new Color(color));
        GdxLabel label = new GdxLabel(text, style);
        label.setPosition((Sizes.GAME_WIDTH - label.getWidth() * Sizes.X_FACTOR) * 0.5f, Sizes.GAME_HEIGHT * 0.7f);

        return label;
    }

    @Override
    public ILabel createBarLabel() {

        BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.GAMEPLAY_FONT));
        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        return new GdxLabel("", style);
    }

    @Override
    public ILabel createMenuLabel(final String text, final float yPos, final int color) {

        BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.TIME_LABELS_FONT));
        Label.LabelStyle style = new Label.LabelStyle(font, new Color(color));
        GdxLabel label = new GdxLabel(text, style);
        label.setX(Sizes.GAME_WIDTH * 0.5f - label.getWidth() * 0.5f);
        label.setY(yPos);

        return label;
    }

    @Override
    public ILabel createDetailerToucher(final float yPos, final String details, final ILabel infoLabel) {

        BitmapFont font = new BitmapFont(Gdx.files.internal(Paths.TIME_LABELS_FONT));
        Label.LabelStyle style = new Label.LabelStyle(font, new Color(0xdaa520ff));
        GdxLabel label = new GdxLabel(" ", style);
        label.setX(0);
        label.setY(yPos);
        label.setWidth(Sizes.GAME_WIDTH);
        label.addListener(event -> {
            infoLabel.setText(details);
            infoLabel.pack();
            infoLabel.setX((Sizes.GAME_WIDTH - infoLabel.getWidth()) * 0.5f);
            return true;
        });

        return label;
    }
}
