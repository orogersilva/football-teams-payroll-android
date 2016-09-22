package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.footballteamspayroll.data.Player;
import com.orogersilva.footballteamspayroll.data.source.PlayersDataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by orogersilva on 9/22/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayersLocalDataSourceTest {

    // region FIELDS

    private static Context sContext;

    private final String DB_NAME = "TeamsTest.sqlite";
    private TeamsDbHelper mDbHelper;

    private static PlayersLocalDataSource sPlayersLocalDataSource;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        sContext = InstrumentationRegistry.getTargetContext();

        sPlayersLocalDataSource = PlayersLocalDataSource.getInstance(sContext);
    }

    @Before
    public void setup() {

        mDbHelper = new TeamsDbHelper(sContext, DB_NAME);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void getPlayers_whenNotExistsPlayers_onDataNotAvailableIsSuccessful() {

        // ARRANGE

        PlayersDataSource.LoadPlayersCallback callback = new PlayersDataSource.LoadPlayersCallback() {

            @Override
            public void onPlayersLoaded(List<Player> players) {

                // ASSERT

                fail();
            }

            @Override
            public void onDataNotAvailable() {

                // ASSERT

                return;
            }
        };

        // ACT

        sPlayersLocalDataSource.getPlayers(callback);
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void cleanUp() {

        sContext.deleteDatabase(DB_NAME);
    }

    @AfterClass
    public static void teardownClass() {

        sPlayersLocalDataSource = null;
    }

    // endregion
}
