package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orogersilva.footballteamspayroll.data.source.local.PersistenceContract.PlayerEntry;
import com.orogersilva.footballteamspayroll.data.source.local.PersistenceContract.SupporterEntry;
import com.orogersilva.footballteamspayroll.data.source.local.PersistenceContract.TeamEntry;
import com.orogersilva.footballteamspayroll.data.source.local.PersistenceContract.TeamPlayerSupportEntry;
import com.orogersilva.footballteamspayroll.data.source.local.PersistenceContract.TeamSupporterSupportEntry;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class DbHelper extends SQLiteOpenHelper {

    // region FIELDS

    public static final int DATABASE_VERSION = 1;

    private static final String BOOLEAN_TYPE = " INTEGER";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String TEXT_TYPE = " TEXT";

    private static final String COMMA_SEPARATOR = ",";

    private boolean isTesting = false;

    // endregion

    // region SCRIPTS

    private static final String SQL_CREATE_PLAYER_ENTRIES =
            "CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +
                    PlayerEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    PlayerEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                    PlayerEntry.COLUMN_NAME_AGE + INTEGER_TYPE + COMMA_SEPARATOR +
                    PlayerEntry.COLUMN_NAME_SALARY + REAL_TYPE +
                    " )";

    private static final String SQL_CREATE_SUPPORTER_ENTRIES =
            "CREATE TABLE " + SupporterEntry.TABLE_NAME + " (" +
                    SupporterEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    SupporterEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEPARATOR +
                    SupporterEntry.COLUMN_NAME_REGISTRATION_ID + TEXT_TYPE + COMMA_SEPARATOR +
                    SupporterEntry.COLUMN_NAME_OVERDUE + BOOLEAN_TYPE +
                    " )";

    private static final String SQL_CREATE_TEAM_ENTRIES =
            "CREATE TABLE " + TeamEntry.TABLE_NAME + " (" +
                    TeamEntry.COLUMN_NAME_ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEPARATOR +
                    TeamEntry.COLUMN_NAME_NAME + TEXT_TYPE +
                    ")";

    private static final String SQL_CREATE_TEAM_PLAYER_SUPPORT_ENTRIES =
            "CREATE TABLE " + TeamPlayerSupportEntry.TABLE_NAME + " (" +
                    TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    " PRIMARY KEY (" + TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID + COMMA_SEPARATOR + TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamPlayerSupportEntry.COLUMN_NAME_TEAM_ID +
                        ") REFERENCES " + TeamEntry.TABLE_NAME + "(" + TeamEntry.COLUMN_NAME_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamPlayerSupportEntry.COLUMN_NAME_PLAYER_ID +
                        ") REFERENCES " + PlayerEntry.TABLE_NAME + "(" + PlayerEntry.COLUMN_NAME_ID + ")" +
                    " )";

    private static final String SQL_CREATE_TEAM_SUPPORTER_SUPPORT_ENTRIES =
            "CREATE TABLE " + TeamSupporterSupportEntry.TABLE_NAME + " (" +
                    TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID + INTEGER_TYPE + COMMA_SEPARATOR +
                    " PRIMARY KEY (" + TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID + COMMA_SEPARATOR + TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamSupporterSupportEntry.COLUMN_NAME_TEAM_ID +
                        ") REFERENCES " + TeamEntry.TABLE_NAME + "(" + TeamEntry.COLUMN_NAME_ID + ")" + COMMA_SEPARATOR +
                    " FOREIGN KEY (" + TeamSupporterSupportEntry.COLUMN_NAME_SUPPORTER_ID +
                        ") REFERENCES " + SupporterEntry.TABLE_NAME + "(" + SupporterEntry.COLUMN_NAME_ID + ")" +
                    " )";

    private static final String SQL_INSERT_TEAMS_ENTRIES =
            "INSERT INTO " + TeamEntry.TABLE_NAME + " VALUES (1, 'Internacional'); " +
                    "INSERT INTO " + TeamEntry.TABLE_NAME + " VALUES (2, 'Grêmio'); " +
                    "INSERT INTO " + TeamEntry.TABLE_NAME + " VALUES (3, 'Palmeiras');";

    private static final String SQL_INSERT_PLAYERS_ENTRIES =
            "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (1, 'Danilo Fernandes', 30, 200000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (2, 'Paulão', 32, 100000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (3, 'Ernando', 33, 230000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (4, 'Geferson', 22, 80000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (5, 'William', 21, 90000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (6, 'Fernando Bob', 31, 95000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (7, 'Rodrigo Dourado', 22, 93000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (8, 'Valdívia', 23, 140000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (9, 'Seijas', 31, 160000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (10, 'Aylon', 22, 75000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (11, 'Sasha', 26, 120000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (12, 'Mercelo Grohe', 28, 150000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (13, 'Geromel', 29, 210000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (14, 'Kannemann', 32, 130000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (15, 'Marcelo Oliveira', 33, 80000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (16, 'Edilson, 34, 105000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (17, 'Walace', 21, 65000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (18, 'Maicon', 30, 130000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (19, 'Ramiro', 27, 90000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (20, 'Doublas', 33, 190000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (21, 'Pedro Rocha', 25, 74000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (22, 'Luan', 22, 210000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (23, 'Jailson', 24, 65000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (24, 'Jean', 30, 90000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (25, 'Mina', 25, 70000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (26, 'Edu Dracena', 35, 105000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (27, 'Egídio', 32, 85000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (28, 'Gabriel', 24, 81000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (29, 'Tchê Tchê', 21, 65000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (30, 'Moisés', 28, 87000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (31, 'Dudu', 29, 165000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (32, 'Leandro Pereira', 27, 93000.0); " +
                    "INSERT INTO " + PlayerEntry.TABLE_NAME + " VALUES (33, 'Erik', 25, 82000.0);";

    private static final String SQL_INSERT_SUPPORTERS_ENTRIES =
            "INSERT INTO " + SupporterEntry.TABLE_NAME + " VALUES (1, 'Kelly Matos', '123456', 1); " +
                    "INSERT INTO " + SupporterEntry.TABLE_NAME + "VALUES (2, 'Renata Fan', '789012', 0); " +
                    "INSERT INTO " + SupporterEntry.TABLE_NAME + "VALUES (3, 'Gisele Bundchen', '345678', 0); " +
                    "INSERT INTO " + SupporterEntry.TABLE_NAME + "VALUES (4, 'Adriane Galisteu', '456789', 1); " +
                    "INSERT INTO " + SupporterEntry.TABLE_NAME + "VALUES (5, 'Ratinho', '234567', 0); " +
                    "INSERT INTO " + SupporterEntry.TABLE_NAME + "VALUES (6, 'José Serra', '567890', 1);";

    private static final String SQL_INSERT_TEAM_PLAYER_SUPPORT_ENTRIES =
            "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 1); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 2); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 3); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 4); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 5); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 6); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 7); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 8); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 9); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 10); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (1, 11); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 12); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 13); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 14); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 15); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 16); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 17); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 18); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 19); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 20); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 21); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (2, 22); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 23); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 24); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 25); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 26); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 27); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 28); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 29); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 30); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 31); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 32); " +
                    "INSERT INTO " + TeamSupporterSupportEntry.TABLE_NAME + " VALUES (3, 33);";

    private static final String SQL_INSERT_TEAM_SUPPORTER_SUPPORT_ENTRIES =
            "INSERT INTO " + TeamPlayerSupportEntry.TABLE_NAME + " VALUES (1, 1); " +
                    "INSERT INTO " + TeamPlayerSupportEntry.TABLE_NAME + " VALUES (1, 2); " +
                    "INSERT INTO " + TeamPlayerSupportEntry.TABLE_NAME + " VALUES (2, 3); " +
                    "INSERT INTO " + TeamPlayerSupportEntry.TABLE_NAME + " VALUES (3, 4); " +
                    "INSERT INTO " + TeamPlayerSupportEntry.TABLE_NAME + " VALUES (3, 5); " +
                    "INSERT INTO " + TeamPlayerSupportEntry.TABLE_NAME + " VALUES (3, 6);";

    private static final String SQL_DELETE_TEAM_PLAYER_SUPPORT_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamPlayerSupportEntry.TABLE_NAME;
    private static final String SQL_DELETE_TEAM_SUPPORTER_SUPPORT_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamSupporterSupportEntry.TABLE_NAME;
    private static final String SQL_DELETE_PLAYER_ENTRIES =
            "DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME;
    private static final String SQL_DELETE_SUPPORTER_ENTRIES =
            "DROP TABLE IF EXISTS " + SupporterEntry.TABLE_NAME;
    private static final String SQL_DELETE_TEAM_ENTRIES =
            "DROP TABLE IF EXISTS " + TeamEntry.TABLE_NAME;

    // endregion

    // region CONSTRUCTORS

    public DbHelper(Context context, final String databaseName, boolean isTesting) {

        super(context, databaseName, null, DATABASE_VERSION);

        this.isTesting = isTesting;
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

        if (!isTesting) {

            db.execSQL(SQL_INSERT_PLAYERS_ENTRIES);
            db.execSQL(SQL_INSERT_SUPPORTERS_ENTRIES);
            db.execSQL(SQL_INSERT_TEAMS_ENTRIES);
            db.execSQL(SQL_INSERT_TEAM_PLAYER_SUPPORT_ENTRIES);
            db.execSQL(SQL_INSERT_TEAM_SUPPORTER_SUPPORT_ENTRIES);
        }
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
