package com.naumov.appmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.naumov.appmvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),MainView {

    var vb: ActivityMainBinding?=null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener{
            presenter.counterClickButton1()
        }
        vb?.btnCounter2?.setOnClickListener {
            presenter.counterClickButton2()
        }
        vb?.btnCounter3?.setOnClickListener{
            presenter.counterClickButton3()
        }


    }

    override fun setButtonOneText(text: String) {
        vb?.btnCounter1?.text = text
    }

    override fun setButtonTwoText(text: String) {
        vb?.btnCounter2?.text = text
    }

    override fun setButtonThreeText(text: String) {
        vb?.btnCounter3?.text = text
    }
}