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
        this.frontLeft = (DcMotor)this.hardwareMap.get(DcMotor.class, "fl");
        this.frontRight = (DcMotor)this.hardwareMap.get(DcMotor.class, "fr");
        this.middle = (DcMotor)this.hardwareMap.get(DcMotor.class, "mi");
        this.servoTest = (Servo)this.hardwareMap.get(Servo.class, "sc");
        this.motorTest = (DcMotor)this.hardwareMap.get(DcMotor.class, "mt");
        this.slide = (DcMotor)this.hardwareMap.get(DcMotor.class, "sl");
        this.telemetry.addData("Status", "Initialized");
        this.telemetry.addData("Servo Position", this.servoTest.getPosition());
        this.telemetry.addData("Motor Power", this.motorTest.getPower());
        this.telemetry.addData("Status", "Running");
        this.telemetry.update();
        this.waitForStart();
        double tgtPower = 0.0;
        while (this.opModeIsActive()) {
            this.frontLeft.setPower(this.gamepad1.left_stick_y);
            this.frontRight.setPower(-this.gamepad1.left_stick_y);
            this.middle.setPower(-this.gamepad1.right_stick_x);
            this.frontRight.setPower(-this.gamepad1.left_stick_x);
            this.frontLeft.setPower(-this.gamepad1.left_stick_x);
            tgtPower = -this.gamepad2.left_stick_y;
            this.motorTest.setPower(tgtPower);
            if (this.gamepad1.y) {
                this.servoTest.setPosition(0.0);
            }
            else if (this.gamepad2.right_bumper) {
                this.servoTest.setPosition(0.5);
            }
            else if (this.gamepad2.left_bumper) {
                this.servoTest.setPosition(1.0);
            }
            this.slide.setPower(this.gamepad2.right_trigger);
            this.slide.setPower(-this.gamepad2.left_trigger);
        }
    }
}
