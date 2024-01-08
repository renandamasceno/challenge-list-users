package com.challenge.listusers.view;

import static com.challenge.listusers.util.Utils.fromTimestamp;
import static com.challenge.listusers.util.Utils.showPersonTypeDialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.challenge.listusers.R;
import com.challenge.listusers.databinding.ActivityRegisterUsersBinding;
import com.challenge.listusers.model.User;
import com.challenge.listusers.util.UserValidator;
import com.challenge.listusers.util.Utils;
import com.challenge.listusers.viewmodel.UserViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

import java.time.Instant;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterUsersActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private boolean isFieldsValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRegisterUsersBinding binding = ActivityRegisterUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        onBackPressedToolBar(binding);
        showPersonTypeDialog(
                this,
                () -> Utils.updateHintBasedOnPersonType(binding.cpfCnpjTxtInputLayout, "CPF"),
                () -> Utils.updateHintBasedOnPersonType(binding.cpfCnpjTxtInputLayout, "CNPJ")
        );
        registerNewUser(userViewModel, binding);
        initValidations(userViewModel, binding);
        maskCpfCnpj(binding);

        Glide.with(this)
                .load(R.drawable.camera)
                .centerCrop()
                .placeholder(R.drawable.camera)
                .transform(new CircleCrop())
                .into(binding.registerImgView);

    }

    private void maskCpfCnpj(ActivityRegisterUsersBinding binding) {
        EditText cpfCnpjEditText = binding.cpfCnpjTxtEditText;
        cpfCnpjEditText.addTextChangedListener(Utils.insertCpfCnpjMask(cpfCnpjEditText));
    }

    private void initValidations(UserViewModel userViewModel, ActivityRegisterUsersBinding binding) {
        isFieldsValid = false;
        validateFormatName(binding);
        validateFormatPassword(binding);
        validateFormatEmail(binding);
        validateUserName(userViewModel, binding);
    }

    private void validateUserName(UserViewModel userViewModel, ActivityRegisterUsersBinding binding) {
        TextInputLayout userNameTextInputLayout = binding.usernameTxtInputLayout;
        EditText userNameEditText = binding.usernameTxtEditText;

        userNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.removeCallbacksAndMessages(null);
                handler.postDelayed(() -> checkUsernameExists(editable.toString(), userNameTextInputLayout), 500);
            }
        });
    }

    private void checkUsernameExists(String username, TextInputLayout userNameTextInputLayout) {
        userViewModel.isUsernameAvailable(username).observeForever(isAvailable -> {
            if (!isAvailable) {
                userNameTextInputLayout.setError("Username já está em uso");
            } else {
                userNameTextInputLayout.setError(null);
                isFieldsValid = true;
            }
        });
    }

    private void validateFormatName(ActivityRegisterUsersBinding binding) {
        TextInputLayout nameTextInputLayout = binding.nameUserTxtInputLayout;
        EditText nameEditText = binding.nameUserTxtEditText;

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String name = editable.toString();
                boolean isValid = UserValidator.isValidName(name);

                if (isValid) {
                    nameTextInputLayout.setError(null);
                    isFieldsValid = true;
                } else {
                    nameTextInputLayout.setError("Nome deve ter mais de 30 caracteres");
                }
            }
        });

    }

    private void validateFormatEmail(ActivityRegisterUsersBinding binding) {
        TextInputLayout emailTextInputLayout = binding.emailTxtInputLayout;
        EditText emailEditText = binding.emailTxtEditText;

        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String email = editable.toString();

                if (UserValidator.isValidEmail(email)) {
                    emailTextInputLayout.setError(null);
                    isFieldsValid = true;
                } else {
                    emailTextInputLayout.setError("E-mail inválido. Verifique o formato do e-mail.");
                }
            }
        });
    }

    private void validateFormatPassword(ActivityRegisterUsersBinding binding) {
        TextInputLayout passwordTextInputLayout = binding.passwordTxtInputLayout;
        EditText passwordEditText = binding.passwordTxtEditText;

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();

                if (UserValidator.isValidPassword(password)) {
                    passwordTextInputLayout.setError(null);
                    isFieldsValid = true;
                } else {
                    passwordTextInputLayout.setError("Senha inválida. Deve conter pelo menos 8 caracteres, um número e uma letra maiúscula.");
                }
            }
        });
    }

    private void registerNewUser(UserViewModel userViewModel, ActivityRegisterUsersBinding binding) {

        binding.checkRegisterFab.setOnClickListener(view -> {
            Instant instant = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                instant = Instant.now();
            }
            long timestamp = 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                timestamp = instant.toEpochMilli();
            }
            if (isFieldsValid) {
                userViewModel.insert(
                        new User(
                                binding.nameUserTxtEditText.getText().toString(),
                                binding.usernameTxtEditText.getText().toString(),
                                binding.passwordTxtEditText.getText().toString(),
                                "esse é a imagem por enquanto",
                                binding.addressTxtEditText.getText().toString(),
                                binding.emailTxtEditText.getText().toString(),
                                fromTimestamp(timestamp),
                                Integer.parseInt(binding.genderTxtEditText.getText().toString()),
                                binding.cpfCnpjTxtEditText.getText().toString()
                        )
                );
            }
        });
        binding.cancelRegisterFab.setOnClickListener(view -> onBackPressed());
    }

    private void onBackPressedToolBar(ActivityRegisterUsersBinding binding) {
        MaterialToolbar toolbar = binding.registerToolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(view -> {
            onBackPressed();
        });
    }

}