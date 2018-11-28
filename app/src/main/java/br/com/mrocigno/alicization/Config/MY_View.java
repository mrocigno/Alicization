package br.com.mrocigno.alicization.Config;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class MY_View extends AppCompatActivity {

    public String TAG = "ProjectAlicization";
    public Activity activity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        this.activity = MY_View.this;

    }

    public abstract int getLayoutResource();

}
