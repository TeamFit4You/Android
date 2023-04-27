package com.example.fit4you_android.ui.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityLoginBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.BaseBasicQuestionActivity

class LoginActivity : BaseActivity<ActivityLoginBinding,LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        binding.btnDoLogin.setOnClickListener {
            val intent = Intent(this,BaseBasicQuestionActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}