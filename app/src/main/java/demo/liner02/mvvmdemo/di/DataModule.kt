package demo.liner02.mvvmdemo.di

import com.timper.lonelysword.data.executor.JobExecutor
import com.timper.lonelysword.data.executor.ThreadExecutor
import dagger.Module
import dagger.Provides
import demo.liner02.mvvmdemo.data.WanAndroidRepository
import demo.liner02.mvvmdemo.data.remote.WanAndroidRepositoryImp
import demo.liner02.mvvmdemo.data.service.WanAndroidClient
import javax.inject.Singleton

/**
 * User: sheng.lin1.o
 * Date: 2019-08-01
 * Description:
 * FIXME
 */
@Module
@Singleton
class DataModule {

    @Provides
    @Singleton
    fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    fun provideServiceFactor(): ServiceFactor {
        return ServiceFactor.instance
    }

    @Provides
    @Singleton
    fun provideWanAndroidService(serviceFactor: ServiceFactor): WanAndroidClient {
        return serviceFactor.createService(WanAndroidClient::class.java)
    }

    @Provides
    @Singleton
    fun bindWanAndroidRepository(wanAndroidRepositoryImp: WanAndroidRepositoryImp): WanAndroidRepository {
        return wanAndroidRepositoryImp
    }
}