package com.naumov.appmvp.user

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.naumov.appmvp.App
import com.naumov.appmvp.core.BackPressedLisener
import com.naumov.appmvp.databinding.FragmentUserListBinding
import com.naumov.appmvp.model.GithubUserEntity
import com.naumov.appmvp.recycler.UsersAdapter
import com.naumov.appmvp.repository.impl.GithubRepositoryImpl
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackPressedLisener {

    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding get() = _binding!!
    private val adapter = UsersAdapter()

    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
        }
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(
            GithubRepositoryImpl(),
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
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.userListProgressbar.visibility = View.VISIBLE
        binding.fragmentUserRecycler.visibility = View.GONE

        with(binding) {
            fragmentUserRecycler.layoutManager = LinearLayoutManager(requireContext())
            fragmentUserRecycler.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun initView(usersList: List<GithubUserEntity>) {
        binding.userListProgressbar.visibility = View.GONE
        binding.fragmentUserRecycler.visibility = View.VISIBLE

        adapter.users = usersList
    }

    override fun updateView(usersList: List<GithubUserEntity>) {
    }

    override fun errorView(error: Throwable) {
        binding.userListProgressbar.visibility = View.GONE
        binding.fragmentUserRecycler.visibility = View.GONE

        Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() = presenter.onBackPress()

}