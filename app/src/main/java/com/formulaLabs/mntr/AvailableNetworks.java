package com.formulaLabs.mntr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
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

import com.formulaLabs.mntr.databinding.CurrentNetworkBinding;

import java.util.Objects;

public class AvailableNetworks extends Fragment {

    private CurrentNetworkBinding binding;
    private Handler handler;
    private Runnable runnable;
    private static final int REFRESH_INTERVAL = 5000;

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

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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