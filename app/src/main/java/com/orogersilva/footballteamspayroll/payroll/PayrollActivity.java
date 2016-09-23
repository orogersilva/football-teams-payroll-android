package com.orogersilva.footballteamspayroll.payroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orogersilva.footballteamspayroll.R;
import com.orogersilva.footballteamspayroll.payroll.view.PayrollFragment;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class PayrollActivity extends AppCompatActivity {

    // region FIELDS

    private PayrollFragment mPayrollFragment;
    private PayrollPresenter mPayrollPresenter;

    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);

        /*if (mTeamsFragment == null) {

            mTeamsFragment = TeamsFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mTeamsFragment, R.id.contentFrame);
        }

        mTeamsPresenter = new TeamsPresenter(
                TeamsRepository.getInstance(TeamsLocalDataSource.getInstance(this)), mTeamsFragment
        );*/

        if (mPayrollFragment == null) {


        }
    }

    // endregion
}
