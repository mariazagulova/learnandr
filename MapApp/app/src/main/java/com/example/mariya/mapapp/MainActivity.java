package com.example.mariya.mapapp;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    final String LOG_TAG = "myLogs";
    int[] colors = new int[2];

    GoogleMap mGoogleMap;

    DBHelper dbHelper;
    private SQLiteDatabase database;
    LayoutInflater ltInflater;
    LinearLayout linLayout;

    //НЕ ЗАПОЛНИЛА БД хотя б изначальными штуками!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Google Play services work fine.", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_main);

        } else {
            //No Google Maps layout

        }

        Button btn = (Button) findViewById(R.id.btnWeb);
        btn.setOnClickListener(this);

        Button btnAddTagGroup = (Button) findViewById(R.id.addTagGroup);
        btnAddTagGroup.setOnClickListener(this);

        linLayout = (LinearLayout) findViewById(R.id.linLayout);
        ltInflater = getLayoutInflater();

        dbHelper = new DBHelper(this);
        database = dbHelper.getReadableDatabase();
        fillList(database);







         /* ЛИСТ
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(LOG_TAG, "itemClick: position = " + position + ", id = "
                        + id);
            }
        });

        lvMain.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(LOG_TAG, "scrollState = " + scrollState);
            }

            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                //    Log.d(LOG_TAG, "scroll: firstVisibleItem = " + firstVisibleItem
                //            + ", visibleItemCount" + visibleItemCount
                //            + ", totalItemCount" + totalItemCount);
            }
        });


        lvMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = "
                        + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG, "itemSelect: nothing");
            }
        });
    }

*/

    }


    /*
    private void initDB(){
        database = this.openOrCreateDatabase( DB_NAME , MODE_PRIVATE , null );
        database.execSQL( "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(FirstNumber INT, SecondNumber INT, Result INT);" );
        database.delete( TABLE_NAME, null , null );
    }
*/

    public void initMap() {
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnWeb:
                intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;

            case R.id.addTagGroup:
                intent = new Intent(this, TagGroup.class);
                startActivity(intent);
                break;
        }
    }

    public void fillList(SQLiteDatabase database) {

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");
        int i = 0;
        ContentValues contentValues = new ContentValues();


        Cursor cursor = database.query(DBHelper.TABLE_GROUPS, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.KEY_TAGNAME);
            //int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
            do {
                Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                        ", name = " + cursor.getString(nameIndex));
                // + ", email = " + cursor.getString(emailIndex));

                Log.d("myLogs", "i = " + i);
                View item = ltInflater.inflate(R.layout.item, linLayout, false);
                TextView tvName = (TextView) item.findViewById(R.id.tag);
                tvName.setText(cursor.getString(nameIndex));

                item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
                item.setBackgroundColor(colors[i % 2]);
                linLayout.addView(item);
                i++;

            } while (cursor.moveToNext());
        } else
            Log.d("mLog", "0 rows");

        cursor.close();
    }




    /* база данных
     String name = etName.getText().toString();
        String email = etEmail.getText().toString();


        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        switch (view.getId()) {
            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();

                break;
            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }

        dbHelper.close();
     */


    /* джойны таблиц
    Log.d(LOG_TAG, "---INNER JOIN with rawQuery---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary "
                + "from people as PL "
                + "inner join position as PS "
                + "on PL.posid = PS.id "
                + "where salary > ?";
        cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[] {"40000"});
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");


        Log.d(LOG_TAG, "---INNER JOIN with query---");
        String table = "people as PL inner join position as PS on PL.posid = PS.id";
        String[] columns = {"PL.name as Name", "PS.name as Position", "salary as Salary"};
        String selection = "salary < ?";
        String[] selectionArgs = {"40000"};
        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(LOG_TAG, "--- ---");
     */

    /* транзакции
      private void insertRecords(){
        database.beginTransaction();
        try {
            for ( int i = 0; i < 1000 ; i++){
                ContentValues cv = new ContentValues();
                cv.put( "FirstNumber", i);
                cv.put( "SecondNumber", i);
                cv.put( "Result", i*i);
                database.insert( TABLE_NAME , null , cv);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private void insertRecordsEvenFaster(){
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES(?,?,?);";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        try {
            for ( int i = 0; i < 1000 ; i++){

                statement.clearBindings();
                statement.bindLong( 1, i);
                statement.bindLong( 2, i);
                statement.bindLong( 3, i*i);
                statement.execute();
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }
     */

    /* тост
    Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
     */

    public boolean googleServicesAvailable() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (api.isUserResolvableError(isAvailable)) {
            Dialog dialog = api.getErrorDialog(this, isAvailable, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to Google Play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        goToLocation(55.787795, 49.147323);
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLng(ll);
        mGoogleMap.moveCamera(update);
    }

    private void goToLocation(double lat, double lng, float zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
    }
}

