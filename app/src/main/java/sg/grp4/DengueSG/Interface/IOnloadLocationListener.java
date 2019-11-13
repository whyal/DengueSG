package sg.grp4.DengueSG.Interface;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import sg.grp4.DengueSG.MyLatLng;

public interface IOnloadLocationListener {
    void onLoadLocationSuccess(List<MyLatLng> latLngs);
    void onLoadLocationFailed(String message);

}
