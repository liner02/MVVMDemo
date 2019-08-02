package demo.liner02.mvvmdemo.data.service

import demo.liner02.mvvmdemo.data.BaseResponse
import demo.liner02.mvvmdemo.data.bean.WXArticle
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * User: tangpeng.yang
 * Date: 04/06/2018
 * Description:
 * FIXME
 */

private const val WXARTICLE:String = "/wxarticle/chapters/json"

interface WanAndroidClient {

  @GET(WXARTICLE)
  fun getWXArticle(): Flowable<BaseResponse<List<WXArticle>>>

}
