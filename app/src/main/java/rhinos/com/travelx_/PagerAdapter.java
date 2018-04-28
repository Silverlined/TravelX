package rhinos.com.travelx_;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<DestinationModel> destinations;

    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.destinations = getDestinationsFromDb();
    }

    private List<DestinationModel> getDestinationsFromDb() {
        List<DestinationModel> destinationModels = new ArrayList<>();
        destinationModels.add(new DestinationModel("moon", R.drawable.moon));
        destinationModels.add(new DestinationModel("mars", R.drawable.mars));
        destinationModels.add(new DestinationModel("jupiter", R.drawable.jupiter));
        return destinationModels;
    }

    @Override
    public Fragment getItem(int position) {
        return DestinationFragment.newInstance(destinations.get(position));
    }

    @Override
    public int getCount() {
        return destinations.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return destinations.get(position).getName();
    }

}

