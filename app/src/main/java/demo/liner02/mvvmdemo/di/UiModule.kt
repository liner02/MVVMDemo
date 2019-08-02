package demo.liner02.mvvmdemo.di

import com.timper.lonelysword.data.executor.PostExecutionThread
import dagger.Module
import dagger.Provides
import demo.liner02.mvvmdemo.UiThread
import javax.inject.Singleton

/**
 * User: sheng.lin1.o
 * Date: 2019-07-31
 * Description:
 * FIXME
 */
@Module
@Singleton
class UiModule {

    @Provides
    @Singleton
    fun provideContext(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}