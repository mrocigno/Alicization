package br.com.mrocigno.alicization.Main;

import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.mrocigno.alicization.Config.MY_View;
import br.com.mrocigno.alicization.R;

public class MainActivity extends MY_View implements MainInterface {

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toast.makeText(activity, presenter.getText(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }
}
