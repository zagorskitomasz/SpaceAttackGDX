package spaceattack.game.controlBar;

import spaceattack.game.actors.ILabel;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.stages.UIStage;

public enum ControlBarBuilder {

    INSTANCE;

    public ControlBar attributeBar(final int lineIndex, final Attribute attribute, final UIStage stage,
            final ILabel detailsLabel, final ILabel freePointsLabel) {

        return ControlBar.builder()
                .withDecreaseAction(() -> stage.getGameProgress().getAttributes().decrease(attribute))
                .withDetails(attribute.getDetails())
                .withDetailsLabel(detailsLabel)
                .withFirstLineYPos(0.6f)
                .withFreePointsConsumer(freePointsLabel::setText)
                .withFreePointsSupplier(() -> stage.getGameProgress().getAttributes().getFreePoints())
                .withIncreaseAction(() -> stage.getGameProgress().getAttributes().increase(attribute))
                .withIntervalYPos(0.12f)
                .withLineIndex(lineIndex)
                .withMinValue(Attribute.MIN_VALUE)
                .withName(attribute.getName())
                .withStage(stage)
                .withValueSupplier(() -> stage.getGameProgress().getAttributes().get(attribute))
                .build();
    }
}
