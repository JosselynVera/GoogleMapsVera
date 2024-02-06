package com.example.googlemapsvera;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MainActivity  extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    GoogleMap mapa;
    int cont=0;
    ArrayList<LatLng> puntos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        puntos= new ArrayList<>();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapa=googleMap;
        mapa.setOnMapClickListener(this);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng( -1.0129094429538934, -79.46937361919005), 16);
        mapa.moveCamera(camUpd1);

        /*artaycont*/

        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-1.0119654527974633, -79.47154084400208))
                .add(new LatLng(-1.0132312577206541, -79.47181979372762))
                .add(new LatLng(-1.0135101638237045, -79.46731904719427))
                .add(new LatLng(-1.0123730849453203, -79.46723321650948))
                .add(new LatLng(-1.0119654527974633, -79.47154084400208));
        lineas.width(8);
        lineas.color(Color.RED);
        mapa.addPolyline(lineas);
/*calculate distance y add text*/
    }

    public void ConfigMap(View view)
    {
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
    }

    public void MovMap(View view)
    {
        /*CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(new LatLng(-1.0121731241567231, -79.46951129752085), 17);
        mapa.moveCamera(camUpd1);*/
        LatLng madrid = new LatLng(40.68931450164296, -74.04458432423975);
        CameraPosition camPos = new CameraPosition.Builder()
                .target(madrid)
                .zoom(17)
                .bearing(85) //noreste arriba
                .tilt(70) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        /*Toast.makeText(getApplicationContext(),
                "Lat: " + latLng.latitude
                    + ", Long " + latLng, Toast.LENGTH_LONG).show();*/

        mapa.addMarker(new
                MarkerOptions().position(latLng)
                .title("Punto"));

        puntos.add(latLng);
        cont++;
        if(cont==4)
        {
            PolylineOptions lineas = new
                    PolylineOptions()
                    .add(puntos.get(0))
                    .add(puntos.get(1))
                    .add(puntos.get(2))
                    .add(puntos.get(3))
                    .add(puntos.get(0));
            lineas.width(8);
            lineas.color(Color.RED);
            mapa.addPolyline(lineas);
            cont=0;
            puntos.clear();
        }
    }
}