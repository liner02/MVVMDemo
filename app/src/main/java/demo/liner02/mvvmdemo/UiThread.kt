package demo.liner02.mvvmdemo

import com.timper.lonelysword.data.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * User: sheng.lin1.o
 * Date: 2019-07-31
 * Description:
 * FIXME
 */
class UiThread @Inject constructor() : PostExecutionThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}