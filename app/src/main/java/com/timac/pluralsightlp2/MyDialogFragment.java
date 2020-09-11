package com.timac.pluralsightlp2;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyDialogFragment extends AppCompatDialogFragment {
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    EditText mName;
    EditText mLastName;
    EditText mEmail;
    EditText mLink;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(view);

        Button btnConfirm = view.findViewById(R.id.button_confirm);
        ImageView imgCancel = view.findViewById(R.id.imageViewCancel);
        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
            }
        });
        return builder.create();
    }
    public void createPost() {

        mName = getActivity().findViewById(R.id.first_Name);
        mLastName = getActivity().findViewById(R.id.last_name);
        mEmail = getActivity().findViewById(R.id.editTextTextEmail);
        mLink = getActivity().findViewById(R.id.editTextTextGitHubLink);

        String email = mEmail.getText().toString();
        String name = mName.getText().toString();
        String lastName = mLastName.getText().toString();
        String link = mLink.getText().toString();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<Post> call = jsonPlaceHolderApi.createPost(email,name,lastName,link);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    mLink.setText("Code: " + response.code());
                    return;
                }
                Post postResponse = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                mLink.setText(content);

                getDialog().cancel();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View v = getLayoutInflater().inflate(R.layout.successful_submission,null);
                builder.setView(v).create().show();

            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                getDialog().cancel();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View v = getLayoutInflater().inflate(R.layout.unsuccsessful_submission,null);
                builder.setView(v).create().show();
                mLink.setText(t.getMessage());
            }
        });
    }

    }
