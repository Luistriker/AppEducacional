package com.example.appeducacional.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.appeducacional.Classes.Usuarios;
import com.example.appeducacional.R;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.appeducacional.R.menu.activity_menu_aluno;

public class MenuAlunoActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_menu_aluno);

        autenticacao = FirebaseAuth.getInstance();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(activity_menu_aluno,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.acao_informacao_id){
            Intent intent = new Intent(MenuAlunoActivity.this,InformacoesActivity.class);
            startActivity(intent);
        }else if(id == R.id.acao_jogar_id){
            Intent intent = new Intent(MenuAlunoActivity.this,QuizActivity.class);
            startActivity(intent);
        }else if(id == R.id.acao_sair_id){
            Intent intent = new Intent(MenuAlunoActivity.this,MainActivity.class);
            deslogarUsuario(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario(Intent intent){
            autenticacao.signOut();
            AbrirNovaActivity(intent);
            finish();
    }
    public void AbrirNovaActivity(Intent intent){
        startActivity(intent);
    }

}
