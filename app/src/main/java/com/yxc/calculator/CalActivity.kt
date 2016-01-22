package com.yxc.calculator

import android.os.Bundle
import android.util.Log
import com.greenpineyu.fel.FelEngine
import com.greenpineyu.fel.FelEngineImpl
import com.zhy.autolayout.AutoLayoutActivity
import kotlinx.android.synthetic.main.activity_cal.*
import java.util.regex.Pattern

/**
 * Created by robin on 16-1-22.
 */
class CalActivity : AutoLayoutActivity() {

    val operations: Array<String> = arrayOf("+", "-", "×", "÷")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)
        var i = 0
        while (i < rl_operation.childCount) {
            var child = rl_operation.getChildAt(i)
            child.setOnClickListener {
                doOperate(child.tag as String)
            }
            i++
        }
    }

    fun doOperate(type: String) {
        when (type) {
            "=" -> cal()
            "K" -> brackets()
            "C" -> clear()
            "D" -> delete()
            else -> normal(type)
        }
    }

    fun cal() {
//        var scope:Scope = Scope.create()
//        var exp:Expression = Parser.parse(tv_result.text.toString(), scope)
//        var result = exp.evaluate()
//        Log.d("HHH", "****$result****")

        var exp:String = tv_result.text.toString()
        exp = exp.replace("×", "*")
        exp = exp.replace("÷", "/")
        var result = Arithmetic.arithmetic(exp)
        var resultInt:Int = result.toInt()
        var finalResult:Any = if(result==resultInt.toDouble()) resultInt else result
        Log.d("HHH", "****$result****$resultInt****")
        tv_result.text = finalResult.toString()
    }

    fun brackets() {

    }

    fun clear() {
        tv_result.text = "0"
    }

    fun delete() {
        var str = tv_result.text.toString()
        var result = str.dropLast(1)
        if (result.length == 0) {
            result = "0"
        }
        tv_result.text = result
    }

    fun normal(str: String) {
        when (str) {
            "." -> insertPoint()
            in operations -> insertOperation(str)
            else -> plus(str)
        }
    }

    fun plus(str: String) {
        tv_result.text = tv_result.text.toString().plus(str)
    }

    fun insertPoint() {
        var text = tv_result.text
        var list: List<String> = text.split(Pattern.compile("[\\+\\-×÷]"), 0)
        if (list.last().contains(".")) {
            return
        }
        var last: Int = text[text.length - 1].toInt()
        if ((48..57).contains(last as Any)) {
            plus(".")
        } else {
            plus("0.")
        }
    }

    fun insertOperation(str: String) {
        var text = tv_result.text
        var last: String = text[text.length - 1].toString()
        if(operations.contains(last)){
            tv_result.text = text.dropLast(1)
        }
        plus(str)
    }
}