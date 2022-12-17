package org.firstinspires.ftc.teamcode.stef.resurse;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.openftc.easyopencv.OpenCvCamera;

public class SHardware {

    public static boolean initializat = false;

    public static DcMotor ss, sf, ds, df,
                          lift1, lift2;

    public static Servo intake1, intake2;

    public static BNO055IMU imu;

    public static WebcamName webcamName;

    public static Telemetry telemetry;


    public static void init(OpMode opMode){


        //GIROSCOP
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.RADIANS;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = opMode.hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        //camera

        webcamName = opMode.hardwareMap.get(WebcamName.class, "webcam");

        //MOTOARE
        ss = (DcMotor) opMode.hardwareMap.get("ss");
        sf = (DcMotor) opMode.hardwareMap.get("sf");
        ds = (DcMotor) opMode.hardwareMap.get("ds");
        df = (DcMotor) opMode.hardwareMap.get("df");

        ds.setDirection(DcMotorSimple.Direction.REVERSE);
        df.setDirection(DcMotorSimple.Direction.REVERSE);

        lift1 = (DcMotor) opMode.hardwareMap.get("lift1");
        lift2 = (DcMotor) opMode.hardwareMap.get("lift2");

        lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift1.setDirection(DcMotor.Direction.REVERSE);


        //servo cleste
        intake1 = (Servo) opMode.hardwareMap.get("intake1");
        intake2 = (Servo) opMode.hardwareMap.get("intake2");

        telemetry = opMode.telemetry;

        //LA SFARSITUL INITIALIZARII SETAM VARIABILA LA TRUE
        initializat = true;
        telemetry.addData("Hardware initializat: ", initializat);
    }

}
