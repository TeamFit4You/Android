package com.example.fit4you_android.ui.view.basicstatuscheck

import androidx.activity.viewModels
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityBaseBasicQuestionBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.notice.RomNoticeFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.PhysicalFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.RomExFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.UserRomFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.UserPainFragment

class BaseBasicQuestionActivity :
    BaseActivity<ActivityBaseBasicQuestionBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_base_basic_question
    override val viewModel: BaseBasicQuestionViewModel by viewModels()

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val physicalFrag = PhysicalFragment()
        val painFrag = UserPainFragment()
        val noticeFrag = RomNoticeFragment()
        val romExFrag = RomExFragment()
        val userRomFrag = UserRomFragment()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.basic_frag, physicalFrag)
            .commit()

        binding.btnFragNext.setOnClickListener {
            when (binding.pbBasic.progress) {
                1 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, painFrag)
                        .commit()
                    binding.pbBasic.setProgress(2)
                }
                2 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, noticeFrag)
                        .commit()
                    binding.pbBasic.setProgress(3)
                }
                3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(4)
                }
                4 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(5)
                }
                5 -> {
                    // 1번째 VAS 체크
                }
                6 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(7)
                }
                7 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(8)
                }
                8 -> {
                    // 2번째 VAS 체크
                }
                9 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(10)
                }
                10 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(11)
                }
                11 -> {
                    // 3번째 VAS 체크
                }
                12 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(13)
                }
                13 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(14)
                }
                14 -> {
                    // 4번째 VAS 체크
                }
                15 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(16)
                }
                16 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(17)
                }
                17 -> {
                    // 5번째 VAS 체크
                }
            }
        }
    }
}