package com.example.myweatherbase.activities.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PrevisionesReciclerView extends RecyclerView.Adapter<PrevisionesReciclerView.ViewHolder>{

    private Root root;
    private final LayoutInflater inflater;

    public PrevisionesReciclerView(Context context, Root root){

        this.root = root;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.simple_element,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrevisionesReciclerView.ViewHolder holder, int position) {
        Date date = new Date((long)root.list.get(position).dt*1000);
        //holder.imagen.setImageIcon(Connector.getConector().get(Icon.class,Parameters.ICON_URL_PRE+"10d"+Parameters.ICON_URL_POST));
        cambiarColor(holder.itemView, position);
        holder.dia.setText((new SimpleDateFormat("EEEE")).format(date));
        holder.estadoCielo.setText(root.getList().get(position).getWeather().get(0).getDescription());
        holder.hora.setText(root.getList().get(position).dt_txt.substring(11,16));
        holder.fecha.setText(root.getList().get(position).dt_txt.substring(0,11));
        holder.max.setText("MAX: "+((String.valueOf(root.getList().get(position).main.temp_max).length()>=3)?String.valueOf(root.getList().get(position).main.temp_max).substring(0,3):root.getList().get(position).main.temp_max)+"º");
        holder.min.setText("MAX: "+((String.valueOf(root.getList().get(position).main.temp_min).length()>=3)?String.valueOf(root.getList().get(position).main.temp_min).substring(0,3):root.getList().get(position).main.temp_min)+"º");
        holder.temp.setText("Temp: "+root.getList().get(position).main.temp+"º");
        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE+root.getList().get(position).getWeather().get(0).icon+Parameters.ICON_URL_POST,holder.imagen);
    }

    @SuppressLint("ResourceAsColor")
    private void cambiarColor(View view, int position) {
        if (position %2==0){
            view.setBackgroundColor(R.color.fondo);
        }
    }


    @Override
    public int getItemCount() {
        return root.getList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView dia;
        private TextView hora;
        private TextView fecha;
        private TextView estadoCielo;
        private TextView min;
        private TextView max;
        private TextView temp;

        private View item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dia = itemView.findViewById(R.id.dia);
            hora = itemView.findViewById(R.id.hora);
            fecha = itemView.findViewById(R.id.fecha);
            estadoCielo = itemView.findViewById(R.id.estadocielo);
            min = itemView.findViewById(R.id.min);
            max = itemView.findViewById(R.id.max);
            temp = itemView.findViewById(R.id.temperatura);
            imagen = itemView.findViewById(R.id.image);
        }
    }
}
