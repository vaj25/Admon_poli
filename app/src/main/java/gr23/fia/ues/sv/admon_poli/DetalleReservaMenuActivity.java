package gr23.fia.ues.sv.admon_poli;



import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DetalleReservaMenuActivity extends ListActivity {

    public ArrayList<String> resultados= new ArrayList<>();
    String id;
    ControlBD helper;
    String[] menu={"Insertar Detalle de Reserva","Eliminar Detalle de Reserva","Consultar Detalle de Reserva", "Actualizar Detalle de Reserva"};
    String[] activities={"DetalleReservaInsertarActivity","DetalleReservaEliminarActivity","DetalleReservaConsultarActivity", "DetalleReservaActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("idUsuario");

        try{
            helper=new ControlBD(this);
            Cursor c = helper.obtenerSubMenu(id,"05_");
            if(c!=null) {
                if (c.moveToFirst())
                    do {
                        String desOpcion = c.getString(0);
                        resultados.add(desOpcion);
                    } while (c.moveToNext());
                c.close();
            }
        }catch (SQLiteException e){
            Log.e(getClass().getSimpleName(),"No se pudo crear o abrir la base de datos");
        }
        helper.cerrar();

        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(255, 255, 255));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,resultados);
        setListAdapter(adapter);
    }
    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue="";
        String r = resultados.get(position);

        for (int i=0;i<menu.length;i++)
            if (r.compareTo(menu[i])==0){
                nombreValue=activities[i];
                break;
            }
        try{
            Class<?>
                    clase=Class.forName("gr23.fia.ues.sv.admon_poli."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}