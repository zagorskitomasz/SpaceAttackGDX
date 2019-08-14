package spaceattack.game.controlBar;

import java.util.List;

import spaceattack.game.actors.ILabel;
import spaceattack.game.rpg.Attribute;
import spaceattack.game.rpg.Improvement;
import spaceattack.game.stages.UIStage;
import spaceattack.game.weapons.SpecialWeapons;

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

    public WeaponBar weaponBar(final int lineIndex, final SpecialWeapons weapon, final UIStage stage,
            final ILabel detailsLabel, final List<Runnable> updateActions) {

        return WeaponBar.builder()
                .withDetailsLabel(detailsLabel)
                .withFirstLineYPos(0.6f)
                .withIntervalYPos(0.1f)
                .withLineIndex(lineIndex)
                .withStage(stage)
                .withUpdateActions(updateActions)
                .withWeapon(weapon)
                .build();
    }

    public ControlBar improvementBar(final int lineIndex, final Improvement improvement, final UIStage stage,
            final ILabel detailsLabel, final ILabel freePointsLabel) {

        return ControlBar.builder()
                .withDecreaseAction(() -> stage.getGameProgress().getImprovements().decrease(improvement))
                .withDetails(improvement.getDetails())
                .withDetailsLabel(detailsLabel)
                .withFirstLineYPos(0.76f)
                .withFreePointsConsumer(freePointsLabel::setText)
                .withFreePointsSupplier(() -> stage.getGameProgress().getImprovements().getFreePoints())
                .withIncreaseAction(() -> stage.getGameProgress().getImprovements().increase(improvement))
                .withIntervalYPos(0.06f)
                .withLineIndex(lineIndex)
                .withMinValue(Improvement.MIN_VALUE)
                .withName(improvement.getName())
                .withStage(stage)
                .withEnablityPredicate(() -> stage.getGameProgress().getLevel() >= improvement.getLevelRequired())
                .withCompactLine(true)
                .withLevelRequired(improvement.getLevelRequired())
                .withValueSupplier(() -> stage.getGameProgress().getImprovements().get(improvement))
                .build();
    }
}
