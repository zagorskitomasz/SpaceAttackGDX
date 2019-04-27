package spaceattack.game.bars;

import spaceattack.consts.Sizes;
import spaceattack.game.batch.IBatch;
import spaceattack.game.ships.pools.IPool;
import spaceattack.game.utils.IUtils;

public class ExperienceBarFiller extends AbstractBarFiller {

    public ExperienceBarFiller(IPool pool, IUtils utils) {

        super(pool, utils);

        rect.setRed(0.6f);
        rect.setGreen(0.1f);
        rect.setBlue(0.6f);

        rect.setX(Sizes.GAME_WIDTH - 9 * Sizes.X_FACTOR - Sizes.EXP_BAR_WIDTH);
        rect.setY(Sizes.GAME_HEIGHT - 900 * Sizes.Y_FACTOR + 22 * Sizes.Y_FACTOR);
        rect.setWidth(Sizes.EXP_BAR_WIDTH);
    }

    @Override
    public void drawRect(IBatch batch) {

        rect.setHeight((800 - 44) * Sizes.Y_FACTOR * percent);
        batch.rect(rect);
    }
}
