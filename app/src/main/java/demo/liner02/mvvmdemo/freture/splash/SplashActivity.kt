package demo.liner02.mvvmdemo.freture.splash

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.timper.lonelysword.annotations.apt.AfterViews
import com.timper.lonelysword.annotations.apt.BeforeViews
import com.timper.lonelysword.annotations.apt.Dagger
import com.timper.lonelysword.annotations.apt.RootView
import com.timper.lonelysword.base.AppActivity
import demo.liner02.mvvmdemo.R
import demo.liner02.mvvmdemo.databinding.ActSplashBinding

/**
 * User:
 * Date:
 * Description:
 * FIXME
 */
@Dagger
@RootView(R.layout.act_splash)
class SplashActivity : AppActivity<SplashViewModel, ActSplashBinding>() {
    
    val TAG = "SplashActivity"

    companion object {
        @JvmOverloads
        fun instance(context: Context, bundle: Bundle? = null) {
            val intent = Intent(context, SplashActivity::class.java)
            if (bundle != null) {
                intent.putExtra("data", bundle)
            }
            context.startActivity(intent)
        }
    }

    @AfterViews
    fun afterViews() {
        Log.i(TAG,"afterViews")
        Handler().postDelayed({
            viewModel.label.set("AfterViews")
        },2000)
    }

    @BeforeViews
    fun beforViews() {
        Log.i(TAG,"beforViews")
        viewModel.label.set("Welcome")
        viewModel.bgColor.set(Color.parseColor("#FEDFE1"))
    }
}

