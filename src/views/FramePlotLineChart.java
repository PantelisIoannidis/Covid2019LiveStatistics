/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Coviddata;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import models.PlottingData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Pantelis Ioannidis
 */
public class FramePlotLineChart extends javax.swing.JFrame {

    /**
     * Creates new form FramePlotLineChart
     */
    PlottingData data;
    static final String dateFormatPattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat ;
    
    public FramePlotLineChart() {
        initComponents();
        simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
    }
    public void CalculatePlot(PlottingData data){
        this.data = data;
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        this.setContentPane(chartPanel);
    }
    
    //Περνάμε τα data σε δομές που καταλαβαίνει η βιβλιοθήκη Jfreechart
    private CategoryDataset createDataset() {
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //Θα διαπεράσουμε τις 3 λίστες και θα τις προσθέσουμε στο dataset του jfreechart
        //Ελέγχουμε αν είναι σωρευτικά ή καθημερινά δεδομένα για να επιλέξουμε το κατάλληλο πεδίο
        String date="";
        String series="Επιβεβαιωμένα";
        String series2="Επιβεβαιωμένα Συνολικά";
        for(Coviddata dtype : data.getConfirmedList()){
            date = simpleDateFormat.format(dtype.getTrndate());
            if(data.getShowDailyData()) 
                dataset.addValue(dtype.getQty(), series, date);
            if(data.getShowAccumulativeData())
                dataset.addValue(dtype.getProodqty(), series2, date);
        }
        String series3="Ανάρρωσαν";
        String series4="Ανάρρωσαν Συνολικά";
        for(Coviddata dtype : data.getRecoveredList()){
            date = simpleDateFormat.format(dtype.getTrndate());
            if(data.getShowDailyData()) 
                dataset.addValue(dtype.getQty(), series3, date);
            if(data.getShowAccumulativeData())
                dataset.addValue(dtype.getProodqty(), series4, date);
        }
        
        String series5="Θανατοι";
        String series6="Θανατοι Συνολικά";
        for(Coviddata dtype : data.getDeathsList()){
            date = simpleDateFormat.format(dtype.getTrndate());
            if(data.getShowDailyData()) 
                dataset.addValue(dtype.getQty(), series5, date);
            if(data.getShowAccumulativeData())
                dataset.addValue(dtype.getProodqty(), series6, date);
        }


        return dataset;
                
    }
    
    // Εδώ κατασκευάζεται το chart περνόντας το dataset με τα δεδομένα
    // και κάνοντας κάποιες ρυθμίσεις στην εμφάνιση
    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createLineChart(
            data.getTitle(),      
            "Ημερομηνία",                    
            "Κρούσματα",                   
            dataset,                   
            PlotOrientation.VERTICAL,  
            true,                      
            true,                      
            false                      
        );
        
        //Τα χρώματα
        chart.setBackgroundPaint(Color.white);
        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
        
        
        CategoryItemRenderer renderer =   plot.getRenderer();
        renderer.setSeriesVisible(0, true);

        
        //Ρυθμίσης για τον άξονα Υ
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVerticalTickLabels(false);
        rangeAxis.setAutoRangeIncludesZero(true);
        
        //Ρυθμίσεις για τον άξονα Χ
        CategoryAxis categoryAxis = (CategoryAxis) plot.getDomainAxis();
        categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);  
        categoryAxis.setMaximumCategoryLabelLines(1);
        
        
        
        return chart;
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setTitle("Γράφημα δεδομένων covid19");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 964, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePlotLineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePlotLineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePlotLineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePlotLineChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePlotLineChart().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
