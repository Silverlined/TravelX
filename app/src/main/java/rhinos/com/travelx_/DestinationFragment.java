package rhinos.com.travelx_;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rhinos.com.travelx_.databinding.FragmentDestinationBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class DestinationFragment extends Fragment {

    private FragmentDestinationBinding binding;
    public static final String KEY_DESTINATION = "key_destination";
    private DestinationModel destinationModel;

    public DestinationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            destinationModel = (DestinationModel) getArguments().getSerializable(KEY_DESTINATION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_destination, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.planet.setImageResource(destinationModel.getImage());
        binding.planet.setOnClickListener(v -> goToPlanetInfo());
    }

    private void goToPlanetInfo() {
        Intent startInfo = new Intent(getActivity(), PlanetInfo.class);
        startActivity(startInfo);
    }

    public static DestinationFragment newInstance(DestinationModel destinationModel) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_DESTINATION, destinationModel);
        fragment.setArguments(args);
        return fragment;
    }
}
