package org.firstinspires.ftc.teamcode.stef.resurse.tag;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    public static OpenCvCamera camera;
    public static AprilTagDetectionPipeline aprilTagDetectionPipeline;

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

    private static int ID_TAG_OF_INTEREST = 18; // Tag ID 18 from the 36h11 family
    private static int STANGA = 0;
    private static int MIJLOC = 1;
    private static int DREAPTA = 2;

    public static AprilTagDetection tagOfInterest = null;

    public static boolean initializat = false;

    public static void init(OpMode opMode) {



        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(opMode.hardwareMap.get(WebcamName.class, "webcam"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
                opMode.telemetry.addLine("Camera initializata cu succes");
                opMode.telemetry.update();
                initializat = true;
            }

            @Override
            public void onError(int errorCode) {
                opMode.telemetry.addLine("Eroare initializare");
                opMode.telemetry.update();

            }
        });


    }

    public static void update(OpMode opMode) {


        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

        if (currentDetections.size() != 0) {
            boolean tagFound = false;

            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == STANGA || tag.id == MIJLOC || tag.id == DREAPTA) {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }



            /* Actually do something useful */
            if (tagOfInterest.id == STANGA) {
                opMode.telemetry.addLine("Tag stanga");


            } else if (tagOfInterest.id == MIJLOC) {
                opMode.telemetry.addLine("Tag mijloc");

            } else if (tagOfInterest.id == DREAPTA) {
                opMode.telemetry.addLine("Tag dreapta");

            }


        }


    }



    public static void stop(){
        camera.stopStreaming();
        camera = null;
    }
}