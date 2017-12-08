package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import object.DVDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;

public class DynamicLinePlotPanel extends JPanel {

    private static final float MINMAX = 100;
    private static final int COUNT = 2 * 60;
    private static final Random random = new Random();
    private final DVDataset dvDataset;
    private Timer timer;
    private JTextArea textArea;
    private int currentFrame;
    private DynamicTimeSeriesCollection dataset;

    public DynamicLinePlotPanel(Timer timer, DVDataset dvDataset, JTextArea textArea) {
        this.dvDataset = dvDataset;
        this.timer = timer;
        this.textArea = textArea;
        currentFrame = dvDataset.getFrameOffset();
        dataset = new DynamicTimeSeriesCollection(1, COUNT, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2011));
        dataset.addSeries(initData(), 0, dvDataset.getTitle());
        JFreeChart chart = createChart(dataset);
        ChartPanel cp = new ChartPanel(chart, 464, 188, 464, 188, 464, 188, true, false, false, false, false, false);
        this.add(cp);

        timer.addActionListener(new onTimerChangeActionListener());

    }

    private class onTimerChangeActionListener implements ActionListener {

        final Iterator<Float> dvDatasetItr = dvDataset.getDataList().iterator();
        float[] newData = new float[1];

        @Override
        public void actionPerformed(ActionEvent e) {
            dataset.advanceTime();
            for (int i = 0; i < 100; i++) {
                if (!dvDatasetItr.hasNext()) {
                    break;
                }
                newData[0] = dvDatasetItr.next();
                dataset.appendData(newData);
                currentFrame++;
            }
            textArea.setText("Current Frame: " + currentFrame + "\n" + dvDataset.getTitle() + ": " + newData[0] + "\nMIn = " + dvDataset.getMin() + "\nMax = " + dvDataset.getMax());
            
            if (!dvDatasetItr.hasNext()) {
                    timer.stop();
            }
        }
    }

    private float[] initData() {
        float[] a = new float[dvDataset.getFrameOffset()];
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "", "hh:mm:ss", dvDataset.getTitle(), dataset, false, false, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(dvDataset.getMin(), dvDataset.getMax());
        return result;
    }

    public void start() {
        timer.start();
    }
}
