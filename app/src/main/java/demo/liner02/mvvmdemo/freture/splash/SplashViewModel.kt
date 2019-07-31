package demo.liner02.mvvmdemo.freture.splash

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.graphics.Color
import android.util.Log
import com.timper.lonelysword.ActivityScope
import com.timper.lonelysword.base.AppViewModel
import javax.inject.Inject


/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
class SplashViewModel @Inject constructor() : AppViewModel() {

    var label = ObservableField<String>("")
    var bgColor = ObservableInt(Color.parseColor("#ffffff"))

    val TAG = "SplashViewModel"

    init {
        Log.i(TAG,"init")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"onResume")
    }

    override fun afterViews() {
        super.afterViews()
        Log.i(TAG,"afterViews")
    }
}

