package spaceattack.game.controlBar;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import spaceattack.consts.Sizes;
import spaceattack.game.GameProgress;
import spaceattack.game.actors.IActorsContainer;
import spaceattack.game.actors.IGameActor;
import spaceattack.game.actors.ILabel;
import spaceattack.game.buttons.IImageButton;
import spaceattack.game.factories.Factories;
import spaceattack.game.stages.UIStage;
import spaceattack.game.system.graphics.ITexture;
import spaceattack.game.system.graphics.Textures;
import spaceattack.game.utils.GameplayLabel;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.SpecialWeapons;

public class WeaponBar implements IActorsContainer {

    public static Builder builder() {

        return new Builder();
    }

    private final Collection<IGameActor> components;
    private final SpecialWeapons weapon;
    private final IImageButton button;
    private final GameProgress progress;

    private WeaponBar(final Builder builder) {

        UIStage stage = builder.getStage();
        ILabel detailsLabel = builder.getDetailsLabel();

        int lineIndex = builder.getLineIndex();
        float firstLineYPos = builder.getFirstLineYPos();
        float intervalYPos = builder.getIntervalYPos();

        progress = stage.getGameProgress();
        weapon = builder.getWeapon();
        List<Runnable> updateActions = builder.getUpdateActions();

        IUtils utils = Factories.getUtilsFactory().create();

        float lineYPos = lineYPos(lineIndex, firstLineYPos, intervalYPos);

        ILabel nameLabel;
        if (isWeaponUnlocked()) {
            nameLabel = utils.createMenuLabel(weapon.weaponName, lineYPos, 0xdaa520ff);
        }
        else {
            nameLabel = utils.createMenuLabel("Locked", lineYPos, 0x808080);
        }

        button = weaponButton(stage, detailsLabel, lineYPos, updateActions);

        ILabel infoToucher = utils.createDetailerToucher(lineYPos, detailsLabelText(), detailsLabel, false);

        button.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.2f - button.getWidth());
        nameLabel.setX(Sizes.GAME_WIDTH - Sizes.GAME_WIDTH * 0.27f - button.getWidth() - nameLabel.getWidth());

        components = new LinkedList<>();
        components.add(new GameplayLabel(nameLabel));
        components.add(new GameplayLabel(infoToucher));
        components.add(button);
    }

    protected String detailsLabelText() {

        return isWeaponUnlocked() ? weapon.description
                : ("Weapon available after\ncompleting mission " + weapon.unlockedOnMission);
    }

    private IImageButton weaponButton(final UIStage stage, final ILabel detailsLabel, final float yPos,
            final List<Runnable> updateActions) {

        ITexture texture = isWeaponUnlocked() ? weapon.uncheckedTexture.getTexture()
                : Textures.LOCKED_WEAPON.getTexture();

        IImageButton button = Factories.getImageButtonsFactory().create(texture, texture, texture);

        button.setPosition(0f, yPos);
        updateActions.add(this::updateSelection);

        button.setAction(() -> {
            synchronized (stage) {
                detailsLabel.update(detailsLabelText());
                if (isWeaponUnlocked()) {
                    progress.setSpecialWeapon(weapon.name());
                    updateActions.forEach(Runnable::run);
                }
            }
        });
        return button;
    }

    protected boolean isWeaponUnlocked() {

        return progress.getMission() >= weapon.unlockedOnMission;
    }

    public void updateSelection() {

        if (!isWeaponUnlocked()) {
            return;
        }
        if (progress.getSpecialWeapon().equals(weapon.name())) {
            button.setUp(weapon.checkedTexture.getTexture());
            button.setDown(weapon.checkedTexture.getTexture());
        }
        else {
            button.setUp(weapon.uncheckedTexture.getTexture());
            button.setDown(weapon.uncheckedTexture.getTexture());
        }
    }

    private float lineYPos(final int lineIndex, final float firstLineYPos, final float intervalYPos) {

        return Sizes.GAME_HEIGHT * firstLineYPos - Sizes.GAME_HEIGHT * lineIndex * intervalYPos;
    }

    public static class Builder {

        private UIStage stage;

        private int lineIndex;
        private float firstLineYPos;
        private float intervalYPos;

        private ILabel detailsLabel;

        private SpecialWeapons weapon;
        private List<Runnable> updateActions;

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

        public ILabel getDetailsLabel() {

            return detailsLabel;
        }

        public Builder withDetailsLabel(final ILabel detailsLabel) {

            this.detailsLabel = detailsLabel;
            return this;
        }

        public SpecialWeapons getWeapon() {

            return weapon;
        }

        public Builder withWeapon(final SpecialWeapons weapon) {

            this.weapon = weapon;
            return this;
        }

        public List<Runnable> getUpdateActions() {

            return updateActions;
        }

        public Builder withUpdateActions(final List<Runnable> updateActions) {

            this.updateActions = updateActions;
            return this;
        }

        public WeaponBar build() {

            return new WeaponBar(this);
        }
    }

    @Override
    public Collection<IGameActor> getActors() {

        return components;
    }
}
