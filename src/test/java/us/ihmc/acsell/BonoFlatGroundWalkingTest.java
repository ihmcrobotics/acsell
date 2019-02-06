package us.ihmc.acsell;

import org.junit.jupiter.api.Test;

import us.ihmc.avatar.DRCFlatGroundWalkingTest;
import us.ihmc.avatar.drcRobot.DRCRobotModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Disabled;
import us.ihmc.robotics.controllers.ControllerFailureException;
import us.ihmc.simulationConstructionSetTools.bambooTools.BambooTools;
import us.ihmc.simulationconstructionset.util.simulationRunner.BlockingSimulationRunner.SimulationExceededMaximumTimeException;
import us.ihmc.steppr.parameters.BonoRobotModel;

@Disabled
public class BonoFlatGroundWalkingTest extends DRCFlatGroundWalkingTest
{
   private BonoRobotModel robotModel;

	@Test
   public void testBONOFlatGroundWalking() throws SimulationExceededMaximumTimeException, ControllerFailureException
   {
      robotModel = new BonoRobotModel(false, false);
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
