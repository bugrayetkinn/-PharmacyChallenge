package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    TextView txtSifremiUnuttum;
    TextInputEditText editTextSifre, editTextKullaniciAdi;
    CheckBox checkBox;
    Button buttonGiris;

    ImageView bgapp, cloverimg;
    LinearLayout homeText, menus;
    Animation fromBottom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGiris = findViewById(R.id.buttonGiris);
        txtSifremiUnuttum = findViewById(R.id.txtSifremiUnuttum);
        editTextKullaniciAdi = findViewById(R.id.editTextKullaniciAdi);
        editTextSifre = findViewById(R.id.editTextSifre);
        checkBox = findViewById(R.id.checkBox);
        homeText = findViewById(R.id.hometext);
        bgapp = findViewById(R.id.bgapp);
        menus = findViewById(R.id.menus);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom);

        bgapp.animate().translationY(-1600).setDuration(800).setStartDelay(300);
        homeText.startAnimation(fromBottom);
        menus.startAnimation(fromBottom);


        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editTextSifre.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    editTextSifre.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        txtSifremiUnuttum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyAlertCreate myAlertCreate = new MyAlertCreate();
                View view = getLayoutInflater().inflate(R.layout.alert_sifremi_unuttum, null, false);
                myAlertCreate.createAlert(LoginActivity.this, view);
                final EditText editTxtMail = view.findViewById(R.id.editTxtMail);
                Button btnIptal = view.findViewById(R.id.btnIptal);
                final Button btnGonder = view.findViewById(R.id.btnGonder);


                btnGonder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String text = editTxtMail.getText().toString();
                        if (!text.isEmpty()) {
                            Toast.makeText(LoginActivity.this, "Başarıyla Gönderildi.", Toast.LENGTH_SHORT).show();
                            myAlertCreate.getDialog().dismiss();
                        } else {

                        }

                    }
                });
                btnIptal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myAlertCreate.getDialog().dismiss();
                    }
                });

            }
        });


        buttonGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kullaniciAdi = editTextKullaniciAdi.getText().toString();
                String sifre = editTextSifre.getText().toString();
                if (!kullaniciAdi.isEmpty() && !sifre.isEmpty()) {
                    startActivity(new Intent(LoginActivity.this, HomepageActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Eksik Bilgi Girdiniz", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {

        View view = getLayoutInflater().inflate(R.layout.alert_uygulama_cikis, null, false);

        final MyAlertCreate alertCreate = new MyAlertCreate();
        alertCreate.createAlert(LoginActivity.this, view);

        Button buttonEvet = view.findViewById(R.id.buttonEvet);
        Button buttonIptal = view.findViewById(R.id.buttonIptal);

        buttonEvet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.super.onBackPressed();
                alertCreate.getDialog().dismiss();
            }
        });

        buttonIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCreate.getDialog().dismiss();
            }
        });


    }
}
