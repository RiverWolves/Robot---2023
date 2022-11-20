package org.firstinspires.ftc.teamcode.stef.resurse.tag;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.mina.camera.AprilTagDetectorPipeline;
import org.firstinspires.ftc.teamcode.mina.camera.ConDetectorPipeline;
import org.firstinspires.ftc.teamcode.mina.camera.RWOpenCV;
import org.firstinspires.ftc.teamcode.mina.events.RWEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.ConEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.RecogEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.TagEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;
import org.firstinspires.ftc.teamcode.stef.resurse.SHardware;
import org.opencv.core.Rect;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.util.List;

public class Tag  {

    //APRILTAG
    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    private static double fx = 578.272;
    private static double fy = 578.272;
    private static double cx = 402.145;
    private static double cy = 221.506;

    // UNITS ARE METERS
    private static double tagsize = 0.166;

    private static int STANGA = 1,
                       DREAPTA = 3,
                       MIJLOC = 2;


    private static int numFramesWithoutDetection = 0;

    private static final float DECIMATION_HIGH = 3;
    private static final float DECIMATION_LOW = 2;
    private static final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    private static final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;
    //APRILTAG
    
    public static AprilTagDetectionPipeline aprilTagDetectionPipeline;

    public static OpenCvCamera camera;

    public static int tagOfInterest;
    
    
    
    public static void init(){
        
        camera = SHardware.camera;
        
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
                Telemetrie.addTel(Telemetrie.TelType.INFO, "Camera OPENCV", "s-a deschis");
            }

            @Override
            public void onError(int errorCode)
            {
                Telemetrie.addTel(Telemetrie.TelType.ERR, "Camera OPENCV", "Nu s-a putut deschide");
            }
        });
    }

    public static void getTag(OpMode opMode){
        ArrayList<AprilTagDetection> detections = aprilTagDetectionPipeline.getDetectionsUpdate();

        if(detections.size() != 0) {
            boolean tagFound = false;

            for (AprilTagDetection tag : detections) {
                if (tag.id == STANGA || tag.id == MIJLOC || tag.id == DREAPTA) {
                    tagOfInterest = tag.id;
                    tagFound = true;
                    break;
                }
            }

            opMode.telemetry.addData("tag: ", tagOfInterest);
            opMode.telemetry.update();
        }
    }
    
}
