package com.naumov.appmvp.user.repodetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naumov.appmvp.App
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.FragmentDetailsRepoBinding
import com.naumov.appmvp.user.userdetails.UserCardFragment
import com.naumov.appmvp.user.userdetails.UserCardPresenter
import moxy.MvpAppCompatFragment
import moxy.MvpView
import moxy.ktx.moxyPresenter

class RepoDetailsFragment(private val forkUrl:String) :
    MvpAppCompatFragment(), RepoDetailsView, BackPressedLisener {

    private val presenter: RepoDetailsPresenter by moxyPresenter {
        RepoDetailsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    companion object {
        const val KEY_URL_FORK = "key_url_fork"
        fun newInstance(forkUrl:String): RepoDetailsFragment {
            return RepoDetailsFragment(forkUrl).apply {
                arguments = Bundle().apply {
                    putString(KEY_URL_FORK, forkUrl)
                }
            }
        }
    }

    override fun updateQuantityFork(fork: Int) {
        binding.quantityForksTextView.text = fork.toString()
    }

    override fun initVeiw(login: String, nameRepo: String) {

    }


    private var _binding: FragmentDetailsRepoBinding? = null
    private val binding: FragmentDetailsRepoBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_URL_FORK)?.let {
            presenter.loadDetailRepo( it)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsRepoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed(): Boolean = presenter.onBackPress()
}