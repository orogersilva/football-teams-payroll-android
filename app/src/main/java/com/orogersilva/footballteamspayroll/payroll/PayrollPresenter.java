package com.orogersilva.footballteamspayroll.payroll;

import android.support.annotation.NonNull;

import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.data.source.TeamsRepository;

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


    }

    // endregion

    // region UTILITY METHODS

    private void loadPayroll(final boolean showLoadingUI) {

        if (showLoadingUI) {
            mPayrollView.showLoadingIndicator(true);
        }
    }

    private void processPayroll(Team team) {

        mPayrollView.showPayroll(team);
    }

    // endregion
}
