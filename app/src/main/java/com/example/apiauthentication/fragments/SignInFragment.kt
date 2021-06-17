package com.example.apiauthentication.fragments

import android.util.Log.d
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.apiauthentication.R
import com.example.apiauthentication.UserSharedPreferences
import com.example.apiauthentication.databinding.FragmentSignInBinding
import com.example.apiauthentication.extension.snackBar
import com.example.apiauthentication.extension.visible
import com.example.apiauthentication.network.ResultHandler
import com.example.apiauthentication.viewmodels.SignInViewModel
import com.google.android.material.snackbar.Snackbar

class SignInFragment : BaseFragment<FragmentSignInBinding, SignInViewModel>(
    FragmentSignInBinding::inflate,
    SignInViewModel::class.java
) {
    override fun start() {
        init()
        observes()
    }

    private fun init() {

        val emailArg = arguments?.getString("email")
        val passwordArg = arguments?.getString("password")

        if(!emailArg.isNullOrBlank())
            binding.etEmail.setText(emailArg)

        if(!passwordArg.isNullOrBlank())
            binding.etPassword.setText(passwordArg)

        binding.btnSingIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank())
                requireView().snackBar("input correct info")
            else
                viewModel.signIn(email, password)
        }

        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun observes(){
        viewModel.signInLiveData.observe(this,{
            when(it){
                is ResultHandler.Success ->{
                    UserSharedPreferences.setToken(it.data?.idToken!!,it.data.refreshToken!!)
                    findNavController().navigate(
                        R.id.action_signInFragment_to_userFragment,
                        bundleOf(
                            "email" to binding.etEmail.text.toString(),
                            "password" to binding.etPassword.text.toString()
                        )
                    )
                }
                is ResultHandler.Error -> {
                    requireView().snackBar("something wrong!!!")
                }
                is ResultHandler.Loading -> {
                    binding.progress.visible(it.loading)
                }
            }

        })
    }

}