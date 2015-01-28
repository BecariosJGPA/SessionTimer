package com.example.temporizadorpreguntas;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends Activity implements Chronometer.OnChronometerTickListener {
	Chronometer cronometro1;
	Chronometer cronometro2;
	Button botonStop;
	Button botonEnter;
	
	
	/**
	 * Metodo de arranque del activity
	 * 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		cronometro1 = (Chronometer) findViewById(R.id.chronometer1);
		Button buttonStart1 = (Button) findViewById(R.id.btn_enter1);
		Button buttonStop1 = (Button) findViewById(R.id.btn_stop1);
		Button buttonReset1 = (Button) findViewById(R.id.btn_reset1);
		cronometro2 = (Chronometer) findViewById(R.id.chronometer2);
		Button buttonStart2 = (Button) findViewById(R.id.btn_enter2);
		Button buttonStop2 = (Button) findViewById(R.id.btn_stop2);
		Button buttonReset2 = (Button) findViewById(R.id.btn_reset2);
		cronometro1.setOnChronometerTickListener(this);
		cronometro2.setOnChronometerTickListener(this);
		
		/**
		 * Metodo del botón start, una vez iniciado el cronometro este mismo método
		 * se encarga de que una vez se reinicie vaya todo correctamente
		 */
		buttonStart1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				int stoppedMilliseconds = 0;
				String chronoText = cronometro1.getText().toString();
				String array[] = chronoText.split(":");
				if (array.length == 2) {
					stoppedMilliseconds = Integer.parseInt(array[0]) * 60
							* 1000 + Integer.parseInt(array[1]) * 1000;
				} else if (array.length == 3) {
					stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60
							* 1000 + Integer.parseInt(array[1]) * 60 * 1000
							+ Integer.parseInt(array[2]) * 1000;
				}
				cronometro1.setBase(SystemClock.elapsedRealtime()
						- stoppedMilliseconds);
				cronometro1.start();
			}

		});
		
		buttonStart2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				int stoppedMilliseconds = 0;
				String chronoText = cronometro2.getText().toString();
				String array[] = chronoText.split(":");
				if (array.length == 2) {
					stoppedMilliseconds = Integer.parseInt(array[0]) * 60
							* 1000 + Integer.parseInt(array[1]) * 1000;
				} else if (array.length == 3) {
					stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60
							* 1000 + Integer.parseInt(array[1]) * 60 * 1000
							+ Integer.parseInt(array[2]) * 1000;
				}
				cronometro2.setBase(SystemClock.elapsedRealtime()
						- stoppedMilliseconds);
				cronometro2.start();
			}

		});
		
		/**
		 * Método del botón Stop, para la ejecución del cronometro
		 */
		buttonStop1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				cronometro1.stop();//Se para el cronometro
				long myElapsedMillis = SystemClock.elapsedRealtime()
						- cronometro1.getBase();
				String strElapsedMillis = "Milisengundos transcurridos: "
						+ myElapsedMillis;
				Toast.makeText(MainActivity.this, strElapsedMillis,
						Toast.LENGTH_SHORT).show();
				cronometro1.setTextColor(Color.RED);

			}
		});
		
		buttonStop2.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				cronometro2.stop();//Se para el cronometro
				long myElapsedMillis = SystemClock.elapsedRealtime()
						- cronometro2.getBase();
				String strElapsedMillis = "Milisengundos transcurridos: "
						+ myElapsedMillis;
				Toast.makeText(MainActivity.this, strElapsedMillis,
						Toast.LENGTH_SHORT).show();
				cronometro2.setTextColor(Color.RED);

			}
		});

		/**
		 * Metodo del botón reset,
		 * pone a 0 el cronometro
		 */
			buttonReset1.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v) {
				cronometro1.setBase(SystemClock.elapsedRealtime());//Se estable el tiempo base de conteo
				cronometro1.setTextColor(Color.BLACK);//Color del cronometro a negro
			}
		});

			buttonReset2.setOnClickListener(new Button.OnClickListener() {
				@Override
				public void onClick(View v) {
					cronometro2.setBase(SystemClock.elapsedRealtime());//Se estable el tiempo base de conteo
					cronometro2.setTextColor(Color.BLACK);//Color del cronometro a negro
				}
			});
	}
	
	/**
	 * Listener que va observando el cronometro desde que se inicia, en este caso se utiliza para 
	 * cambiar el color del cronometro en el momento que se haya estipulado 
	 */
		@Override
		public void onChronometerTick(Chronometer chronometer) {
			if("00:05".equals(chronometer.getText()))
				cronometro1.setTextColor(Color.rgb(230, 95, 0));//Cambia a color naranja
			if("00:10".equals(chronometer.getText()))
				cronometro1.setTextColor(Color.RED);//Cambia a color rojo, sería el tiempo limite
		}

}
