package com.formulaLabs.mntr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.formulaLabs.mntr.databinding.CurrentNetworkBinding;

import java.util.List;

public class AvailableNetworks extends Fragment {

    private CurrentNetworkBinding binding;
    private Handler handler;
    private Runnable runnable;
    private static final int REFRESH_INTERVAL = 5000;
    private NetworkScanner networkScanner;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = CurrentNetworkBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @SuppressLint("SetTextI18n")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        networkScanner = new NetworkScanner(requireContext());
        networkScanner.startNetworkScan();

        // Initialize Handler and Runnable
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                List<String> availableNetworks = networkScanner.getAvailableNetworks();

                // TODO: View implementation

                // Schedule the next execution of the runnable after the defined interval
                handler.postDelayed(this, REFRESH_INTERVAL);
            }
        };

        // Start the initial execution of the runnable
        handler.postDelayed(runnable, 0);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
                networkScanner.stopNetworkScan();
                NavHostFragment.findNavController(AvailableNetworks.this)
                        .navigate(R.id.action_AvailableNetworks_to_MainPage);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}