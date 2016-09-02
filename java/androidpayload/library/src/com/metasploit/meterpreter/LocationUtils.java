package com.metasploit.meterpreter;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;

import java.util.LinkedList;
import java.util.List;

public class LocationUtils {

    public static Location getLastLocation(Context context) {
        LocationManager locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager == null) {
            return null;
        }
        List<Location> locations = new LinkedList<Location>();
        for (String providerName : locationManager.getAllProviders()) {
            Location loc = locationManager.getLastKnownLocation(providerName);
            locations.add(loc);
        }
        if (locations.size() == 0) {
            return null;
        }
        long bestTime = Long.MAX_VALUE;
        Location bestLocation = locations.get(0);
        for (int i = 1; i < locations.size(); i++) {
            if (bestLocation == null) {
                bestLocation = locations.get(i);
                if (bestLocation != null)
                    bestTime = Math.abs(bestLocation.getTime() - System.currentTimeMillis());
            } else if (locations.get(i) != null && bestTime > Math.abs(locations.get(i).getTime() - System.currentTimeMillis())) {
                bestLocation = locations.get(i);
                bestTime = Math.abs(bestLocation.getTime() - System.currentTimeMillis());
            }
        }
        return bestLocation;
    }

}
