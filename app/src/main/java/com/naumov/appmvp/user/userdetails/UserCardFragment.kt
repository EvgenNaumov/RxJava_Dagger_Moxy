package com.naumov.appmvp.user.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.naumov.appmvp.App
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.FragmentUserCardBinding
import com.naumov.appmvp.hideView
import com.naumov.appmvp.model.UserRepoEntity
import com.naumov.appmvp.showView
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserCardFragment(private val login: String, private val userId: Long) :
    MvpAppCompatFragment(),
    UserCardView, BackPressedLisener {

    companion object {
        const val KEY_PARAM_LOGIN = "login_user"
        const val KEY_PARAM_USERID = "user_id"
        fun newInstance(login: String, userId: Long): UserCardFragment {
            return UserCardFragment(login, userId).apply {
                arguments = Bundle().apply {
                    putString(KEY_PARAM_LOGIN, login)
                    putLong(KEY_PARAM_USERID, userId)
                }
            }
        }
    }

    private var listEmpty: Boolean = true

    private var _binding: FragmentUserCardBinding? = null
    private val binding: FragmentUserCardBinding get() = _binding!!
    private val adapter = UserDetailAdapter()

    private val presenter: UserCardPresenter by moxyPresenter {
        UserCardPresenter(
            App.instance.repoUser,
            App.instance.repoRepos,
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

        with(binding) {
            forksListRecycler.layoutManager = LinearLayoutManager(requireContext())
            forksListRecycler.adapter = adapter
        }

        if (adapter.listRepo.isNotEmpty()) {
            listEmpty = false
        }

        if (listEmpty) {
            arguments?.apply {
                presenter.loadUser(this.getString(KEY_PARAM_LOGIN)?.let { it } ?: "")
                presenter.loadRepoUser(
                    this.getString(KEY_PARAM_LOGIN)?.let { it } ?: "",
                    this.getLong(KEY_PARAM_USERID).let { it })
            }


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

    override fun initView(login: String) {
        binding.apply {
            titleLoginTextViewFragmentCarduser.text = login
        }
    }

    override fun updateList(repoList: List<UserRepoEntity>) {
        adapter.listRepo = repoList
    }

    override fun updateView() {
        binding.apply {
            titleLoginTextViewFragmentCarduser.text = ""
        }
    }

    override fun onBackPressed() = presenter.onBackPress()
}