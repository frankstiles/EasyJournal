package com.example.frankstiles.easyjournal.data;

import android.provider.BaseColumns;

/**
 * Created by FrankStiles on 28-Jun-18.
 */

public final class JournalContract {

    public class JournalEntry implements BaseColumns{

        public static final String TABLE_USER_NAME = "users";
        public static final String _ID = BaseColumns._ID;
        public static final String G_ID = "g_id";
        public static final String COLUMN_NAME = "first_name";

        public static final String TABLE_CONTACT_NAME = "contacts";
        public static final String COLUMN_EMAIL = "email";

        public static final String TABLE_JOURNALS_NAME = "users_journals";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_DATE = "date";
    }
}
