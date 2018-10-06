package infotech.com.kotlinstarted

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_second.view.*

class SecondActivity : AppCompatActivity() {

    lateinit var textWelcome: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val intent: Intent = intent;
        val name = intent.getSerializableExtra(MainActivity.KEY_NAME) as Hero
        textWelcome = findViewById(R.id.txtWelcome);
        textWelcome.text = "Hello I'm ${name.name} and My Real Name is ${name.realName}";
    }
}
