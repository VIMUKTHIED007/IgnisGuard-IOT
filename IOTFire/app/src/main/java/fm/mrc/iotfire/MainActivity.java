package fm.mrc.iotfire;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private static final int PERMISSION_REQUEST_CODE = 1234;
    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int AUDIO_PERMISSION_CODE = 200;

    private SurfaceView cameraView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private boolean isRecording = false;
    private AudioRecord audioRecord;
    private boolean isListening = false;

    private ImageButton btnForward, btnBackward, btnLeft, btnRight;
    private MaterialButton btnSpeak, btnListen;
    private TextView notificationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();
        
        // Request permissions
        requestPermissions();
        
        // Setup camera
        setupCamera();
        
        // Setup controls
        setupControls();
    }

    private void initializeViews() {
        cameraView = findViewById(R.id.cameraView);
        surfaceHolder = cameraView.getHolder();
        surfaceHolder.addCallback(this);

        btnForward = findViewById(R.id.btnForward);
        btnBackward = findViewById(R.id.btnBackward);
        btnLeft = findViewById(R.id.btnLeft);
        btnRight = findViewById(R.id.btnRight);
        btnSpeak = findViewById(R.id.btnSpeak);
        btnListen = findViewById(R.id.btnListen);
        notificationText = findViewById(R.id.notificationText);
    }

    private void requestPermissions() {
        String[] permissions = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.INTERNET
        };

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) 
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
                break;
            }
        }
    }

    private void setupCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) 
            == PackageManager.PERMISSION_GRANTED) {
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(surfaceHolder);
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
                updateNotification("Camera initialization failed");
            }
        }
    }

    private void setupControls() {
        // Remote control buttons
        btnForward.setOnClickListener(v -> sendCommand("FORWARD"));
        btnBackward.setOnClickListener(v -> sendCommand("BACKWARD"));
        btnLeft.setOnClickListener(v -> sendCommand("LEFT"));
        btnRight.setOnClickListener(v -> sendCommand("RIGHT"));

        // Audio control buttons
        btnSpeak.setOnClickListener(v -> toggleSpeaking());
        btnListen.setOnClickListener(v -> toggleListening());
    }

    private void sendCommand(String command) {
        // TODO: Implement actual command sending to IoT device
        updateNotification("Sending command: " + command);
    }

    private void toggleSpeaking() {
        if (!isRecording) {
            startSpeaking();
        } else {
            stopSpeaking();
        }
    }

    private void toggleListening() {
        if (!isListening) {
            startListening();
        } else {
            stopListening();
        }
    }

    private void startSpeaking() {
        // TODO: Implement actual audio streaming
        isRecording = true;
        btnSpeak.setText("Stop Speaking");
        updateNotification("Speaking mode activated");
    }

    private void stopSpeaking() {
        isRecording = false;
        btnSpeak.setText("Speak");
        updateNotification("Speaking mode deactivated");
    }

    private void startListening() {
        // TODO: Implement actual audio recording
        isListening = true;
        btnListen.setText("Stop Listening");
        updateNotification("Listening mode activated");
    }

    private void stopListening() {
        isListening = false;
        btnListen.setText("Listen");
        updateNotification("Listening mode deactivated");
    }

    private void updateNotification(String message) {
        notificationText.setText(message);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setupCamera();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (camera != null) {
            try {
                camera.stopPreview();
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (camera != null) {
            camera.release();
            camera = null;
        }
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
        }
    }
} 