package com.orogersilva.footballteamspayroll.data.source.local;

import android.provider.BaseColumns;

/**
 * Created by orogersilva on 9/22/2016.
 */

public class TeamsPersistenceContract {

    // region CONSTRUCTORS

    private TeamsPersistenceContract() {}

    // endregion

    // region INNER CLASSES

    public static abstract class TeamEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "team";

        public static final String COLUMN_NAME_ID = TABLE_NAME + "_id";
        public static final String COLUMN_NAME_NAME = TABLE_NAME + "_name";

        // endregion
    }

    public static abstract class PlayerEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "player";

        public static final String COLUMN_NAME_ID = TABLE_NAME + "_id";
        public static final String COLUMN_NAME_NAME = TABLE_NAME + "_name";
        public static final String COLUMN_NAME_AGE = TABLE_NAME + "_age";
        public static final String COLUMN_NAME_SALARY = TABLE_NAME + "_salary";

        // endregion
    }

    public static abstract class SupporterEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "supporter";

        public static final String COLUMN_NAME_ID = TABLE_NAME + "_id";
        public static final String COLUMN_NAME_NAME = TABLE_NAME + "_name";
        public static final String COLUMN_NAME_REGISTRATION_ID = TABLE_NAME + "_registration_id";
        public static final String COLUMN_NAME_OVERDUE = TABLE_NAME + "_overdue";

        // endregion
    }

    public static abstract class TeamPlayerSupportEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "team_player_support";

        public static final String COLUMN_NAME_TEAM_ID = TABLE_NAME + "_team_id";
        public static final String COLUMN_NAME_PLAYER_ID = TABLE_NAME + "_player_id";

        // endregion
    }

    public static abstract class TeamSupporterSupportEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "team_supporter_support";

        public static final String COLUMN_NAME_TEAM_ID = TABLE_NAME + "_team_id";
        public static final String COLUMN_NAME_SUPPORTER_ID = TABLE_NAME + "_supporter_id";

        // endregion
    }

    // endregion
}
