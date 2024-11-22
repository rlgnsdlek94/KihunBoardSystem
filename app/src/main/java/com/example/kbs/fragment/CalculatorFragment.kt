package com.example.kbs.fragment

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kbs.R
import com.example.kbs.util.CalculateHelper

class CalculatorFragment : Fragment(){

    private lateinit var calculateHelper: CalculateHelper
    private var isDot = false
    private var isBracket = false
    private var isPreview = false

    private lateinit var textView: TextView
    private lateinit var textView2: TextView

    private var size = 0
    private var result = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.calculator_fragment, container, false)
        calculateHelper = CalculateHelper()
        setButton(view)
        setTextView(view)
        return view
    }

    private fun setButton(v: View) {
        val buttons = listOf(
            R.id.num0 to "0", R.id.num1 to "1", R.id.num2 to "2", R.id.num3 to "3",
            R.id.num4 to "4", R.id.num5 to "5", R.id.num6 to "6", R.id.num7 to "7",
            R.id.num8 to "8", R.id.num9 to "9"
        )

        buttons.forEach { (id, value) ->
            v.findViewById<Button>(id).setOnClickListener { onNumClick(value) }
        }

        val marks = mapOf(
            R.id.add to " + ", R.id.sub to " - ", R.id.mul to " * ",
            R.id.div to " / ", R.id.percent to " % "
        )

        marks.forEach { (id, value) ->
            v.findViewById<Button>(id).setOnClickListener { onMarkClick(value) }
        }

        v.findViewById<Button>(R.id.clear).setOnClickListener { onClearClick() }
        v.findViewById<Button>(R.id.bracket).setOnClickListener { onBracketClick() }
        v.findViewById<Button>(R.id.back).setOnClickListener { onBackClick() }
        v.findViewById<Button>(R.id.dot).setOnClickListener { onDotClick() }
        v.findViewById<Button>(R.id.equal).setOnClickListener { onEqualClick() }
    }

    private fun onNumClick(value: String) {
        textView.append(value)
        preview()
    }

    private fun onMarkClick(value: String) {
        textView.append(value)
        isPreview = true
    }

    private fun onClearClick() {
        textView.text = ""
        textView2.text = ""
        calculateHelper = CalculateHelper()
        isPreview = false
    }

    private fun onBracketClick() {
        textView.append(if (!isBracket) "( " else " )")
        isBracket = !isBracket
        isPreview = true
    }

    private fun onBackClick() {
        size = textView.text.length
        if (size > 0) {
            textView.text = textView.text.substring(0, size - 1)
        }

        if (size > 1 && calculateHelper.checkNumber(textView.text.toString().last().toString())) {
            preview()
        } else {
            isPreview = false
            textView2.text = ""
        }
    }

    private fun onDotClick() {
        textView.append(".")
        isDot = true
    }

    private fun onEqualClick() {
        result = textView.text.toString()
        val r = calculateHelper.process(result)
        textView.text = if (isDot) r.toString() else r.toInt().toString()
        textView2.text = ""
        isDot = false
        isPreview = false
    }

    private fun preview() {
        if (isPreview) {
            result = textView.text.toString()
            val r = calculateHelper.process(result)
            textView2.text = if (isDot) r.toString() else r.toInt().toString()
        }
    }

    private fun setTextView(v: View) {
        textView = v.findViewById(R.id.first_textView)
        textView2 = v.findViewById(R.id.second_textView)
    }

}