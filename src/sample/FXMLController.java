package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import java.net.URL;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.javascript.object.Polyline;
import com.lynden.gmapsfx.javascript.object.PolylineOptions;
import com.lynden.gmapsfx.shapes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import netscape.javascript.JSObject;


public class FXMLController implements Initializable, MapComponentInitializedListener {

    public Label bTest;
    @FXML
    private Button button;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }

    @Override
    public void mapInitialized() {
        /*LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);
*/

        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(19.371761, -99.263299))
                .mapType(MapTypeIdEnum.TERRAIN)
                .mapTypeControl(false)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(20);

        map = mapView.createMap(mapOptions);

        LatLong markerLatLong = new LatLong(47.606189, -122.335842);
        LatLong markerLatLong2 = new LatLong(47.906189, -122.335842);

        LatLong[] ary = new LatLong[]{markerLatLong, markerLatLong2};
        MVCArray mvc = new MVCArray(ary);

        com.lynden.gmapsfx.shapes.PolylineOptions polyOpts = new com.lynden.gmapsfx.shapes.PolylineOptions()
                .path(mvc)
                .strokeColor("red")
                .strokeWeight(2);

        com.lynden.gmapsfx.shapes.Polyline poly = new com.lynden.gmapsfx.shapes.Polyline(polyOpts);
        map.addMapShape(poly);

        LatLong poly1 = new LatLong(19.371761, -99.263299);
        LatLong poly2 = new LatLong(19.371761, -99.3);
        LatLong poly3 = new LatLong(19.4, -99.3);
        LatLong[] pAry = new LatLong[]{poly1, poly2, poly3};
        MVCArray pmvc = new MVCArray(pAry);

        PolygonOptions polygOpts = new PolygonOptions()
                .paths(pmvc)
                .strokeColor("blue")
                .strokeWeight(2)
                .editable(true)
                .fillColor("lightBlue")
                .fillOpacity(0.5);

        Polygon pg = new Polygon(polygOpts);
        map.addMapShape(pg);
        map.addUIEventHandler(pg, UIEventType.click, (JSObject obj) -> {
            //polygOpts.editable(true);
            pg.setEditable(!pg.getEditable());
        });


        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
        });

        /*//Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(joeSmithLocation);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);

        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);

        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);

        /*map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);*/
    }

    public void Satelite(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.SATELLITE);
    }

    public void Hybrid(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.HYBRID);
    }

    public void Road(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.ROADMAP);
    }

    public void Terrain(ActionEvent actionEvent) {
        map.setMapType(MapTypeIdEnum.TERRAIN);
    }
}