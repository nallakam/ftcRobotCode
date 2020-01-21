package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous
public class RobotAuto extends LinearOpMode
{
    private DcMotor motorTest;
    private Servo servoTest;
    private DcMotor slide;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor middle;

    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        middle = hardwareMap.get(DcMotor.class, "mi");
        servoTest = hardwareMap.get(Servo.class, "sc");
        motorTest = hardwareMap.get(DcMotor.class, "mt");
        slide = hardwareMap.get(DcMotor.class, "sl");
        telemetry.addData("Status", "Initialized"); 
        // telemetry.addData("Servo Position", this.servoTest.getPosition());
        // telemetry.addData("Motor Power", this.motorTest.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();
        
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        
        telemetry.addData("Mode", "waiting");
        telemetry.update();

        waitForStart(); //wait for start button

        telemetry.addData("Mode", "running");
        telemetry.update();

        frontLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        
        // set left motor to run to 1000 encoder counts.
        frontLeft.setTargetPosition(7096);
        frontRight.setTargetPosition(7096);
        
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        // set both motors to 25% power. Movement will start.
        // 159.25 rotations is 1 in 
        frontLeft.setPower(0.50);
        frontRight.setPower(0.50);
        
        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive())   
        {
            telemetry.addData("LeftEncoder", frontLeft.getCurrentPosition() + "  busy=" + frontLeft.isBusy());
            telemetry.addData("RightEncoder", frontRight.getCurrentPosition() + "  busy=" + frontRight.isBusy());
            telemetry.update();
            
            idle();
        }
        
        frontLeft.setPower(0);
        frontRight.setPower(0);
        
        }
    }
