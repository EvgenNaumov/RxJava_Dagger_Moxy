package com.naumov.appmvp.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.naumov.appmvp.App
import com.naumov.appmvp.KEY_POS_LIST
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.FragmentUserCardBinding
import com.naumov.appmvp.model.GithubUserEntity
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserCardFragment(private val userEntity:GithubUserEntity) : MvpAppCompatFragment(),UserCardView, BackPressedLisener {

    companion object {
        fun newInstance(i:GithubUserEntity): UserCardFragment {
            return UserCardFragment(i)
        }
    }

//     private var user:GithubUserEntity? = null
//        get() = userEntity
//        private set(value)  {
//            field = userEntity
//        }

    private var _binding: FragmentUserCardBinding? = null
    private val binding:FragmentUserCardBinding get() = _binding!!

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
        _binding = FragmentUserCardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun initView() {
        binding.apply {
            loginTextViewFragmentCarduser.text = userEntity.login
        }
    }

    override fun updateView() {
    }

    override fun onBackPressed() = presenter.onBackPress()
}