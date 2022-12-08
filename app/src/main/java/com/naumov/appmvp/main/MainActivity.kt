package com.naumov.appmvp.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.naumov.appmvp.App
import com.naumov.appmvp.R
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(),MainView {

    private val navigator = AppNavigator(this, R.id.container)
    private lateinit var vb: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        App.instance.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { currentFragment ->
            if (currentFragment is BackPressedLisener && currentFragment.onBackPressed())
                return
        }
        presenter.onBackPressed()
    }

}