package sg.grp4.DengueSG;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import sg.grp4.DengueSG.Interface.IOnloadLocationListener;

public class HotspotFragment extends Fragment implements OnMapReadyCallback, GeoQueryEventListener, IOnloadLocationListener {

    GoogleMap mGoogleMap;
    MapView mapview;
    View mView;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Marker currentUser;
    private DatabaseReference myLocationRef;
    private GeoFire geofire;
    private List<LatLng> dengueLocation;
    private IOnloadLocationListener listener;

    private DatabaseReference singapore;
    private Location lastLocation;
    private GeoQuery geoQuery;

    public HotspotFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_hotspot, container,false);
        mView = inflater.inflate(R.layout.fragment_hotspot, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sendNotification("DDENGUE", String.format("%s have entered a dengue hotspot", "You"));


        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        buildLocationRequest();
                        buildLocationCallback();
                        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity())); //might cause error

                        initArea();
                        setGeoFire();


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getActivity(),"You must enable permission", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

//        mapview = (MapView) mView.findViewById(R.id.map);
//        if(mapview != null){
//            mapview.onCreate(null);
//            mapview.onResume();
//            mapview.getMapAsync(this);
//        }
    }

    private void initArea() {

        singapore = FirebaseDatabase.getInstance()
                .getReference("DengueHotspot")
                .child("Singapore");

        listener = this;

        //Load from Firebase
//        singapore.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        List<MyLatLng> latLngList = new ArrayList<>();
//                        for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()){
//                            MyLatLng latLng = locationSnapshot.getValue(MyLatLng.class);
//                            latLngList.add(latLng);
//                        }
//                        listener.onLoadLocationSuccess(latLngList);
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        listener.onLoadLocationFailed(databaseError.getMessage());
//                    }
//                });
        singapore.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //update dangerousArea list
                List<MyLatLng> latLngList = new ArrayList<>();
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()){
                    MyLatLng latLng = locationSnapshot.getValue(MyLatLng.class);
                    latLngList.add(latLng);

                }

                listener.onLoadLocationSuccess(latLngList);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





//        dengueLocation = new ArrayList<>();
//        dengueLocation.add(new LatLng(1.332214, 103.774380));
//        dengueLocation.add(new LatLng(1.324112, 103.933267));
//        dengueLocation.add(new LatLng(1.290589, 103.845831));
//        dengueLocation.add(new LatLng(1.372385, 103.957634));
//        dengueLocation.add(new LatLng(1.356243, 103.960103));
//        dengueLocation.add(new LatLng(1.340747, 103.949378));
//        dengueLocation.add(new LatLng(1.333014, 103.948929));
//        dengueLocation.add(new LatLng(1.333582, 103.944407));


//        // ------ COMMENT AFTER SUBMITTING THIS TO FIREBASE --------
//        FirebaseDatabase.getInstance()
//                .getReference("DengueHotspot")
//                .child("Singapore")
//                .setValue(dengueLocation)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getActivity(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }

    private void addUserLocation() {
        geofire.setLocation("You", new GeoLocation(lastLocation.getLatitude(), lastLocation.getLongitude()), new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {

                BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.user_location_pic);
                Bitmap smallMarker = Bitmap.createScaledBitmap(bitmapdraw.getBitmap(), 150, 150, false);

                if (currentUser != null) currentUser.remove();
                currentUser = mGoogleMap.addMarker(new MarkerOptions().position(new LatLng(lastLocation.getLatitude(),
                        lastLocation.getLongitude())).title("You").icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));

                //After adding camera move camera
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentUser.getPosition(), 12.0f));
            }
        });
    }

    private void setGeoFire() {

        myLocationRef = FirebaseDatabase.getInstance().getReference("MyLocation");
        geofire = new GeoFire(myLocationRef);

    }

    private void buildLocationCallback() {
        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(final LocationResult locationResult) {
                if(mGoogleMap != null){

                    lastLocation = locationResult.getLastLocation();

                    addUserLocation();

                }
            }
        };
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mGoogleMap = googleMap;
        googleMap.setMapType(googleMap.MAP_TYPE_NORMAL);

//        googleMap.addMarker(new MarkerOptions().position(new LatLng(1.302870,103.831778)).title("Test").snippet("I love going here"));
//        CameraPosition test = CameraPosition.builder().target(new LatLng(1.302870,103.831778)).zoom(15).bearing(45).build();
//        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(test));

        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        if(fusedLocationProviderClient != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(getContext(),android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());

            //add a geo fence for dengue hotspots (circle)
            addDengueHotspot();
        }
    }

    private void addDengueHotspot() {
        if(geoQuery != null) {
            geoQuery.removeGeoQueryEventListener(this);
            geoQuery.removeAllListeners();
        }


        for (LatLng latLng : dengueLocation){
            mGoogleMap.addCircle(new CircleOptions().center(latLng)
                    .radius(300) //500 M
                    .strokeColor(Color.RED)
                    .fillColor(Color.argb(75,242,104,104))
                    .strokeWidth(5.0f)
            );

            // create geoQuery when user is in dangerous location
            GeoQuery geoQuery = geofire.queryAtLocation(new GeoLocation(latLng.latitude,latLng.longitude),5.0f); //500m
            geoQuery.addGeoQueryEventListener(HotspotFragment.this);
        }
    }

    @Override
    public void onStop() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
        super.onStop();
    }

    @Override
    public void onKeyEntered(String key, GeoLocation location) {
        sendNotification("DDENGUE", String.format("%s you have entered a dengue hotspot", key));
        Toast.makeText(getActivity(), "onKeyEntered", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onKeyExited(String key) {
        sendNotification("DDENGUE", String.format("%s you just left a dengue hotspot", key));
        Toast.makeText(getActivity(), "onKeyEntered", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onKeyMoved(String key, GeoLocation location) {
        sendNotification("DDENGUE", String.format("%s you are moving within a dengue hotspot", key));
        Toast.makeText(getActivity(), "onKeyEntered", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGeoQueryReady() {

    }

    @Override
    public void onGeoQueryError(DatabaseError error) {
        Toast.makeText(getActivity(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void sendNotification(String title, String content) {

        Toast.makeText(getActivity(), ""+content, Toast.LENGTH_SHORT).show();
        
        String NOTIFICATION_CHANNEL_ID = "ddengue_multiple_location";
        NotificationManager notificationManager = (NotificationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_DEFAULT);

            //config
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title)
                .setContentText(content)
                .setAutoCancel(false)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        Notification notification = builder.build();
        notificationManager.notify(new Random().nextInt(),notification);
    }

    @Override
    public void onLoadLocationSuccess(List<MyLatLng> latLngs) {
        dengueLocation = new ArrayList<>();
        for (MyLatLng myLatLng : latLngs){
            LatLng convert = new LatLng(myLatLng.getLatitude(),myLatLng.getLongitude());
            dengueLocation.add(convert);
        }

        mapview = (MapView) mView.findViewById(R.id.map);
        if(mapview != null){
            mapview.onCreate(null);
            mapview.onResume();
            mapview.getMapAsync(HotspotFragment.this);
        }

        //clear map and add again
        if (mGoogleMap != null){

            mGoogleMap.clear();

            //add user marker
            addUserLocation();

            //Add Dengue Hotspot Geofence
            addDengueHotspot();

        }

    }

    @Override
    public void onLoadLocationFailed(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }
}
