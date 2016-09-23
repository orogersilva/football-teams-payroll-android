package com.orogersilva.footballteamspayroll.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class ActivityUtils {

    // region STATIC METHODS

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

        fragmentManager.beginTransaction()
                .add(frameId, fragment)
                .commit();
    }

    // endregion
}
