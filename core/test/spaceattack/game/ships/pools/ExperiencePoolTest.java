package spaceattack.game.ships.pools;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import spaceattack.consts.Experience;
import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.GameProgress;
import spaceattack.game.factories.Factories;

public class ExperiencePoolTest {

    private static final int LEVEL = 3;
    private static final int LEVEL_BACKUP = 2;

    private ExperiencePool pool;
    private GameProgress progress;
    private GameProgress backup;

    @Before
    public void setUp() {

        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        progress = new GameProgress();
        progress.setLevel(LEVEL);
        progress.setExperience(1700l);

        backup = new GameProgress();
        backup.setLevel(LEVEL_BACKUP);
        backup.setExperience(800l);

        pool = new ExperiencePool(progress, backup);
    }

    @Test
    public void valuesAreGetFromExperienceTable() {

        pool.update();

        assertEquals(Experience.INSTANCE.expForLevel(LEVEL + 1) - Experience.INSTANCE.expForLevel(LEVEL),
                pool.getMaxAmount(), 0);
        assertEquals(1700 - Experience.INSTANCE.expForLevel(LEVEL), pool.getAmount(), 0);
    }

    @Test
    public void afterDestroyingUsingValuesFromBackup() {

        pool.update();
        pool.destroy();
        pool.update();

        assertEquals(Experience.INSTANCE.expForLevel(LEVEL_BACKUP + 1) - Experience.INSTANCE.expForLevel(LEVEL_BACKUP),
                pool.getMaxAmount(), 0);
        assertEquals(800 - Experience.INSTANCE.expForLevel(LEVEL_BACKUP), pool.getAmount(), 0);
    }
}
