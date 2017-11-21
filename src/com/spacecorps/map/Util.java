package com.spacecorps.map;

public class Util {


    public static double calculateDistanceBetweenTwoPoint(int x, int y, int z, int a, int b, int c){
        double result;
        result = Math.sqrt((x-a)^2 + (y-b)^2 + (z-c)^2);
        return result;
    }

    public static double calculateDistanceBetweenTwoPoint(double[] a, double[] b){
        double result = Math.sqrt(Math.pow((b[0]-a[0]),2) + Math.pow((b[1]-a[1]),2) + Math.pow((b[2]-a[2]),2));
        return result;
    }

    public static double calculateDistanceBetweenTwoPoint(XYZcoord a, XYZcoord b){
        return Util.calculateDistanceBetweenTwoPoint(a.getxAbsolute(),a.getyAbsolute(),a.getzAbsolute(),b.getxAbsolute(),b.getyAbsolute(),b.getzAbsolute());
    }

    public static XYZcoord calculateVectorDirectionBetweenTwoPoints(XYZcoord a, XYZcoord b){
        return new XYZcoord((a.getxAbsolute() - b.getxAbsolute()),(a.getyAbsolute() - b.getyAbsolute()),(a.getzAbsolute() - b.getzAbsolute()));
    }

    public static XYZcoord getPositionGivenSpeedAndVelocityDirection(double speed, XYZcoord a, XYZcoord b){
        double[] matrixA = a.doubleMatrix();
        double[] matrixB = b.doubleMatrix();
        double[] resultMatrix = new double[3];
        for(int i = 0; i < matrixA.length; i++){
            resultMatrix[i] = matrixB[i] - matrixA[i];
        }
        double speedCoefficient = Math.abs(resultMatrix[0]) + Math.abs(resultMatrix[1]) + Math.abs(resultMatrix[2]);
        double coefficient = speed / speedCoefficient;
        double result[] = {matrixA[0] + resultMatrix[0]*coefficient,matrixA[1] + resultMatrix[1]*coefficient,matrixA[2] + resultMatrix[2]*coefficient};
        return new XYZcoord(result);
    }
}
