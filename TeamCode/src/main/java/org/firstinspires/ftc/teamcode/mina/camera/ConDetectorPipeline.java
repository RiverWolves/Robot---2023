package org.firstinspires.ftc.teamcode.mina.camera;

import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ConDetectorPipeline extends OpenCvPipeline {
    private Mat hsv = new Mat();
    private Mat bitEdges = new Mat();
    private List<MatOfPoint> contururi = new ArrayList<>();
    private MatOfPoint2f[] contururiPoly;
    private Rect[] detectii = new Rect[0], detectiiUpdate = new Rect[0];

    private final Object detectionsUpdateSync = new Object();

    boolean rosu = false;

    public ConDetectorPipeline(){
        if(RWRobot.startType.toString().toLowerCase().contains("rosu")){
            rosu = true;
        }
    }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);

        if(rosu) {
            Core.inRange(hsv, new Scalar(4, 123, 0), new Scalar(11, 255, 255), bitEdges);
        }else{
            Core.inRange(hsv, new Scalar(90, 50, 70), new Scalar(128, 255, 255), bitEdges);
        }

        contururi.clear();
        Imgproc.findContours(bitEdges, contururi, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_NONE);

        detectii = new Rect[contururi.size()];
        contururiPoly = new MatOfPoint2f[contururi.size()];
        for(int i = 0; i < contururi.size(); ++i) {
            contururiPoly[i] = new MatOfPoint2f();
            Imgproc.approxPolyDP(new MatOfPoint2f(contururi.get(i).toArray()), contururiPoly[i], 3.0, true);
            detectii[i] = Imgproc.boundingRect(new MatOfPoint(contururiPoly[i].toArray()));
        }
        synchronized (detectionsUpdateSync) {
            detectiiUpdate = detectii;
        }
        return input;
    }


    public Rect[] getLatestDetections()
    {
        return detectii;
    }

    public Rect[] getDetectionsUpdate()
    {
        synchronized (detectionsUpdateSync) {
            Rect[] ret = detectiiUpdate;
            detectiiUpdate = null;
            return ret;
        }
    }


}