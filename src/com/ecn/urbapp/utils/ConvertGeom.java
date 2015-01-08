package com.ecn.urbapp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osmdroid.util.Position;

import android.graphics.Point;

import com.ecn.urbapp.db.GpsGeom;
import com.ecn.urbapp.db.PixelGeom;
import com.ecn.urbapp.zones.UtilCharacteristicsZone;
import com.ecn.urbapp.zones.Zone;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class ConvertGeom{

	private static WKTReader wktr = new WKTReader();
	
	//TODO test every function
	public static Zone pixelGeomToZone(PixelGeom the_geom){
			Zone temp = new Zone();
			Coordinate[] coords;
			try {
				Geometry geom = wktr.read(the_geom.getPixelGeom_the_geom());
				for (PixelGeom pg : UtilCharacteristicsZone.getPixelGeomsFromGeom(geom, false)) {
					Polygon poly = (Polygon) wktr.read(pg.getPixelGeom_the_geom());
					coords = poly.getCoordinates();
					for (Coordinate coord : coords) {
						temp.addPoint(new Point((int) coord.x, (int) coord.y));
					}
				}
				temp.actualizePolygon();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return temp;
	}
	
	public static String ZoneToPixelGeom(Zone zone){
		zone.actualizePolygon();
		return zone.getPolygon().toText();
	}
	
	public static ArrayList<Position> gpsGeomToLatLng(GpsGeom the_geom){
		ArrayList<Position> list = new ArrayList<Position>();

		String s = the_geom.getGpsGeomCord().replace("LINESTRING(", "");
		s = s.replace(")", "");
		ArrayList<String> tab = new ArrayList<String>(Arrays.asList(s.split(",")));
		for(String str : tab){
			//TODO debug
			list.add(new Position(Double.parseDouble(str.split(" ")[0]), Double.parseDouble(str.split(" ")[1])));
		}
		return list;
	}
	
	public static String latLngToGpsGeom(ArrayList<Position> list){
		String ret="LINESTRING(";
		
		String s="";
		for(Position ll : list){
			s+=ll.getLatitude()+" "+ll.getLongitude();
			if(list.get(list.size()-1)!=ll){
				s+=",";
			}
		}
		ret+=s;
		
		ret+=")";
		return ret;
	}
}