package rhinos.com.travelx_;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PlanetInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_info);
        setFullScreen();

        Button learnMoreBtn = findViewById(R.id.learnMoreBtn);
        Button travelBtn = findViewById(R.id.travelBtn);

        learnMoreBtn.setOnClickListener(v -> goToLearnMoreActivity());
        travelBtn.setOnClickListener(v -> goToSpaceStationsActivity());
    }

    private void goToSpaceStationsActivity() {
        Intent startStations = new Intent(getApplicationContext(), SpaceStations.class);
        startActivity(startStations);
    }

    private void goToLearnMoreActivity() {
        Intent startLearn = new Intent(getApplicationContext(), LearnMoreActivity.class);
        startActivity(startLearn);
    }

    private void setFullScreen() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
        );
    }
}
