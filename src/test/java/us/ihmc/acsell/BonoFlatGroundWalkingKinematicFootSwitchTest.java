package us.ihmc.acsell;

import org.junit.jupiter.api.Test;

import us.ihmc.avatar.DRCFlatGroundWalkingTest;
import us.ihmc.avatar.drcRobot.DRCRobotModel;
import us.ihmc.commonWalkingControlModules.configurations.WalkingControllerParameters;
import us.ihmc.continuousIntegration.ContinuousIntegrationAnnotations.ContinuousIntegrationPlan;
import us.ihmc.continuousIntegration.ContinuousIntegrationAnnotations.ContinuousIntegrationTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Disabled;
import us.ihmc.continuousIntegration.IntegrationCategory;
import us.ihmc.robotics.controllers.ControllerFailureException;
import us.ihmc.sensorProcessing.stateEstimation.FootSwitchType;
import us.ihmc.sensorProcessing.stateEstimation.StateEstimatorParameters;
import us.ihmc.simulationConstructionSetTools.bambooTools.BambooTools;
import us.ihmc.simulationconstructionset.util.simulationRunner.BlockingSimulationRunner.SimulationExceededMaximumTimeException;
import us.ihmc.steppr.controlParameters.BonoStateEstimatorParameters;
import us.ihmc.steppr.controlParameters.BonoWalkingControllerParameters;
import us.ihmc.steppr.parameters.BonoRobotModel;

@Disabled
public class BonoFlatGroundWalkingKinematicFootSwitchTest extends DRCFlatGroundWalkingTest
{
   private BonoRobotModel robotModel;

	@Test
   public void testBONOFlatGroundWalking() throws SimulationExceededMaximumTimeException, ControllerFailureException
   {
      final boolean runningOnRealRobot = false;

      //create a subclass of standard DRCRobot model but overrides FootSwitchType for both WalkingControl/StateEstimation parameters.
      robotModel = new BonoRobotModel(runningOnRealRobot, false)
      {
         @Override
         public WalkingControllerParameters getWalkingControllerParameters()
         {
            return new BonoWalkingControllerParameters(super.getJointMap(), runningOnRealRobot)
            {
               @Override
               public FootSwitchType getFootSwitchType()
               {
                  return FootSwitchType.KinematicBased;
               }
            };
         }

         @Override
         public StateEstimatorParameters getStateEstimatorParameters()
         {
            return new BonoStateEstimatorParameters(runningOnRealRobot, getEstimatorDT())
            {
               @Override
               public FootSwitchType getFootSwitchType()
               {
                  return FootSwitchType.KinematicBased;
               }
            };

         }
      };

      super.testFlatGroundWalking(robotModel, false);
   }

   @Override
   public DRCRobotModel getRobotModel()
   {
      return robotModel;
   }

   @Override
   public String getSimpleRobotName()
   {
      return BambooTools.getSimpleRobotNameFor(BambooTools.SimpleRobotNameKeys.BONO);
   }
}
