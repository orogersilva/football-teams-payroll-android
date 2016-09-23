package com.orogersilva.footballteamspayroll.teams.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orogersilva.footballteamspayroll.ItemView;
import com.orogersilva.footballteamspayroll.R;
import com.orogersilva.footballteamspayroll.data.Team;
import com.orogersilva.footballteamspayroll.teams.view.TeamsFragment;
import com.orogersilva.footballteamspayroll.utils.NumberUtils;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.orogersilva.footballteamspayroll.utils.NumberUtils.isEven;

/**
 * Created by orogersilva on 9/23/2016.
 */

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ItemViewHolder> {

    // region FIELDS

    private List<Team> mTeams;
    private TeamsFragment.TeamItemListener mTeamItemListener;

    private int mSelectedSortingOptionPosition;

    // endregion

    // region CONSTRUCTORS

    public TeamsAdapter(List<Team> teams, TeamsFragment.TeamItemListener teamItemListener, int selectedSortingOptionPosition) {

        mTeams = teams;
        mTeamItemListener = teamItemListener;
        setSelectedSortingOptionPosition(selectedSortingOptionPosition);
    }

    // endregion

    // region PUBLIC METHODS

    public void setSelectedSortingOptionPosition(int selectedSortingOptionPosition) {

        mSelectedSortingOptionPosition = selectedSortingOptionPosition;
    }

    // endregion

    // region OVERRIDED METHODS

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemview_team, parent, false);

        v.setTag(new TeamsItemPresenter());

        ItemViewHolder teamsItemViewHolder = new ItemViewHolder(v);

        return teamsItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        ((TeamsItemPresenter) holder.itemView.getTag()).presentListItem(holder, mTeams.get(position), mSelectedSortingOptionPosition);
    }

    @Override
    public int getItemCount() {

        return mTeams.size();
    }

    // endregion

    // region UTILITY CLASSES

    class ItemViewHolder extends RecyclerView.ViewHolder implements ItemView<Team> {

        // region FIELDS

        @BindView(R.id.nameTextView)
        TextView mNameTextView;
        @BindView(R.id.infoTextView)
        TextView mInfoTextView;

        // endregion

        // region CONSTRUCTORS

        public ItemViewHolder(View itemView) {

            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        // endregion

        // region EVENT HANDLERS

        @OnClick(R.id.itemview_team)
        public void onTeamClick() {

            mTeamItemListener.onTeamClick(mTeams.get(getAdapterPosition()));
        }

        // endregion

        // region OVERRIDED METHODS

        @Override
        public void setItem(Team team, int selectedSortingOptionPosition) {

            if (isEven(getAdapterPosition())) {
                itemView.setBackgroundResource(R.color.indigo);
            } else {
                itemView.setBackgroundResource(R.color.light_indigo);
            }

            mNameTextView.setText(team.getName());

            if (selectedSortingOptionPosition == 0) {

                Locale locale = new Locale("pt", "BR");
                NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

                mInfoTextView.setText(String.valueOf(currencyFormatter.format(team.getTotalPayroll())));

            } else {
                mInfoTextView.setText(String.valueOf(team.getNumbersOfSupporters()));
            }
        }

        // endregion
    }

    // endregion
}
