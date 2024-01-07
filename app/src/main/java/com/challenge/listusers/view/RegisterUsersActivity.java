package com.challenge.listusers.view;

import static com.challenge.listusers.Utils.showPersonTypeDialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.challenge.listusers.R;
import com.challenge.listusers.Utils;
import com.challenge.listusers.databinding.ActivityRegisterUsersBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterUsersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterUsersBinding binding = ActivityRegisterUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        showPersonTypeDialog(this,
                () -> Utils.updateHintBasedOnPersonType(findViewById(R.id.cpf_cnpj_txt_input_layout), "CPF"),
                () -> Utils.updateHintBasedOnPersonType(findViewById(R.id.cpf_cnpj_txt_input_layout), "CNPJ")
        );

        Glide.with(this)
                .load(R.drawable.camera)
                .centerCrop()
                .placeholder(R.drawable.camera)
                .transform(new CircleCrop())
                .into(binding.registerImgView);

    }

}