package spaceattack.game.bars;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.system.graphics.Textures;

public class HpEnergyBar extends Bar {

    private AbstractBarFiller energyFiller;
    private AbstractBarFiller hpFiller;

    public HpEnergyBar(IPool energyPool, IPool hpPool) {

        texture = Textures.HP_ENE_BAR.getTexture();
    }

    void setFillers(AbstractBarFiller hpFiller, AbstractBarFiller energyFiller) {

        this.hpFiller = hpFiller;
        this.energyFiller = energyFiller;
    }

    @Override
    protected void drawTexture(IBatch batch) {

        batch.draw(texture, 0, Sizes.GAME_HEIGHT - texture.getHeight());
    }

    @Override
    protected void drawFillerLabel(IBatch batch) {

        energyFiller.drawLabel(batch);
        hpFiller.drawLabel(batch);
    }

    @Override
    protected void drawBarRect(IBatch batch) {

        energyFiller.drawRect(batch);
        hpFiller.drawRect(batch);
    }

    float getMaxEnergy() {

        return energyFiller.getMaxAmount();
    }

    float getEnergy() {

        return energyFiller.getAmount();
    }

    float getEnergyPercent() {

        return energyFiller.getPercent();
    }

    float getMaxHp() {

        return hpFiller.getMaxAmount();
    }

    float getHp() {

        return hpFiller.getAmount();
    }

    float getHpPercent() {

        return hpFiller.getPercent();
    }
}
