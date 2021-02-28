package com.ssnc.tests.latlong;

@SuppressWarnings("rawtypes")
public class CitiesShortDistance implements Comparable {
	private City fromCity;
	private City toCity;
	private double distance;
	
	public CitiesShortDistance(City fromCity, City toCity, double distance) {
		super();
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.distance = distance;
	}
	
	public City getFromCity() {
		return fromCity;
	}
	public void setFromCity(City fromCity) {
		this.fromCity = fromCity;
	}
	public City getToCity() {
		return toCity;
	}
	public void setToCity(City toCity) {
		this.toCity = toCity;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		double dist =((CitiesShortDistance) o).getDistance();
		return (int) (this.distance-dist);
	}

}
