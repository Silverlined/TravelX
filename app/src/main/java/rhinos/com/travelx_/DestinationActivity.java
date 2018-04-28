package rhinos.com.travelx_;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import rhinos.com.travelx_.databinding.ActivityDestinationBinding;

public class DestinationActivity extends AppCompatActivity {
    private ActivityDestinationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_destination);
        setupViewPager();
    }

    private void setupViewPager() {
        PagerAdapter adapter = new PagerAdapter(this, getSupportFragmentManager());
        binding.viewPager.setAdapter(adapter);
    }
}
