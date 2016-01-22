package com.yxc.calculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.zhy.autolayout.AutoLayoutActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AutoLayoutActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_go.setOnClickListener {
            var input:String = et_input.text.toString()
            var str:String = checkLanguage(input)
            tv_response.text = str
        }
        tv_response.setOnClickListener {
            val intent = Intent(this, CalActivity::class.java)
            startActivity(intent)
//            Main2Activity.launch(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun calSum(a: Int, b:Int): Int{
        return a+b
    }

    fun checkLanguage(lg: String): String{
        return when(lg){
            "EN"->"Hello World"
            "CN"->"你好世界"
            "JP"->"こんにちは世界"
            "KR"->"안녕하세요, 세계"
            "FR"->"bonjour, monde"
            else -> "你好 Hello 世界 World"
        }
    }
}
