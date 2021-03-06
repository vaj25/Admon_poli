package gr23.fia.ues.sv.admon_poli;

/**
 * Created by FAMILY on 07/05/2016.
 */
public class Horario {


    private int idHorario;
    private String hora;
    private String dia;
    private Boolean instructor;

    public Horario(int idHorario, String hora, String dia, Boolean instructor){
        this.idHorario = idHorario;
        this.hora = hora;
        this.dia = dia;
        this.instructor = instructor;
    }

    public Horario() {
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getHora() {
        return hora;
    }
    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }

    public Boolean getInstructor() {
        return instructor;
    }
    public void setInstructor(Boolean instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return idHorario +"-"+ dia + " " +hora;
    }
}
