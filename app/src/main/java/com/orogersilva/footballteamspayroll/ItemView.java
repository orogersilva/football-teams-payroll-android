package com.orogersilva.footballteamspayroll;

/**
 * Created by orogersilva on 9/23/2016.
 */

public interface ItemView<T> {

    // region METHODS

    void setItem(T item, int selectedSortingOptionPosition);

    // endregion
}
