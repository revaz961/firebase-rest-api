package com.example.apiauthentication.fragments

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.apiauthentication.R
import com.example.apiauthentication.databinding.FragmentSignUpBinding
import com.example.apiauthentication.extension.snackBar
import com.example.apiauthentication.extension.visible
import com.example.apiauthentication.network.ResultHandler
import com.example.apiauthentication.viewmodels.SignUpViewModel
import com.google.android.material.snackbar.Snackbar

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>(
    FragmentSignUpBinding::inflate,
    SignUpViewModel::class.java
) {
    override fun start() {
        init()
        observes()
    }

    private fun init() {
        binding.btnSignUp.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank())
                Snackbar.make(requireView(), "Enter correct value", Snackbar.LENGTH_LONG).show()
            else
                viewModel.signUp(email, password)
        }

        binding.btnSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun observes() {
        viewModel.signUpLiveData.observe(this, {
            when (it) {
                is ResultHandler.Success -> {
                    findNavController().navigate(
                        R.id.action_signUpFragment_to_signInFragment,
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