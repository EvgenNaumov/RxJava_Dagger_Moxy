package com.naumov.appmvp.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.naumov.appmvp.App
import com.naumov.appmvp.KEY_POS_LIST
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.FragmentUserCardBinding
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserCardFragment() : MvpAppCompatFragment(),UserCardView, BackPressedLisener {

    companion object {
        fun newInstance(): UserCardFragment {
            return UserCardFragment()
        }
    }

    private var _binding: FragmentUserCardBinding? = null
    private val binding:FragmentUserCardBinding get() = _binding!!
    private var userEntity:GithubUserEntity? = null

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

        if (savedInstanceState!=null) run {
             userEntity = savedInstanceState.getParcelable(
                 KEY_POS_LIST
             )!!
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun initView() {
        Toast.makeText(requireContext(),"aaa",Toast.LENGTH_SHORT).show()
//        binding.apply {
//            //loginTextViewFragmentCarduser.text = userEntity.login
//        }
    }

    override fun updateView() {
    }

    override fun onBackPressed() = presenter.onBackPress()
}