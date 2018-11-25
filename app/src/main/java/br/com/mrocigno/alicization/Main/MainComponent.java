package br.com.mrocigno.alicization.Main;

import dagger.Component;

@Component(modules = MainModule.class)
interface MainComponent {
    void inject(MainActivity activity);
}
