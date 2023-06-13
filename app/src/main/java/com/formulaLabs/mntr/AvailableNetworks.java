package com.formulaLabs.mntr;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.formulaLabs.mntr.databinding.AvailableNetworksBinding;

import java.util.List;

public class AvailableNetworks extends Fragment {

    private AvailableNetworksBinding binding;
    private Handler handler;
    private Runnable runnable;
    private static final int REFRESH_INTERVAL = 5000;
    private NetworkScanner networkScanner;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = AvailableNetworksBinding.inflate(inflater, container, false);
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

                TextView textView = requireView().findViewById(R.id.textview_available_networks);
                StringBuilder content = new StringBuilder("Available Networks:\n\n");
                for (String availableNetwork : availableNetworks) {
                    content = new StringBuilder(content + availableNetwork + "\n");
                }
                SpannableString spannableString = new SpannableString(content);
                spannableString.setSpan(new RelativeSizeSpan(0.8f), 20, content.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                textView.setText(spannableString);
                textView.setTextSize(30);
                textView.setTypeface(null, Typeface.BOLD);


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