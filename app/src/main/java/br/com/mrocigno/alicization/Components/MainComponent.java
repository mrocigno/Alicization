package br.com.mrocigno.alicization.Components;

import br.com.mrocigno.alicization.Modules.MainModule;
import br.com.mrocigno.alicization.Views.MainActivity;
import dagger.Component;

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
