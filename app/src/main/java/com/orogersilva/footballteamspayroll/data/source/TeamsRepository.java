package com.orogersilva.footballteamspayroll.data.source;

import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class TeamsRepository implements TeamsDataSource {

    // region FIELDS

    private static TeamsRepository INSTANCE = null;

    Map<Long, Team> mCachedTeams = null;

    private final TeamsDataSource mTeamsLocalDataSource;

    // endregion

    // region CONSTRUCTORS

    private TeamsRepository(@NonNull TeamsDataSource teamsLocalDataSource) {

        mTeamsLocalDataSource = teamsLocalDataSource;
    }

    // endregion

    // region STATIC METHODS

    public static TeamsRepository getInstance(@NonNull TeamsDataSource teamsLocalDataSource) {

        if (INSTANCE == null) {
            INSTANCE = new TeamsRepository(teamsLocalDataSource);
        }

        return INSTANCE;
    }

    public static void destroyInstance() {

        INSTANCE = null;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void getTeam(long id, LoadTeamsCallback callback) {

        if (mCachedTeams != null) {

            callback.onTeamsLoaded(Arrays.asList(mCachedTeams.get(id)));
            return;
        }
    }

    @Override
    public void getTeams(final LoadTeamsCallback callback) {

        if (mCachedTeams != null) {

            callback.onTeamsLoaded(new ArrayList<Team>(mCachedTeams.values()));
            return;
        }

        mTeamsLocalDataSource.getTeams(new LoadTeamsCallback() {

            @Override
            public void onTeamsLoaded(List<Team> teams) {

                refreshCache(teams);
                callback.onTeamsLoaded(new ArrayList<Team>(mCachedTeams.values()));
            }

            @Override
            public void onDataNotAvailable() {

                callback.onDataNotAvailable();
            }
        });
    }

    // endregion

    // region UTILITY METHODS

    private void refreshCache(List<Team> teams) {

        if (mCachedTeams == null) {
            mCachedTeams = new LinkedHashMap<>();
        }

        mCachedTeams.clear();

        for (Team team : teams) {
            mCachedTeams.put(team.getId(), team);
        }
    }

    // endregion
}
