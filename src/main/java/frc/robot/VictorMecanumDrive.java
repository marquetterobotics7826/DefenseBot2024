package frc.robot;

import java.util.function.Consumer;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class VictorMecanumDrive {

    VictorSPX frontLeft;
    VictorSPX rearLeft;
    VictorSPX frontRight;
    VictorSPX rearRight;

    public VictorMecanumDrive(VictorSPX frontLeft, VictorSPX rearLeft, VictorSPX frontRight, VictorSPX rearRight) {
        this.frontLeft = frontLeft;
        this.rearLeft = rearLeft;
        this.frontRight = frontRight;
        this.rearRight = rearRight;
    }

    public void drive(double leftStickX, double leftStickY, double rightStickX, double rightStickY) {
        double rotation = rightStickX;
        if (rotation != 0) {
            this.frontLeft.set(VictorSPXControlMode.PercentOutput, rotation);
            this.rearLeft.set(VictorSPXControlMode.PercentOutput, rotation);
            this.frontRight.set(VictorSPXControlMode.PercentOutput, -rotation);
            this.rearRight.set(VictorSPXControlMode.PercentOutput, -rotation);
            return;
        }
        
        double x = leftStickX/2;
        double y = leftStickY/2;

        this.frontLeft.set(VictorSPXControlMode.PercentOutput, -x + y);
        this.rearLeft.set(VictorSPXControlMode.PercentOutput, -x + -y);
        this.frontRight.set(VictorSPXControlMode.PercentOutput, x + y);
        this.rearRight.set(VictorSPXControlMode.PercentOutput, x + -y);
    }
    
}
