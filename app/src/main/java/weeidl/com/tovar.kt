package weeidl.com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import weeidl.com.R.drawable
import weeidl.com.json.JSONConverter

class tovar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tovar)
        val index = intent.getIntExtra("index", 0)
        val price = JSONConverter.readJSONProducts(this, index).price
        val name = JSONConverter.readJSONProducts(this, index).name
        findViewById<TextView>(R.id.fragment_tovar).setText(name)
        findViewById<TextView>(R.id.price).setText(price)
        if(index==0){
            findViewById<ImageView>(R.id.imageView).setImageResource(drawable.ps_4)
            findViewById<Button>(R.id.button_ar).setOnClickListener{
                val intent = Intent(this, Ar::class.java)
                startActivity(intent)
            }
        }
        else if (index==1){
            findViewById<ImageView>(R.id.imageView).setImageResource(drawable.macbook_2)
        }else if (index==2){
            findViewById<ImageView>(R.id.imageView).setImageResource(drawable.imac_2)
        }else if(index==3){
            findViewById<ImageView>(R.id.imageView).setImageResource(drawable.msinout)
            findViewById<Button>(R.id.button_ar).setOnClickListener{
                val intent = Intent(this, ArTwo::class.java)
                startActivity(intent)
            }
        }
        else if(index==4){
            findViewById<ImageView>(R.id.imageView).setImageResource(drawable.oneee)
        }
        else if(index==5) {
            findViewById<ImageView>(R.id.imageView).setImageResource(drawable.freeeee)
        }
    }
}
