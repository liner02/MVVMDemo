package demo.liner02.mvvmdemo.freture.splash

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.graphics.Color
import android.util.Log
import com.timper.lonelysword.ActivityScope
import com.timper.lonelysword.base.AppViewModel
import demo.liner02.mvvmdemo.data.GetWXActicleUseCase
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
class SplashViewModel @Inject constructor(var useCase: GetWXActicleUseCase) : AppViewModel() {

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
            .subscribe(object : FlowableSubscriber<List<WXArticle>>{
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

            override fun onNext(t: List<WXArticle>?) {
                Log.e(TAG,"onNext")
                t?.run {
                    if (t.isNotEmpty()){
                        Log.e(TAG,"onNext isNotEmpty")
                        label.set(t[0].name)
                    }
                }
            }

        })
    }
}

