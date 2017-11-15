package graphics;

/*
 * Copyright (c) 1995, 2011, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

/* 
 * ScrollDemo.java requires these files:
 *   Rule.java
 *   Corner.java
 *   ScrollablePicture.java
 *   images/flyingBee.jpg
 */
public class ScrollDemo extends JPanel {

    private Axis columnView;
    private Axis rowView;

    public ScrollDemo() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        //Get the image to use.
//        ImageIcon bee = createImageIcon("/images/flyingBee.jpg");
        java.util.List<Integer> scores = new ArrayList<Integer>();
        Random random = new Random();
        int maxDataPoints = 16;
        int maxScore = 20;
        for (int i = 0; i < maxDataPoints; i++) {
            scores.add(random.nextInt(maxScore));
        }
        DrawGraph mainPanel = new DrawGraph(scores);
        BufferedImage awtImage = mainPanel.getPaintImage();
        mainPanel.revalidate();

        //Create the row and column headers.
        columnView = new Axis(Axis.HORIZONTAL, mainPanel.getxScale(), mainPanel.getyScale());
        rowView = new Axis(Axis.VERTICAL, mainPanel.getxScale(), mainPanel.getyScale());

//        if (bee != null) {
//            columnView.setPreferredWidth(bee.getIconWidth());
//            rowView.setPreferredHeight(bee.getIconHeight());
//        } else {
//            columnView.setPreferredWidth(320);
//            rowView.setPreferredHeight(480);
//        }
        if (awtImage != null) {
            columnView.setPreferredWidth(awtImage.getWidth());
            rowView.setPreferredHeight(awtImage.getHeight());
        } else {
            columnView.setPreferredWidth(464);
            rowView.setPreferredHeight(188);
        }
        

        //Set up the scroll pane.
//        picture = new ScrollablePicture(bee, columnView.getIncrement());
//        picture = new ScrollablePicture(new ImageIcon(awtImage), columnView.getIncrement());
        JScrollPane pictureScrollPane = new JScrollPane(mainPanel);
        pictureScrollPane.setPreferredSize(new Dimension(464, 188));
        pictureScrollPane.setViewportBorder(
                BorderFactory.createLineBorder(Color.black));

        pictureScrollPane.setColumnHeaderView(columnView);
        pictureScrollPane.setRowHeaderView(rowView);

        //Set the corners.
        //In theory, to support internationalization you would change
        //UPPER_LEFT_CORNER to UPPER_LEADING_CORNER,
        //LOWER_LEFT_CORNER to LOWER_LEADING_CORNER, and
        //UPPER_RIGHT_CORNER to UPPER_TRAILING_CORNER.  In practice,
        //bug #4467063 makes that impossible (in 1.4, at least).
        pictureScrollPane.setCorner(JScrollPane.LOWER_LEFT_CORNER,
                new Corner());
        pictureScrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER,
                new Corner());

        //Put it in this panel.
        add(pictureScrollPane);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        System.out.println("SD FINISHED");
    }
}
