package com.example.fit4you_android.ui.view.basicstatuscheck.questions

import androidx.fragment.app.activityViewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentUserPainBinding
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.BaseBasicQuestionViewModel

class UserPainFragment : BaseFragment<FragmentUserPainBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_pain
    override val viewModel: BaseBasicQuestionViewModel by activityViewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {

    }
}