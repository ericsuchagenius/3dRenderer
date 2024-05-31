import java.awt.*;
import java.util.*;
import java.util.List;
import java.awt.Polygon;

public class threeDObj {
    List<List<List<Double>>> data = new ArrayList<>();
    int numPoly = 0;
    Color color = null;
    List<Polygon> drawPoly = new ArrayList<>();
//    List<Double> avgDistance = new ArrayList<>();
    public threeDObj(Double[][][] dataPoints, Color color) { // each object will be made of multiple triangles, so the first layer is the storage of triangles and the second will be the specific points of the triangles
        //1st layer, in objManager, number of objects for this task
        //2nd layer, for the storage of triangles/polygons for each object
        //3rd layer, for storing the points in an object
        //4th layer for storing the three x,y,z variable for each triangle, after calculation it will turn into two

        //this is the triangle version, which lacks a good triangulation algorithm
//        this.numTri = dataPoints.length;
//        for(int i = 0; i<numTri; i++){
//            List<List<Double>> tempPoly = new ArrayList<>();
//            for(int j = 0; j<3;j++){
//                List<Double> tempPoint = new ArrayList<>();
//                tempPoint.add(dataPoints[i][j][0]);
//                tempPoint.add(dataPoints[i][j][1]);
//                tempPoint.add(dataPoints[i][j][2]);
//                //{{{1,2,3},{3,2,1},{4,5,6}}}
//                tempPoly.add(tempPoint);
//            }
//            data.add(tempPoly);
//        }
//        this.color = color;

        this.numPoly = dataPoints.length;
        for (int i = 0; i < numPoly; i++) {
            List<List<Double>> tempPoly = new ArrayList<>();
            for (int j = 0; j < dataPoints[i].length; j++) {
                List<Double> tempPoint = new ArrayList<>();
                tempPoint.add(dataPoints[i][j][0]);
                tempPoint.add(dataPoints[i][j][1]);
                tempPoint.add(dataPoints[i][j][2]);
                //{{{1,2,3},{3,2,1},{4,5,6}}}
                tempPoly.add(tempPoint);
            }
            data.add(tempPoly);
        }
        this.color = color;

        for (int i = 0; i < numPoly; i++) {
            Polygon P = new Polygon();
            for (int j = 0; j < data.get(i).size(); j++) {
                double x = 5*calculator.calculatePositionX(Renderer.viewFrom, Renderer.viewTo, data.get(i).get(j).get(0), data.get(i).get(j).get(1), data.get(i).get(j).get(2));
                double y = 5*calculator.calculatePositionY(Renderer.viewFrom, Renderer.viewTo, data.get(i).get(j).get(0), data.get(i).get(j).get(1), data.get(i).get(j).get(2));
                x = reposition.repositon_2dX(x);
                y = reposition.repositon_2dY(y);

                P.addPoint((int)x,(int)y);
            }
            drawPoly.add(P);
        }

        // check the reading output
//        for(int i = 0; i<numTri; i++){
//            for(int j = 0; j<3;j++){
//                System.out.println(data.get(i).get(j).get(0));
//                System.out.println(data.get(i).get(j).get(1));
//                System.out.println(data.get(i).get(j).get(2));
//            }
//        }
    }

    public void computePoints() { // basically like the reading part except there's an additional computing process and only two values in the 4th layer
        drawPoly.clear();
        for (int i = 0; i < numPoly; i++) {
            Polygon P = new Polygon();
            for (int j = 0; j < data.get(i).size(); j++) {
                double x = 5*calculator.calculatePositionX(Renderer.viewFrom, Renderer.viewTo, data.get(i).get(j).get(0), data.get(i).get(j).get(1), data.get(i).get(j).get(2));
                double y = 5*calculator.calculatePositionY(Renderer.viewFrom, Renderer.viewTo, data.get(i).get(j).get(0), data.get(i).get(j).get(1), data.get(i).get(j).get(2));
                x = reposition.repositon_2dX(x);
                y = reposition.repositon_2dY(y);
                P.addPoint((int)x,(int)y);
            }
            drawPoly.add(P);
        }
    }


//    public void setDistance(){
//        avgDistance.clear();
//        for(int i = 0; i<numPoly;i++){
//            avgDistance.add()
//        }
//    }
//    public double getDistance(int tObject) {
//        double total = 0;
//        for (int i = 0; i < numPoly; i++) {
//            total+= getDistancetoP(i,j);
//        }
//        return total/numPoly;
//    }
//
//    public double getDistancetoP(int i, int j){
//        return 0;
//    }

}
