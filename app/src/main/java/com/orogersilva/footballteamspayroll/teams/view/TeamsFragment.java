package com.orogersilva.footballteamspayroll.teams.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.teams.TeamsContract;
import com.orogersilva.footballteamspayroll.teams.TeamsContract.Presenter;

import java.util.List;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class TeamsFragment extends Fragment implements TeamsContract.View {

    // region FIELDS

    private Presenter mTeamsPresenter;

    // endregion

    // region CONSTRUCTORS

    public TeamsFragment() {}

    // endregion

    // region STATIC METHODS

    public static TeamsFragment newInstance() {

        return new TeamsFragment();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return null;
    }

    @Override
    public void onResume() {

        super.onResume();

        mTeamsPresenter.start();
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void showTeams(List<Team> teams) {

    }

    @Override
    public void setPresenter(Presenter presenter) {

        mTeamsPresenter = presenter;
    }

    // endregion
}
