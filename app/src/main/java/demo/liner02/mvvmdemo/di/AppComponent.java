package demo.liner02.mvvmdemo.di;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import demo.liner02.mvvmdemo.MainApplication;
import lonelysword.di.AppModule$$app;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, DataModule.class, UiModule.class, AppModule$$app.class
})
public interface AppComponent extends AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<MainApplication> {
    }
}