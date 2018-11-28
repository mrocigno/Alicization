package br.com.mrocigno.alicization.Modules;

import br.com.mrocigno.alicization.Presenters.MainPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    public MainPresenter getInterface(){
        return new MainPresenter();
    }

}
