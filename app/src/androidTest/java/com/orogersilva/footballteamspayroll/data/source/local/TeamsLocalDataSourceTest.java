package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.orogersilva.footballteamspayroll.data.Player;
import com.orogersilva.footballteamspayroll.data.Supporter;
import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.data.source.TeamsDataSource;
import com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.PlayerEntry;
import com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.SupporterEntry;
import com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.TeamEntry;
import com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.TeamPlayerSupportEntry;
import com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.TeamSupporterSupportEntry;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by orogersilva on 9/22/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TeamsLocalDataSourceTest {

    // region FIELDS

    private static Context sContext;

    private static final String DB_NAME = "TeamsTest.sqlite";
    private TeamsDbHelper mDbHelper;

    private static TeamsLocalDataSource sTeamsLocalDataSource;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        sContext = InstrumentationRegistry.getTargetContext();

        sTeamsLocalDataSource = TeamsLocalDataSource.getInstance(sContext, DB_NAME);
    }

    @Before
    public void setup() {

        mDbHelper = new TeamsDbHelper(sContext, DB_NAME);
    }

    // endregion

    // region TEST METHODS

    @Test
    public void getTeams_whenNotExistsTeams_onDataNotAvailableIsSuccessful() {

        // ARRANGE

        TeamsDataSource.LoadTeamsCallback callback = new TeamsDataSource.LoadTeamsCallback() {

            @Override
            public void onTeamsLoaded(List<Team> teams) {

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

        sTeamsLocalDataSource.getTeams(callback);
    }

    @Test
    public void getTeams_whenExistsTeams_onTeamsLoadedIsSuccessful() {

        // ARRANGE

        // Player 1

        final long PLAYER_ID_1 = 1;
        final String PLAYER_NAME_1 = "Seijas";
        final int PLAYER_AGE_1 = 30;
        final float PLAYER_SALARY_1 = 150000.0f;

        // Player 2

        final long PLAYER_ID_2 = 2;
        final String PLAYER_NAME_2 = "Sasha";
        final int PLAYER_AGE_2 = 24;
        final float PLAYER_SALARY_2 = 50000.0f;

        // Player 3

        final long PLAYER_ID_3 = 3;
        final String PLAYER_NAME_3 = "Luan";
        final int PLAYER_AGE_3 = 23;
        final float PLAYER_SALARY_3 = 200000.0f;

        // Supporter 1

        final long SUPPORTER_ID_1 = 1;
        final String SUPPORTER_NAME_1 = "Kelly Matos";
        final String SUPPORTER_REGISTRATION_ID_1 = "123456";
        final boolean SUPPORTER_IS_OVERDUE_1 = true;

        // Supporter 2

        final long SUPPORTER_ID_2 = 2;
        final String SUPPORTER_NAME_2 = "Renata Fan";
        final String SUPPORTER_REGISTRATION_ID_2 = "789012";
        final boolean SUPPORTER_IS_OVERDUE_2 = false;

        // Supporter 3

        final long SUPPORTER_ID_3 = 3;
        final String SUPPORTER_NAME_3 = "Gisele Bundchen";
        final String SUPPORTER_REGISTRATION_ID_3 = "345678";
        final boolean SUPPORTER_IS_OVERDUE_3 = false;

        Player player1 = new Player(PLAYER_ID_1, PLAYER_NAME_1, PLAYER_AGE_1, PLAYER_SALARY_1);
        Player player2 = new Player(PLAYER_ID_2, PLAYER_NAME_2, PLAYER_AGE_2, PLAYER_SALARY_2);
        Player player3 = new Player(PLAYER_ID_3, PLAYER_NAME_3, PLAYER_AGE_3, PLAYER_SALARY_3);
        Supporter supporter1 = new Supporter(SUPPORTER_ID_1, SUPPORTER_NAME_1, SUPPORTER_REGISTRATION_ID_1, SUPPORTER_IS_OVERDUE_1);
        Supporter supporter2 = new Supporter(SUPPORTER_ID_2, SUPPORTER_NAME_2, SUPPORTER_REGISTRATION_ID_2, SUPPORTER_IS_OVERDUE_2);
        Supporter supporter3 = new Supporter(SUPPORTER_ID_3, SUPPORTER_NAME_3, SUPPORTER_REGISTRATION_ID_3, SUPPORTER_IS_OVERDUE_3);

        // Team 1

        final long TEAM_ID_1 = 1;
        final String TEAM_NAME_1 = "Internacional";

        List<Player> team1Players = Arrays.asList(player1, player2);
        List<Supporter> team1Supporters = Arrays.asList(supporter1, supporter2);

        // Team 2

        final long TEAM_ID_2 = 2;
        final String TEAM_NAME_2 = "Grêmio";

        List<Player> team2Players = Arrays.asList(player3);
        List<Supporter> team2Supporters = Arrays.asList(supporter3);

        Team team1 = new Team(TEAM_ID_1, TEAM_NAME_1, team1Players, team1Supporters);
        Team team2 = new Team(TEAM_ID_2, TEAM_NAME_2, team2Players, team2Supporters);

        final List<Team> expectedTeams = Arrays.asList(team1, team2);


        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues player1Values = new ContentValues();

        player1Values.put(PlayerEntry.COLUMN_NAME_ID, player1.getId());
        player1Values.put(PlayerEntry.COLUMN_NAME_NAME, player1.getName());
        player1Values.put(PlayerEntry.COLUMN_NAME_AGE, player1.getAge());
        player1Values.put(PlayerEntry.COLUMN_NAME_SALARY, player1.getSalary());

        ContentValues player2Values = new ContentValues();

        player2Values.put(PlayerEntry.COLUMN_NAME_ID, player2.getId());
        player2Values.put(PlayerEntry.COLUMN_NAME_NAME, player2.getName());
        player2Values.put(PlayerEntry.COLUMN_NAME_AGE, player2.getAge());
        player2Values.put(PlayerEntry.COLUMN_NAME_SALARY, player2.getSalary());

        ContentValues supporter1Values = new ContentValues();

        ContentValues player3Values = new ContentValues();

        player3Values.put(PlayerEntry.COLUMN_NAME_ID, player3.getId());
        player3Values.put(PlayerEntry.COLUMN_NAME_NAME, player3.getName());
        player3Values.put(PlayerEntry.COLUMN_NAME_AGE, player3.getAge());
        player3Values.put(PlayerEntry.COLUMN_NAME_SALARY, player3.getSalary());

        supporter1Values.put(SupporterEntry.COLUMN_NAME_ID, supporter1.getId());
        supporter1Values.put(SupporterEntry.COLUMN_NAME_NAME, supporter1.getName());
        supporter1Values.put(SupporterEntry.COLUMN_NAME_REGISTRATION_ID, supporter1.getRegistrationId());
        supporter1Values.put(SupporterEntry.COLUMN_NAME_OVERDUE, supporter1.isOverdue());

        ContentValues supporter2Values = new ContentValues();

        supporter2Values.put(SupporterEntry.COLUMN_NAME_ID, supporter2.getId());
        supporter2Values.put(SupporterEntry.COLUMN_NAME_NAME, supporter2.getName());
        supporter2Values.put(SupporterEntry.COLUMN_NAME_REGISTRATION_ID, supporter2.getRegistrationId());
        supporter2Values.put(SupporterEntry.COLUMN_NAME_OVERDUE, supporter2.isOverdue());

        ContentValues supporter3Values = new ContentValues();

        supporter3Values.put(SupporterEntry.COLUMN_NAME_ID, supporter3.getId());
        supporter3Values.put(SupporterEntry.COLUMN_NAME_NAME, supporter3.getName());
        supporter3Values.put(SupporterEntry.COLUMN_NAME_REGISTRATION_ID, supporter3.getRegistrationId());
        supporter3Values.put(SupporterEntry.COLUMN_NAME_OVERDUE, supporter3.isOverdue());

        ContentValues team1Values = new ContentValues();

        team1Values.put(TeamEntry.COLUMN_NAME_ID, team1.getId());
        team1Values.put(TeamEntry.COLUMN_NAME_NAME, team1.getName());

        ContentValues team2Values = new ContentValues();

        team2Values.put(TeamEntry.COLUMN_NAME_ID, team2.getId());
        team2Values.put(TeamEntry.COLUMN_NAME_NAME, team2.getName());

        db.insert(PlayerEntry.TABLE_NAME, null, player1Values);
        db.insert(PlayerEntry.TABLE_NAME, null, player2Values);
        db.insert(PlayerEntry.TABLE_NAME, null, player3Values);
        db.insert(SupporterEntry.TABLE_NAME, null, supporter1Values);
        db.insert(SupporterEntry.TABLE_NAME, null, supporter2Values);
        db.insert(SupporterEntry.TABLE_NAME, null, supporter3Values);
        db.insert(TeamEntry.TABLE_NAME, null, team1Values);
        db.insert(TeamEntry.TABLE_NAME, null, team2Values);

        for (Team team : expectedTeams) {

            for (Player player : team.getPlayers()) {

                ContentValues contentValues = new ContentValues();

                contentValues.put(TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID, team.getId());
                contentValues.put(TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID, player.getId());

                db.insert(TeamPlayerSupportEntry.TABLE_NAME, null, contentValues);
            }

            for (Supporter supporter : team.getSupporters()) {

                ContentValues contentValues = new ContentValues();

                contentValues.put(TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID, team.getId());
                contentValues.put(TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID, supporter.getId());

                db.insert(TeamSupporterSupportEntry.TABLE_NAME, null, contentValues);
            }
        }

        db.close();


        TeamsDataSource.LoadTeamsCallback callback = new TeamsDataSource.LoadTeamsCallback() {

            @Override
            public void onTeamsLoaded(List<Team> teams) {

                // ASSERT

                assertEquals(expectedTeams, teams);
            }

            @Override
            public void onDataNotAvailable() {

                // ASSERT

                fail();
            }
        };

        // ACT

        sTeamsLocalDataSource.getTeams(callback);
    }

    // endregion

    // region TEARDOWN METHODS

    @After
    public void cleanUp() {

        sContext.deleteDatabase(DB_NAME);
    }

    @AfterClass
    public static void teardownClass() {

        sTeamsLocalDataSource = null;
    }

    // endregion
}
