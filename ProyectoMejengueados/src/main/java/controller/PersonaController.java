/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.Persona;
import model.PersonaGestion;

/**
 *
 * @author wmolina
 */
@Named(value = "personaController")
@RequestScoped
public class PersonaController extends Persona implements Serializable {

    private String tiraJson;

    /**
     * Creates a new instance of ProspectoController
     */
    public void respaldo(){
        ZipOutputStream out= null;
        try {
            String json= PersonaGestion.generarJson();
            File f= new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/respaldo")+"personas.zip");
            out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e=new ZipEntry("personas.jason");
            out.putNextEntry(e);
            byte [] data = json.getBytes();
            out.write(data, 0, data.length);
            out.close();
            File zipPath = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/respaldo")+"personas.zip");
            byte[] zip = Files.readAllBytes(zipPath.toPath());
            HttpServletResponse respuesta =(HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream sos = respuesta.getOutputStream();
            respuesta.setContentType("application/zip");
            respuesta.setHeader("Content-Disposition", "attachment: filename=personas.zip");
            sos.write(zip);
            sos.flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        
    }
    
    
    public void recupera(String cedula) {
                Persona persona = PersonaGestion.getPersona(cedula);
        if (persona != null) {
            this.setCedula(persona.getCedula());
            this.setNombre(persona.getNombre());
            this.setApellido1(persona.getApellido1());
            this.setApellido2(persona.getApellido2());
            this.setFechaNacimiento(persona.getFechaNacimiento());
            this.setCorreo(persona.getCorreo());
            this.setCelular(persona.getCelular());
            noImprimir = false;
        } else {
            this.setNombre("");
            this.setApellido1("");
            this.setApellido2("");
            this.setFechaNacimiento(null);
            this.setCorreo("");
            this.setCelular("");
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "No encontrado",
                    "Estudiante no encontrado");
            FacesContext.getCurrentInstance().addMessage(
                    "cerficicacionEstudianteForm:identificacion", msg);
            noImprimir = true;
        }


    }
   public void creaJson() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaNacimiento = dateFormat.format(this.getFechaNacimiento());
        JsonObjectBuilder creadorJson = Json.createObjectBuilder();
        JsonObject objetoJson = creadorJson.
                add("cedula", this.getCedula())
                .add("nombre", this.getNombre())
                .add("apellido1", this.getApellido1())
                .add("apellido2", this.getApellido2())
                .add("fechaNacimiento", fechaNacimiento)
                .add("correo", this.getCorreo())
                .add("celular", this.getCelular())
                .build();
        StringWriter tira = new StringWriter();
        JsonWriter jsonWriter = Json.createWriter(tira);
        jsonWriter.writeObject(objetoJson);
        setTiraJson(tira.toString());
    }
   public void creaObjectPreospecto() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            
            JsonReader lectorJson = Json.createReader(new StringReader(tiraJson));
            JsonObject objetoJson = lectorJson.readObject();
            this.setCedula(objetoJson.getString("cedula"));
            this.setNombre(objetoJson.getString("nombre"));
            this.setApellido1(objetoJson.getString("apellido1"));
            this.setApellido2(objetoJson.getString("apellido2"));
            this.setFechaNacimiento(sdf.parse(objetoJson.getString("fechaNacimiento")));
            this.setCorreo(objetoJson.getString("correo"));
            this.setCelular(objetoJson.getString("celular"));
            
        } catch (ParseException ex) {
            Logger.getLogger(PersonaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public PersonaController() {
    }
    private boolean noImprimir = true;  //Para el boton de imprimir certificación

    public boolean isImprimir() {
        return noImprimir;
    }

    public void setImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void editas(String cedula) {
        Persona persona = PersonaGestion.getPersona(cedula);
        if (persona != null) {
            this.setCedula(persona.getCedula());
            this.setNombre(persona.getNombre());
            this.setApellido1(persona.getApellido1());
            this.setApellido2(persona.getApellido2());
            this.setFechaNacimiento(persona.getFechaNacimiento());
            this.setCorreo(persona.getCorreo());
            this.setCelular(persona.getCelular());
            noImprimir = false;
        } else {
            this.setNombre("");
            this.setApellido1("");
            this.setApellido2("");
            this.setFechaNacimiento(null);
            this.setCorreo("");
            this.setCelular("");
            FacesMessage msg = new FacesMessage(
                    FacesMessage.SEVERITY_WARN, "No encontrado",
                    "Estudiante no encontrado");
            FacesContext.getCurrentInstance().addMessage(
                    "cerficicacionEstudianteForm:identificacion", msg);
            noImprimir = true;
        }

    }

    public String save() {
        if (PersonaGestion.insertar(this)) {
            FacesMessage msg = new FacesMessage("Creado " + this.getNombre());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "confirmacion.xhtml";
        } else {
            FacesMessage msg = new FacesMessage("Error creando el registro");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "persona.xhtml";
        }
    }

    public String getTiraJson() {
        return tiraJson;
    }

    public void setTiraJson(String tiraJson) {
        this.tiraJson = tiraJson;
    }

}
