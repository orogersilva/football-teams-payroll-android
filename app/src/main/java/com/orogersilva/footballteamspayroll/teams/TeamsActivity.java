package com.orogersilva.footballteamspayroll.teams;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orogersilva.footballteamspayroll.R;
import com.orogersilva.footballteamspayroll.data.source.TeamsRepository;
import com.orogersilva.footballteamspayroll.data.source.local.TeamsLocalDataSource;
import com.orogersilva.footballteamspayroll.teams.view.TeamsFragment;
import com.orogersilva.footballteamspayroll.utils.ActivityUtils;

public class TeamsActivity extends AppCompatActivity {

    // region FIELDS

    private TeamsFragment mTeamsFragment;
    private TeamsPresenter mTeamsPresenter;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        if (mTeamsFragment == null) {

            mTeamsFragment = TeamsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mTeamsFragment, R.id.contentFrame);
        }

        mTeamsPresenter = new TeamsPresenter(
                TeamsRepository.getInstance(TeamsLocalDataSource.getInstance(this)), mTeamsFragment
        );
    }

    // endregion
}
