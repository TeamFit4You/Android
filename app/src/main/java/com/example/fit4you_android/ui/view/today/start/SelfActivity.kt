package com.example.fit4you_android.ui.view.today.start

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
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

        binding.btnSelfNext.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun videoStartOrStop() {
        if (recording != null) {    // 녹화중이면 stop
//            stopVideo()
            return
        } else {        // 아니면 start
            startTimer()
        }
    }

    private var recordingCount = 0
    private var mediaPlayer: MediaPlayer? = null

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
        mediaPlayer?.stop()
        recording = null
        binding.tvTodayTimer.toVisible()
        recordingCount = 0
    }

    // 비디오 캡쳐 메소드
    private fun startVideoCapture() {
        // 먼저 videoCapture 인스턴스가 존재하는지 확인. 존재한다면 비디오캡쳐 시작.
        val videoCapture = this.videoCapture ?: return

        binding.btnSelfStart.isEnabled = false

        // 녹화가 진행 중이라면 녹화를 중지하고, 그렇지 않다면 새로운 녹화를 시작
        val curRecording = recording
        if (curRecording != null) {
            curRecording.stop()
            recording = null
            timer?.cancel()
            return
        }

        // 캡쳐를 시작할 때 현재 시간을 기반으로 파일명을 생성하고 MediaStore에 저장할 정보 설정. 그 후 VideoRecordEvent의 상태에 따라 UI 업데이트
        val name = SimpleDateFormat(FILENAME_FORMAT, Locale.KOREA)
            .format(System.currentTimeMillis())

        // Android에서 데이터베이스에 데이터를 삽입할 때 사용하는 클래스
        // 비디오 파일에 대한 메타데이터를 설정
        val contentValues = ContentValues().apply {
            // 파일의 표시 이름을 설정
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            // "video/mp4"를 넣어 파일의 MIME 타입이 MP4 비디오임을 명시
            put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
            // Android 버전이 P 버전보다 높은지
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                // 파일의 상대 저장 경로를 설정
                put(MediaStore.Video.Media.RELATIVE_PATH, "Movies/CameraX-Video")
            }
        }

        // 이 객체는 녹화된 비디오의 출력 설정에 사용
        val mediaStoreOutputOptions = MediaStoreOutputOptions
            // MediaStore.Video.Media.EXTERNAL_CONTENT_URI: 저장할 위치로 외부 저장소의 비디오 디렉터리를 가리킴
            .Builder(this.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
            // 비디오 파일에 대한 메타데이터를 설정
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
                Log.d("recordEvent", "$recording")
                when (recordEvent) {
                    is VideoRecordEvent.Start -> {
                        binding.btnSelfStart.apply {
                            text = getString(R.string.stop_capture)
                            isEnabled = true
                        }
                        // 녹화 시작 시 카운트 증가
                        recordingCount++
                        // 녹화 시작 9초 후 다음 녹화를 준비
                        if (recordingCount <= 9) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                recording?.stop()
                                mediaPlayer =
                                    MediaPlayer.create(this@SelfActivity, R.raw.cnt_sound)
                                mediaPlayer?.start()
                                // TODO: Insert code here to send video file to the server
                                // TODO: Also handle showing progress bar during video file upload
                                binding.pbSelfLoaderView.toVisible()
                                startVideoCapture() // Start capturing again after 9 seconds of video has been sent to the server
                            }, 9000)

                            // 각 세트가 끝날 때마다 하단에 textview 표시
                            if (recordingCount == 3) {
                                binding.tvSet1.toVisible()
                            } else if (recordingCount == 6) {
                                binding.tvSet2.toVisible()
                            } else if (recordingCount == 9) {
                                binding.tvSet3.toVisible()
                            }
                        }
                    }
                    is VideoRecordEvent.Finalize -> {
                        // 3번의 녹화가 완료되면 녹화 중지
                        if (recordingCount >= 10) {
                            stopVideo()
                        } else if (!recordEvent.hasError()) {
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
        // 인스턴스 생성. 카메라 생명주기를 관리하며, 카메라를 사용하고 해제하는 데 필요한 리소스를 제공.
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        // 앱의 메인 실행기에서 비동기 작업을 실행하는 데 사용하는 Listener를 추가
        cameraProviderFuture.addListener(Runnable {
            // 카메라 Provider를 얻고
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            // Builder를 사용해 미리보기 설정을 생성하고, SurfaceProvider를 바인딩한다. 이렇게 하면 미리보기가 표시
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }
            // Builder를 사용해 레코더를 설정한다. 이 레코더는 비디오 캡처에 사용
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
            // 선택된 카메라와 레코더를 바인딩. 바인딩에 실패하면 에러 로그를 출력
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