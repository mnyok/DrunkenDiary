package ms.drunkdiary;

import android.provider.BaseColumns;

/**
 * Created by orc12 on 2015-12-05.
 */
public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String ALCOHOL_TYPE = "alcohol_type";
        public static final String BOTTLE = "bottle";
        public static final String GLASS = "glass";
        public static final String DATE = "date";
        public static final String STATUS = "status";
        public static final String NOTE = "note";
        public static final String _TABLENAME = "item";
        public static final String _CREATE =
                "create table "+_TABLENAME+"("
                        +_ID+" integer primary key autoincrement, "
                        +ALCOHOL_TYPE+" text not null , "
                        +BOTTLE+" text not null , "
                        +GLASS+" text not null"
                        +DATE+" date not null"
                        +STATUS+" text not null"
                        +NOTE+" text);";
    }
}
