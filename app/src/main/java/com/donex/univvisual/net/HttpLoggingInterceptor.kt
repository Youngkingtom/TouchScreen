/*
 * /++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright(c) 2019 - 2022, SHOWTIME
 * + 项目名称:TouchScreen
 * + 文件名称:HttpLoggingInterceptor.kt
 * + 最近修改时间:2022/12/9 下午2:24
 * + Author:Youngkingdom
 * + All rights reserved.
 * ++++++++++++++++++++++++++++++++++++++++++++++++\
 */

package com.donex.univvisual.net

import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.internal.http.promisesBody
import okio.Buffer
import java.io.IOException
import java.net.URLDecoder
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

class HttpLoggingInterceptor : Interceptor {

    @Volatile
    private var level = Level.NONE
    protected var logger: Logger? = null
    protected var tag: String? = null
    private var isPrintStack = true

    enum class Level {
        NONE,  //不打印log
        BASIC,  //只打印 请求首行 和 响应首行
        HEADERS,  //打印请求和响应的所有 Header
        BODY,  //所有数据全部打印
        PARAM //只打印请求和响应参数
    }

    private fun log(message: String?) {
        logger!!.log(java.util.logging.Level.INFO, message)
    }

    constructor(tag: String?) : super() {
        setTag(tag)
    }

    constructor(tag: String?, isPrintStack: Boolean) : super() {
        setTag(tag)
        this.isPrintStack = isPrintStack
    }

    fun setLevel(level: Level): HttpLoggingInterceptor {
        if (level == null) throw NullPointerException("level == null. Use Level.NONE instead.")
        this.level = level
        return this
    }

    private fun setTag(tag: String?): HttpLoggingInterceptor {
        this.tag = tag
        logger = tag?.let { Logger.getLogger(it) }
        return this
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (Level.NONE == level) { //不打任何日志
            return chain.proceed(request)
        }
        //请求日志拦截
        logForRequest(request, chain.connection())
        //执行请求，计算请求时间
        val startNs = System.nanoTime()
        val response: Response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            e.printStackTrace()
            log("<-- HTTP FAILED: " + e.message)
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        //响应日志拦截
        return logForResponse(response, tookMs)
    }

    @Throws(IOException::class)
    protected fun logForRequest(request: Request, connection: Connection?) {
        if (level != Level.PARAM) {
            log("-------------------------------request-------------------------------")
        }
        val logBody = level == Level.BODY || level == Level.PARAM
        val logHeaders = level == Level.BODY || level == Level.HEADERS
        val requestBody = request.body
        val hasRequestBody = requestBody != null
        val protocol = connection?.protocol() ?: Protocol.HTTP_1_1
        try {
            val requestStartMessage = "--> " + request.method + ' ' + request.url + ' ' + protocol
            log(requestStartMessage)
            if (logHeaders) {
                val headers = request.headers
                var i = 0
                val count = headers.size
                while (i < count) {
                    log("\t" + headers.name(i) + ": " + headers.value(i))
                    i++
                }
            }
            if (logBody && hasRequestBody) {
                if (isPlaintext(requestBody!!.contentType())) {
                    log("\tbody:" + bodyToString(request))
                } else {
                    log("\tbody: maybe [file part] , too large too print , ignored!")
                }
            }
        } catch (e: Exception) {
            onError(e)
        } finally {
            if (level != Level.PARAM) {
                log("--> END " + request.method)
            }
        }
    }

    protected fun logForResponse(response: Response, tookMs: Long): Response {
        if (level != Level.PARAM) {
            log("-------------------------------response-------------------------------")
        }
        val builder: Response.Builder = response.newBuilder()
        val clone: Response = builder.build()
        var responseBody = clone.body
        val logBody = level == Level.BODY || level == Level.PARAM
        val logHeaders = level == Level.BODY || level == Level.HEADERS
        try {
            log("<-- " + clone.code + ' ' + clone.message + ' ' + clone.request.url + " (" + tookMs + "ms）")
            if (logHeaders) {
                log(" ")
                val headers = clone.headers
                var i = 0
                val count = headers.size
                while (i < count) {
                    log("\t" + headers.name(i) + ": " + headers.value(i))
                    i++
                }
                log(" ")
            }
            if (logBody && clone.promisesBody()) {
                if (isPlaintext(responseBody!!.contentType())) {
                    val body = responseBody.string()
                    log("\tbody:$body")
                    responseBody = body.toResponseBody(responseBody.contentType())
                    return response.newBuilder().body(responseBody).build()
                } else {
                    log("\tbody: maybe [file part] , too large too print , ignored!")
                }
                if (level != Level.PARAM) {
                    log(" ")
                }
            }
        } catch (e: Exception) {
            onError(e)
        } finally {
            if (level != Level.PARAM) {
                log("<-- END HTTP")
            }
        }
        return response
    }

    private fun isPlaintext(mediaType: MediaType?): Boolean {
        if (mediaType == null) {
            return false
        }
        if (mediaType.type == "text") {
            return true
        }
        var subtype = mediaType.subtype
        if (subtype != null) {
            subtype = subtype.lowercase(Locale.getDefault())
            return subtype.contains("x-www-form-urlencoded") ||
                    subtype.contains("json") ||
                    subtype.contains("xml") ||
                    subtype.contains("html")
        }
        return false
    }

    protected fun bodyToString(request: Request): String? {
        try {
            val copy = request.newBuilder().build()
            val buffer = Buffer()
            copy.body!!.writeTo(buffer)
            var charset: Charset = Charset.forName("UTF-8")
            val contentType = copy.body!!.contentType()
            if (contentType != null) {
                charset = contentType.charset(Charset.forName("UTF-8"))!!
            }
            return URLDecoder.decode(buffer.readString(charset), Charset.forName("UTF-8").name())
        } catch (e: Exception) {
            onError(e)
        }
        return ""
    }

    protected fun onError(t: Throwable) {
        if (isPrintStack) t.printStackTrace()
    }
}