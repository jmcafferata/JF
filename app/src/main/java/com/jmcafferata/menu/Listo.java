package com.jmcafferata.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class Listo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_listo);

        // Ticket animado
        ImageView ticket = (ImageView) findViewById(R.id.ticket);
        Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        ticket.startAnimation(startRotateAnimation);

        // Importar pedido
        Bundle bun = getIntent().getExtras();
        final Pedido pedido = bun.getParcelable("pedidoFinal");
        Calendar now = Calendar.getInstance();
        pedido.setHoraPedido(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
        System.out.println(pedido.toString());

        // Boton VER
        Button ver = (Button) findViewById(R.id.ver);
        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Listo.this,Ver.class);
                intent.putExtra("pedido", pedido);
                startActivityForResult(intent,1);
            }
        });

        // Boton mozo
        ImageView mozo = (ImageView) findViewById(R.id.mozo);
        mozo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(new ContextThemeWrapper(Listo.this, R.style.mozoDialog));
                builder1.setMessage("¿Desea llamar al mozo?");
                builder1.setCancelable(true);
                builder1.setIcon(R.drawable.mozo);
                builder1.setTitle("Confirmar");
                builder1.setPositiveButton(
                        "ACEPTAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // TODO: LLAMAR MOZO
                            }
                        });

                builder1.setNegativeButton(
                        "CANCELAR",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder1.create();
                alert11.show();
                TextView tv = (TextView)alert11.getWindow().findViewById(android.R.id.message);
                tv.setTextSize(30);
            }
        });
    }
}
