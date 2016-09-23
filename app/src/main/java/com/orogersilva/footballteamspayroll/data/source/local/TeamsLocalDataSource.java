package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.Player;
import com.orogersilva.footballteamspayroll.data.Supporter;
import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.data.source.TeamsDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.*;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class TeamsLocalDataSource implements TeamsDataSource {

    // region FIELDS

    private static TeamsLocalDataSource INSTANCE;

    private static final String DB_NAME = "Teams.sqlite";

    private TeamsDbHelper mDbHelper;

    // endregion

    // region CONSTRUCTORS

    private TeamsLocalDataSource(@NonNull Context context, @NonNull String databaseName) {

        mDbHelper = new TeamsDbHelper(context, databaseName);
    }

    // endregion

    // region STATIC METHODS

    public static TeamsLocalDataSource getInstance(@NonNull Context context) {

        return getInstance(context, DB_NAME);
    }

    public static TeamsLocalDataSource getInstance(@NonNull Context context, @NonNull final String databaseName) {

        if (INSTANCE == null) {

            INSTANCE = new TeamsLocalDataSource(context, databaseName);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getTeams(@NonNull LoadTeamsCallback callback) {

        List<Long> teamIds = getTeamsId();

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String teamsSql = "SELECT t.team_name " +
                            "FROM team as t " +
                            "WHERE t.team_id = ?";

        String teamPlayersSql = "SELECT p.player_id, p.player_name, p.player_age, p.player_salary " +
                                "FROM team as t " +
                                "INNER JOIN team_player_support as tps " +
                                    "ON t.team_id = tps.team_player_support_team_id " +
                                "INNER JOIN player as p " +
                                    "ON p.player_id = tps.team_player_support_player_id " +
                                "WHERE t.team_id = ?";

        String teamSupportersSql = "SELECT s.supporter_id, s.supporter_name, s.supporter_registration_id, s.supporter_overdue " +
                                        "FROM team as t " +
                                        "INNER JOIN team_supporter_support as tss " +
                                            "ON t.team_id = tss.team_supporter_support_team_id " +
                                        "INNER JOIN supporter as s " +
                                            "ON s.supporter_id = tss.team_supporter_support_supporter_id " +
                                        "WHERE t.team_id = ?";

        List<Team> teams = new ArrayList<>();

        for (Long teamId : teamIds) {

            Cursor c1 = db.rawQuery(teamsSql, new String[]{String.valueOf(teamId)});

            if (c1 != null && c1.getCount() > 0) {

                String teamName = null;

                if (c1.moveToFirst()) {

                    teamName = c1.getString(c1.getColumnIndex(TeamEntry.COLUMN_NAME_NAME));
                }

                c1.close();

                List<Player> players = new ArrayList<>();

                Cursor c2 = db.rawQuery(teamPlayersSql, new String[]{String.valueOf(teamId)});

                if (c2 != null && c2.getCount() > 0) {

                    while (c2.moveToNext()) {

                        long playerId = c2.getLong(c2.getColumnIndex(PlayerEntry.COLUMN_NAME_ID));
                        String playerName = c2.getString(c2.getColumnIndex(PlayerEntry.COLUMN_NAME_NAME));
                        int playerAge = c2.getInt(c2.getColumnIndex(PlayerEntry.COLUMN_NAME_AGE));
                        float playerSalary = c2.getFloat(c2.getColumnIndex(PlayerEntry.COLUMN_NAME_SALARY));

                        Player player = new Player(playerId, playerName, playerAge, playerSalary);

                        players.add(player);
                    }

                    c2.close();

                    List<Supporter> supporters = new ArrayList<>();

                    Cursor c3 = db.rawQuery(teamSupportersSql, new String[]{String.valueOf(teamId)});

                    if (c3 != null && c3.getCount() > 0) {

                        while (c3.moveToNext()) {

                            long supporterId = c3.getLong(c3.getColumnIndex(SupporterEntry.COLUMN_NAME_ID));
                            String supporterName = c3.getString(c3.getColumnIndex(SupporterEntry.COLUMN_NAME_NAME));
                            String supporterRegistrationId = c3.getString(c3.getColumnIndex(SupporterEntry.COLUMN_NAME_REGISTRATION_ID));
                            boolean supporterIsOverdue = c3.getInt(c3.getColumnIndex(SupporterEntry.COLUMN_NAME_OVERDUE)) > 0;

                            Supporter supporter = new Supporter(supporterId, supporterName, supporterRegistrationId, supporterIsOverdue);

                            supporters.add(supporter);
                        }

                        c3.close();

                        Team team = new Team(teamId, teamName, players, supporters);

                        teams.add(team);
                    }
                }
            }
        }

        db.close();

        if (teams.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onTeamsLoaded(teams);
        }
    }

    // endregion

    // region UTILITY METHODS

    private List<Long> getTeamsId() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                TeamEntry.COLUMN_NAME_ID,
                TeamEntry.COLUMN_NAME_NAME,
        };

        Cursor c = db.query(
                true, TeamEntry.TABLE_NAME, projection, null, null, TeamEntry.COLUMN_NAME_ID,
                null, TeamEntry.COLUMN_NAME_ID, null
        );

        List<Long> ids = new ArrayList<>();

        if (c != null && c.getCount() > 0) {

            while (c.moveToNext()) {

                long id = c.getLong(c.getColumnIndex(TeamEntry.COLUMN_NAME_ID));

                ids.add(id);
            }
        }

        if (c != null) {

            c.close();
        }

        db.close();

        return ids;
    }

    // endregion
}