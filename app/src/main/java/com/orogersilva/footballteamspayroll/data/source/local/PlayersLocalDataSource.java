package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.Player;
import com.orogersilva.footballteamspayroll.data.source.PlayersDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.orogersilva.footballteamspayroll.data.source.local.PersistenceContract.*;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class PlayersLocalDataSource implements PlayersDataSource {

    // region FIELDS

    private static PlayersLocalDataSource INSTANCE;

    private final static String DB_NAME = "Teams.sqlite";

    private DbHelper mDbHelper;

    // endregion

    // region CONSTRUCTORS

    private PlayersLocalDataSource(@NonNull Context context, @NonNull String databaseName, boolean isTesting) {

        mDbHelper = new DbHelper(context, databaseName, isTesting);
    }

    // endregion

    // region STATIC METHODS

    public static PlayersLocalDataSource getInstance(@NonNull Context context) {

        return getInstance(context, DB_NAME, false);
    }

    public static PlayersLocalDataSource getInstance(@NonNull Context context, @NonNull final String databaseName, boolean isTesting) {

        if (INSTANCE == null) {

            INSTANCE = new PlayersLocalDataSource(context, databaseName, isTesting);
        }

        return INSTANCE;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getPlayers(@NonNull LoadPlayersCallback callback) {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                PlayerEntry.COLUMN_NAME_ID,
                PlayerEntry.COLUMN_NAME_NAME,
                PlayerEntry.COLUMN_NAME_AGE,
                PlayerEntry.COLUMN_NAME_SALARY
        };

        Cursor c = db.query(
                PlayerEntry.TABLE_NAME, projection, null, null, null, null, null
        );

        List<Player> players = new ArrayList<>();

        if (c != null && c.getCount() > 0) {

            while (c.moveToNext()) {

                long id  = c.getLong(c.getColumnIndex(PlayerEntry.COLUMN_NAME_ID));
                String name = c.getString(c.getColumnIndex(PlayerEntry.COLUMN_NAME_NAME));
                int age = c.getInt(c.getColumnIndex(PlayerEntry.COLUMN_NAME_AGE));
                float salary = c.getFloat(c.getColumnIndex(PlayerEntry.COLUMN_NAME_SALARY));

                Player player = new Player(id, name, age, salary);

                players.add(player);
            }
        }

        if (c != null) {

            c.close();
        }

        db.close();

        if (players.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onPlayersLoaded(players);
        }
    }

    // endregion
}
