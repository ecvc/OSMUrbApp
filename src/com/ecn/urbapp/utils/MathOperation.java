package com.ecn.urbapp.utils;

/**
 * Many static methods useful 
 * @author Sebastien
 *
 */
public class MathOperation {

	public static Position barycenter(ArrayList<Position> GPSPoints) {
		int numberPoint = GPSPoints.size();
		Double x = Double.valueOf(0);
		Double y = Double.valueOf(0);
		for (Position GPSinCase:GPSPoints){
			x += GPSinCase.getLatitude();
			y += GPSinCase.getLongitude();
		}
		
		Position GPSCentered = new Position(x/numberPoint,y/numberPoint);
		
		return GPSCentered;
	}
	
}