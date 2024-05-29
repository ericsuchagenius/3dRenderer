public class calculator { //static reference of the methods
    static double CalculatotposX(double[] Viewfrom, double[] Viewto, double x,double y,double z){
        return calculator.setStuff(Viewfrom,Viewto,x,y,z,true);
    }
    static double CalculatotposY(double[] Viewfrom, double[] Viewto, double x,double y,double z){
        return calculator.setStuff(Viewfrom,Viewto,x,y,z, false);
    }
    private static double setStuff(double[] ViewFrom, double[] ViewTo, double x, double y, double z, boolean isX){
        Vector viewVector = new Vector(ViewTo[0]-ViewFrom[0], ViewTo[1]-ViewFrom[1], ViewTo[2]-ViewFrom[2]);
        Vector dirVector = new Vector(1,1,1);
        Vector Planeventor1 = viewVector.CrossProduct(dirVector);
        Vector Planeventor2 = viewVector.CrossProduct(Planeventor1);

        Vector ViewToPoint  = new Vector(x-ViewFrom[0], y-ViewFrom[1],z-ViewFrom[2]);

        double t = (viewVector.x*ViewTo[0]+viewVector.y*ViewTo[1]+viewVector.z*ViewTo[2])
                - (viewVector.x*ViewFrom[0]+viewVector.y*ViewFrom[1]+viewVector.z*ViewFrom[2])
                / (viewVector.x*ViewToPoint.x+viewVector.y*ViewToPoint.y+viewVector.z*ViewToPoint.z);

        x = ViewFrom[0] * ViewToPoint.x*t;
        y = ViewFrom[1] * ViewToPoint.y*t;
        z = ViewFrom[2] * ViewToPoint.z*t;

        if (t>=0){
            if(isX){
                return Planeventor2.x*x + Planeventor2.y*y +Planeventor2.z*z;
            }
            return Planeventor1.x*x + Planeventor1.y*y +Planeventor1.z*z;
        }
        return -1;

    }
}