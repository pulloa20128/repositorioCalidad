package dashboard;

import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.CanchaGestion;
//import model.YearGender;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.LineChartModel;

@Named(value = "chartAreaView")
@Dependent
public class ChartAreaView {
    private LineChartModel areaModel;

    public ChartAreaView() {
    }
    public LineChartModel getAreaModel() {
        return areaModel;
    }

    @PostConstruct
    public void init() {
        createAreaModel();
    }

    private void createAreaModel() {
        areaModel = new LineChartModel();

        LineChartSeries boys = new LineChartSeries();
        boys.setFill(true);
        boys.setLabel("Masculino");

        LineChartSeries girls = new LineChartSeries();
        girls.setFill(true);
        girls.setLabel("Femenino");
        
//        ArrayList<YearGender> lista = 
//                EstudianteGestion.getIngresoYearGender();
//        int mayor=lista.get(0).getTotal(); //haciendo el primero mayor
//        for(YearGender dato : lista) {
//            if (dato.getGenero().equalsIgnoreCase("M")) {
//                boys.set(dato.getYear(), dato.getTotal());
//            } else {
//                girls.set(dato.getYear(), dato.getTotal());
//            }
//            if (mayor<dato.getTotal()) {
//                mayor=dato.getTotal();
//            }
//        }
        
        areaModel.addSeries(boys);
        areaModel.addSeries(girls);

        areaModel.setTitle("Total de ingresos por año según género");
        areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);

        Axis xAxis = new CategoryAxis("Año");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Ingresos");
        yAxis.setMin(0);
        //yAxis.setMax(mayor*2);
    }
}
