package demo.liner02.mvvmdemo.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * User: tangpeng.yang
 * Date: 20/03/2018
 * Description:
 * FIXME
 */
class ServiceFactor private constructor() {
    companion object {
        val instance = SingletonHolder.holder
    }

    private object SingletonHolder {
        val holder = ServiceFactor()
    }

    private val mRetrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .baseUrl("https://wanandroid.com/")
            .client(OkHttpClient.Builder().addInterceptor(createHeadInterceptor()).addInterceptor(logging).build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())   //数据解析器
            .build()
    }

//    val okHttpClientBuilder by lazy {
//        OkHttpClient.Builder().connectTimeout(30L,TimeUnit.SECONDS).readTimeout(30L,TimeUnit.SECONDS).retryOnConnectionFailure(true)
//    }
//
//    init {
//        okHttpClientBuilder.ho
//    }
//  var mHelper = RetrofitHelper()

//  var env: IHttpProvider
//
//  var map:Map<String,String>
//
//  init {
//    if (HttpSdk.getInstance().isDebug) {
//      mHelper.okHttpClientBuilder.addNetworkInterceptor(StethoInterceptor())
//    }
//    //默认test环境
////    var api =
//    env = create(SpUtil.readString("env", "https://toolkitapi-test.niohome.com"))
////    Map<String,String> map = new Map();
//    map = HashMap()
////    (map as HashMap<String, String>)["sso_token"] = env.accessToken
////    mHelper.addInterceptor(TokenInterceptor(env))
//    mHelper.addInterceptor(ParamPackInterceptor())
//      .initUrl(env.getSchema() + "://" + env.getHost() + "/")
//      .build()
//  }
//
//  fun create(env: String): IHttpProvider {
//    when (env) {
//      "test" -> return QaEnv()
//      "uat" -> return SitEnv()
//      "stg" -> return StgEnv()
//      "prod" -> return ProdEnv()
//    }
//    return QaEnv()
//  }

    fun <T> createService(clazz: Class<T>): T {
        return mRetrofit.create(clazz)
    }

    private fun <T> createService(host: String, okHttpClient: OkHttpClient, gson: Gson, clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(host)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(clazz)
    }

    private fun createOkHttpClient(isDebug: Boolean?, httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(httpLoggingInterceptor)
            .addInterceptor(createHeadInterceptor())
        isDebug?.let {
            if (it) {
                builder.addNetworkInterceptor(StethoInterceptor())
            }
        }
        builder.retryOnConnectionFailure(true)
        return builder.connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    private fun createGson(): Gson {
        return GsonBuilder().setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    private fun createHeadInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json;charset=UTF-8")
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (isDebug) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logging.level = HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }
}
