package cristina.ejercicio34_retrofit;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class AgregarActualizarActivity extends AppCompatActivity {

    public static final String EXTRA_ALUMNO = "alumno";
    private static final String EXTRA_ID_AUX = "idAux";

    private Intent intento;
    private TextInputLayout tilNombre, tilEdad, tilDireccion, tilTelefono, tilRepetidor, tilCurso, tilFoto;
    private Button btnAceptar;
    private Alumno alumno;
    private int idAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_actualizar);

        initView();

        intento = getIntent();
        if (intento != null ) {
            if (intento.hasExtra(EXTRA_ALUMNO)) {
                alumno = intento.getParcelableExtra(EXTRA_ALUMNO);
                cargarAlumno();
            }
            if (intento.hasExtra(EXTRA_ID_AUX))
                idAux = intento.getIntExtra(EXTRA_ID_AUX, 0);
        }

    }

    private void initView(){
        tilNombre = (TextInputLayout) findViewById(R.id.tilNombre);
        tilEdad = (TextInputLayout) findViewById(R.id.tilEdad);
        tilDireccion = (TextInputLayout) findViewById(R.id.tilDireccion);
        tilTelefono = (TextInputLayout) findViewById(R.id.tilTelefono);
        tilRepetidor = (TextInputLayout) findViewById(R.id.tilRepetidor);
        tilCurso = (TextInputLayout) findViewById(R.id.tilCurso);
        tilFoto = (TextInputLayout) findViewById(R.id.tilFoto);
        btnAceptar = (Button) findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void cargarAlumno(){
        tilFoto.getEditText().setText(alumno.getFoto());
        tilCurso.getEditText().setText(alumno.getCurso());
        tilRepetidor.getEditText().setText(String.valueOf(alumno.getRepetidor()));
        tilTelefono.getEditText().setText(alumno.getTelefono());
        tilDireccion.getEditText().setText(alumno.getDireccion());
        tilEdad.getEditText().setText(String.valueOf(alumno.getEdad()));
        tilNombre.getEditText().setText(alumno.getNombre());
    }

    public static void start(Activity a, Alumno alumno, int idAux, int requestCode){
        Intent intento = new Intent(a, AgregarActualizarActivity.class);
        if (idAux != 0)
            intento.putExtra(EXTRA_ID_AUX, idAux);
        if (alumno != null)
            intento.putExtra(EXTRA_ALUMNO, alumno);
        a.startActivityForResult(intento, requestCode);
    }

    @Override
    public void finish() {
        Intent intento2 = new Intent();
        if (alumno == null)
            alumno = new Alumno(idAux, String.valueOf(tilFoto.getEditText().getText()), tilNombre.getEditText().getText().toString(), tilCurso.getEditText().getText().toString(), tilTelefono.getEditText().getText().toString(), tilDireccion.getEditText().getText().toString(), Integer.parseInt(tilEdad.getEditText().getText().toString()), tilRepetidor.getEditText().toString().equals("true")?true:false);
        else {
            alumno.setNombre(String.valueOf(tilNombre.getEditText().getText()));
            alumno.setTelefono(String.valueOf(tilTelefono.getEditText().getText()));
            alumno.setDireccion(String.valueOf(tilDireccion.getEditText().getText()));
            alumno.setCurso(String.valueOf(tilCurso.getEditText().getText()));
            alumno.setEdad(Integer.parseInt(String.valueOf(tilEdad.getEditText().getText())));
            alumno.setRepetidor(tilRepetidor.getEditText().toString().equals("true")?true:false);
            alumno.setCurso(String.valueOf(tilCurso.getEditText().getText()));
        }
        intento2.putExtra(EXTRA_ALUMNO, alumno);
        setResult(RESULT_OK, intento2);
        super.finish();
    }
}
