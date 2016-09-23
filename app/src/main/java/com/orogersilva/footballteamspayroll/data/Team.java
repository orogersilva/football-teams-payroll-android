package com.orogersilva.footballteamspayroll.data;

import android.util.Log;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class Team implements Comparable<Team> {

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

    public float getTotalPayroll() {

        float totalPayroll = 0.0f;

        for (Player player : mPlayers) {

            totalPayroll += player.getSalary();
        }

        return totalPayroll;
    }

    public int getNumbersOfSupporters() {

        return mSupporters.size();
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

    @Override
    public int compareTo(Team anotherTeam) {

        Float totalPayrollFloat = new Float(getTotalPayroll());
        Float anotherTeamPayrollFloat = new Float(anotherTeam.getTotalPayroll());

        return anotherTeamPayrollFloat.compareTo(totalPayrollFloat);
    }

    // endregion

    // region UTILITY CLASSES

    public static Comparator<Team> NumberOfSupportersComparator = new Comparator<Team>() {

        // region OVERRIDED METHODS

        @Override
        public int compare(Team team1, Team team2) {

            return team2.getNumbersOfSupporters() - team1.getNumbersOfSupporters();
        }

        // endregion
    };

    // endregion
}
