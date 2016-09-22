package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.source.TeamsDataSource;

import static com.orogersilva.footballteamspayroll.data.source.local.TeamsPersistenceContract.*;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class TeamsLocalDataSource implements TeamsDataSource {

    // region FIELDS

    private static TeamsLocalDataSource INSTANCE;

    private final String DB_NAME = "Teams.sqlite";

    private TeamsDbHelper mDbHelper;

    // endregion

    // region CONSTRUCTORS

    private TeamsLocalDataSource(@NonNull Context context) {

        mDbHelper = new TeamsDbHelper(context, DB_NAME);
    }

    // endregion

    // region STATIC METHODS

    public static TeamsLocalDataSource getInstance(@NonNull Context context) {

        if (INSTANCE == null) {

            INSTANCE = new TeamsLocalDataSource(context);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getTeams(@NonNull LoadTeamsCallback callback) {

        // TODO: 9/22/2016 TO IMPLEMENT AFTER CRUD OF PLAYER AND SUPPORTER MODELS.
    }

    // endregion
}