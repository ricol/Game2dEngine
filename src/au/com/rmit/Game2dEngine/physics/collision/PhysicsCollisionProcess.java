/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.com.rmit.Game2dEngine.physics.collision;

import au.com.rmit.Game2dEngine.geometry.CircleShape;
import au.com.rmit.Game2dEngine.geometry.ClosureShape;
import au.com.rmit.Game2dEngine.geometry.Shape;
import au.com.rmit.Game2dEngine.math.CollisionQuadraticEquation;
import au.com.rmit.Game2dEngine.math.Vector;
import au.com.rmit.Game2dEngine.sprite.Sprite;

/**
 *
 * @author ricolwang
 */
public class PhysicsCollisionProcess
{

    public static boolean detectCollision(Sprite A, Sprite B)
    {
        Shape theShape = A.getTheShape();
        Shape theTargetShape = B.getTheShape();
        if ((theShape instanceof ClosureShape) && (theTargetShape instanceof ClosureShape))
        {
            return ((ClosureShape) theShape).collideWith((ClosureShape) theTargetShape);
        } else
            return false;
    }

    public static void processCollision(Sprite A, Sprite B)
    {
        if (A.getTheShape() instanceof CircleShape && B.getTheShape() instanceof CircleShape)
        {
            //a circle collide with a circle
            Vector AB = new Vector(B.getCentreX() - A.getCentreX(), B.getCentreY() - A.getCentreY());
            if (AB.getMagnitude() <= 0)
                return;

            Vector BC = AB.getPerpendicularUnitVectorClockwise();

            Vector V_A = new Vector(A.getVelocityX(), A.getVelocityY());

            double cosBC_V_A = BC.getCosValueForAngleToVector(V_A);
            if (cosBC_V_A < 0)
                BC = AB.getPerpendicularUnitVectorCounterClockwise();

            Vector UNIT_AB = AB.getTheUnitVector();
            Vector V_A_AB = V_A.getProjectVectorOn(UNIT_AB);

            Vector V_B = new Vector(B.getVelocityX(), B.getVelocityY());
            Vector V_B_AB = V_B.getProjectVectorOn(UNIT_AB);

            double absV_A_AB = V_A_AB.getMagnitude();

            if (V_A.getCosValueForAngleToVector(AB) < 0)
                absV_A_AB = -absV_A_AB;

            double absV_B_AB = V_B_AB.getMagnitude();

            if (V_B.getCosValueForAngleToVector(AB) < 0)
                absV_B_AB = -absV_B_AB;

            CollisionQuadraticEquation aEquation = new CollisionQuadraticEquation(A.getMass(), B.getMass(), absV_A_AB, absV_B_AB);
            double resultAbsV_A_AB = aEquation.getNewVelocityAlternative();
            double resultAbsV_B_AB = aEquation.getTheOtherObjectVelocityAlternative();

            Vector RESULT_V_A_AB = UNIT_AB.multiplyNumber(resultAbsV_A_AB);
            Vector UNIT_BC = BC.getTheUnitVector();
            Vector V_A_BC = V_A.getProjectVectorOn(UNIT_BC);
            Vector RESULT_V_A = RESULT_V_A_AB.addVector(V_A_BC);

            A.setVelocityX(RESULT_V_A.x);
            A.setVelocityY(RESULT_V_A.y);

            Vector RESULT_V_B_AB = UNIT_AB.multiplyNumber(resultAbsV_B_AB);
            Vector V_B_BC = V_B.getProjectVectorOn(UNIT_BC);
            Vector RESULT_V_B = RESULT_V_B_AB.addVector(V_B_BC);

            B.setVelocityX(RESULT_V_B.x);
            B.setVelocityY(RESULT_V_B.y);

            A.setTargetCollisionProcessed(true);
        } else
            System.out.println("Warning: Shape Collision except Circle not implemented!");
    }
}
