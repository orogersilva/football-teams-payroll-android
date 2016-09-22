package com.orogersilva.footballteamspayroll.data;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class Supporter {

    // region FIELDS

    private String mName;
    private String mRegistrationId;
    private boolean mOverdue;

    // endregion

    // region GETTERS AND SETTERS

    public String getName() {

        return mName;
    }

    public String getRegistrationId() {

        return mRegistrationId;
    }

    public boolean isOverdue() {

        return mOverdue;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supporter supporter = (Supporter) o;

        return mName.equals(supporter.getName()) &&
                mRegistrationId.equals(supporter.getRegistrationId()) &&
                isOverdue() == supporter.isOverdue();
    }

    // endregion
}
