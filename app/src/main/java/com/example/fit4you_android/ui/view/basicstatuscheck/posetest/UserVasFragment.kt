package com.example.fit4you_android.ui.view.basicstatuscheck.posetest

import android.widget.RatingBar
import androidx.fragment.app.activityViewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.FragmentUserVasBinding
import com.example.fit4you_android.ui.base.BaseFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.BaseBasicQuestionViewModel

class UserVasFragment : BaseFragment<FragmentUserVasBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_vas
    override val viewModel: BaseBasicQuestionViewModel by activityViewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        // 7, 10, 13, 16, 19, 22
        val bundle = arguments?.getInt("progress")
        when (bundle) {
            7 -> {
                binding.ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                        viewModel.updateVas1(rating.toInt())
                    }
            }
            10 -> {
                binding.ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                        viewModel.updateVas2(rating.toInt())
                    }
            }
            13 -> {
                binding.ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                        viewModel.updateVas3(rating.toInt())
                    }
            }
            16 -> {
                binding.ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                        viewModel.updateVas4(rating.toInt())
                    }
            }
            19 -> {
                binding.ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                        viewModel.updateVas5(rating.toInt())
                    }
            }
            22 -> {
                binding.ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { _, rating, _ ->
                        viewModel.updateVas6(rating.toInt())
                    }
            }
        }
    }
}