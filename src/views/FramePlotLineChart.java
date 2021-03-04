package views;

import entities.Coviddata;
import java.awt.Color;
import java.awt.Image;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import models.PlottingData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Pantelis Ioannidis
 */

//Η γραφική παράσταση της χρονοσειράς
public class FramePlotLineChart extends javax.swing.JFrame {

    /**
     * Creates new form FramePlotLineChart
     */
    PlottingData data;
    static final String dateFormatPattern = "dd/MM/yyyy";
    SimpleDateFormat simpleDateFormat ;
    
    public FramePlotLineChart() {
        initComponents();
        setIconImage();
        simpleDateFormat = new SimpleDateFormat(dateFormatPattern);
    }
    public void CalculatePlot(PlottingData data){
        this.data = data;
        final XYDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        this.setContentPane(chartPanel);
    }
    
    //Περνάμε τα data σε δομές που καταλαβαίνει η βιβλιοθήκη Jfreechart
    private XYDataset createDataset() {
        
        final TimeSeriesCollection  dataset = new TimeSeriesCollection ();

        //Θα διαπεράσουμε τις 3 λίστες και θα τις προσθέσουμε στο dataset του jfreechart
        //Ελέγχουμε αν είναι σωρευτικά ή καθημερινά δεδομένα για να επιλέξουμε το κατάλληλο πεδίο
        TimeSeries  series=new TimeSeries("Επιβεβαιωμένα");
        TimeSeries series2=new TimeSeries("Επιβεβαιωμένα Συνολικά");
        for(Coviddata dtype : data.getConfirmedList()){
            if(data.getShowDailyData()) 
                series.add(new Day(dtype.getTrndate()),dtype.getQty());
            if(data.getShowAccumulativeData())
                series2.add(new Day(dtype.getTrndate()),dtype.getProodqty());
        }
        
        
        TimeSeries  series3=new TimeSeries("Ανάρρωσαν");
        TimeSeries  series4=new TimeSeries("Ανάρρωσαν Συνολικά");
        for(Coviddata dtype : data.getRecoveredList()){
            if(data.getShowDailyData()) 
                series3.add(new Day(dtype.getTrndate()),dtype.getQty());
            if(data.getShowAccumulativeData())
                series4.add(new Day(dtype.getTrndate()),dtype.getProodqty());
        }
        
        
        TimeSeries  series5=new TimeSeries("Θανατοι");
        TimeSeries  series6=new TimeSeries("Θανατοι Συνολικά");
        for(Coviddata dtype : data.getDeathsList()){
            if(data.getShowDailyData()) 
                series5.add(new Day(dtype.getTrndate()),dtype.getQty());
            if(data.getShowAccumulativeData())
                series6.add(new Day(dtype.getTrndate()),dtype.getProodqty());
        }
        
        //ενημερώνουμε το dataset με όσες timeseries είχαν δεδομένα
        if(series.getItemCount()>0) dataset.addSeries(series);   
        if(series2.getItemCount()>0) dataset.addSeries(series2);
        if(series3.getItemCount()>0) dataset.addSeries(series3);
        if(series4.getItemCount()>0) dataset.addSeries(series4);
        if(series5.getItemCount()>0) dataset.addSeries(series5);
        if(series6.getItemCount()>0) dataset.addSeries(series6);

        return dataset;
                
    }
    
    // Εδώ κατασκευάζεται το chart περνόντας το dataset με τα δεδομένα
    // και κάνοντας κάποιες ρυθμίσεις στην εμφάνιση
    private JFreeChart createChart(XYDataset  dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
            data.getTitle(),      
            "Ημερομηνία",                    
            "Κρούσματα",                   
            dataset                 
        );
        
        //Τα χρώματα
        chart.setBackgroundPaint(Color.white);
        final XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);
               
       
        //Ρυθμίσης για τον άξονα Υ
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setVerticalTickLabels(false);
        rangeAxis.setAutoRangeIncludesZero(true);
        
        //Ρυθμίσεις για τον άξονα Χ
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
              
        return chart;
    }
    
    //Το Εικονίδιο στην γωνία του παραθύρου
    private void setIconImage() {
        Image image = new ImageIcon(this.getClass().getResource("/resources/covid-19.png")).getImage();
        this.setIconImage(image);
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
