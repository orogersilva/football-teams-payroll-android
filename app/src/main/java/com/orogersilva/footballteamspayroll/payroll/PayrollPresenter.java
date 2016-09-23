package com.orogersilva.footballteamspayroll.payroll;

import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.data.source.TeamsDataSource;
import com.orogersilva.footballteamspayroll.data.source.TeamsRepository;

import java.util.List;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class PayrollPresenter implements PayrollContract.Presenter {

    // region FIELDS

    private PayrollContract.View mPayrollView;
    private TeamsRepository mTeamsRepository;

    // endregion

    // region CONSTRUCTORS

    public PayrollPresenter(@NonNull TeamsRepository teamsRepository, @NonNull PayrollContract.View payrollView) {

        mTeamsRepository = teamsRepository;
        mPayrollView = payrollView;

        mPayrollView.setPresenter(this);
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public void start() {

        loadPayroll(true);
    }

    // endregion

    // region UTILITY METHODS

    private void loadPayroll(final boolean showLoadingUI) {

        if (showLoadingUI) {
            mPayrollView.showLoadingIndicator(true);
        }

        mTeamsRepository.getTeam(1, new TeamsDataSource.LoadTeamsCallback() {

            @Override
            public void onTeamsLoaded(List<Team> teams) {

                if (showLoadingUI) {
                    mPayrollView.showLoadingIndicator(false);
                }

                processPayroll(teams.get(0));
            }

            @Override
            public void onDataNotAvailable() {
            }
        });
    }

    private void processPayroll(Team team) {

        mPayrollView.showPayroll(team);
    }

    // endregion
}
