/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so2_project;

import java.util.Random;

/**
 *
 * @author giovannacianfaglione
 */
public class Personajes  {
    private int id;
    private int prioridad;
    private String nombre;
    private String pathImage;
    private int contadorPrioridad;
    private int[] estadisticas;
    private String empresa;
    private String pathPower;
    private String powerNintendo;
    private String powerCapcom;
    private String[] nombresPersonajesNintendo;
    private String[] nombresPersonajesCapcom;
    private String[] pathPersonajesNintendo;
    private String[] pathPersonajesCapcom;
    private String[] powersCapcom = {"Haduken","Shoryuken"};
    private String[] powersNintendo= {"Master Sword","Tri Fuerza"};
    private String power;
    private boolean ganador;

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
    }
    

    public Personajes(int id, String empresa) {
        this.id = id;
        this.empresa=empresa;
        this.nombrePersonaje();
        this.sacarEstadisticas();
        
    }
    
    public void nombrePersonaje(){
        Random random = new Random();
        int index= random.nextInt(15);
        
        if(this.getEmpresa().equals("Nintendo")){
            this.setNombre(this.getNombresPersonajesNintendo()[index]); 
            this.setPathImage(this.getPathPersonajesNintendo()[index]);
           
            
        }else{
            this.setNombre(this.getNombresPersonajesCapcom()[index]); 
            this.setPathImage(this.getPathPersonajesCapcom()[index]);
        }
        
        
    }
    
    public void updatePower(){
        Random random = new Random(); 
        int index = random.nextInt(2);
         if(this.getEmpresa().equals("Nintendo")){
             this.setPower(this.getPowersNintendo()[index]); 
         }else{
             this.setPower(this.getPowersCapcom()[index]); 
         }
    }
    
    public void sacarPrioridad(){
        int total = 0; 
        for (int i = 0; i < this.getEstadisticas().length; i++) {
            total += this.getEstadisticas()[i]; 
            
        }
        
        if(total == 4 ||total == 5){
            this.setPrioridad(3); 
        }else if(total >= 6 && total <= 8){
            this.setPrioridad(2); 
        }else{
            this.setPrioridad(1); 
        }
    }
        
    public void sacarEstadisticas(){
        Random random = new Random();
        double randomValue = random.nextDouble();
        this.setEstadisticas(new int[4]);
        for (int i=0; i<=3; i++){
            
        }
        
        if (randomValue < 0.6) {
            this.getEstadisticas()[0]= 3;
        } else if (randomValue < 0.8) {
            this.getEstadisticas()[0] = 1;
        } else {
            this.getEstadisticas()[0] = 2;
        }
        // ahora 1; 
        if (randomValue < 0.7) {
            this.getEstadisticas()[1]= 3;
        } else if (randomValue < 0.85) {
            this.getEstadisticas()[1] = 1;
        } else {
            this.getEstadisticas()[1] = 2;
        }
        // ahora 2; 
         if (randomValue < 0.5) {
             this.getEstadisticas()[2]= 3;
        } else if (randomValue < 0.75) {
            this.getEstadisticas()[2] = 1;
        } else {
            this.getEstadisticas()[2] = 2;
        }
         // ahora 3; 
          if (randomValue < 0.4) {
              this.getEstadisticas()[3]= 3;
        } else if (randomValue < 0.7) {
            this.getEstadisticas()[3] = 1;
        } else {
            this.getEstadisticas()[3] = 2;
        }
        
        
        
    }
    
    public void subirPrioridad(){
      
      if (this.getContadorPrioridad() ==8){
          if(this.getPrioridad() ==3){
              this.setPrioridad(2);
              
          }else if(this.getPrioridad()==2){
              this.setPrioridad(1);
              
          }else if(this.getPrioridad()==1){
              this.setPrioridad(1);
//              stay
          }
      }   
  } 

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the pathImage
     */
    public String getPathImage() {
        return pathImage;
    }

    /**
     * @param pathImage the pathImage to set
     */
    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    /**
     * @return the contadorPrioridad
     */
    public int getContadorPrioridad() {
        return contadorPrioridad;
    }

    /**
     * @param contadorPrioridad the contadorPrioridad to set
     */
    public void setContadorPrioridad(int contadorPrioridad) {
        this.contadorPrioridad = contadorPrioridad;
    }

    /**
     * @return the estadisticas
     */
    public int[] getEstadisticas() {
        return estadisticas;
    }

    /**
     * @param estadisticas the estadisticas to set
     */
    public void setEstadisticas(int[] estadisticas) {
        this.estadisticas = estadisticas;
    }

    /**
     * @return the empresa
     */
    public String getEmpresa() {
        return empresa;
    }

    /**
     * @param empresa the empresa to set
     */
    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    /**
     * @return the pathPower
     */
    public String getPathPower() {
        return pathPower;
    }

    /**
     * @param pathPower the pathPower to set
     */
    public void setPathPower(String pathPower) {
        this.pathPower = pathPower;
    }

    /**
     * @return the powerNintendo
     */
    public String getPowerNintendo() {
        return powerNintendo;
    }

    /**
     * @param powerNintendo the powerNintendo to set
     */
    public void setPowerNintendo(String powerNintendo) {
        this.powerNintendo = powerNintendo;
    }

    /**
     * @return the powerCapcom
     */
    public String getPowerCapcom() {
        return powerCapcom;
    }

    /**
     * @param powerCapcom the powerCapcom to set
     */
    public void setPowerCapcom(String powerCapcom) {
        this.powerCapcom = powerCapcom;
    }

    /**
     * @return the nombresPersonajesNintendo
     */
    public String[] getNombresPersonajesNintendo() {
        return nombresPersonajesNintendo;
    }

    /**
     * @param nombresPersonajesNintendo the nombresPersonajesNintendo to set
     */
    public void setNombresPersonajesNintendo(String[] nombresPersonajesNintendo) {
        this.nombresPersonajesNintendo = nombresPersonajesNintendo;
    }

    /**
     * @return the nombresPersonajesCapcom
     */
    public String[] getNombresPersonajesCapcom() {
        return nombresPersonajesCapcom;
    }

    /**
     * @param nombresPersonajesCapcom the nombresPersonajesCapcom to set
     */
    public void setNombresPersonajesCapcom(String[] nombresPersonajesCapcom) {
        this.nombresPersonajesCapcom = nombresPersonajesCapcom;
    }

    /**
     * @return the pathPersonajesNintendo
     */
    public String[] getPathPersonajesNintendo() {
        return pathPersonajesNintendo;
    }

    /**
     * @param pathPersonajesNintendo the pathPersonajesNintendo to set
     */
    public void setPathPersonajesNintendo(String[] pathPersonajesNintendo) {
        this.pathPersonajesNintendo = pathPersonajesNintendo;
    }

    /**
     * @return the pathPersonajesCapcom
     */
    public String[] getPathPersonajesCapcom() {
        return pathPersonajesCapcom;
    }

    /**
     * @param pathPersonajesCapcom the pathPersonajesCapcom to set
     */
    public void setPathPersonajesCapcom(String[] pathPersonajesCapcom) {
        this.pathPersonajesCapcom = pathPersonajesCapcom;
    }

    /**
     * @return the powersCapcom
     */
    public String[] getPowersCapcom() {
        return powersCapcom;
    }

    /**
     * @param powersCapcom the powersCapcom to set
     */
    public void setPowersCapcom(String[] powersCapcom) {
        this.powersCapcom = powersCapcom;
    }

    /**
     * @return the powersNintendo
     */
    public String[] getPowersNintendo() {
        return powersNintendo;
    }

    /**
     * @param powersNintendo the powersNintendo to set
     */
    public void setPowersNintendo(String[] powersNintendo) {
        this.powersNintendo = powersNintendo;
    }

    /**
     * @return the power
     */
    public String getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(String power) {
        this.power = power;
    }
        
        
        
    }

   
    
    

    
    
    
    
    
    
  
  
//  public String escogerNombreImagen(){
//      
//  }
//  
//  public int[] escogerEstadisticas(){
//    
//}
//  
//  public void escogerPower(){
//      
//  }
  
    
    
    

