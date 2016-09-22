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
    }

    // endregion

    // region CRUD

    void getTeams(LoadTeamsCallback callback);

    // endregion
}
