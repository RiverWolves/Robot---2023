package org.firstinspires.ftc.teamcode.mina;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;

public class RWConfig {

    //Modul DEBUG activat
    public static final boolean DEBUG = true;
    //Daca OPENCV-ul sa se activeze cand robotul este in TELEOP
    public static final boolean OPENCV_IN_CONTROL = false;

    //ID-urile de la AprilTaguri corespunzatoare fiecarei zone de parcat
    public static final int SECTIUNEA_1 = 10,
                            SECTIUNEA_2 = 11,
                            SECTIUNEA_3 = 12;

    //Daca jocul a inceput
    public static boolean INCEPUT = false;

    //Motoarele pentru roti
    public static DcMotorEx ss, sf, ds, df;
    //Giroscopul
    public static BNO055IMU imu;
    //Camera USB
    public static WebcamName webcamName;

    public static void init(){
        HardwareMap hardwareMap = RWRobot.opMode.hardwareMap;

        webcamName = hardwareMap.get(WebcamName.class, "webcam");

        ss = (DcMotorEx) hardwareMap.get("ss");
        sf = (DcMotorEx) hardwareMap.get("sf");
        ds = (DcMotorEx) hardwareMap.get("ds");
        df = (DcMotorEx) hardwareMap.get("df");

        //REVERSE!!!
        ds.setDirection(DcMotorSimple.Direction.REVERSE);
        df.setDirection(DcMotorSimple.Direction.REVERSE);



        // TODO: If the hub containing the IMU you are using is mounted so that the "REV" logo does
        // not face up, remap the IMU axes so that the z-axis points upward (normal to the floor.)
        //
        //             | +Z axis
        //             |
        //             |
        //             |
        //      _______|_____________     +Y axis
        //     /       |_____________/|__________
        //    /   REV / EXPANSION   //
        //   /       / HUB         //
        //  /_______/_____________//
        // |_______/_____________|/
        //        /
        //       / +X axis
        //
        // This diagram is derived from the axes in section 3.4 https://www.bosch-sensortec.com/media/boschsensortec/downloads/datasheets/bst-bno055-ds000.pdf
        // and the placement of the dot/orientation from https://docs.revrobotics.com/rev-control-system/control-system-overview/dimensions#imu-location
        //
        // For example, if +Y in this diagram faces downwards, you would use AxisDirection.NEG_Y.
        // BNO055IMUUtil.remapZAxis(imu, AxisDirection.NEG_Y);
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        imu.initialize(parameters);
    }

}
