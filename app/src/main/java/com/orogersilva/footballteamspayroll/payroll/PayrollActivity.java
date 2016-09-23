package com.orogersilva.footballteamspayroll.payroll;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orogersilva.footballteamspayroll.R;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class PayrollActivity extends AppCompatActivity {

    // region FIELDS



    // endregion

    // region ACTIVITY LIFECYCLE METHODS

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payroll);
    }

    // endregion
}
