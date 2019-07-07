package staticClasses;


public class ConfigDB {

    public static final String DB_NAME = "trending_git_repo_db";
    public final static int DB_VERSION = 1;
    public static final String TYPE_SET_DATA = "Type Set Data";
    public static final String TYPE_GET_DATA = "Type Get Data";
    public static final String TYPE_DELETE_DATA = "Type Delete Data";
    public static final String TYPE_UPDATE_DATA = "Type Update Data";
    public static final String TYPE_JOB_TERMINATED = "Type Job Terminated";
    public static final String TYPE_JOB_HOLD = "Type Job Hold";
    public static final String TYPE_GET_ALL_DATA = "Type Get All Data";
    public static final String TYPE_EMPTY_ALL_DATA = "Type Empty All Data";

    public static final String TYPE_UPDATE_DATA_NEW = "Type Update Data New";

    public static final String TYPE_SET_DATA_NEW_JOB = "Type Set Data New Job";

    public static final String TYPE_EMPTY_DATA = "TypeEmpty Data";
    public static final String jobData = "trending_git_repo_table";

    //Music Player Data
    public static final String KEY_ID = "KEY_ID";

    public final static String CREATING_DB_TRENDING_TABLE = "create table " + jobData + "( " + KEY_ID+ " INTEGER AUTO_INCREMENT PRIMARY KEY," +
            DataBaseKeys.author + " TEXT, " + DataBaseKeys.name + " TEXT, "
            + DataBaseKeys.url + " TEXT, " + DataBaseKeys.description + " TEXT, "+ DataBaseKeys.language + " TEXT, "
    + DataBaseKeys.languageColor +" TEXT, " + DataBaseKeys.stars + "TEXT," + DataBaseKeys.forks + "TEXT, " + DataBaseKeys.currentPeriodStars + "TEXT, "
    + DataBaseKeys.builtBy + "TEXT )";
}