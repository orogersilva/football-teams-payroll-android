package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class TeamsDbHelper extends SQLiteOpenHelper {

    // region FIELDS

    public static final int DATABASE_VERSION = 1;

    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEPARATOR = ",";


    private static final String SQL_CREATE_PLAYER_ENTRIES =
            "CREATE TABLE " + TeamsPersistenceContract.PlayerEntry.TABLE_NAME + " (" +
                    TeamsPersistenceContract.PlayerEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    TeamsPersistenceContract.PlayerEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                    TeamsPersistenceContract.PlayerEntry.COLUMN_NAME_AGE + INTEGER_TYPE + COMMA_SEPARATOR +
                    TeamsPersistenceContract.PlayerEntry.COLUMN_NAME_SALARY + REAL_TYPE +
                    " )";

    private static final String SQL_CREATE_SUPPORTER_ENTRIES =
            "CREATE TABLE " + TeamsPersistenceContract.SupporterEntry.TABLE_NAME + " (" +
                    TeamsPersistenceContract.SupporterEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    TeamsPersistenceContract.SupporterEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                    TeamsPersistenceContract.SupporterEntry.COLUMN_NAME_REGISTRATION_ID + TEXT_TYPE + COMMA_SEPARATOR +
                    TeamsPersistenceContract.SupporterEntry.COLUMN_NAME_OVERDUE + BOOLEAN_TYPE +
                    " )";

    private static final String SQL_CREATE_TEAM_ENTRIES =
            "CREATE TABLE " + TeamsPersistenceContract.TeamEntry.TABLE_NAME + " (" +
                    TeamsPersistenceContract.TeamEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    TeamsPersistenceContract.TeamEntry.COLUMN_NAME_NAME + TEXT_TYPE +
                    ")";

    private static final String SQL_CREATE_TEAM_PLAYER_SUPPORT_ENTRIES =
            "CREATE TABLE " + TeamsPersistenceContract.TeamPlayerSupportEntry.TABLE_NAME + " (" +
                    TeamsPersistenceContract.TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    TeamsPersistenceContract.TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    " PRIMARY KEY (" + TeamsPersistenceContract.TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID + COMMA_SEPARATOR + TeamsPersistenceContract.TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamsPersistenceContract.TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID +
                        ") REFERENCES " + TeamsPersistenceContract.TeamEntry.TABLE_NAME + "(" + TeamsPersistenceContract.TeamEntry.COLUMN_NAME_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamsPersistenceContract.TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID +
                        ") REFERENCES " + TeamsPersistenceContract.PlayerEntry.TABLE_NAME + "(" + TeamsPersistenceContract.PlayerEntry.COLUMN_NAME_ID + ")" +
                    " )";

    private static final String SQL_CREATE_TEAM_SUPPORTER_SUPPORT_ENTRIES =
            "CREATE TABLE " + TeamsPersistenceContract.TeamSupporterSupportEntry.TABLE_NAME + " (" +
                    TeamsPersistenceContract.TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    TeamsPersistenceContract.TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    " PRIMARY KEY (" + TeamsPersistenceContract.TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID + COMMA_SEPARATOR + TeamsPersistenceContract.TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamsPersistenceContract.TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID +
                        ") REFERENCES " + TeamsPersistenceContract.TeamEntry.TABLE_NAME + "(" + TeamsPersistenceContract.TeamEntry.COLUMN_NAME_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamsPersistenceContract.TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID +
                        ") REFERENCES " + TeamsPersistenceContract.SupporterEntry.TABLE_NAME + "(" + TeamsPersistenceContract.SupporterEntry.COLUMN_NAME_ID + ")" +
                    " )";

    private static final String SQL_DELETE_TEAM_PLAYER_SUPPORT_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamsPersistenceContract.TeamPlayerSupportEntry.TABLE_NAME;
    private static final String SQL_DELETE_TEAM_SUPPORTER_SUPPORT_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamsPersistenceContract.TeamSupporterSupportEntry.TABLE_NAME;
    private static final String SQL_DELETE_PLAYER_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamsPersistenceContract.PlayerEntry.TABLE_NAME;
    private static final String SQL_DELETE_SUPPORTER_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamsPersistenceContract.SupporterEntry.TABLE_NAME;
    private static final String SQL_DELETE_TEAM_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamsPersistenceContract.TeamEntry.TABLE_NAME;

    // endregion

    // region CONSTRUCTORS

    public TeamsDbHelper(Context context, final String databaseName) {

        super(context, databaseName, null, DATABASE_VERSION);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_PLAYER_ENTRIES);
        db.execSQL(SQL_CREATE_SUPPORTER_ENTRIES);
        db.execSQL(SQL_CREATE_TEAM_ENTRIES);
        db.execSQL(SQL_CREATE_TEAM_PLAYER_SUPPORT_ENTRIES);
        db.execSQL(SQL_CREATE_TEAM_SUPPORTER_SUPPORT_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_TEAM_SUPPORTER_SUPPORT_ENTRIES);
        db.execSQL(SQL_DELETE_TEAM_PLAYER_SUPPORT_ENTRIES);
        db.execSQL(SQL_DELETE_TEAM_ENTRIES);
        db.execSQL(SQL_DELETE_SUPPORTER_ENTRIES);
        db.execSQL(SQL_DELETE_PLAYER_ENTRIES);

        onCreate(db);
    }

    // endregion
}
