/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so2_project;

import Estructuras.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author giovannacianfaglione
 * 
 */
public class Admin extends Thread{
    
    
    private Queue cola1Nintendo = new Queue(); 
    private Queue cola2Nintendo  = new Queue(); 
    private Queue cola3Nintendo  = new Queue(); 
    private Queue colaRefuerzoNintendo  = new Queue(); 
    private Queue cola1Capcon  = new Queue(); 
    private Queue cola2Capcon  = new Queue();
    private Queue cola3Capcon  = new Queue(); 
    private Queue colaRefuerzoCapcon  = new Queue();  
    private int contadorNintendo = 0; 
    private int contadorCapcom = 0; 
    private Queue ganadoresNintendo = new Queue();
    private Queue ganadoresCapcom = new Queue(); 
    public Semaphore mutex = new Semaphore(1); 
    
    
    
    @Override
    public void run(){
        try {
            this.crearPersonajes(); // crea los primeros juegos 
        } catch (InterruptedException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
     }
// public Personajes(int id, String empresa)
    
    public void crearPersonajes() throws InterruptedException{
        //nintendo
        for (int i = 0; i < 10; i++) {
            Personajes p = new Personajes(getContadorNintendo(), "Nintendo"); 
            mutex.acquire(); 
            if (p.getPrioridad() == 1) {
                this.getCola1Nintendo().enqueue(p);
            }else if(p.getPrioridad() == 2){
                this.getCola2Nintendo().enqueue(p);
            }else{
                this.getCola3Nintendo().enqueue(p); 
            }mutex.release();
            this.setContadorNintendo(this.getContadorNintendo() + 1); 
            
        }
        
        // Capcom 
         for (int i = 0; i < 10; i++) {
            Personajes pe = new Personajes(getContadorCapcom(), "Capcom");
            mutex.acquire();
            if (pe.getPrioridad() == 1) {
                this.getCola1Capcon().enqueue(pe);
            }else if(pe.getPrioridad() == 2){
                this.getCola2Capcon().enqueue(pe);
            }else{
                this.getCola3Capcon().enqueue(pe); 
            }mutex.release();
            this.setContadorCapcom(this.getContadorCapcom() + 1); 
            
        }
         // ahora revisamos si alguna lista esta vacia: 
         // capcom: 
         mutex.acquire();
         this.setContadorCapcom(this.revisarColasVacias(getCola1Capcon(), 1, getContadorCapcom(), "Capcom"));
         this.setContadorCapcom(this.revisarColasVacias(getCola2Capcon(), 2, getContadorCapcom(), "Capcom"));
         this.setContadorCapcom(this.revisarColasVacias(getCola3Capcon(), 3, getContadorCapcom(), "Capcom"));
         
         // nintendo
         this.setContadorNintendo(this.revisarColasVacias(getCola1Nintendo(), 1, getContadorNintendo(), "Nintendo")); 
         this.setContadorNintendo(this.revisarColasVacias(getCola2Nintendo(), 2, getContadorNintendo(), "Nintendo")); 
         this.setContadorNintendo(this.revisarColasVacias(getCola3Nintendo(), 3, getContadorNintendo(), "Nintendo")); 
        mutex.release();
        
    }
    
    public int revisarColasVacias(Queue cola, int prioridad, int contadorEmpresa, String empresa) throws InterruptedException{
      mutex.acquire();
        if(cola.isEmpty()){

            for (int i = 0; i < 5; i++) {
                Personajes p = new Personajes(contadorEmpresa, empresa); 
                p.setPrioridad(prioridad);
                cola.enqueue(p);
                contadorEmpresa ++; 
            }mutex.release();
        }return contadorEmpresa; 
    }
    
    public Torneo getTorneo() throws InterruptedException{
        Torneo torneo = new Torneo();
        mutex.acquire();
        torneo.setpCapcom((Personajes)this.getCola1Capcon().dequeue().getData());
        torneo.setpNintendo((Personajes)this.getCola1Nintendo().dequeue().getData());
        mutex.release();
         return torneo; 
    }
 
    public void generatePersonajes() throws InterruptedException{
        Random random = new Random(); 
        double randomValue = random.nextDouble();
        if(randomValue<0.8){
            Personajes p = new Personajes(this.getContadorNintendo(), "Nintendo"); 
            mutex.acquire();
            if(p.getPrioridad() == 1){
                this.getCola1Nintendo().enqueue(p);
            }else if(p.getPrioridad() == 2){
                this.getCola2Nintendo().enqueue(p);
            }else{
                this.getCola3Nintendo().enqueue(p);
            }mutex.release();
            this.setContadorNintendo(this.getContadorNintendo() + 1); 
            Personajes p2 = new Personajes(this.getContadorCapcom(), "Capcom"); 
            mutex.acquire();
            if(p2.getPrioridad() ==1){
                this.getCola1Capcon().enqueue(p2); 
            }else if(p2.getPrioridad() == 2){
                this.getCola2Capcon().enqueue(p2); 
            }else{
                this.getCola3Capcon().enqueue(p2); 
            }mutex.release(); 
            this.setContadorCapcom(this.getContadorCapcom() + 1); 

        }

    }
    
    
    
    /**
     * @return the cola1Nintendo
     */
    public Queue getCola1Nintendo() {
        return cola1Nintendo;
    }

    /**
     * @param cola1Nintendo the cola1Nintendo to set
     */
    public void setCola1Nintendo(Queue cola1Nintendo) {
        this.cola1Nintendo = cola1Nintendo;
    }

    /**
     * @return the cola2Nintendo
     */
    public Queue getCola2Nintendo() {
        return cola2Nintendo;
    }

    /**
     * @param cola2Nintendo the cola2Nintendo to set
     */
    public void setCola2Nintendo(Queue cola2Nintendo) {
        this.cola2Nintendo = cola2Nintendo;
    }

    /**
     * @return the cola3Nintendo
     */
    public Queue getCola3Nintendo() {
        return cola3Nintendo;
    }

    /**
     * @param cola3Nintendo the cola3Nintendo to set
     */
    public void setCola3Nintendo(Queue cola3Nintendo) {
        this.cola3Nintendo = cola3Nintendo;
    }

    /**
     * @return the colaRefuerzoNintendo
     */
    public Queue getColaRefuerzoNintendo() {
        return colaRefuerzoNintendo;
    }

    /**
     * @param colaRefuerzoNintendo the colaRefuerzoNintendo to set
     */
    public void setColaRefuerzoNintendo(Queue colaRefuerzoNintendo) {
        this.colaRefuerzoNintendo = colaRefuerzoNintendo;
    }

    /**
     * @return the cola1Capcon
     */
    public Queue getCola1Capcon() {
        return cola1Capcon;
    }

    /**
     * @param cola1Capcon the cola1Capcon to set
     */
    public void setCola1Capcon(Queue cola1Capcon) {
        this.cola1Capcon = cola1Capcon;
    }

    /**
     * @return the cola2Capcon
     */
    public Queue getCola2Capcon() {
        return cola2Capcon;
    }

    /**
     * @param cola2Capcon the cola2Capcon to set
     */
    public void setCola2Capcon(Queue cola2Capcon) {
        this.cola2Capcon = cola2Capcon;
    }

    /**
     * @return the cola3Capcon
     */
    public Queue getCola3Capcon() {
        return cola3Capcon;
    }

    /**
     * @param cola3Capcon the cola3Capcon to set
     */
    public void setCola3Capcon(Queue cola3Capcon) {
        this.cola3Capcon = cola3Capcon;
    }

    /**
     * @return the colaRefuerzoCapcon
     */
    public Queue getColaRefuerzoCapcon() {
        return colaRefuerzoCapcon;
    }

    /**
     * @param colaRefuerzoCapcon the colaRefuerzoCapcon to set
     */
    public void setColaRefuerzoCapcon(Queue colaRefuerzoCapcon) {
        this.colaRefuerzoCapcon = colaRefuerzoCapcon;
    }

    /**
     * @return the contadorNintendo
     */
    public int getContadorNintendo() {
        return contadorNintendo;
    }

    /**
     * @param contadorNintendo the contadorNintendo to set
     */
    public void setContadorNintendo(int contadorNintendo) {
        this.contadorNintendo = contadorNintendo;
    }

    /**
     * @return the contadorCapcom
     */
    public int getContadorCapcom() {
        return contadorCapcom;
    }

    /**
     * @param contadorCapcom the contadorCapcom to set
     */
    public void setContadorCapcom(int contadorCapcom) {
        this.contadorCapcom = contadorCapcom;
    }

    /**
     * @return the ganadoresNintendo
     */
    public Queue getGanadoresNintendo() {
        return ganadoresNintendo;
    }

    /**
     * @param ganadoresNintendo the ganadoresNintendo to set
     */
    public void setGanadoresNintendo(Queue ganadoresNintendo) {
        this.ganadoresNintendo = ganadoresNintendo;
    }

    /**
     * @return the ganadoresCapcom
     */
    public Queue getGanadoresCapcom() {
        return ganadoresCapcom;
    }

    /**
     * @param ganadoresCapcom the ganadoresCapcom to set
     */
    public void setGanadoresCapcom(Queue ganadoresCapcom) {
        this.ganadoresCapcom = ganadoresCapcom;
    }


    
    
}
