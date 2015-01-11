package ar.android.prueba.pasut.org.myapplication;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.beyondar.android.fragment.BeyondarFragment;
import com.beyondar.android.fragment.BeyondarFragmentSupport;
import com.beyondar.android.util.location.BeyondarLocationManager;
import com.beyondar.android.world.GeoObject;
import com.beyondar.android.world.World;

public class MainActivity extends Activity {
    private BeyondarFragment fragment;
    private GeoObject item;
    private World world;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BeyondarLocationManager.setLocationManager((LocationManager) this.getSystemService(Context.LOCATION_SERVICE));
        fragment = (BeyondarFragment) getFragmentManager().findFragmentById(R.id.beyondarFragment);
        world = new World(this);
        world.setGeoPosition(-34.62037907419714, -58.57991542082124, 43.58456417724236);
        item = new GeoObject(1l);
        item.setImageUri("http://pokeapi.co/media/img/1383571573.78.png");
        item.setGeoPosition(-34.62034837419714, -58.57997472082124, 43.58456417724236);
        world.addBeyondarObject(item);

        item = new GeoObject(2l);
        item.setImageUri("http://pokeapi.co/media/img/3.png");
        item.setGeoPosition(-34.62027987419714, -58.57891572082124, 43.58456417724236);
        world.addBeyondarObject(item);

        item = new GeoObject(3l);
        item.setImageUri("http://pokeapi.co/media/img/4.png");
        item.setGeoPosition(-34.62027987419714, -58.57981472082124, 43.58456417724236);
        world.addBeyondarObject(item);

        item = new GeoObject(4l);
        item.setImageUri("http://pokeapi.co/media/img/5.png");
        item.setGeoPosition(-34.62047977419714, -58.57991572082124, 43.58456417724236);
        world.addBeyondarObject(item);

        world.setDefaultImage(R.drawable.ic_launcher);
        fragment.setWorld(world);
        BeyondarLocationManager.addLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                world.setLocation(location);
                Log.d("MainActivity", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        // Enable GPS
        BeyondarLocationManager.enable();
    }

    @Override
    protected void onPause(){
        super.onPause();
        // Disable GPS
        BeyondarLocationManager.disable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BeyondarLocationManager.removeAllLocationListener();
    }
}
