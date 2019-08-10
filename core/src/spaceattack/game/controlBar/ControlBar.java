package spaceattack.game.controlBar;

import java.util.Collection;
import java.util.LinkedList;
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

        IUtils utils = Factories.getUtilsFactory().create();

        float lineYPos = lineYPos(lineIndex, firstLineYPos, intervalYPos);

        ILabel nameLabel = utils.createMenuLabel(name, lineYPos, 0xdaa520ff);
        ILabel valueLabel = utils.createMenuLabel(formatValue(() -> 0), lineYPos, 0xff0000ff);
        float valueLabelWidth = valueLabel.getWidth();
        valueLabel.setText(formatValue(valueSupplier));

        IButton decreaserButton = actionButton(stage, details, decreaseAction,
                freePointsSupplier, valueSupplier, valueLabel::setText, freePointsConsumer, detailsLabel, lineYPos,
                "-");

        IButton increaserButton = actionButton(stage, details, increaseAction,
                freePointsSupplier, valueSupplier, valueLabel::setText, freePointsConsumer, detailsLabel, lineYPos,
                "+");

        ILabel infoToucher = utils.createDetailerToucher(lineYPos, details, detailsLabel);

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
                button -> valueSupplier.get() > minValue);
        stage.addButtonsEnabledPredicate(increaserButton,
                button -> freePointsSupplier.get() > 0);
    }

    private IButton actionButton(final UIStage stage, final String details,
            final Runnable action, final Supplier<Integer> freePointsSupplier,
            final Supplier<Integer> valueSupplier,
            final Consumer<String> valueConsumer, final Consumer<String> freePointsConsumer,
            final ILabel detailsLabel, final float yPos, final String text) {

        IButton button = Factories.getTextButtonFactory().create(text);

        button.setPosition(0f, yPos);
        button.setSize(Sizes.SQUARE_BUTTON_SIZE, Sizes.SQUARE_BUTTON_SIZE);
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

        public UIStage getStage() {

            return stage;
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

        public ControlBar build() {

            return new ControlBar(this);
        }
    }

    @Override
    public Collection<IGameActor> getActors() {

        return components;
    }
}
