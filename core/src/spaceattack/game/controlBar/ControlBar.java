package spaceattack.game.controlBar;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

import spaceattack.consts.Sizes;
import spaceattack.game.actors.IActorsContainer;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.ILabel;
import spaceattack.game.buttons.IButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.UIStage;
import spaceattack.game.utils.GameplayLabel;
import spaceattack.game.utils.IUtils;

public class ControlBar implements IActorsContainer {

    public static Builder builder() {

        return new Builder();
    }

    private final Collection<IGameActor> components;

    private ControlBar(final Builder builder) {

        UIStage stage = builder.getStage();

        int lineIndex = builder.getLineIndex();
        float firstLineYPos = builder.getFirstLineYPos();
        float intervalYPos = builder.getIntervalYPos();

        String name = builder.getName();
        String details = builder.getDetails();
        ILabel detailsLabel = builder.getDetailsLabel();

        Supplier<Integer> valueSupplier = builder.getValueSupplier();
        Supplier<Integer> freePointsSupplier = builder.getFreePointsSupplier();
        Consumer<String> freePointsConsumer = builder.getFreePointsConsumer();

        Runnable decreaseAction = builder.getDecreaseAction();
        Runnable increaseAction = builder.getIncreaseAction();
        int minValue = builder.getMinValue();
        int maxValue = builder.getMaxValue();

        BooleanSupplier enablityPredicate = builder.getEnablityPredicate() != null ? builder.getEnablityPredicate()
                : () -> true;

        int levelRequired = builder.getLevelRequired();
        details = enablityPredicate.getAsBoolean() ? details : "Will be unlocked at level " + levelRequired;

        boolean compactLine = builder.isCompactLine();

        IUtils utils = Factories.getUtilsFactory().create();

        float lineYPos = lineYPos(lineIndex, firstLineYPos, intervalYPos);

        ILabel nameLabel;
        ILabel valueLabel;
        if (compactLine) {
            nameLabel = utils.createSmallMenuLabel(name, lineYPos,
                    enablityPredicate.getAsBoolean() ? 0xff00ffff : 0x808080);
            valueLabel = utils.createSmallMenuLabel(formatValue(() -> 0), lineYPos,
                    enablityPredicate.getAsBoolean() ? 0xff0000ff : 0x808080);
        }
        else {
            nameLabel = utils.createMenuLabel(name, lineYPos, enablityPredicate.getAsBoolean() ? 0xdaa520ff : 0x808080);
            valueLabel = utils.createMenuLabel(formatValue(() -> 0), lineYPos,
                    enablityPredicate.getAsBoolean() ? 0xff0000ff : 0x808080);
        }

        float valueLabelWidth = valueLabel.getWidth();
        valueLabel.setText(formatValue(valueSupplier));

        IButton decreaserButton = actionButton(stage, details, decreaseAction,
                freePointsSupplier, valueSupplier, valueLabel::setText, freePointsConsumer, detailsLabel, lineYPos,
                "-", compactLine ? 0.8f : 1);

        IButton increaserButton = actionButton(stage, details, increaseAction,
                freePointsSupplier, valueSupplier, valueLabel::setText, freePointsConsumer, detailsLabel, lineYPos,
                "+", compactLine ? 0.8f : 1);

        ILabel infoToucher = utils.createDetailerToucher(lineYPos, details, detailsLabel, compactLine);

        increaserButton.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.04f - increaserButton.getWidth());
        valueLabel.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.08f - increaserButton.getWidth()
                - valueLabelWidth);
        decreaserButton.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.12f - increaserButton.getWidth()
                - valueLabelWidth - decreaserButton.getWidth());
        nameLabel.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.16f - increaserButton.getWidth()
                - valueLabelWidth - decreaserButton.getWidth() - nameLabel.getWidth());

        components = new LinkedList<>();
        components.add(new GameplayLabel(nameLabel));
        components.add(new GameplayLabel(valueLabel));
        components.add(new GameplayLabel(infoToucher));
        components.add(decreaserButton);
        components.add(increaserButton);

        stage.addButtonsEnabledPredicate(decreaserButton,
                button -> enablityPredicate.getAsBoolean() && valueSupplier.get() > minValue);
        stage.addButtonsEnabledPredicate(increaserButton,
                button -> enablityPredicate.getAsBoolean() && freePointsSupplier.get() > 0
                        && valueSupplier.get() < maxValue);
    }

    private IButton actionButton(final UIStage stage, final String details,
            final Runnable action, final Supplier<Integer> freePointsSupplier,
            final Supplier<Integer> valueSupplier,
            final Consumer<String> valueConsumer, final Consumer<String> freePointsConsumer,
            final ILabel detailsLabel, final float yPos, final String text, final float sizeFactor) {

        IButton button = Factories.getTextButtonFactory().create(text);

        button.setPosition(0f, yPos - 10 * Sizes.Y_FACTOR);
        button.setSize(Sizes.SQUARE_BUTTON_SIZE * sizeFactor, Sizes.SQUARE_BUTTON_SIZE * sizeFactor);
        button.addHoverListener(() -> detailsLabel.update(details));

        button.addListener(() -> {
            synchronized (stage) {
                detailsLabel.update(details);
                action.run();
                valueConsumer.accept(String.format("%03d", valueSupplier.get()));
                freePointsConsumer.accept("Free points: " + freePointsSupplier.get());
                stage.updateControls();
            }
        });
        return button;
    }

    private String formatValue(final Supplier<Integer> attributeValueSupplier) {

        return String.format("%03d", attributeValueSupplier.get());
    }

    private float lineYPos(final int lineIndex, final float firstLineYPos, final float intervalYPos) {

        return Sizes.GAME_HEIGHT * firstLineYPos - Sizes.GAME_HEIGHT * lineIndex * intervalYPos;
    }

    public static class Builder {

        private UIStage stage;

        private int lineIndex;
        private float firstLineYPos;
        private float intervalYPos;

        private String name;
        private String details;
        private ILabel detailsLabel;

        private Supplier<Integer> valueSupplier;
        private Supplier<Integer> freePointsSupplier;
        private Consumer<String> freePointsConsumer;

        private Runnable decreaseAction;
        private Runnable increaseAction;
        private int minValue;
        private int maxValue = Integer.MAX_VALUE;

        private BooleanSupplier enablityPredicate;
        private boolean compactLine;
        private int levelRequired;

        public UIStage getStage() {

            return stage;
        }

        public int getLevelRequired() {

            return levelRequired;
        }

        public Builder withLevelRequired(final int levelRequired) {

            this.levelRequired = levelRequired;
            return this;
        }

        public Builder withStage(final UIStage stage) {

            this.stage = stage;
            return this;
        }

        public int getLineIndex() {

            return lineIndex;
        }

        public Builder withLineIndex(final int lineIndex) {

            this.lineIndex = lineIndex;
            return this;
        }

        public float getFirstLineYPos() {

            return firstLineYPos;
        }

        public Builder withFirstLineYPos(final float firstLineYPos) {

            this.firstLineYPos = firstLineYPos;
            return this;
        }

        public float getIntervalYPos() {

            return intervalYPos;
        }

        public Builder withIntervalYPos(final float intervalYPos) {

            this.intervalYPos = intervalYPos;
            return this;
        }

        public String getName() {

            return name;
        }

        public Builder withName(final String name) {

            this.name = name;
            return this;
        }

        public String getDetails() {

            return details;
        }

        public Builder withDetails(final String details) {

            this.details = details;
            return this;
        }

        public ILabel getDetailsLabel() {

            return detailsLabel;
        }

        public Builder withDetailsLabel(final ILabel detailsLabel) {

            this.detailsLabel = detailsLabel;
            return this;
        }

        public Supplier<Integer> getValueSupplier() {

            return valueSupplier;
        }

        public Builder withValueSupplier(final Supplier<Integer> valueSupplier) {

            this.valueSupplier = valueSupplier;
            return this;
        }

        public Supplier<Integer> getFreePointsSupplier() {

            return freePointsSupplier;
        }

        public Builder withFreePointsSupplier(final Supplier<Integer> freePointsSupplier) {

            this.freePointsSupplier = freePointsSupplier;
            return this;
        }

        public Consumer<String> getFreePointsConsumer() {

            return freePointsConsumer;
        }

        public Builder withFreePointsConsumer(final Consumer<String> freePointsConsumer) {

            this.freePointsConsumer = freePointsConsumer;
            return this;
        }

        public Runnable getDecreaseAction() {

            return decreaseAction;
        }

        public Builder withDecreaseAction(final Runnable decreaseAction) {

            this.decreaseAction = decreaseAction;
            return this;
        }

        public Runnable getIncreaseAction() {

            return increaseAction;
        }

        public Builder withIncreaseAction(final Runnable increaseAction) {

            this.increaseAction = increaseAction;
            return this;
        }

        public int getMinValue() {

            return minValue;
        }

        public Builder withMinValue(final int minValue) {

            this.minValue = minValue;
            return this;
        }

        public int getMaxValue() {

            return maxValue;
        }

        public Builder withMaxValue(final int maxValue) {

            this.maxValue = maxValue;
            return this;
        }

        public BooleanSupplier getEnablityPredicate() {

            return enablityPredicate;
        }

        public Builder withEnablityPredicate(final BooleanSupplier enablityPredicate) {

            this.enablityPredicate = enablityPredicate;
            return this;
        }

        public boolean isCompactLine() {

            return compactLine;
        }

        public Builder withCompactLine(final boolean compactLine) {

            this.compactLine = compactLine;
            return this;
        }

        public ControlBar build() {

            return new ControlBar(this);
        }
    }

    @Override
    public Collection<IGameActor> getActors() {

        return components;
    }
}
