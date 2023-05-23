package com.example.fit4you_android.ui.view.today.start

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.CountDownTimer
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.fit4you_android.R
import com.example.fit4you_android.databinding.ActivitySelfBinding
import com.example.fit4you_android.ui.base.BaseActivity
import com.example.fit4you_android.ui.view.basicstatuscheck.posetest.UserRomFragment
import com.example.fit4you_android.util.toGone
import com.example.fit4you_android.util.toVisible
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class SelfActivity : BaseActivity<ActivitySelfBinding, SelfViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_self
    override val viewModel: SelfViewModel by viewModels()

    private var videoCapture: VideoCapture<Recorder>? = null
    private var recording: Recording? = null
    private lateinit var cameraExecutor: ExecutorService
    private var timer: CountDownTimer? = null

    override fun initBeforeBinding() {
        // 카메라 권한 확인. 권한이 모두 부여되었으면 카메라 시작, 그렇지 않다면 필요한 권한 요청.
        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
    }

    override fun initAfterBinding() {

    }

    override fun initView() {
        val bodyPart = intent.getStringExtra("key")
        binding.tbSelfPart.text = bodyPart

        binding.btnSelfStart.setOnClickListener { videoStartOrStop() }
        cameraExecutor = Executors.newSingleThreadExecutor()

//        binding.btnSelfPrev.setOnClickListener {
//            val intent = Intent(this, ExampleActivity::class.java)
//            startActivity(intent)
//            finish()
//        }

        binding.btnSelfNext.setOnClickListener {

        }
    }

    private fun videoStartOrStop() {
        if (recording != null) {
            stopVideo()
            binding.tvTodayTimer.toVisible()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(5999, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                // This is called every interval (1 second in this case)
                // Display the remaining time (in seconds) to the user
                val secondsRemaining = millisUntilFinished / 1000
                binding.tvTodayTimer.text = secondsRemaining.toString()
            }

            override fun onFinish() {
                binding.tvTodayTimer.toGone()
                startVideoCapture()
            }
        }
        // 타이머 시작
        timer?.start()
    }

    private fun stopVideo() {
        timer?.cancel()
        recording?.stop()
        recording = null
    }

    // 비디오 캡쳐 메소드
    private fun startVideoCapture() {
        // 먼저 videoCapture 인스턴스가 존재하는지 확인. 존재한다면 비디오캡쳐 시작.
        val videoCapture = this.videoCapture ?: return

        binding.btnSelfStart.isEnabled = false

        // 캡쳐중인 경우 캡쳐를 중단
        val curRecording = recording
        if (curRecording != null) {
            curRecording.stop()
            recording = null
            timer?.cancel()
            return
        }

        // 캡쳐를 시작할 때 현재 시간을 기반으로 파일명을 생성하고 MediaStore에 저장할 정보 설정. 그 후 VideoRecordEvent의 상태에 따라 UI 업데이트
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video")
            }
        }

        val mediaStoreOutputOptions = MediaStoreOutputOptions
            .Builder(this.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            .setContentValues(contentValues)
            .build()

        recording = videoCapture.output
            .prepareRecording(this, mediaStoreOutputOptions)
            .apply {
                if (PermissionChecker.checkSelfPermission(
                        this@SelfActivity,
                        Manifest.permission.RECORD_AUDIO
                    ) ==
                    PermissionChecker.PERMISSION_GRANTED
                ) {
                    withAudioEnabled()
                }
            }
            .start(ContextCompat.getMainExecutor(this)) { recordEvent ->
                when (recordEvent) {
                    is VideoRecordEvent.Start -> {
                        binding.btnSelfStart.apply {
                            text = getString(R.string.stop_capture)
                            isEnabled = true
                        }
                    }
                    is VideoRecordEvent.Finalize -> {
                        if (!recordEvent.hasError()) {
                            val msg = "영상촬영 완료! 저장 경로: " +
                                    "${recordEvent.outputResults.outputUri}"
                            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
                            Log.d(TAG, msg)
                        } else {
                            recording?.close()
                            recording = null
                            Log.e(
                                TAG, "Video capture ends with error: " +
                                        "${recordEvent.error}"
                            )
                        }
                        binding.btnSelfStart.apply {
                            text = getString(R.string.start_capture)
                            isEnabled = true
                        }
                    }
                }
            }
    }

    // 카메라 시작하는 메서드.
    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // 카메라 Provider를 얻고
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            // Preview를 설정하고
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            // 비디오 캡쳐를 위한 Recorder를 설정
            val recorder = Recorder.Builder()
                .setQualitySelector(
                    QualitySelector.from(
                        Quality.SD,
                        FallbackStrategy.lowerQualityThan(Quality.SD)
                    )
                )
                .build()
            videoCapture = VideoCapture.withOutput(recorder)

            // 그 후 카메라를 선택하고 (전면카메라가 default)
            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            // use case를 바인딩
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    videoCapture
                )
                // 바인딩에 실패하면 로그 출력
            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }
        }, ContextCompat.getMainExecutor(this))
    }

    // 필요한 모든 권한이 부여되었는지 확인하는 메소드.
    // 만약 모든 권한이 부여되지 않았다면 false를 반환. 사용자가 앱에 필요한 권한을 부여했는지 확인하는데에 사용
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            this, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    // 상수와 필요한 권한을 정의.
    companion object {
        private const val TAG = "CameraXApp"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO
            ).apply {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
                    add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }.toTypedArray()
    }

    // 사용자가 권한 요청에 응답한 후 호출
    // 사용자가 필요한 권한을 모두 부여했다면 카메라를 시작하고 그렇지 않다면 Toast Message를 보여주고 앱 종료
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(
                    this,
                    "Permissions not granted by the user.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }
}