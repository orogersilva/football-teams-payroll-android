package com.orogersilva.footballteamspayroll.teams.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.orogersilva.footballteamspayroll.R;
import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.teams.TeamsContract;
import com.orogersilva.footballteamspayroll.teams.TeamsContract.Presenter;
import com.orogersilva.footballteamspayroll.teams.view.adapter.TeamsAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class TeamsFragment extends Fragment implements TeamsContract.View {

    // region FIELDS

    private Presenter mTeamsPresenter;

    private Spinner mSortingOptionsSpinner;

    private RecyclerView mTeamsRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TeamsAdapter mAdapter;

    private AVLoadingIndicatorView mLoadingView;

    private List<Team> mTeams = new ArrayList<>();

    private TeamItemListener mTeamItemListener = new TeamItemListener() {

        @Override
        public void onTeamClick(Team team) {

            // TODO: 9/23/2016 TO IMPLEMENT.
        }
    };

    // endregion

    // region CONSTRUCTORS

    public TeamsFragment() {}

    // endregion

    // region INTERFACES

    public interface TeamItemListener {

        // region METHODS

        void onTeamClick(Team team);

        // endregion
    }

    // endregion

    // region STATIC METHODS

    public static TeamsFragment newInstance() {

        return new TeamsFragment();
    }

    // endregion

    // region FRAGMENT LIFECYCLE METHODS

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_teams, container, false);

        mSortingOptionsSpinner = (Spinner) view.findViewById(R.id.sortingOptionsSpinner);

        ArrayAdapter<CharSequence> mSortingOptionsAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sorting_options_array, R.layout.spinner_sorting_option_item);

        mSortingOptionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSortingOptionsSpinner.setAdapter(mSortingOptionsAdapter);
        mSortingOptionsSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    Collections.sort(mTeams);
                } else {
                    Collections.sort(mTeams, Team.NumberOfSupportersComparator);
                }

                mAdapter.setSelectedSortingOptionPosition(position);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mTeamsRecyclerView = (RecyclerView) view.findViewById(R.id.teamsRecyclerView);
        mLoadingView = (AVLoadingIndicatorView) view.findViewById(R.id.loadingView);

        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new TeamsAdapter(mTeams, mTeamItemListener, mSortingOptionsSpinner.getSelectedItemPosition());
        mTeamsRecyclerView.setLayoutManager(mLayoutManager);
        mTeamsRecyclerView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onResume() {

        super.onResume();

        mTeamsPresenter.start();
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void showLoadingIndicator(boolean isActive) {

        if (isActive) {
            mLoadingView.show();
        } else {
            mLoadingView.hide();
        }
    }

    @Override
    public void showTeams(List<Team> teams) {

        mTeams.clear();
        mTeams.addAll(teams);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(Presenter presenter) {

        mTeamsPresenter = presenter;
    }

    // endregion
}
