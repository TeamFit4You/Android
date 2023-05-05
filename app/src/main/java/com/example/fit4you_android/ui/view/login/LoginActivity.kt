package com.example.fit4you_android.ui.view.login

import android.content.Intent
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.error.EMAIL_OR_PASSWORD_ERROR
import com.example.fit4you_android.data.model.response.SignInRes
import com.example.fit4you_android.databinding.ActivityLoginBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.BaseBasicQuestionActivity
import com.example.fit4you_android.ui.view.signup.SignupActivity
import com.example.fit4you_android.util.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()


    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        initSignUpBtn()
        initSignInBtn()
    }

    override fun initBeforeBinding() {

    }

    private fun observeViewModel() {
        observe(viewModel.signInProcess, ::handleSignInResult)
        observeToast(viewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun handleSignInResult(status: Resource<SignInRes>) {
        when (status) {
            is Resource.Loading -> binding.pbLoginLoaderView.toVisible()
            is Resource.Success -> status.data.let {
                binding.pbLoginLoaderView.toGone()
                when (status.data.result) {
                    "SUCCESS" -> {
                        val intent = Intent(this, BaseBasicQuestionActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    "FAIL" -> viewModel.showToastMessage(EMAIL_OR_PASSWORD_ERROR)
                }
            }
            is Resource.Error -> {
                binding.pbLoginLoaderView.toGone()
                status.let {
                    viewModel.showToastMessage(it.message)
                }
            }
        }
    }

    private fun initSignUpBtn() {
        binding.tvSignup.setOnClickListener {
            val signUpIntent = Intent(this, SignupActivity::class.java)
            startActivity(signUpIntent)
        }
    }

    private fun initSignInBtn() {
        binding.btnDoLogin.setOnClickListener {
            postSignIn()
        }
    }

    private fun postSignIn() {
        viewModel.doSignIn(binding.etId.text.toString(), binding.etPassword.text.toString())
    }
}