package com.orogersilva.footballteamspayroll.teams;

import com.orogersilva.footballteamspayroll.BasePresenter;
import com.orogersilva.footballteamspayroll.BaseView;
import com.orogersilva.footballteamspayroll.data.Team;

import java.util.List;

/**
 * Created by orogersilva on 9/23/2016.
 */

public interface TeamsContract {

    // region INTERFACES

    interface View extends BaseView<Presenter> {

        // region METHODS

        void showLoadingIndicator(boolean isActive);
        void showTeams(List<Team> teams);

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS

        // endregion
    }

    // endregion
}
