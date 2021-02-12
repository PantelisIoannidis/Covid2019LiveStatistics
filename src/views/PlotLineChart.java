/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Coviddata;
import org.jfree.ui.ApplicationFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.PlottingData;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import static views.FrameDataDisplay.dateFormatPattern;

/**
 *
 * @author panti
 */
public class PlotLineChart extends ApplicationFrame {
    
    PlottingData data;
    static final String dateFormatPattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat ;
    public PlotLineChart(final String title) {
        super(title);
        simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
    }
    
    public void CalculatePlot(PlottingData data){
        this.data = data;
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    /**
     * Creates a sample dataset.
     * 
     * @return The dataset.
     */
    private CategoryDataset createDataset() {
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int chartCount=0;
        String date="";
        String series="Επιβεβαιωμένα";
        String series2="Επιβεβαιωμένα Συνολικά";
        for(Coviddata dtype : data.getConfirmedList()){
            date = simpleDateFormat.format(dtype.getTrndate());
            if(data.getDailyData()) 
                dataset.addValue(dtype.getQty(), series, date);
            if(data.getAccumulativeData())
                dataset.addValue(dtype.getProodqty(), series2, date);
        }
        String series3="Ανάρρωσαν";
        String series4="Ανάρρωσαν Συνολικά";
        for(Coviddata dtype : data.getRecoveredList()){
            date = simpleDateFormat.format(dtype.getTrndate());
            if(data.getDailyData()) 
                dataset.addValue(dtype.getQty(), series3, date);
            if(data.getAccumulativeData())
                dataset.addValue(dtype.getProodqty(), series4, date);
        }
        
        String series5="Θανατοι";
        String series6="Θανατοι Συνολικά";
        for(Coviddata dtype : data.getDeathsList()){
            date = simpleDateFormat.format(dtype.getTrndate());
            if(data.getDailyData()) 
                dataset.addValue(dtype.getQty(), series5, date);
            if(data.getAccumulativeData())
                dataset.addValue(dtype.getProodqty(), series6, date);
        }


        return dataset;
                
    }
    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createLineChart(
            data.getTitle(),       // chart title
            "Type",                    // domain axis label
            "Value",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
    //    legend.setShapeScaleX(1.5);
      //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        

        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        //renderer.setDrawShapes(true);

        for(int i=0;i<=5;i++){
            renderer.setSeriesStroke(
                i, new BasicStroke(
                    2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                    1.0f, new float[] {2.0f, 6.0f}, 0.0f
                )
            );
        }
       
        
        return chart;
    }
    
}
