package com.orogersilva.footballteamspayroll.data;

import java.util.List;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class Team {

    // region FIELDS

    private long mId;
    private String mName;
    private List<Player> mPlayers;
    private List<Supporter> mSupporters;

    // endregion

    // region CONSTRUCTORS

    public Team(long id, String name, List<Player> players, List<Supporter> supporters) {

        mId = id;
        mName = name;
        mPlayers = players;
        mSupporters = supporters;
    }

    // endregion

    // region GETTERS AND SETTERS

    public long getId() {

        return mId;
    }

    public String getName() {

        return mName;
    }

    public List<Player> getPlayers() {

        return mPlayers;
    }

    public List<Supporter> getSupporters() {

        return mSupporters;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return mName.equals(team.getName()) &&
                mPlayers.equals(team.getPlayers()) &&
                mSupporters.equals(team.getSupporters());
    }

    // endregion
}
