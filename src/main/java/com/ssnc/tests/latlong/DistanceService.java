package com.ssnc.tests.latlong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.Math;

public class DistanceService {
	private Map<String, City> cityLocations = new HashMap<>();
	private static final double RADIUS_OF_THE_EARTH = 6371.0;
	private static final double LOCAL_PI = 3.1415926535897932385; 

	public DistanceService(Set<City> cities) {
		cities.stream()
				.forEach(city -> cityLocations.put(city.getName(), city));
	}

	@SuppressWarnings("unchecked")
	public Pair<City> findClosestCities(Set<String> cityNames)
			throws LatLongException {
		// TODO: add code here - you may add other classes/methods as you wish
		
		List<CitiesShortDistance> shortDistance = new ArrayList<CitiesShortDistance>();
		for (Map.Entry<String, City> cityOne : cityLocations.entrySet()) {
			String ctyOne = cityOne.getKey();
			City cOne = cityOne.getValue();
			double lat1 = cityOne.getValue().getLatitude();
			double lng1 = cityOne.getValue().getLongitude();
			for (Map.Entry<String, City> cityTwo : cityLocations.entrySet()) {
				String ctyTwo = cityTwo.getKey();
				City cTwo = cityTwo.getValue();
				//It is an error if, for example, a provided city name does not exist in the lat long list.
				if (!cityNames.contains(ctyTwo)) throw new LatLongException();
				
				double lat2 = cityTwo.getValue().getLatitude();
				double lng2 = cityTwo.getValue().getLongitude();
				if (!ctyOne.equals(ctyTwo)) {
					double dist = distFrom(lat1,lng1,lat2,lng2);
					CitiesShortDistance cShortDistance = new CitiesShortDistance(cOne,cTwo,dist);
					shortDistance.add(cShortDistance);
				}
			}
		}
		
		Collections.sort(shortDistance);
		Pair<City> pCity = new Pair<City>(shortDistance.get(0).getFromCity(), shortDistance.get(0).getToCity());

		return pCity;
	}

	public static double ToRadians(double degrees) {
		double radians = degrees * LOCAL_PI / 180;
		return radians;
	}

	public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
	    double earthRadius = RADIUS_OF_THE_EARTH * 1000; //meters
	    //double dLat = Math.toRadians(lat2-lat1);
	    double dLng = Math.toRadians(lng2-lng1);
	    double a = Math.sqrt(Math.pow(Math.cos(ToRadians(lat2)) * Math.sin(dLng),2) +
	               Math.pow((Math.cos(ToRadians(lat1)) * Math.sin(ToRadians(lat2)) -
	                (Math.sin(ToRadians(lat1)) * Math.cos(ToRadians(lat2)) * Math.cos(dLng))),2));
	    double b = Math.sin(ToRadians(lat1)) * Math.sin(ToRadians(lat2)) +
	               Math.cos(ToRadians(lat1)) * Math.cos(ToRadians(lat2)) * Math.cos(dLng);
	    double c = 2 * Math.atan(a/b);
	    double dist = (float) (earthRadius * c);

	    return dist;
	    }
}
