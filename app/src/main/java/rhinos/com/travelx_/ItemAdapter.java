package rhinos.com.travelx_;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private String[] destinations;
    private String[] departures;
    private String[] distances;
    private String departureStr;

    public ItemAdapter(Context c, String[] destinations, String[] departures, String[] distance, String departureStr) {
        this.destinations = destinations;
        this.departures = departures;
        this.distances = distance;
        this.departureStr = departureStr;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return destinations.length;
    }

    @Override
    public Object getItem(int position) {
        return destinations[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mInflater.inflate(R.layout.listview_layout, null);
        TextView destinationView = view.findViewById(R.id.destinationView);
        TextView daysView = view.findViewById(R.id.daysView);
        TextView distanceView = view.findViewById(R.id.distanceVIew);
        TextView departureView = view.findViewById(R.id.departure);

        String destination = destinations[position];
        String departure = departures[position];
        String distance = distances[position];

        destinationView.setText(destination);
        daysView.setText(departure);
        distanceView.setText(distance);
        departureView.setText(departureStr);

        return view;
    }
}
