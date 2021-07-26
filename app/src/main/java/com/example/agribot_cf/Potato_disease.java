package com.example.agribot_cf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Potato_disease extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageView image;
    Button choose, upload;
    int PICK_IMAGE_REQUEST = 111;
    String URL ="https://610a27c1ef1a.ngrok.io/predict/image";
    Bitmap bitmap;
    ProgressDialog progressDialog;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;

//    public static BluetoothSocket mmSocket;
//    public static ConnectedThread connectedThread;
//    public static CreateConnectThread createConnectThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potato_disease);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        textView=findViewById(R.id.crop_title);
        toolbar=findViewById(R.id.toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        image = (ImageView)findViewById(R.id.image);
        choose = (Button)findViewById(R.id.choose);
        upload = (Button)findViewById(R.id.upload);

        //opening image chooser option
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(Potato_disease.this);
                progressDialog.setMessage("Uploading, please wait...");
                progressDialog.show();



                //converting image to base64 string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                final String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

                //sending image to server
                VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, URL, new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse s) {
                        progressDialog.dismiss();
//                        String jsonString = new String(s.data, HttpHeaderParser.parseCharset(s.headers));
//                        String imageStringres = Base64.encodeToString(s.data, Base64.DEFAULT);
                        String json = null;
                        try {
                            json = new String(s.data, HttpHeaderParser.parseCharset(s.headers));
                            Toast.makeText(Potato_disease.this,json, Toast.LENGTH_LONG).show();

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

//                        if(s.equals("true")){
//                            Toast.makeText(MainActivity.this, "Uploaded Successful", Toast.LENGTH_LONG).show();
//                        }
//                        else{
//                            Toast.makeText(MainActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
//                        }
                    }
                },new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(Potato_disease.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
                    }
                }) {
                    @Override
                    protected Map<String, DataPart> getByteData() {
                        Map<String, DataPart> params = new HashMap<>();
                        params.put("file", new DataPart("pdfname.jpg" ,imageBytes));
                        return params;
                    }
                    //adding parameters to send

                };

                VolleySingleton.getInstance(getBaseContext()).addToRequestQueue(multipartRequest);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_home:
                Intent intent4= new Intent(Potato_disease.this, Crops.class );
                startActivity(intent4);
                break;

            case R.id.nav_controller:
                Intent intent= new Intent(Potato_disease.this, BotController.class );
                startActivity(intent);
                break;

            case R.id.nav_abtus:
                Intent intent1= new Intent(Potato_disease.this, AbtUs.class );
                startActivity(intent1);
                break;

            case R.id.nav_faq:
                Intent intent2= new Intent(Potato_disease.this, faq.class );
                startActivity(intent2);
                break;

            case R.id.nav_profile:
                Intent intent3= new Intent(Potato_disease.this, Profile.class );
                startActivity(intent3);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();

            try {
                //getting image from gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                //Setting image to ImageView
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}