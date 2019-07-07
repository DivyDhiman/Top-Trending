package functionalityAll

import android.content.Context
import com.gojek.test.gojektest.R
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

// All Api Request Method class
class ApiResponse {
    private var urlConnection: HttpURLConnection? = null
    private var response: String? = null
    private var url1: URL? = null
    private val inputStream: InputStream? = null

    //Get request method
    fun ResponseGet(context: Context, str: String): String {

        try {
            url1 = URL(str)
            urlConnection = url1!!.openConnection() as HttpURLConnection
            urlConnection!!.connectTimeout = 40000
            urlConnection!!.readTimeout = 40000
            urlConnection!!.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            urlConnection!!.connect()

            val HttpResult = urlConnection!!.responseCode
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                val ins = BufferedInputStream(urlConnection!!.inputStream)
                val br = BufferedReader(InputStreamReader(ins))
                val sb = StringBuilder()
                var line : String?
                do {
                    line = br.readLine()
                    if (line == null)
                        break
                    sb.append(line)
                } while (true)

//                while ((line = br.readLine()) != null) {
//                }
                response = sb.toString()
                br.close()
                urlConnection!!.disconnect()
            } else if (HttpResult == HttpURLConnection.HTTP_NOT_FOUND) {
                response = context.getString(R.string.error_Http_not_found)
            } else if (HttpResult == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                response = context.getString(R.string.error_Http_internal)
            } else {
                response = context.getString(R.string.error_Http_other)
            }
        } catch (e: Exception) {
            urlConnection!!.disconnect()
            response = "Error"
        }

        return response.toString()
    }

}