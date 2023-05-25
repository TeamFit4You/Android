package com.example.fit4you_android.ui.view.basicstatuscheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.ui.res.stringArrayResource
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.fit4you_android.ui.view.MainActivity
import androidx.lifecycle.Observer
import com.example.fit4you_android.R
import com.example.fit4you_android.data.Resource
import com.example.fit4you_android.data.dto.response.BaseQuestionRes
import com.example.fit4you_android.databinding.ActivityBaseBasicQuestionBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.notice.RomNoticeFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.PhysicalFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.RomExFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.UserRomFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.UserVasFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.UserHistoryFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.UserPainFragment
import com.example.fit4you_android.util.*
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseBasicQuestionActivity :
    BaseActivity<ActivityBaseBasicQuestionBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_base_basic_question
    override val viewModel: BaseBasicQuestionViewModel by viewModels()

    // 사용자의 통증 정보를 파악할 수 있는 샘플 영상
    private val sample_video = arrayListOf(
        R.raw.romex1,
        R.raw.romex2,
        R.raw.romex3,
        R.raw.romex4,
        R.raw.romex5,
        R.raw.romex6,
    )

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {
        observeViewModel()
    }

    override fun initView() {
        val email = intent.getStringExtra("email")
        Log.d("email", "$email")
        viewModel.setEmail(email!!)
        val physicalFrag = PhysicalFragment()
        val historyFrag = UserHistoryFragment()
        val painFrag = UserPainFragment()
        val noticeFrag = RomNoticeFragment()
        val romExFrag = RomExFragment()
        val userRomFrag = UserRomFragment()
        val vasFrag = UserVasFragment()

        val baseVideoUri =
            Uri.parse("android.resource://" + this.packageName + "/")

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.basic_frag, physicalFrag)
            .commit()

        binding.btnFragNext.setOnClickListener {
            Log.d("progress", "${binding.pbBasic.progress}")
            when (binding.pbBasic.progress) {
                1 -> updateFragment(painFrag, 2)
                2 -> updateFragment(historyFrag, 3)
                3 -> updateFragment(noticeFrag, 4)
                // 4, 7, 10, 13, 16, 19
                in 4..21 step 3 -> updateRomFragment(
                    (binding.pbBasic.progress - 3) / 3,
                    romExFrag,
                    baseVideoUri.toString()
                )
                // 5, 8, 11, 14, 17, 20
                in 5..22 step 3 -> updateFragment(userRomFrag, binding.pbBasic.progress + 1)
                // 6, 9, 12, 15, 18, 21
                in 6..22 step 3 -> updateFragment(vasFrag, binding.pbBasic.progress + 1)
                else -> {
//                    viewModel.setValue()
                    Log.d("basequesion", viewModel.baseQuestionReq.value.toString())
                    val emailR = viewModel.baseQuestionReq.value?.email
                    val hist = viewModel.baseQuestionReq.value?.hist
                    val vas1 = viewModel.baseQuestionReq.value?.neck
                    val vas2 = viewModel.baseQuestionReq.value?.shoulder
                    val vas3 = viewModel.baseQuestionReq.value?.lumbar
                    val vas4 = viewModel.baseQuestionReq.value?.wrist
                    val vas5 = viewModel.baseQuestionReq.value?.elbow
                    val vas6 = viewModel.baseQuestionReq.value?.knee
                    viewModel.postQuestion(
                        emailR!!,
                        hist!!,
                        vas2!!,
                        vas3!!,
                        vas4!!,
                        vas5!!,
                        vas6!!,
                        vas1!!
                    )
                }
            }
        }
    }

    private fun observeViewModel() {
        observe(viewModel.survey, ::handleSurveyResult)
        observeToast(viewModel.showToast)
    }

    private fun observeToast(event: LiveData<SingleEvent<Any>>) {
        binding.root.showToast(this, event, Snackbar.LENGTH_LONG)
    }

    private fun handleSurveyResult(status: Resource<BaseQuestionRes>) {
        Log.d("status","$status")
        when (status) {
            is Resource.Loading -> {
                binding.lottieQuestion.toVisible()
                binding.lottieQuestion.playAnimation()
            }
            is Resource.Success -> status.data.let {
                binding.lottieQuestion.pauseAnimation()
                binding.lottieQuestion.toGone()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            is Resource.Error -> {
                binding.lottieQuestion.pauseAnimation()
                binding.lottieQuestion.toGone()
                viewModel.showToastMessage(status.message)
            }
        }
    }

    private fun updateFragment(frag: Fragment, progress: Int) {
        val bundle = Bundle()
        bundle.putInt("progress", progress)
        frag.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.basic_frag, frag).commit()
        binding.pbBasic.setProgress(progress)
    }

    private fun updateRomFragment(
        index: Int,
        romExFrag: Fragment,
        baseVideoUri: String
    ) {
        val title = arrayListOf(
            resources.getString(R.string.notice_2_1_1),
            resources.getString(R.string.notice_2_1_2),
            resources.getString(R.string.notice_2_2_1),
            resources.getString(R.string.notice_2_2_2),
            resources.getString(R.string.notice_2_3_1),
            resources.getString(R.string.notice_2_3_2),
        )
        val bundle = Bundle()
        viewModel.setVideo(baseVideoUri, sample_video[index])
        bundle.putInt("idx", index)
        bundle.putString("rom_title$index", title[index])
        viewModel.videoUri.observe(this, Observer {
            bundle.putString("file$index", it.toString())
        })
        romExFrag.arguments = bundle
        Log.d("idx", "$index")
        if (index == 0) {
            supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag).commit()
            binding.pbBasic.setProgress(5)
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag).commit()
            binding.pbBasic.setProgress(index * 3 + 5)
        }
    }
}