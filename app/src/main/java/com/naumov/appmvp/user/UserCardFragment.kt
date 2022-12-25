package com.naumov.appmvp.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.naumov.appmvp.App
import com.naumov.appmvp.KEY_POS_LIST
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.FragmentUserCardBinding
import com.naumov.appmvp.hideView
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.showView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserCardFragment(private val userEntity: GithubUserEntity) : MvpAppCompatFragment(),
    UserCardView, BackPressedLisener {

    companion object {
        const val KEY_PARAM_LOGIN = "login_user"
        fun newInstance(i: GithubUserEntity): UserCardFragment {
            return UserCardFragment(i).apply {
                arguments = Bundle().apply {
                    putString(KEY_PARAM_LOGIN, i.login)
                }
            }
        }
    }

    private var _binding: FragmentUserCardBinding? = null
    private val binding: FragmentUserCardBinding get() = _binding!!

    private val presenter: UserCardPresenter by moxyPresenter {
        UserCardPresenter(
            App.instance.router
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_PARAM_LOGIN)?.let {
            presenter.loadUser(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun showLoading() {
        with(binding) {
            progressBar.showView()
        }
    }

    override fun hideLoading() {
        with(binding) {
            progressBar.hideView()
        }
    }

    override fun initView() {
        binding.apply {
            loginTextViewFragmentCarduser.text = userEntity.login

        }
    }

    override fun updateView() {
        binding.apply {
            loginTextViewFragmentCarduser.text = userEntity.login
            userImage.load(userEntity.avatarUrl)
        }
    }

    override fun onBackPressed() = presenter.onBackPress()
}