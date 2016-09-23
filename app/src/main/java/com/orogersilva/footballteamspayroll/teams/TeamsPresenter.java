package com.orogersilva.footballteamspayroll.teams;

import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.data.source.TeamsDataSource;
import com.orogersilva.footballteamspayroll.data.source.TeamsRepository;

import java.util.List;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class TeamsPresenter implements TeamsContract.Presenter {

    // region FIELDS

    private final TeamsContract.View mTeamsView;
    private final TeamsRepository mTeamsRepository;

    // endregion

    // region CONSTRUCTORS

    public TeamsPresenter(@NonNull TeamsRepository teamsRepository, @NonNull TeamsContract.View teamsView) {

        mTeamsRepository = teamsRepository;
        mTeamsView = teamsView;

        mTeamsView.setPresenter(this);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void start() {

        loadTeams(true);
    }

    // endregion

    // region UTILITY METHODS

    private void loadTeams(final boolean showLoadingUI) {

        if (showLoadingUI) {
            mTeamsView.showLoadingIndicator(true);
        }

        mTeamsRepository.getTeams(new TeamsDataSource.LoadTeamsCallback() {

            @Override
            public void onTeamsLoaded(List<Team> teams) {

                if (showLoadingUI) {
                    mTeamsView.showLoadingIndicator(false);
                }

                processTeams(teams);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    private void processTeams(List<Team> teams) {

        mTeamsView.showTeams(teams);
    }

    // endregion
}
