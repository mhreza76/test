package com.example.retrofitemonvai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.retrofitemonvai.Utils.RetrofitClient;
import com.example.retrofitemonvai.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.retrofitemonvai.Utils.Constants.BASE_URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    List<User> userList;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);

        getAllUser();

        Log.d(TAG, "onCreate: ");



    }

    private void getAllUser() {


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RetrofitClient client = retrofit.create(RetrofitClient.class);

        Call<List<User>> usersRetrofitCall = client.getUsers();

        usersRetrofitCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.d(TAG, "onResponse: " + response.body().toString());

                userList = response.body();

                //List is resdy

                if(userList != null ){
                    for( User u : userList){
                        // operations
                        // u.getEmail();
                        // u.getName()
                        // .......
                    }
                    textView.setText("i got " +userList.size() + " users");
                }


            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.d(TAG, "onResponse: " + t.getLocalizedMessage());
                textView.setText(t.getLocalizedMessage());

            }
        });


    }
}
