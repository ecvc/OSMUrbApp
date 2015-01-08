package com.ecn.urbapp.utils;

import org.osmdroid.util.Position;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
* Defines a mix of differents objects, needed for the Async method
* Contains
*                         a marker
*                         the position
*                         the address (facultative first)
* @author Sebastien
*
*/
public class MarkerPos {

	//TODO Adddescription for javadoc
        private Marker marker;
    	//TODO Adddescription for javadoc
        private Position position;
    	//TODO Adddescription for javadoc
        private String adresse="Adresse inconnue";

    	//TODO Adddescription for javadoc
        public MarkerPos(Marker marker, Position position) {
                super();
                this.marker = marker;
                this.position = position;
        }

    	//TODO Adddescription for javadoc
        public MarkerPos(MarkerPos markpos) {
                super();
                this.marker = markpos.getMarker();
                this.position = markpos.getPosition();
                this.adresse = markpos.getAdresse();
        }

    	//TODO Adddescription for javadoc
        public Marker getMarker() {
                return marker;
        }
    	//TODO Adddescription for javadoc
        public void setMarker(Marker marker) {
                this.marker = marker;
        }
    	//TODO Adddescription for javadoc
        public Position getPosition() {
                return position;
        }
    	//TODO Adddescription for javadoc
        public void setPosition(Position position) {
                this.position = position;
        }

    	//TODO Adddescription for javadoc
        public String getAdresse() {
                return adresse;
        }

    	//TODO Adddescription for javadoc
        public void setAdresse(String adresse) {
                this.adresse = adresse;
        }
        
        

}