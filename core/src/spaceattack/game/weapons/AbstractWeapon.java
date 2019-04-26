package spaceattack.game.weapons;

import spaceattack.consts.Consts;
import spaceattack.game.system.FrameController;
import spaceattack.game.utils.IUtils;
import spaceattack.game.weapons.missiles.Missile;

public abstract class AbstractWeapon implements IWeapon {

    @SuppressWarnings("unused")
    private IUtils utils;
    protected FrameController frameController;
    protected IWeaponController controller;
    protected MissilesLauncher launcher;

    protected float dmg;
    protected float speed;
    protected float energyCost;

    protected abstract Missile buildMissile();

    public void setUtils(IUtils utils) {

        this.utils = utils;
        frameController = new FrameController(utils, Consts.Weapons.LASER_ATTACKS_PER_SECOND);
    }

    public void setController(IWeaponController weaponController) {

        this.controller = weaponController;
    }

    public void setMissilesLauncher(MissilesLauncher missilesLauncher) {

        launcher = missilesLauncher;
    }

    @Override
    public boolean use() {

        if (!frameController.check())
            return false;

        if (!controller.takeEnergy(energyCost))
            return false;

        Missile missile = buildMissile();
        launcher.launch(missile);
        return true;
    }

    @Override
    public float getEnergyCost() {

        return energyCost;
    }
}
