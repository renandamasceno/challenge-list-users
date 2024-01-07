package com.challenge.listusers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.textfield.TextInputLayout;

public class Utils {

    private Utils() {
    }

    public static void showPersonTypeDialog(Context context, Runnable onFisicaSelected, Runnable onJuridicaSelected) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_person_type, null);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();

        RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroup);
        Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);

        btnConfirm.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId == R.id.radioFisica && onFisicaSelected != null) {
                onFisicaSelected.run();
            } else if (selectedId == R.id.radioJuridica && onJuridicaSelected != null) {
                onJuridicaSelected.run();
            }

            dialog.dismiss();
        });

        dialog.show();
    }

    public static void updateHintBasedOnPersonType(TextInputLayout editText, String newHint) {
        editText.setHint(newHint);
    }
}
