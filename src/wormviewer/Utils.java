/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormviewer;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author mingfeishao
 */
public class Utils {
    public static Vector generateDataVectorFromFiveNumberSummaryList (String label, ArrayList<FiveNumberSummary> fnsList) {
        DecimalFormat df = new DecimalFormat("#.##");
        Vector<Object> vector = new Vector<>();
        vector.add(label);
        for (FiveNumberSummary fns : fnsList) {
            switch (label) {
                case "Min":
                    vector.add(df.format(fns.getMin()));
                    break;
                case "1st Quartile":
                    vector.add(df.format(fns.getFirstQuartile()));
                    break;
                case "Median":
                    vector.add(df.format(fns.getMedian()));
                    break;
                case "3rd Quartile":
                    vector.add(df.format(fns.getThirdQuartile()));
                    break;
                case "Max":
                    vector.add(df.format(fns.getMax()));
                    break;
                default:
                    break;
            }
            
        }
        return vector;
    }
}
