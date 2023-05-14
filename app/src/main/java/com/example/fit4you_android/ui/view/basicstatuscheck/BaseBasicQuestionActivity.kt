package com.example.fit4you_android.ui.view.basicstatuscheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.fit4you_android.ui.view.MainActivity
import androidx.lifecycle.Observer
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivityBaseBasicQuestionBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.notice.RomNoticeFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.PhysicalFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.RomExFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.UserRomFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.UserVasFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.UserHistoryFragment
import com.example.fit4you_android.ui.view.basicstatuscheck.questions.UserPainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BaseBasicQuestionActivity :
    BaseActivity<ActivityBaseBasicQuestionBinding, BaseBasicQuestionViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_base_basic_question
    override val viewModel: BaseBasicQuestionViewModel by viewModels()

    // 사용자의 통증 정보를 파악할 수 있는 샘플 영상
    private val sample_video = arrayListOf(
        R.raw.rom_ex_video,
        R.raw.rom_ex_video2,
        R.raw.rom_ex_video3,
        R.raw.rom_ex_video4,
        R.raw.rom_ex_video5
    )

    override fun initBeforeBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val physicalFrag = PhysicalFragment()
        val historyFrag = UserHistoryFragment()
        val painFrag = UserPainFragment()
        val noticeFrag = RomNoticeFragment()
        val romExFrag = RomExFragment()
        val userRomFrag = UserRomFragment()
        val vasFrag = UserVasFragment()

        val baseVideoUri =
            Uri.parse("android.resource://" + this.packageName + "/")
        val bundle = Bundle()

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
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, historyFrag)
                        .commit()
                    binding.pbBasic.setProgress(3)
                }
                3 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, noticeFrag)
                        .commit()
                    binding.pbBasic.setProgress(4)
                }
                4 -> {
                    viewModel.setVideo(baseVideoUri.toString(), sample_video[0])
                    bundle.putInt("idx", 0)
                    viewModel.videoUri.observe(this, Observer {
                        bundle.putString("file0", it.toString())
                    })
                    romExFrag.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(5)
                }
                5 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(6)
                }
                6 -> {
                    // 1번째 VAS 체크
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, vasFrag)
                        .commit()
                    binding.pbBasic.setProgress(7)
                }
                7 -> {
                    viewModel.setVideo(baseVideoUri.toString(), sample_video[1])
                    bundle.putInt("idx", 1)
                    viewModel.videoUri.observe(this, Observer {
                        bundle.putString("file1", it.toString())
                    })
                    romExFrag.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(8)
                }
                8 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(9)
                }
                9 -> {
                    // 2번째 VAS 체크
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, vasFrag)
                        .commit()
                    binding.pbBasic.setProgress(10)
                }
                10 -> {
                    viewModel.setVideo(baseVideoUri.toString(), sample_video[2])
                    bundle.putInt("idx", 2)
                    viewModel.videoUri.observe(this, Observer {
                        bundle.putString("file2", it.toString())
                    })
                    romExFrag.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(11)
                }
                11 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(12)
                }
                12 -> {
                    // 3번째 VAS 체크
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, vasFrag)
                        .commit()
                    binding.pbBasic.setProgress(13)
                }
                13 -> {
                    viewModel.setVideo(baseVideoUri.toString(), sample_video[3])
                    bundle.putInt("idx", 3)
                    viewModel.videoUri.observe(this, Observer {
                        bundle.putString("file3", it.toString())
                    })
                    romExFrag.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(14)
                }
                14 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(15)
                }
                15 -> {
                    // 4번째 VAS 체크
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, vasFrag)
                        .commit()
                    binding.pbBasic.setProgress(16)
                }
                16 -> {
                    viewModel.setVideo(baseVideoUri.toString(), sample_video[4])
                    bundle.putInt("idx", 4)
                    viewModel.videoUri.observe(this, Observer {
                        bundle.putString("file4", it.toString())
                    })
                    romExFrag.arguments = bundle

                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, romExFrag)
                        .commit()
                    binding.pbBasic.setProgress(17)
                }
                17 -> {
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, userRomFrag)
                        .commit()
                    binding.pbBasic.setProgress(18)
                }
                18 -> {
                    // 5번째 VAS 체크
                    supportFragmentManager.beginTransaction().replace(R.id.basic_frag, vasFrag)
                        .commit()
                    binding.pbBasic.setProgress(19)
                }
                else -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}