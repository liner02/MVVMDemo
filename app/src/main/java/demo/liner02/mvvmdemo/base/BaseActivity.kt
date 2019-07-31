package demo.liner02.mvvmdemo.base


import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import com.timper.lonelysword.base.AppActivity
import com.timper.lonelysword.base.AppViewModel
import demo.liner02.mvvmdemo.utils.StatusBarUtil

/**
 * User: tangpeng.yang
 * Date: 18/07/2018
 * Description:
 * FIXME
 */
abstract class BaseActivity<V : AppViewModel, T : ViewDataBinding> : AppActivity<V, T>() {
    private var mFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBar()
    }

    protected open fun setStatusBar() {
        StatusBarUtil.setTransparent(this)
        StatusBarUtil.setLightMode(this)
    }

    override fun addFragment(frameLayoutId: Int, fragment: Fragment?) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            if (fragment.isAdded) {
//                if (mFragment != null) {
//                    transaction.hide(mFragment)
//                        .show(fragment)
//                } else {
//                    transaction.show(fragment)
//                }
                mFragment?.let { transaction.hide(it)
                    .show(fragment) } ?.run {
                    transaction.show(fragment)
                }
//                if (mFragment != null) {
//                    transaction.hide(mFragment)
//                        .add(frameLayoutId, fragment)
//                } else {
//                    transaction.add(frameLayoutId, fragment)
//                }
                mFragment?.let { transaction.hide(it)
                    .show(fragment) } ?.run {
                    transaction.add(frameLayoutId, fragment)
                }
            }
            mFragment = fragment
            transaction.commit()
        }
    }

    protected fun replaceFragment(frameLayoutId: Int, fragment: Fragment?) {
        if (fragment != null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(frameLayoutId, fragment)
            transaction.commit()
        }
    }

}
