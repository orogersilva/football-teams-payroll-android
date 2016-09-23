package com.orogersilva.footballteamspayroll.teams.view.adapter;

import com.orogersilva.footballteamspayroll.data.Team;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class TeamsItemPresenter {

    // region PUBLIC METHODS

    public void presentListItem(TeamsAdapter.ItemViewHolder itemViewHolder, Team team, int selectedSortingOptionPosition) {

        itemViewHolder.setItem(team, selectedSortingOptionPosition);
    }

    // endregion
}
