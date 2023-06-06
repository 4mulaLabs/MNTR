package com.formulaLabs.mntr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.formulaLabs.mntr.databinding.CurrentNetworkBinding;

import java.util.Objects;

public class CurrentNetwork extends Fragment {

    private CurrentNetworkBinding binding;

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

        Context context = getContext();
        Operator currentOperator = NetworkInfo.getCurrentOperator(context);

        TextView textView = requireView().findViewById(R.id.textview_second);
        if (Objects.equals(currentOperator.getOperatorCode(), "No_Operator_Code")) {
            view.setBackgroundColor(Color.RED);
            textView.setText("Disconnected");
            textView.setTextSize(40);
            textView.setTypeface(null, Typeface.BOLD);


        } else {
            view.setBackgroundColor(Color.GREEN);
            String content = "Connected!\n\n"
                    + "Operator: " + currentOperator.getBrandName()
                    + "\nCode: " + currentOperator.getOperatorCode()
                    + "\nQuality: " + currentOperator.getSignalStrengthLevel()
                    + "\nDbm: " + currentOperator.getSignalStrengthDbm();
            SpannableString spannableString = new SpannableString(content);
            spannableString.setSpan(new RelativeSizeSpan(0.6f), 12, content.length(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(spannableString);
            textView.setTextSize(40);
            textView.setTypeface(null, Typeface.BOLD);

        }


        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CurrentNetwork.this)
                        .navigate(R.id.action_CurrentNetwork_to_MainPage);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}