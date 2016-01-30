package cristina.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Cristina on 20/01/2016.
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder>{

    private final ArrayList<Alumno> datos;

    public Adaptador(ArrayList<Alumno> datos){
        this.datos = datos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.alumno_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(datos.get(position));
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    // Añade un elemento al adaptador.
    public void addItem(Alumno alumno) {
        datos.add(alumno);
        notifyItemInserted(datos.size()-1);
    }

    public void addItems(List<Alumno> alumnos) {
        datos.addAll(alumnos);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblNombre, lblTelefono, lblDireccion, lblCurso, lblRepetidor, lblEdad;
        private ImageView imgFoto;

        public ViewHolder(View itemView) {
            super(itemView);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblTelefono = (TextView) itemView.findViewById(R.id.lblTelefono);
            lblDireccion = (TextView) itemView.findViewById(R.id.lblDireccion);
            lblCurso = (TextView) itemView.findViewById(R.id.lblCurso);
            lblRepetidor = (TextView) itemView.findViewById(R.id.lblRepetidor);
            lblEdad = (TextView) itemView.findViewById(R.id.lblEdad);
            imgFoto = (ImageView) itemView.findViewById(R.id.imgFoto);
        }

        public void onBind(Alumno alumno){
            lblNombre.setText(alumno.getNombre());
            lblTelefono.setText(alumno.getTelefono());
            lblDireccion.setText(alumno.getDireccion());
            lblCurso.setText(alumno.getCurso());
            lblRepetidor.setText(String.format("%s", alumno.isRepetidor() ? "si" : "no"));
            lblEdad.setText(lblEdad.getText());
            Picasso.with(itemView.getContext()).load(alumno.getFoto()).into(imgFoto);
        }
    }
}
