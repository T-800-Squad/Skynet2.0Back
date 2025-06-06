package edu.eci.cvds.Labtools.dto;



/**
 * Clase CreateBookingDTO (Data Transfer Object) que representa la información necesaria
 * para crear una nueva reserva. Esta clase se utiliza para transferir datos desde la capa
 * de presentación a la capa de servicio.
 * @author Miguel Angel Vanegas Cardenas, Yojhan Toro Rivera e Ivan Cubillos Vela.
 */

public class CreateBookingDTO {
    private String userName;
    private String labName;
    private String date;
    private int priority;

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getLabName(){
        return labName;
    }
    public void setLabName(String labName){
        this.labName = labName;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
    public int getPriority(){ return priority;}
    public void setPriority(int priority){ this.priority = priority;}
}
