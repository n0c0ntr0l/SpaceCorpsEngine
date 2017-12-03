package com.spacecorps.map;


public class XYZcoord {


    public int xAbsolute;
    public int yAbsolute;
    public int zAbsolute;


    public double xExact, yExact, zExact;
    public int xFromCenter;
    public int yFromCenter;
    public int zFromCenter;


    public XYZcoord(int x, int y, int z) {
        this.xAbsolute = x;
        this.yAbsolute = y;
        this.zAbsolute = z;
        this.xExact = (double)x;
        this.yExact = (double)y;
        this.zExact = (double)z;

        calculatePositionFromCentre();
    }

    public XYZcoord(double x, double y, double z){
        this.xExact = Util.roundDoubleToTwoDecimalPlaces(x);
        this.yExact = Util.roundDoubleToTwoDecimalPlaces(y);
        this.zExact = Util.roundDoubleToTwoDecimalPlaces(z);
        this.xAbsolute = (int)x;
        this.yAbsolute = (int)y;
        this.zAbsolute = (int)z;
        this.calculatePositionFromCentre();

    }

    public XYZcoord(double[] x){
        this.xExact = x[0];
        this.yExact = x[1];
        this.zExact = x[2];
        this.xAbsolute = (int) x[0];
        this.yAbsolute = (int) x[1];
        this.zAbsolute = (int) x[2];
        this.calculatePositionFromCentre();

    }


    private void calculatePositionFromCentre() {
        xFromCenter = xAbsolute - GalaxyMapTop.HALFPOINT;
        yFromCenter = yAbsolute - GalaxyMapTop.HALFPOINT;
        zFromCenter = zAbsolute - GalaxyMapTop.HALFPOINT;
    }

    private double calculateExactDistance(XYZcoord xyzCoord) {
        return Util.calculateDistanceBetweenTwoPoint(this.xAbsolute, this.yAbsolute, this.zAbsolute,
                xyzCoord.getxAbsolute(), xyzCoord.getyAbsolute(), xyzCoord.getzAbsolute());
    }

    public int getxAbsolute() {
        return xAbsolute;
    }

    public int getyAbsolute() {
        return yAbsolute;
    }

    public int getzAbsolute() {
        return zAbsolute;
    }

    public double getxExact() {
        return xExact;
    }

    public double getyExact() {
        return yExact;
    }

    public double getzExact() {
        return zExact;
    }

    public void setxExact(double xExact) {
        this.xExact = xExact;
    }

    public void setyExact(double yExact) {
        this.yExact = yExact;
    }

    public void setzExact(double zExact) {
        this.zExact = zExact;
    }

    public int[] integerMatrix() {
        int[] result = new int[3];
        result[0] = this.getxAbsolute();
        result[1] = this.getyAbsolute();
        result[2] = this.getzAbsolute();
        return result;
    }

    public double[] doubleMatrix() {
        double[] result = new double[3];
        result[0] = this.getxExact();
        result[1] = this.getyExact();
        result[2] = this.getzExact();
        return result;
    }


    public boolean equals(XYZcoord xyZcoord){
        for(int i = 0; i < 2; i++){
            if(this.integerMatrix()[i] != xyZcoord.integerMatrix()[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "X: " + this.xExact + " Y: " + this.yExact + " Z: " + this.zExact;
    }
}
