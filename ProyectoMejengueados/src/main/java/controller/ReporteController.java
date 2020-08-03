package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Conexion;
import model.Persona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author wmolina
 */
@Named(value = "reporteController")
@RequestScoped
public class ReporteController {

    /**
     * Creates a new instance of ReporteController
     */
    public ReporteController() {
    }

    

    public void verPdf() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona/registro2.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type", "application/pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();

            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void descargarPdf() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/persona/registro2.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.addHeader("Content-disposition", "attachment; filename=reporte.pdf");
            ServletOutputStream flujo = respuesta.getOutputStream();

            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void verXml() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporte.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.setContentType("application/xml");
            respuesta.addHeader("Content-Type", "application/xml");
            ServletOutputStream flujo = respuesta.getOutputStream();

            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void descargarXml() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/reporte.jasper"));
            JasperPrint reporteJasper = JasperFillManager.fillReport(jasper.getPath(), null, Conexion.getConexion());

            HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            respuesta.addHeader("Content-disposition", "attachment; filename=reporte.xml");
            ServletOutputStream flujo = respuesta.getOutputStream();

            JasperExportManager.exportReportToXmlStream(reporteJasper, flujo);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (JRException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
