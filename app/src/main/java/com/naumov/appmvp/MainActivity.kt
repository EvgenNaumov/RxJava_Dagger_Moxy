package com.naumov.appmvp

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.naumov.appmvp.databinding.ActivityMainBinding
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.recycler.UsersAdapter
import com.naumov.appmvp.repository.impl.CountersRepository
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var vb: ActivityMainBinding
    private val presenter by moxyPresenter { MainPresenter(GithubRepositoryImpl()) }
    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)

        with(vb) {
            githubUserRecycler.layoutManager = LinearLayoutManager(this@MainActivity)
            githubUserRecycler.setItemViewCacheSize(1)
            githubUserRecycler.adapter = adapter
        }
    }

    override fun initList(list: List<GithubUserEntity>) {
        adapter.users = list

    }

    override fun updateList(list: List<GithubUserEntity>) {

    }

}