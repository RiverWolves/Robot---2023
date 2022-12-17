package org.firstinspires.ftc.teamcode.mina.camera;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.AprilEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.ConEvent;
import org.firstinspires.ftc.teamcode.mina.events.opencv.TagEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;
import org.opencv.core.Rect;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;

import java.util.ArrayList;

public class RWOpenCV {


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

    private static int numFramesWithoutDetection = 0;

    private static final float DECIMATION_HIGH = 3;
    private static final float DECIMATION_LOW = 2;
    private static final float THRESHOLD_HIGH_DECIMATION_RANGE_METERS = 1.0f;
    private static final int THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION = 4;
    //APRILTAG

    public static OpenCvCamera camera;

    private static AprilTagDetectorPipeline aprilTagDetectorPipeline;

    private static AprilEvent aprilEvent;

    public static void init(){
        if(RWRobot.startType == StartEvent.StartType.CONTROL && !RWConfig.OPENCV_IN_CONTROL)
            return;

        aprilTagDetectorPipeline = new AprilTagDetectorPipeline(tagsize, fx, fy, cx, cy);

        aprilEvent = new AprilEvent(-1);

        if(RWConfig.DEBUG) {
            int cameraMonitorViewId = RWRobot.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", RWRobot.opMode.hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(RWConfig.webcamName, cameraMonitorViewId);
        }else {
            camera = OpenCvCameraFactory.getInstance().createWebcam(RWConfig.webcamName);
        }

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800, 448);
            }
            @Override
            public void onError(int errorCode)
            {
                Telemetrie.addTel(Telemetrie.TelType.ERR, "Camera OPENCV", "Nu s-a putut deschide");
            }
        });
    }

    public static void update(){
        if(RWRobot.startType == StartEvent.StartType.CONTROL && !RWConfig.OPENCV_IN_CONTROL)
            return;
        if(camera == null)
            return;
        Telemetrie.addTel("FPS", ""+camera.getFps());
        Telemetrie.addTel("Overhead ms", ""+camera.getOverheadTimeMs());
        Telemetrie.addTel("Pipeline ms", ""+camera.getPipelineTimeMs());

        ArrayList<AprilTagDetection> detections = aprilTagDetectorPipeline.getDetectionsUpdate();
        AprilTagDetection detect = null;
        if(detections != null) {
            if(detections.size() == 0) {
                numFramesWithoutDetection++;
                if(numFramesWithoutDetection >= THRESHOLD_NUM_FRAMES_NO_DETECTION_BEFORE_LOW_DECIMATION) {
                    aprilTagDetectorPipeline.setDecimation(DECIMATION_LOW);
                }
            } else {
                if(detections.get(0).pose.z < THRESHOLD_HIGH_DECIMATION_RANGE_METERS) {
                    aprilTagDetectorPipeline.setDecimation(DECIMATION_HIGH);
                }
                detect = detections.get(0);
            }
        }
        if(detect == null && aprilEvent.getId() != -1){
            aprilEvent.setId(-1).execute();
        }else if(detect != null && aprilEvent.getId() != detect.id){
            aprilEvent.setId(detect.id).execute();
        }
    }


    public static void stop(){
        camera.stopStreaming();
        camera = null;
    }

}
