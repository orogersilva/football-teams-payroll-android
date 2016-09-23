package com.orogersilva.footballteamspayroll.payroll;

import com.orogersilva.footballteamspayroll.BasePresenter;
import com.orogersilva.footballteamspayroll.BaseView;
import com.orogersilva.footballteamspayroll.data.Team;

/**
 * Created by orogersilva on 9/23/2016.
 */

public interface PayrollContract {

    // region INTERFACES

    interface View extends BaseView<Presenter> {

        // region METHODS

        void showLoadingIndicator(boolean isActive);
        void showPayroll(Team team);

        // endregion
    }

    interface Presenter extends BasePresenter {

        // region METHODS



        // endregion
    }

    // endregion
}
