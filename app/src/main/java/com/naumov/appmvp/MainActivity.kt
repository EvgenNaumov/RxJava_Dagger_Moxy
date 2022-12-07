package com.naumov.appmvp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.naumov.appmvp.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter

class MainActivity : MvpAppCompatActivity(),MainView {

    private  lateinit var vb: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.btnCounterOne.setOnClickListener{
            presenter.counterClickButtonOne()
        }
        vb.btnCounterTwo.setOnClickListener {
            presenter.counterClickButtonTwo()
        }
        vb.btnCounterThree.setOnClickListener{
            presenter.counterClickButtonThree()
        }


    }

//    private fun initPresenter() {
//        presenter = MainPresenter(CountersModel())
//    }

    override fun setButtonOneText(text: String) {
        vb.btnCounterOne.text = text
    }

    override fun setButtonTwoText(text: String) {
        vb.btnCounterTwo.text = text
    }

    override fun setButtonThreeText(text: String) {
        vb.btnCounterThree.text = text
    }
}