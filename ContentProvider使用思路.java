/*
*应用B中数据库设计：确保数据库能够存储多个用户的设置。
*/
public class ControlDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "controls.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "control_states";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_VISIBLE = "visible";

    public ControlDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_USER_ID + " TEXT PRIMARY KEY," +
            COLUMN_VISIBLE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

/*
* 应用B中的ControlProvider  
*/
public class ControlProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.appb.controlprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/control");

    private ControlDbHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new ControlDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String userId = selectionArgs[0];
        return db.query(ControlDbHelper.TABLE_NAME, new String[]{ControlDbHelper.COLUMN_VISIBLE},
                ControlDbHelper.COLUMN_USER_ID + " = ?", new String[]{userId},
                null, null, null);
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String userId = selectionArgs[0];
        return db.update(ControlDbHelper.TABLE_NAME, values,
                ControlDbHelper.COLUMN_USER_ID + " = ?", new String[]{userId});
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }
}


