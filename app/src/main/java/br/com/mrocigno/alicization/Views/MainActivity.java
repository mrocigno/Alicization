package br.com.mrocigno.alicization.Views;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.inject.Inject;

import br.com.mrocigno.alicization.Components.DaggerMainComponent;
import br.com.mrocigno.alicization.Config.MY_View;

import br.com.mrocigno.alicization.Components.MainComponent;
import br.com.mrocigno.alicization.Presenters.MainInterface;
import br.com.mrocigno.alicization.Presenters.MainPresenter;
import br.com.mrocigno.alicization.R;
import br.com.mrocigno.alicization.Services.OverlayService;
import lib.rocigno.photopicker.PhotoPicker;

public class MainActivity extends MY_View implements MainInterface {

    @Inject
    MainPresenter presenter;

    PhotoPicker photo;
    Button action;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainComponent component = DaggerMainComponent.builder()
                .build();
        component.inject(this);

        initVars();

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(!Settings.canDrawOverlays(activity)) {

                //If the draw over permission is not available open the settings screen
                //to grant the permission.
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 2084);
            }
            Intent serviceintent = new Intent(activity, OverlayService.class);
            startService(serviceintent);



//                finish();

//                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
//                if(textRecognizer.isOperational()){
//                    InputStream stream = null;
//                    try {
//                        stream = getContentResolver().openInputStream(photo.getArrayList().get(0).getImg());
//                        Bitmap bitmap = BitmapFactory.decodeStream(stream);
//                        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
//
//                        textRecognizer.detect(frame);
//
//                        SparseArray<TextBlock> teste = textRecognizer.detect(frame);
//
//                        for (int i = 0; i < teste.size(); i++) {
//                            try{
//                                Log.e(TAG, teste.get(i).getValue());
//                            }catch (Exception e){
//                                Log.e(TAG, e.getMessage() + " - at " + i);
//                            }
//                        }
//
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for(int result:grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                Toast.makeText(activity, "Precisamos destas permissões para o App funcionar!", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected void initVars(){
        photo = findViewById(R.id.photo);
        action = findViewById(R.id.action);
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
