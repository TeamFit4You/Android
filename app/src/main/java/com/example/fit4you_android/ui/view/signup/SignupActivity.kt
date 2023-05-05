package com.example.fit4you_android.ui.view.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivitySignupBinding
import com.example.fit4you_android.ui.base.BaseActivity

class SignupActivity : BaseActivity<ActivitySignupBinding, SignupViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_signup
    override val viewModel: SignupViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}