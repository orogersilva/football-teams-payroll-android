package com.orogersilva.footballteamspayroll.payroll.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.payroll.PayrollContract;
import com.orogersilva.footballteamspayroll.payroll.PayrollContract.Presenter;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class PayrollFragment extends Fragment implements PayrollContract.View {

    // region FIELDS

    private Presenter mPayrollPresenter;

    private RecyclerView mPayrollRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    // TODO: 9/23/2016 TO IMPLEMENT ADAPTER.

    private AVLoadingIndicatorView mLoadingView;

    // endregion

    // region CONSTRUCTORS

    public PayrollFragment() {}

    // endregion

    // region STATIC METHODS

    public static PayrollFragment newInstance() {

        return new PayrollFragment();
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

        mPayrollPresenter.start();
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
    public void showPayroll(Team team) {

        // TODO: 9/23/2016 TO IMPLEMENT.
    }

    @Override
    public void setPresenter(Presenter presenter) {

        mPayrollPresenter = presenter;
    }

    // endregion
}
