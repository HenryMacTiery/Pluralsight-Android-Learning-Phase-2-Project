package com.timac.pluralsightlp2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LearningLeaders extends Fragment {

    private ImageView imgBadge;
    private TextView txtName;
    private TextView txtHours;
    private TextView txtCountry;
    private RecyclerView mRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.learning_leaders, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecyclerView = getView().findViewById(R.id.recyclerView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Hour>> call = jsonPlaceHolderApi.getHours();
        call.enqueue(new Callback<List<Hour>>() {
            @Override
            public void onResponse(Call<List<Hour>> call, Response<List<Hour>> response) {
                if (!response.isSuccessful()) {
                    txtName.setText("Code: " + response.code());
                    return;
                }
                List<Hour> hours = response.body();
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                MyAdapter adapter = new MyAdapter(getContext(), hours);
                mRecyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<Hour>> call, Throwable t) {
                //txtName.setText(t.getMessage());          // Will cause an error if the response is unsuccessful
            }
        });
    }
}
