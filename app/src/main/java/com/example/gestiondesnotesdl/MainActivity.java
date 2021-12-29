package com.example.gestiondesnotesdl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listeView;
    private EditText Note;
    static double somme;
    static ArrayList<String> ModuleExiste;
    private Button button;
    private TextView moyenne;
    private ArrayList<String> itemsN;
    ArrayAdapter<String> adap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //on remplit le spinner on utilisant un arrayAdapter
        Spinner dropdown = findViewById(R.id.myspinner);
        String[] items = new String[]{"math", "Infos", "Chimie","Reseaux"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        //la partie d'initialisation
        listeView=findViewById(R.id.litViewItems);
        Note=findViewById(R.id.Note);
        button=findViewById(R.id.add);
        moyenne=findViewById(R.id.moyenne);
        ModuleExiste = new ArrayList<>();
        itemsN = new ArrayList<>();
        adap=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,itemsN);
        listeView.setAdapter(adap);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String mynote =Note.getText().toString();
                String module=dropdown.getSelectedItem().toString();
               
                if(mynote==null || mynote.length()==0){
                    mToast("Ajouter la note SVP !!");
                }else{
                    //LE TEST DU MODULE IF EXISTE  DANS LA LISTVIEW
                    if (existe(ModuleExiste,module)){
                        mToast("Le module "+module+" existe déjà");
                    }else{
                        //Ajouter le module et la note a la listView
                        addItem(mynote,module);
                        double no = Double.parseDouble(mynote);
                        //Calcul la somme des notes
                        somme= somme + no;
                        //calcul de la moyenne
                        double moyen=somme/(itemsN.size());
                        moyenne.setText("La moyenne est: "+moyen);
                        Note.setText("");
                    }


                }

            }
        });
    }
    //la fonction mToast permet d'afficher les messages
    Toast t;
    public void mToast(String s){
        if(t!=null) t.cancel();
        t=Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        t.show();
    }
    //L'ajout de la note et le module au ListView
    public void addItem( String item,String module){
        int n=itemsN.size()+1;
        ModuleExiste.add(module);
        itemsN.add(n+"          "+item+"         "+module);
        listeView.setAdapter(adap);
    }
    //Verification d'existante d'un module
    public boolean existe(ArrayList<String> ModuleExiste, String val){
        boolean ex=false;
        for(int i = 0 ; i<ModuleExiste.size();i++){
            // La méthode equals() retourne true si les deux chaines de caractères contiennent les mêmes caractères
            if(val.equals(ModuleExiste.get(i))){
                ex=true;
            }
        }
        return ex;
    }
}