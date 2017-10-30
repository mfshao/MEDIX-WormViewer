package wormviewer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author mshao1
 */
public class StatisticsUtils {

    private static double getMiddleNum(Double[] num) {
        int middle = (num.length - 1) / 2;

        if (num.length % 2 == 0) {
            double num1 = num[middle];
            double num2 = num[middle + 1];
            return (num1 + num2) / 2;
        } else {
            return num[middle];
        }
    }

    private static FiveNumberSummary generateFiveNumberSummary(List<Double> toCalc, String name) {
        FiveNumberSummary result = new FiveNumberSummary(name);
        int n = toCalc.size() - 1;

        if (!toCalc.isEmpty() && n != 0) {
            Double[] numbers = toCalc.toArray(new Double[toCalc.size()]);
            Arrays.sort(numbers);

            // Max and Min
            result.setMax(numbers[n]);
            result.setMin(numbers[0]);

            // Middle Number (Q2)
            result.setMedian(getMiddleNum(numbers));

            // Q1
            result.setFirstQuartile(getMiddleNum(Arrays.copyOfRange(numbers, 0, (numbers.length) / 2)));

            // Q3
            if (numbers.length % 2 == 0) {
                result.setThirdQuartile(getMiddleNum(Arrays.copyOfRange(numbers, (numbers.length) / 2, numbers.length)));
            } else {
                result.setThirdQuartile(getMiddleNum(Arrays.copyOfRange(numbers, ((numbers.length) / 2) + 1, numbers.length)));
            }
        } else {
            result = new FiveNumberSummary(name, toCalc.get(0), toCalc.get(0), toCalc.get(0), toCalc.get(0), toCalc.get(0));
        }
        return result;
    }

    static ArrayList<FiveNumberSummary> getAllFiveNumberSummaries(HashMap<String, ArrayList<Double>> resultMap) {
        ArrayList<FiveNumberSummary> fnsList = new ArrayList();
        
        for (String s : resultMap.keySet()) {
            fnsList.add(generateFiveNumberSummary(resultMap.get(s), s));
        }
        
        return fnsList;
    }
}