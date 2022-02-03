package com.example.customdialog2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button mShowDialog = findViewById(R.id.btnShowDialog);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                mBuilder.setView(mView).setCancelable(false); //set setCancelable false
                final EditText mEmail = mView.findViewById(R.id.etEmail);
                final EditText mPassword = mView.findViewById(R.id.etPassword);
                //Button mLogin = mView.findViewById(R.id.btnLogin);


//                mLogin.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
//                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(MainActivity.this, "fill missing values", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
                mBuilder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();

                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()) {
                            Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {

                            if (mEmail.getText().toString().isEmpty()) {
                                mEmail.setError("Email Cannot be Empty");
                                mEmail.requestFocus();
                                v.vibrate(100);
                                return;
                            }
                            if (mPassword.getText().toString().isEmpty()) {
                                mPassword.setError("Password Cannot be Empty");
                                mPassword.requestFocus();
                                v.vibrate(100);
                                return;
                            }
                        }
                    }
                });
            }
        });
    }
}