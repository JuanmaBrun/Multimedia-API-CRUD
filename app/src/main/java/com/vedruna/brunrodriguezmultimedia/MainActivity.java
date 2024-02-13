package com.vedruna.brunrodriguezmultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.vedruna.brunrodriguezmultimedia.RetrofitService.RetrofitService;
import com.vedruna.brunrodriguezmultimedia.interfaces.UserInterface;
import com.vedruna.brunrodriguezmultimedia.model.User;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditText = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldBranch);
        TextInputEditText inputEditLocation = findViewById(R.id.form_textFieldLocation);
        MaterialButton buttonSave = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        UserInterface userInterface = retrofitService.getRetrofit().create(UserInterface.class);

        buttonSave.setOnClickListener(view -> {
            String name = String.valueOf(inputEditText.getText());
            String branch = String.valueOf(inputEditBranch.getText());
            String location = String.valueOf(inputEditLocation.getText());

            User user = new User();
            user.setUsername(name);
            user.setPassword(branch);
            user.setEmail(location);

            userInterface.register(user)
                    .enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(MainActivity.this, "Save Successful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save Failed", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Err: ", t);
                        }
                    });
        });
    }


}