package sg.edu.rp.c346.id22024848.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btnAdd;
    Button btnClear;
    ListView lwTodo;
    String newTodo;
    Spinner spinner;
    Button btnDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.editText);
        btnAdd=findViewById(R.id.buttonAdd);
        btnClear=findViewById(R.id.buttonClear);
        lwTodo=findViewById(R.id.listViewTodo);
        spinner=findViewById(R.id.spinnerTodo);
        btnDelete=findViewById(R.id.buttonDelete);
        ArrayList<String> alTodo;
        alTodo=new ArrayList<String>();

        ArrayAdapter aaTodo=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alTodo);
        lwTodo.setAdapter(aaTodo);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                newTodo=et.getText().toString();
                alTodo.add(newTodo);

                aaTodo.notifyDataSetChanged();
                lwTodo.setAdapter(aaTodo);




            }

        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                alTodo.clear();

                aaTodo.notifyDataSetChanged();
                lwTodo.setAdapter(aaTodo);




            }

        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        et.setHint("Type in a new task here");
                        et.setInputType(InputType.TYPE_NULL);
                        et.getText().clear();


                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        et.setHint("Type in the index of the task to be removed");
                        et.setInputType(InputType.TYPE_CLASS_NUMBER);
                        et.getText().clear();

                        break;
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }




        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                int index;
                Boolean inRange=false;
                for(int i=0;i<alTodo.size();i++){
                    if(Integer.parseInt(et.getText().toString())==i){
                        inRange=true;
                    }
                }
                if(alTodo.size()!=0 ) {
                    if(inRange==true) {
                        index = Integer.parseInt(et.getText().toString());
                        alTodo.remove(index);
                        aaTodo.notifyDataSetChanged();
                        lwTodo.setAdapter(aaTodo);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Wrong index number" ,Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"You don't have any task to remove" ,Toast.LENGTH_LONG).show();
                }




            }

        });



    }
}