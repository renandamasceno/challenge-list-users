package com.challenge.listusers.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.room.TypeConverter;

import com.challenge.listusers.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Date;

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

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    public static TextWatcher insertCpfCnpjMask(final EditText editText) {
        return new TextWatcher() {
            boolean isUpdating;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (isUpdating) {
                    isUpdating = false;
                    return;
                }

                String cpfCnpj = unmask(charSequence.toString());
                if (cpfCnpj.length() == 11 || cpfCnpj.length() == 14) {
                    isUpdating = true;
                    editText.setText(maskCpfCnpj(cpfCnpj));
                    editText.setSelection(editText.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
    }

    private static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    private static String maskCpfCnpj(String cpfCnpj) {
        if (cpfCnpj.length() == 11) {
            return cpfCnpj.replaceFirst("(\\d{3})(\\d{3})(\\d{3})", "$1.$2.$3-");
        } else if (cpfCnpj.length() == 14) {
            return cpfCnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        } else {
            return cpfCnpj;
        }
    }
}
