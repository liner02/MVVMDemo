package demo.liner02.mvvmdemo.data.remote

import demo.liner02.mvvmdemo.data.ErrorTransformer
import demo.liner02.mvvmdemo.data.WanAndroidRepository
import demo.liner02.mvvmdemo.data.bean.WXArticle
import demo.liner02.mvvmdemo.data.service.WanAndroidClient
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * User: sheng.lin1.o
 * Date: 2019-08-01
 * Description:
 * FIXME
 */
class WanAndroidRepositoryImp @Inject constructor(internal var client: WanAndroidClient) : WanAndroidRepository{
    override fun getWXActicle(): Flowable<List<WXArticle>> {
        return client.getWXArticle().compose(ErrorTransformer())
    }

}