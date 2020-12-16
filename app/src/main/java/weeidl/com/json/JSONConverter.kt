package weeidl.com.json

import android.content.Context
import org.json.JSONArray
import org.json.JSONException
import weeidl.com.R
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object JSONConverter {

    @Throws(JSONException::class, IOException::class)
    fun readJSONProducts(context: Context, index: Int): Products {
        val jsonText = readText(context, R.raw.products)
        val JSONArray = JSONArray(jsonText)
        val jsonRoot = JSONArray.getJSONObject(index)
        val id = jsonRoot.getInt("id")
        val name = jsonRoot.getString("name")
        val price = jsonRoot.getString("price")
        val path = jsonRoot.getString("path")
        val products = Products()
        products.id = id
        products.name = name
        products.path = path
        products.price = price
        return products
    }

    @Throws(IOException::class)
    private fun readText(context: Context, resId: Int): String {
        val `is` = context.resources.openRawResource(resId)
        val br = BufferedReader(InputStreamReader(`is`))
        val sb = StringBuilder()
        var s: String? = null
        while (br.readLine().also { s = it } != null) {
            sb.append(s)
            sb.append("\n")
        }
        return sb.toString()
    }
}