package com.orogersilva.footballteamspayroll.data;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class Player {

    // region FIELDS

    private long mId;
    private String mName;
    private int mAge;
    private float mSalary;

    // endregion

    // region CONSTRUCTORS

    public Player() {}

    public Player(long id, String name, int age, float salary) {

        mId = id;
        mName = name;
        mAge = age;
        mSalary = salary;
    }

    // endregion

    // region GETTERS AND SETTERS

    public long getId() {

        return mId;
    }

    public String getName() {

        return mName;
    }

    public int getAge() {

        return mAge;
    }

    public float getSalary() {

        return mSalary;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        return mName.equals(player.getName()) &&
                mAge == player.getAge() &&
                Math.abs(mSalary - player.getSalary()) < 0.01;
    }

    // endregion
}
