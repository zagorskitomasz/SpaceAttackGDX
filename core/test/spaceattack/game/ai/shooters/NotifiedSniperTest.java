package spaceattack.game.ai.shooters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import spaceattack.ext.utils.ExtUtilsFactory;
import spaceattack.game.ai.ShooterAI;
import spaceattack.game.factories.Factories;

public class NotifiedSniperTest {

    private ShooterAI sniper;

    @Before
    public void setUp() {

        Factories.setUtilsFactory(ExtUtilsFactory.INSTANCE);
        sniper = new NotifiedSniper();
    }

    @Test
    public void notifiedSniperCanShootBoth() {

        sniper.notify(null);
        assertEquals(PossibleAttacks.BOTH, sniper.checkShot());
    }

    @Test
    public void notifiedSniperCanShootBothEachTime() {

        sniper.notify(null);
        sniper.checkShot();
        sniper.checkShot();
        sniper.checkShot();
        assertEquals(PossibleAttacks.BOTH, sniper.checkShot());
    }

    @Test
    public void unnotifiedSniperCanShotPrimary() {

        assertEquals(PossibleAttacks.PRIMARY, sniper.checkShot());
    }

    @Test
    public void notifiedButSleepedSniperCanShootBothOnlyOneTime() throws InterruptedException {

        sniper.notify(null);
        Thread.sleep(1000);
        sniper.checkShot();
        assertEquals(PossibleAttacks.PRIMARY, sniper.checkShot());
    }
}
