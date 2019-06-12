package com.example.appeducacional.Activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appeducacional.Classes.Usuarios;
import com.example.appeducacional.R;

public class MenuActivity extends AppCompatActivity {
    private Usuarios usuario;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(usuario.getSenha().equals("adminmaster")){
            setContentView(R.menu.activity_admin);

        }else if(usuario.getSenha().equals("professorabio")){
            setContentView(R.menu.activity_menu_professor);

        }else{
            setContentView(R.menu.activity_menu_aluno);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_aluno,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.acao_informacao_id){


        }else if(id == R.id.acao_jogar_id){

        }else{

        }
        return super.onOptionsItemSelected(item);
    }




}
