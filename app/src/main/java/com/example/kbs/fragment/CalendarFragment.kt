package com.example.kbs.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kbs.R

class CalendarFragment : Fragment() {

    private var fname: String? = null
    private var str: String? = null
    private lateinit var calendarView: CalendarView
    private lateinit var chaBtn: Button
    private lateinit var delBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var diaryTextView: TextView
    private lateinit var textView2: TextView
    private lateinit var textView3: TextView
    private lateinit var contextEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.calendar_fragment, container, false)

        // View 초기화
        calendarView = view.findViewById(R.id.calendarView)
        diaryTextView = view.findViewById(R.id.diaryTextView)
        saveBtn = view.findViewById(R.id.save_Btn)
        delBtn = view.findViewById(R.id.del_Btn)
        chaBtn = view.findViewById(R.id.cha_Btn)
        textView2 = view.findViewById(R.id.textView2)
        textView3 = view.findViewById(R.id.textView3)
        contextEditText = view.findViewById(R.id.contextEditText)

        val name = "Developer"
        val userID = ""

        textView3.text = "달력 일기장"

        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            diaryTextView.visibility = View.VISIBLE
            saveBtn.visibility = View.VISIBLE
            contextEditText.visibility = View.VISIBLE
            textView2.visibility = View.INVISIBLE
            chaBtn.visibility = View.INVISIBLE
            delBtn.visibility = View.INVISIBLE

            diaryTextView.text = String.format("%d / %d / %d", year, month + 1, dayOfMonth)
            contextEditText.setText("")
            checkDay(year, month, dayOfMonth, userID)
        }

        saveBtn.setOnClickListener {
            saveDiary(fname)
            str = contextEditText.text.toString()
            textView2.text = str
            saveBtn.visibility = View.INVISIBLE
            chaBtn.visibility = View.VISIBLE
            delBtn.visibility = View.VISIBLE
            contextEditText.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE
        }

        return view
    }

    private fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        fname = "$userID$cYear-${cMonth + 1}-$cDay.txt" // 저장할 파일 이름 설정
        try {
            val fis = requireContext().openFileInput(fname)
            val fileData = fis.readBytes()
            fis.close()

            str = String(fileData)

            contextEditText.visibility = View.INVISIBLE
            textView2.visibility = View.VISIBLE
            textView2.text = str

            saveBtn.visibility = View.INVISIBLE
            chaBtn.visibility = View.VISIBLE
            delBtn.visibility = View.VISIBLE

            chaBtn.setOnClickListener {
                contextEditText.visibility = View.VISIBLE
                textView2.visibility = View.INVISIBLE
                contextEditText.setText(str)

                saveBtn.visibility = View.VISIBLE
                chaBtn.visibility = View.INVISIBLE
                delBtn.visibility = View.INVISIBLE
                textView2.text = contextEditText.text
            }

            delBtn.setOnClickListener {
                textView2.visibility = View.INVISIBLE
                contextEditText.setText("")
                contextEditText.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                chaBtn.visibility = View.INVISIBLE
                delBtn.visibility = View.INVISIBLE
                removeDiary(fname!!)
            }

            if (textView2.text.isNullOrEmpty()) {
                textView2.visibility = View.INVISIBLE
                diaryTextView.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                chaBtn.visibility = View.INVISIBLE
                delBtn.visibility = View.INVISIBLE
                contextEditText.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun removeDiary(fileName: String) {
        try {
            requireContext().openFileOutput(fileName, Context.MODE_PRIVATE).use {
                it.write("".toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun saveDiary(fileName: String?) {
        try {
            fileName?.let {
                requireContext().openFileOutput(it, Context.MODE_PRIVATE).use { fos ->
                    val content = contextEditText.text.toString()
                    fos.write(content.toByteArray())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
