package br.com.mrocigno.alicization.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import br.com.mrocigno.alicization.Config.MY_View;
import br.com.mrocigno.alicization.R;
import br.com.mrocigno.alicization.Utils.ScreenshotUtils;
import lib.rocigno.photopicker.Utils.GlideUtil;

public class ViewActivity extends MY_View {

    ImageView img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri imagem = ScreenshotUtils.takeScreenshot(activity);
                GlideUtil.initGlide(activity, imagem, img);
            }
        });
    }


    @Override
    public int getLayoutResource() {
        return R.layout.activity_view;
    }
}
