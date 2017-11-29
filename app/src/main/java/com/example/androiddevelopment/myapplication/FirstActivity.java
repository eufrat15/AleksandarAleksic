package com.example.androiddevelopment.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androiddevelopment.myapplication.db.DatabaseHelper;
import com.example.androiddevelopment.myapplication.db.model.TuristickaAtrakcija;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final int SELECT_PICTURE = 1;
    public static String NOTIF_TOAST = "pref_toast";
    public static String NOTIF_STATUS = "pref_notification";
    private DatabaseHelper databaseHelper;
    private AlertDialog dialogAlert;
    private SharedPreferences preferences;

    private ImageView preview;
    private String imagePath = null;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_first);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.BLACK);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer);
            actionBar.show();
        }


        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        List<TuristickaAtrakcija> ta = new ArrayList<TuristickaAtrakcija>();
        try {
            ta = getDatabaseHelper().getmTuristickaAtrakcijaDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> taName = new ArrayList<String>();
        for (TuristickaAtrakcija i : ta) {
            taName.add(i.getmNaziv());
        }

        final ListView listView = (ListView) findViewById(R.id.list_first_activity);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(FirstActivity.this, R.layout.list_item, taName);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TuristickaAtrakcija turistickaAtrakcija = (TuristickaAtrakcija) listView.getItemAtPosition(position);
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("position", turistickaAtrakcija.getmId());
                startActivity(intent);

            }

        });

    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_first, menu);
            return super.onCreateOptionsMenu(menu);

        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {

                case R.id.action_add:

                    final Dialog dialog = new Dialog(FirstActivity.this);
                    dialog.setContentView(R.layout.dialog_turistickaatrakcija);

                    Button choosebtn = (Button) dialog.findViewById(R.id.btn_choose);
                    choosebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preview = (ImageView) dialog.findViewById(R.id.preview_image);
                            selectPicture();
                        }

                    });

                    final EditText taNaziv = (EditText) dialog.findViewById(R.id.input_turistickaatrakcija_naziv);
                    final EditText taOpis = (EditText) dialog.findViewById(R.id.input_turistickaatrakcija_opis);
                    final EditText taPostanskaAdresa = (EditText) dialog.findViewById(R.id.input_turistickaagencija_posta);
                    final EditText taTelefon = (EditText) dialog.findViewById(R.id.input_turistickaagencija_telefon);
                    final EditText taWeb = (EditText) dialog.findViewById(R.id.input_turistickaatrakcija_web);
                    final EditText taCena = (EditText) dialog.findViewById(R.id.input_turistickaatrakcija_cena);
                    final EditText taKomentar = (EditText) dialog.findViewById(R.id.input_turistickaatrakcija_komentar);

                    Button save = (Button) dialog.findViewById(R.id.btn_save);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String naziv = taNaziv.getText().toString();
                            if (naziv.isEmpty()) {
                                Toast.makeText(FirstActivity.this, "Must be entered", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String opis = taOpis.getText().toString();
                            if (opis.isEmpty()) {
                                Toast.makeText(FirstActivity.this, "Must be entered", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            int postanskaadresa = 0;
                            try {
                                postanskaadresa = Integer.parseInt(taTelefon.getText().toString());
                            } catch (NumberFormatException e) {
                                Toast.makeText(FirstActivity.this, "Must be number.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            int telefon = 0;
                            try {
                                telefon = Integer.parseInt(taTelefon.getText().toString());
                            } catch (NumberFormatException e) {
                                Toast.makeText(FirstActivity.this, "Must be number.", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String web = taWeb.getText().toString();
                            if (web.isEmpty()) {
                                Toast.makeText(FirstActivity.this, "Must be entered", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            double cena = 0;
                            try {
                                cena = Double.parseDouble(taCena.getText().toString());
                            } catch (NumberFormatException e) {
                                Toast.makeText(FirstActivity.this, "Must be number.", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            String komentar = taKomentar.getText().toString();
                            if (komentar.isEmpty()) {
                                Toast.makeText(FirstActivity.this, "Must be entered", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (preview == null || imagePath == null) {
                            Toast.makeText(FirstActivity.this, "Picture must be choose", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        TuristickaAtrakcija turistickaAtrakcija = new TuristickaAtrakcija();
                        turistickaAtrakcija.setmNaziv(naziv);
                        turistickaAtrakcija.setmOpis(opis);
                        turistickaAtrakcija.setmFotografija(imagePath);
                        turistickaAtrakcija.setmPosta(postanskaadresa);
                        turistickaAtrakcija.setmTelefon(telefon);
                        turistickaAtrakcija.setmWeb(web);
                        //turistickaAtrakcija.setmRadnovreme();


                    }







    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    private void selectPicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_list) {

        } else if (id == R.id.nav_settings) {

            Intent intent = new Intent(FirstActivity.this, SettingsActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

