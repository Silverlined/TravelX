
package rhinos.com.travelx_;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

public class SpaceStations extends AppCompatActivity {

    private ListView listView;
    private String[] destinations;
    private String[] departures;
    private String[] distance;
    private String departureStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_stations);
        setFullScreen();

        Resources res = getResources();
        listView = findViewById(R.id.listView);
        destinations = res.getStringArray(R.array.destinations);
        departures = res.getStringArray(R.array.departures);
        distance = res.getStringArray(R.array.distance);
        departureStr = res.getString(R.string.departure);

        ItemAdapter itemAdapter = new ItemAdapter(this, destinations, departures, distance, departureStr);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> goToMapsActivity(position));
    }

    private void goToMapsActivity(int position) {
        Intent startMaps = new Intent(getApplicationContext(), MapsActivity.class);
        startMaps.putExtra("rhinos.com.travelx_.ITEM_INDEX", position);
        startActivity(startMaps);
    }

    private void setFullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}
