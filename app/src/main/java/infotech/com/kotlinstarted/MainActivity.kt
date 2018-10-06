package infotech.com.kotlinstarted

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val  KEY_NAME = "name";
    }
    lateinit var etName: EditText;
    lateinit var btnClickMe: Button;
    lateinit var textWelcome: TextView;
    lateinit var btnNextAct: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName);
        btnClickMe = findViewById(R.id.buttonClickMe);
        textWelcome = findViewById(R.id.tvWelcome)
        btnNextAct = findViewById(R.id.btnNextAct);
        btnClickMe.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                val text = etName.text;
                textWelcome.text = "Hello $text";
            }

        })

        btnNextAct.setOnClickListener(){
            val intent : Intent = Intent(applicationContext,SecondActivity::class.java)
            val name: String = etName.text.toString();
            var hero : Hero = Hero("spiderMan", "Peter Parker")
            intent.putExtra(KEY_NAME,hero);
            startActivity(intent)
        }
    }



}
