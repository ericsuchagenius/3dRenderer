import java.awt.*;
import java.util.*;
import java.util.List;

public class threeDObj {
    List<List<List<Double>>> data = new ArrayList<>();
    int numPoly = 0;
    Color color = null;
    List<List<List<Double>>> computedPoints = new ArrayList<>();
    public threeDObj(Double[][][] dataPoints, Color color){ // each object will be made of multiple triangles, so the first layer is the storage of triangles and the second will be the specific points of the triangles
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
        for(int i = 0; i<numPoly; i++){
            List<List<Double>> tempPoly = new ArrayList<>();
            for(int j = 0; j<dataPoints[i].length;j++){
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


        // check the reading output
//        for(int i = 0; i<numTri; i++){
//            for(int j = 0; j<3;j++){
//                System.out.println(data.get(i).get(j).get(0));
//                System.out.println(data.get(i).get(j).get(1));
//                System.out.println(data.get(i).get(j).get(2));
//            }
//        }
    }

    public void computePoints(){ // basically like the reading part except there's an additional computing process and only two values in the 4th layer
        computedPoints.clear();
        for(int i = 0; i<numPoly; i++){
            List<List<Double>> tempTri = new ArrayList<>();
            for(int j = 0; j<data.get(i).size();j++){
                List<Double> tempPoint = new ArrayList<>();
                tempPoint.add(5*calculator.CalculatotposX(Renderer.viewFrom, Renderer.viewTo, data.get(i).get(j).get(0),data.get(i).get(j).get(1),data.get(i).get(j).get(2)));
                tempPoint.add(5*calculator.CalculatotposY(Renderer.viewFrom, Renderer.viewTo, data.get(i).get(j).get(0),data.get(i).get(j).get(1),data.get(i).get(j).get(2)));
                //{{{1,2,3},{3,2,1},{4,5,6}}}
                tempPoint = reposition.reposition_point(tempPoint);
                tempTri.add(tempPoint);
            }
            computedPoints.add(tempTri);
        }
//        for(int i = 0; i<computedPoints.size(); i++){
//            for(int j = 0; j<computedPoints.get(i).size();j++){
//                System.out.println(data.get(i).get(j).get(0));
//                System.out.println(data.get(i).get(j).get(1));
//            }
//        }
    }
}
