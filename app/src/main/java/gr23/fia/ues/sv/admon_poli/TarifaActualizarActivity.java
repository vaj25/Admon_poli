package gr23.fia.ues.sv.admon_poli;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint("NewApi")
public class TarifaActualizarActivity extends AppCompatActivity {

    ControlBD helper;
    List<Tarifa> lista ;
    //EditText EditMonto;
    EditText HorasTarifa;
    EditText PersonasTarifa;
    EditText montoTarifa;
    Spinner idTarifa ;
    private Conexion conexion;
    private final String service = "/AdmonPoli/actualizar_tarifa.php?";

    @SuppressLint("NewApi")
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_actualizar);
        helper=new ControlBD(this);
        conexion = new Conexion();

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //EditMonto=(EditText)findViewById(R.id.editText16);
        HorasTarifa=(EditText)findViewById(R.id.cantHoras);
        PersonasTarifa=(EditText)findViewById(R.id.cantPersonas);
        montoTarifa=(EditText)findViewById(R.id.monto);
        idTarifa = (Spinner) findViewById(R.id.selectTarifa) ;

        lista = new ArrayList<>();
        lista=helper.consultaTarifa();

        ArrayAdapter<Tarifa> adaptador =new ArrayAdapter<Tarifa>(this,android.R.layout.simple_spinner_item,lista);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idTarifa.setAdapter(adaptador);
    }

    public void actualizarTarifa(View v) {
        Tarifa tarif= new Tarifa();
        //Solicitante sol = new Solicitante();
        //tarif = helper.consultarTarifa(idtarifa);
        tarif.setIdTarifa(posicion());
        tarif.setCanthora(Double.parseDouble(HorasTarifa.getText().toString()));
        tarif.setCantPersonas(Integer.parseInt(PersonasTarifa.getText().toString()));
        tarif.monto();
        double precio = tarif.getPrecio();

        helper.abrir();
        String estado = helper.actualizar(tarif);
        helper.cerrar();
        montoTarifa.setText(String.valueOf(precio));
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void actualizarTarifaServices(View v){
        Tarifa tarifa = new Tarifa(posicion(), Double.parseDouble(HorasTarifa.getText().toString()),
                Integer.parseInt(PersonasTarifa.getText().toString()));
        tarifa.monto();
        String url = conexion.getURLLocal() + service + "idtarifa=" + tarifa.getIdTarifa() + "&precio=" + tarifa.getPrecio()
                + "&canthora=" + tarifa.getCanthora() + "&cantpersona=" + tarifa.getCantPersonas();
        String tarifaActualizada = ControlServicio.obtenerRespuestaPeticion(url, this);
        montoTarifa.setText(String.valueOf(tarifa.getPrecio()));
        int dato = ControlServicio.verifiacaActualizar(tarifaActualizada, this);
        if(dato == 0)
            Toast.makeText(this, "Registro no actualizado", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Registro actualizado", Toast.LENGTH_LONG).show();
    }

    public void limpiarTexto(View v) {   //metodo convocado en el evento onclick del boton limpiar en el layout
        HorasTarifa.setText("");
        PersonasTarifa.setText("");
    }

    public int posicion() {
        int position = idTarifa.getSelectedItemPosition();
        Iterator iterador = lista.listIterator();
        int count = 0;
        int idtarifa = 0;
        while (iterador.hasNext()) {
            Tarifa tar = (Tarifa) iterador.next();
            if (count == position) {
                idtarifa = tar.getIdTarifa();
            }
            count++;
        }
        return idtarifa;
    }
}