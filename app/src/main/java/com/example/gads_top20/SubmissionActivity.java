package com.example.gads_top20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity {

//    Form credentials
    private static  final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static GadsApi mGadsApi;

    private EditText editName;
    private EditText editSurName;
    private EditText editEmail;
    private EditText editProjectLink;

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mProjectLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        editName = findViewById(R.id.etFirstName);
        editSurName = findViewById(R.id.etLastName);
        editEmail = findViewById(R.id.etEmail);
        editProjectLink = findViewById(R.id.etProjectLink);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmission);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (formWellFilled()) {
                    openConfirmationDialog();
                }
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mGadsApi = retrofit.create(GadsApi.class);

    }
// function return false if user don't fill the form correctly or completely
    private boolean formWellFilled(){
        boolean result = true;

        mFirstName = editName.getText().toString();
        mLastName = editSurName.getText().toString();
        mEmail = editEmail.getText().toString();
        mProjectLink = editProjectLink.getText().toString();

        if (TextUtils.isEmpty(mFirstName) ||
            TextUtils.isEmpty(mLastName) ||
            TextUtils.isEmpty(mEmail) ||
            TextUtils.isEmpty(mProjectLink)){

            Toast.makeText(this, "All field are required", Toast.LENGTH_SHORT).show();
            result = false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editEmail.getText().toString()).matches()){
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            result = false;
        }

        return  result;
    }

    private void openConfirmationDialog() {
        DialogConfirm dialog =  new DialogConfirm();
        dialog.show(getSupportFragmentManager(), "123");
    }

    private void openSuccessDialog() {
        Dialog dialog =  new Dialog(SubmissionActivity.this);
        getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submission_success);

        dialog.show();
    }

    private void openFailedDialog() {
        Dialog dialog =  new Dialog(SubmissionActivity.this);
        getWindow();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.submission_failed);

        dialog.show();
    }

//   Retrofit for sending data  by making an HTTP POST request
    private void sendDataTask(String fName, String lName, String email, String projectLink) {
        Call<PostSubmit> callForKristo = mGadsApi.createPostForKristo(fName, lName, email, projectLink);
        Call<PostSubmit> call = mGadsApi.createPost(fName, lName, email, projectLink);
        call.enqueue(new Callback<PostSubmit>() {
            @Override
            public void onResponse(@NonNull Call<PostSubmit> call, @NonNull Response<PostSubmit> response) {
                if (!response.isSuccessful()){
                    Log.i("POST REQUEST FAILED", "some thing when wrong");
                    return;
                }
                PostSubmit sendPost = response.body();
                String content = "";
                content += "Code: "+ response.code() + "\n";

                assert sendPost != null;
                content += "Name: : "+ sendPost.getFirstName() + "\n";
                content += "Email: "+ sendPost.getEmail() + "\n";
                content += "Gidhub: "+ sendPost.getProjectLink() + "\n";

                Log.i("RESULT", content);
                openSuccessDialog();
            }

            @Override
            public void onFailure(@NonNull Call<PostSubmit> call, @NonNull Throwable t) {
                Log.i("FAILED", t.getMessage());
                openFailedDialog();
            }
        });

        callForKristo.enqueue(new Callback<PostSubmit>() {
            @Override
            public void onResponse(@NonNull Call<PostSubmit> call, @NonNull Response<PostSubmit> response) {
                if (!response.isSuccessful()){
                    Log.i("POST REQUEST FAILED", "some thing when wrong");
                    return;
                }
                PostSubmit sendPost = response.body();
                String content = "";
                content += "Code: "+ response.code() + "\n";

                assert sendPost != null;
                content += "Name: : "+ sendPost.getFirstName() + "\n";
                content += "Email: "+ sendPost.getEmail() + "\n";
                content += "Gidhub: "+ sendPost.getProjectLink() + "\n";

                Log.i("**RESULT FOR KRISTO**", content);
                openSuccessDialog();
            }

            @Override
            public void onFailure(@NonNull Call<PostSubmit> call, @NonNull Throwable t) {
                Log.i("**FAILED FOR KRISTO**", t.getMessage());
                openFailedDialog();
            }
        });
    }

    public static class DialogConfirm extends DialogFragment{

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle saveInstanceState){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_confirm, null);

            final Button btnConfirm = dialogView.findViewById(R.id.btnConfirm);
            final ImageButton btnCancel = dialogView.findViewById(R.id.btnCancel);

            builder.setView(dialogView);

//            Close window on negative respond
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    close window
                    dismiss();
                }
            });

//            Call function sendDataTask of SubmissionActivity for POST request on positive respond
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SubmissionActivity callingActivity = (SubmissionActivity) getActivity();
                    assert callingActivity != null;
                    callingActivity.sendDataTask(callingActivity.mFirstName,
                            callingActivity.mLastName,
                            callingActivity.mEmail,
                            callingActivity.mProjectLink);

//                    Quit the dialog
                    dismiss();
                }
            });

            return builder.create();
        }
    }
}
