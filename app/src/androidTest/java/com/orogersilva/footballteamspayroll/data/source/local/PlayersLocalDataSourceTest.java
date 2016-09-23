package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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

import java.util.Arrays;
import java.util.List;

import static com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by orogersilva on 9/22/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PlayersLocalDataSourceTest {

    // region FIELDS

    private static Context sContext;

    private final static String DB_NAME = "TeamsTest.sqlite";
    private TeamsDbHelper mDbHelper;

    private static PlayersLocalDataSource sPlayersLocalDataSource;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        sContext = InstrumentationRegistry.getTargetContext();

        sPlayersLocalDataSource = PlayersLocalDataSource.getInstance(sContext, DB_NAME);
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

    @Test
    public void getPlayers_whenExistsPlayers_onPlayersLoadedIsSuccessful() {

        // ARRANGE

        // Player 1

        final long ID_1 = 1;
        final String NAME_1 = "Seijas";
        final int AGE_1 = 30;
        final float SALARY_1 = 150000.0f;

        // Player 2

        final long ID_2 = 2;
        final String NAME_2 = "Luan";
        final int AGE_2 = 23;
        final float SALARY_2 = 200000.0f;

        Player player1 = new Player(ID_1, NAME_1, AGE_1, SALARY_1);
        Player player2 = new Player(ID_2, NAME_2, AGE_2, SALARY_2);

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values1 = new ContentValues();

        values1.put(PlayerEntry.COLUMN_NAME_ID, player1.getId());
        values1.put(PlayerEntry.COLUMN_NAME_NAME, player1.getName());
        values1.put(PlayerEntry.COLUMN_NAME_AGE, player1.getAge());
        values1.put(PlayerEntry.COLUMN_NAME_SALARY, player1.getSalary());

        ContentValues values2 = new ContentValues();

        values2.put(PlayerEntry.COLUMN_NAME_ID, player2.getId());
        values2.put(PlayerEntry.COLUMN_NAME_NAME, player2.getName());
        values2.put(PlayerEntry.COLUMN_NAME_AGE, player2.getAge());
        values2.put(PlayerEntry.COLUMN_NAME_SALARY, player2.getSalary());

        db.insert(PlayerEntry.TABLE_NAME, null, values1);
        db.insert(PlayerEntry.TABLE_NAME, null, values2);

        db.close();


        final List<Player> expectedPlayers = Arrays.asList(player1, player2);

        PlayersDataSource.LoadPlayersCallback callback = new PlayersDataSource.LoadPlayersCallback() {

            @Override
            public void onPlayersLoaded(List<Player> players) {

                // ASSERT

                assertEquals(expectedPlayers, players);
            }

            @Override
            public void onDataNotAvailable() {

                // ASSERT

                fail();
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
