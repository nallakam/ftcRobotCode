package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class Robot extends LinearOpMode
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
        telemetry.addData("Servo Position", this.servoTest.getPosition());
        telemetry.addData("Motor Power", this.motorTest.getPower());
        telemetry.addData("Status", "Running");
        telemetry.update();
        waitForStart();
        double tgtPower = 0.0;
        while (opModeIsActive()) {

            // forward
            if (gamepad1.dpad_up) {
                frontLeft.setPower(-1);
                frontRight.setPower(1);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
            }

            // backward
            if (gamepad1.dpad_down) {
                frontLeft.setPower(1);
                frontRight.setPower(-1);
            } else {
                frontLeft.setPower(0);
                frontRight.setPower(0);
            }

            // strafe right
            if (gamepad1.dpad_right) {
                middle.setPower(1);
            } else {
                middle.setPower(0);
            }

            // strafe left
            if (gamepad1.dpad_right) {
                middle.setPower(-1);
            } else {
                middle.setPower(0);
            }

            // frontLeft.setPower(gamepad1.left_stick_y);
            // frontRight.setPower(-gamepad1.left_stick_y);
            middle.setPower(-gamepad1.right_stick_x);
            // frontRight.setPower(-gamepad1.left_stick_x);
            // frontLeft.setPower(-gamepad1.left_stick_x);
            tgtPower = -gamepad2.left_stick_y;
            motorTest.setPower(tgtPower);



            if (gamepad1.y) {
                servoTest.setPosition(0.0);
            }
            else if (gamepad2.right_bumper) {
                servoTest.setPosition(0.38);
            }
            else if (gamepad2.left_bumper) {
                servoTest.setPosition(1.0);
            }
            slide.setPower(gamepad2.right_trigger);
            slide.setPower(-gamepad2.left_trigger);
        }
    }
}
