package gr23.fia.ues.sv.admon_poli;

/**
 * Created by FAMILY on 07/05/2016.
 */
public class Reserva {


    private int idReserva;
    private int idHorario;
    private String fechaReserva;
    private String tiempoReserva;

    public Reserva(int idReserva, int idHorario, String fechaReserva, String tiempoReserva){
        this.idReserva = idReserva;
        this.idHorario = idHorario;
        this.fechaReserva = fechaReserva;
        this.tiempoReserva = tiempoReserva;
    }

    public Reserva() {
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getFechaReserva(){
        return fechaReserva;
    }
    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getTiempoReserva() {
        return tiempoReserva;
    }

    public void setTiempoReserva(String tiempoReserva) {
        this.tiempoReserva = tiempoReserva;
    }

    @Override
    public String toString() {
        return idReserva +"-"+ fechaReserva;
    }


}
