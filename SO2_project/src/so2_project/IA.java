/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so2_project;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovannacianfaglione
 */
public class IA extends Thread{
    private int contadorParejas = 0;
    Main main = new Main(); 
    private int dayDuration;

    public IA(int dayDuration) {
        this.dayDuration=dayDuration;
        
        
    }
    
    
    
    @Override
    public void run(){ 
        
        try {
            
            this.checkCounter(); //si ya pasaron 2 parejas el genera una pareja de jugadore snintendo y capcom
            this.finalizarTorneo(); //para selccionar al ganador o empateo o no aplica 
            sleep(this.dayDuration*1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(IA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    
    
    
    
    public void checkCounter() throws InterruptedException{
        if(contadorParejas == 2){
            main.admin.generatePersonajes();
            this.contadorParejas = 0; 
        }
    }
    
        public String crearTipoDeTorneo(){
        String tipoDeTorneo; 
        this.contadorParejas ++; 
         Random random = new Random(); 
        double randomValue = random.nextDouble();
        if(randomValue <0.4){
            tipoDeTorneo = "ganadores"; 

        }else if(randomValue <0.67){
            tipoDeTorneo = "empate"; 
        }else{
            tipoDeTorneo = "refuerzo"; 
            
        }return tipoDeTorneo; 
        
    }
        
     public void finalizarTorneo() throws InterruptedException{
         
         Torneo torneo = main.admin.getTorneo(); 
         String tipoDeTorneo = this.crearTipoDeTorneo(); 
         
         if(tipoDeTorneo.equals("ganadores")){
             torneo.getpCapcom().updatePower();
             torneo.getpNintendo().updatePower();
             main.admin.mutex.acquire();
             if(torneo.getpNintendo().getPower().equals("Master Sword") && torneo.getpCapcom().getPower().equals("Shoryuken")){
                 torneo.getpNintendo().setGanador(true);
                 torneo.getpCapcom().setGanador(false);
                 main.admin.getGanadoresNintendo().enqueue(torneo.getpNintendo());
                 
             }else if(torneo.getpNintendo().getPower().equals("Tri Fuerza") && torneo.getpCapcom().getPower().equals("Haduken")){
                 torneo.getpNintendo().setGanador(true);
                 torneo.getpCapcom().setGanador(false);
                 main.admin.getGanadoresNintendo().enqueue(torneo.getpNintendo());
             } else if(torneo.getpNintendo().getPower().equals("Tri Fuerza") && torneo.getpCapcom().getPower().equals("Shoryuken")){
                  torneo.getpNintendo().setGanador(false);
                 torneo.getpCapcom().setGanador(true);
                 main.admin.getGanadoresCapcom().enqueue(torneo.getpCapcom());
             }else if(torneo.getpNintendo().getPower().equals("Master Sword") && torneo.getpCapcom().getPower().equals("Haduken")){
                  torneo.getpNintendo().setGanador(false);
                 torneo.getpCapcom().setGanador(true);
                 main.admin.getGanadoresCapcom().enqueue(torneo.getpCapcom());
             }
             
             
//                private String[] powersCapcom = {"Haduken","Shoryuken"};
//    private String[] powersNintendo= {"Master Sword","Tri Fuerza"};

             
         }else if(tipoDeTorneo.equals("empate")){
             main.admin.getCola1Capcon().enqueue(torneo.getpCapcom());
             torneo.getpCapcom().setPrioridad(1);
             main.admin.getCola1Nintendo().enqueue(torneo.getpNintendo());
             torneo.getpNintendo().setPrioridad(1);
             
         }else{
             main.admin.getColaRefuerzoCapcon().enqueue(torneo.getpCapcom());
             main.admin.getColaRefuerzoNintendo().enqueue(torneo.getpNintendo());
             
         }
         main.admin.mutex.release();
     }

    /**
     * @return the contadorParejas
     */
    public int getContadorParejas() {
        return contadorParejas;
    }

    /**
     * @param contadorParejas the contadorParejas to set
     */
    public void setContadorParejas(int contadorParejas) {
        this.contadorParejas = contadorParejas;
    }
    
}
