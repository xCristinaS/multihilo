package c.ejercicio40_listarep_boundservice;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Cristina on 15/02/2016.
 */
public class MyAdaptador extends RecyclerView.Adapter<MyAdaptador.ViewHolder> {

    public interface OnItemClick{
        public void onItemClick(int numCancion);
    }

    private final ArrayList<Cancion> canciones;
    private OnItemClick listener;
    private int selectedElement = -1;

    public MyAdaptador(ArrayList<Cancion> canciones){
        this.canciones = canciones;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (selectedElement != -1) {
            if (position == selectedElement)
                holder.itemView.setActivated(true);
            else
                holder.itemView.setActivated(false);
        }
        holder.onBind(canciones.get(position));
    }

    @Override
    public int getItemCount() {
        return canciones.size();
    }

    public void setOnItemClickListener(OnItemClick listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView lblTituloCancion;

        public ViewHolder(View itemView) {
            super(itemView);
            lblTituloCancion = (TextView) itemView.findViewById(R.id.lblTituloCancion);
			// listener.onItemClick(getAdapterPosition(itemView)); // para hacerlo aqui. 
        }

        public void onBind(final Cancion c){
            lblTituloCancion.setText(c.getNombre());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(canciones.indexOf(c));
                }
            });
        }
    }

    public void setSelectedElement(int selectedElement){
        int aux = this.selectedElement;
        this.selectedElement = selectedElement;
        notifyItemChanged(aux);
        notifyItemChanged(selectedElement);
    }
}
