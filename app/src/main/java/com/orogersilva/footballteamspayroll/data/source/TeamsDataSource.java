package com.orogersilva.footballteamspayroll.data.source;

import com.orogersilva.footballteamspayroll.data.Team;

import java.util.List;

/**
 * Created by orogersilva on 9/22/2016.
 */

public interface TeamsDataSource {

    // region CALLBACKS

    interface LoadTeamsCallback {

        void onTeamsLoaded(List<Team> teams);
        void onDataNotAvailable();
    }

    // endregion

    // region CRUD

    void getTeam(long id, LoadTeamsCallback callback);
    void getTeams(LoadTeamsCallback callback);

    // endregion
}
