package demo.liner02.mvvmdemo;

import com.timper.lonelysword.Lonelysword;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import demo.liner02.mvvmdemo.di.DaggerAppComponent;

/**
 * User: sheng.lin1.o
 * Date: 2019-07-30
 * Description:
 * FIXME
 */
public class MainApplication extends DaggerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化框架
        Lonelysword.init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
