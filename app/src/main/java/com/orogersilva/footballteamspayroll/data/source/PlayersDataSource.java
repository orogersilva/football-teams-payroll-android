package com.orogersilva.footballteamspayroll.data.source;

import com.orogersilva.footballteamspayroll.data.Player;

import java.util.List;

/**
 * Created by orogersilva on 9/22/2016.
 */

public interface PlayersDataSource {

    // region CALLBACKS

    interface LoadPlayersCallback {

        void onPlayersLoaded(List<Player> players);
        void onDataNotAvailable();
    }

    // endregion

    // region CRUD

    void getPlayers(LoadPlayersCallback callback);

    // endregion
}
