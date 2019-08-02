package demo.liner02.mvvmdemo.data

/**
 * User: sheng.lin1.o
 * Date: 2019-08-01
 * Description:
 * FIXME
 */
public final class BaseResponse<T> public constructor() : java.io.Serializable {
    var data: T? = null
    var errorMsg: String? = null
    var errorCode: Int? = 0
}