package br.com.mrocigno.alicization.Main;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public MainPresenter getInterface(){
        return new MainPresenter();
    }

}
