package com.orogersilva.footballteamspayroll.data;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class Supporter {

    // region FIELDS

    private long mId;
    private String mName;
    private String mRegistrationId;
    private boolean mOverdue;

    // endregion

    // region CONSTRUCTORS

    public Supporter(long id, String name, String registrationId, boolean isOverdue) {

        mId = id;
        mName = name;
        mRegistrationId = registrationId;
        mOverdue = isOverdue;
    }

    // endregion

    // region GETTERS AND SETTERS

    public long getId() {

        return mId;
    }

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
