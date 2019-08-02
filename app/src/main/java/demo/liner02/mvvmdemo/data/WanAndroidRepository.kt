package demo.liner02.mvvmdemo.data

import com.timper.lonelysword.annotations.apt.UseCase
import demo.liner02.mvvmdemo.data.bean.WXArticle
import io.reactivex.Flowable

/**
 * User: sheng.lin1.o
 * Date: 2019-08-01
 * Description:
 * FIXME
 */
interface WanAndroidRepository {

    @UseCase
    fun getWXActicle(): Flowable<List<WXArticle>>
}