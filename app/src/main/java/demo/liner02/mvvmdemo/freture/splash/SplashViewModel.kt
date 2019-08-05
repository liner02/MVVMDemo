package demo.liner02.mvvmdemo.freture.splash

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.graphics.Color
import android.util.Log
import com.timper.lonelysword.ActivityScope
import com.timper.lonelysword.base.AppViewModel
import demo.liner02.mvvmdemo.data.GetHomeUseCase
import demo.liner02.mvvmdemo.data.bean.Article
import demo.liner02.mvvmdemo.data.bean.Home
import demo.liner02.mvvmdemo.data.bean.WXArticle
import io.reactivex.FlowableSubscriber
import org.reactivestreams.Subscription
import javax.inject.Inject


/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
class SplashViewModel @Inject constructor(var useCase: GetHomeUseCase) : AppViewModel() {

    var label = ObservableField<String>("")
    var bgColor = ObservableInt(Color.parseColor("#ffffff"))
    val TAG = "SplashViewModel"

    init {
        Log.i(TAG, "init")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun afterViews() {
        super.afterViews()
        Log.i(TAG, "afterViews")
        useCase.execute(null)
            .subscribe(object : FlowableSubscriber<Home>{
            override fun onComplete() {
                Log.e(TAG,"onComplete")
            }

            override fun onSubscribe(s: Subscription) {
                Log.e(TAG,"onSubscribe")
                s.request(Long.MAX_VALUE)
            }

            override fun onError(t: Throwable?) {
                Log.e(TAG,"onError $t")
            }

            override fun onNext(t: Home?) {
                Log.e(TAG,"onNext")
                t?.run {
                    if (t.datas.isNotEmpty()){
                        Log.e(TAG,"onNext isNotEmpty")
                        label.set(t.datas[0].author)
                    }
                }
            }
        })
    }
}

