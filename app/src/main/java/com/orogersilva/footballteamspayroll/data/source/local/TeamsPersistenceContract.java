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

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_PLAYER_ID = "player_id";
        public static final String COLUMN_SUPPORTER_ID = "support_id";

        // endregion
    }

    public static abstract class SupporterEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "supporter";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_REGISTRATION_ID = "registration_id";
        public static final String COLUMN_NAME_OVERDUE = "overdue";

        // endregion
    }

    public static abstract class PlayerEntry implements BaseColumns {

        // region FIELDS

        public static final String TABLE_NAME = "player";

        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_AGE = "age";
        public static final String COLUMN_NAME_SALARY = "salary";

        // endregion
    }

    // endregion
}
