
package rhinos.com.travelx_;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class SpaceStations extends AppCompatActivity {

    private ListView listView;
    private String[] destinations;
    private String[] departures;
    private String[] distance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_space_stations);

        Resources res = getResources();
        listView = findViewById(R.id.listView);
        destinations = res.getStringArray(R.array.destinations);
        departures = res.getStringArray(R.array.departures);
        distance = res.getStringArray(R.array.distance);

        ItemAdapter itemAdapter = new ItemAdapter(this, destinations, departures, distance);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> goToMapsActivity(position));
    }

    private void goToMapsActivity(int position) {
        Intent startMaps = new Intent(getApplicationContext(), MapsActivity.class);
        startMaps.putExtra("rhinos.com.travelx_.ITEM_INDEX", position);
        startActivity(startMaps);
    }
}
