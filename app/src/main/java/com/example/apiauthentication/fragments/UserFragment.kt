package com.example.apiauthentication.fragments

import com.example.apiauthentication.databinding.FragmentUserBinding
import com.example.apiauthentication.viewmodels.UserViewModel


class UserFragment : BaseFragment<FragmentUserBinding, UserViewModel>(
    FragmentUserBinding::inflate,
    UserViewModel::class.java
) {
    override fun start() {
        init()
    }

    private fun init(){
        val email = arguments?.getString("email")!!
        val password = arguments?.getString("password")!!
        binding.tvEmail.text = email
        binding.tvPassword.text = password
    }

}