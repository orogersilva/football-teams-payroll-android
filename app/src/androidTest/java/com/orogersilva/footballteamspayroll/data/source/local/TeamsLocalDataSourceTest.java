package com.orogersilva.footballteamspayroll.data.source.local;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Created by orogersilva on 9/22/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TeamsLocalDataSourceTest {

    // region FIELDS

    private static Context sContext;

    private final String DB_NAME = "TeamsTest.sqlite";
    private TeamsDbHelper mDbHelper;

    private static TeamsLocalDataSource sTeamsLocalDataSource;

    // endregion

    // region SETUP METHODS

    @BeforeClass
    public static void setupClass() {

        sContext = InstrumentationRegistry.getTargetContext();

        sTeamsLocalDataSource = TeamsLocalDataSource.getInstance(sContext);
    }

    @Before
    public void setup() {

        mDbHelper = new TeamsDbHelper(sContext, DB_NAME);
    }

    // endregion

    // region TEST METHODS



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
