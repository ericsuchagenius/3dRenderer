import java.util.ArrayList;
import java.util.List;

public class reposition {
    static public List<List<Double>> reposition_vector(List<List<Double>> list_in){
        List<List<Double>> ans = new ArrayList<>();

        for(int i = 0; i<list_in.size(); i++){
            List<Double> tempans = new ArrayList<>();
            tempans.add(list_in.get(i).get(0) + Renderer.Width/2);
            tempans.add(list_in.get(i).get(0) + Renderer.Width/2);
            ans.add(tempans);
        }
        return ans;
    }

    static public List<Double> reposition_point(List<Double> list_in){
        List<Double> tempans = new ArrayList<>();
        tempans.add(list_in.get(0) + Renderer.Width/2);
        tempans.add(list_in.get(1) + Renderer.Width/2);
        return tempans;
    }
    static public double repositon_2dX(double x){
        return x+ Renderer.Width/2;
    }
    static public double repositon_2dY(double y){return y+ Renderer.Height/2;}
}