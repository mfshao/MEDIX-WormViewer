/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.ArrayList;

/**
 *
 * @author mingfeishao
 */
public class DVDataset {
   private float min;
   private float max;
   private ArrayList<Float> dataList;
   private String title;
   private int frameOffset;
   private int size;

    public DVDataset(float min, float max, ArrayList<Float> dataList, String title) {
        this.min = min;
        this.max = max;
        this.dataList = dataList;
        this.title = title;
        this.size = dataList.size();
        this.frameOffset = 7;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public ArrayList<Float> getDataList() {
        return dataList;
    }

    public String getTitle() {
        return title;
    }

    public int getFrameOffset() {
        return frameOffset;
    }

    public int getSize() {
        return size;
    }
   
   
}