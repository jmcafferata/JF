package com.jmcafferata.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Calendar;

public class Listo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}
