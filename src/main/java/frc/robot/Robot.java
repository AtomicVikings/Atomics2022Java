package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;

// Motors and Controllers
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.cameraserver.CameraServer;

// Drive
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// Input
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

// Timing
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {

  //Constants
    //Mech button bindings
    int intakeIn = 5;
    int intakeOut = 6;
    int raiseIntake = 7;
    int lowerIntake = 8;


  //Motors
  WPI_TalonFX leftTop, leftFront, leftBack, rightTop, rightFront, rightBack;
  CANSparkMax mechLevel;
  CANSparkMax mechIntake;

  // Drive
  MotorControllerGroup leftDrive, rightDrive;
  DifferentialDrive drive;

  // Inputs
  Joystick driver, mech;
  //SendableChooser autoBool;
  CameraServer driveCam;

  // Auto
  Timer driveTime;

  // Constants 

  @Override
  public void robotInit() {
    
    // Motors and Controllers
    leftTop = new WPI_TalonFX(5);
    leftFront = new WPI_TalonFX(6);
    leftBack = new WPI_TalonFX(7);
    rightTop = new WPI_TalonFX(8);
    rightFront = new WPI_TalonFX(9);
    rightBack= new WPI_TalonFX(10);

    mechLevel = new CANSparkMax(11, MotorType.kBrushless);
    mechIntake = new CANSparkMax(12, MotorType.kBrushless);

    // Drive
    leftDrive = new MotorControllerGroup(leftTop, leftFront, leftBack);
    rightDrive = new MotorControllerGroup(rightTop, rightFront, rightBack);
    drive = new DifferentialDrive(leftDrive, rightDrive);
    rightTop.setInverted(true);
    rightFront.setInverted(true);
    rightBack.setInverted(true);

    // Input
    driver = new Joystick(0);
    mech = new Joystick(1);
    //autoBool = new SendableChooser<Boolean>();
    CameraServer.startAutomaticCapture();

    // Auto
    driveTime = new Timer();
    

  }


  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
    
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    
  }

  @Override
  public void teleopPeriodic() {


    // Drive command
    drive.arcadeDrive(driver.getRawAxis(1) * -.8, driver.getRawAxis(4) * .7);

    // Mechanism command
    mech();
  }

  @Override
  public void disabledInit() {
    
  }

  @Override
  public void disabledPeriodic() {
    
  }
  

  @Override
  public void testInit() {

  }

  @Override
  public void testPeriodic() {

  }

  private void mech() {
    if (mech.getRawButton(intakeIn)) {
      mechIntake.set(1);
    } else if (mech.getRawButton(intakeOut)) {
      mechIntake.set(-1);
    } else {
      mechIntake.set(0);
    }

    if (mech.getRawButton(raiseIntake)) {
      mechLevel.set(.3);
    } else if (mech.getRawButton(lowerIntake)) {
      mechLevel.set(-.3);
    } else {
      mechLevel.set(0);
    }
  }
}
