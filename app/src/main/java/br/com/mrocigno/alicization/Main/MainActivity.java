package br.com.mrocigno.alicization.Main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;

import br.com.mrocigno.alicization.Config.MY_View;
import br.com.mrocigno.alicization.R;
import lib.rocigno.photopicker.PhotoPicker;

public class MainActivity extends MY_View implements MainInterface {

//    @Inject
//    MainPresenter presenter;

    PhotoPicker photo;
    Button action;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photo = findViewById(R.id.photo);
        action = findViewById(R.id.action);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
                if(textRecognizer.isOperational()){
                    InputStream stream = null;
                    try {
                        stream = getContentResolver().openInputStream(photo.getArrayList().get(0).getImg());
                        Bitmap bitmap = BitmapFactory.decodeStream(stream);
                        Frame frame = new Frame.Builder().setBitmap(bitmap).build();

                        textRecognizer.detect(frame);

                        SparseArray<TextBlock> teste = textRecognizer.detect(frame);

                        for (int i = 0; i < teste.size(); i++) {
                            try{
                                Log.e("TESTEE", teste.get(i).getValue());
                            }catch (Exception e){
                                Log.e("TESTEE", e.getMessage() + " - at " + i);
                            }
                        }

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        photo.result(requestCode,resultCode,data);
    }

}
