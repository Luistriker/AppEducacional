
package com.example.appeducacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appeducacional.Classes.Usuarios;
import com.example.appeducacional.DAO.ConfiguracaoFireBase;
import com.example.appeducacional.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText TextoEmail,TextoSenha;
    private Button Entrar,Cadastrar;
    private Usuarios usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        TextoEmail = (EditText) findViewById(R.id.EmailCadastroId);
        TextoSenha = (EditText) findViewById(R.id.SenhaCadastroId);
        Entrar = (Button) findViewById(R.id.EntrarId);
        Cadastrar = (Button)findViewById(R.id.CadastroId);

       if(usuarioLogado()){
           if(true){
               if(usuario.getSenha().equals("adminmaster")){
                   Intent intentMinhaConta = new Intent(MainActivity.this, MenuAdminActivity.class);
                   startActivity(intentMinhaConta);
               }else if(usuario.getSenha().equals("proflucianabio")){
                   Intent intentMinhaconta = new Intent(MainActivity.this, MenuProfessorActivity.class);
                   startActivity(intentMinhaconta);
               }else{
                   Intent intentMihaConta = new Intent(MainActivity.this, MenuAlunoActivity.class);
                   startActivity(intentMihaConta);
               }
           }else{
               Entrar.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String EmailDigitado = TextoEmail.getText().toString();
                       String SenhaDigitada = TextoSenha.getText().toString();

                       if(!EmailDigitado.equals("") && !SenhaDigitada.equals("")){
                           usuario = new Usuarios();
                           usuario.setEmail(EmailDigitado);
                           usuario.setSenha(SenhaDigitada);
                           validarLogin(usuario.getEmail());

                       }else if(EmailDigitado.equals("")){
                           TextoEmail.setError("Erro digite seu e-mail");

                       }else{
                           TextoSenha.setError("Erro digite sua senha");
                       }

                   }
               });
           }

       }



        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                AbrirNovaActivity(intentCadastro);
            }
        });


    }


    private void validarLogin(final String email){
        autenticacao = ConfiguracaoFireBase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,  email +"logado com sucesso", Toast.LENGTH_SHORT).show();

                    if(usuario.getSenha().equals("adminmaster")){
                        Intent intent = new Intent(MainActivity.this, MenuAdminActivity.class);
                        startActivity(intent);
                    }else if(usuario.getSenha().equals("proflucianabio")){
                        Intent intent = new Intent(MainActivity.this, MenuProfessorActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(MainActivity.this, MenuAlunoActivity.class);
                        startActivity(intent);
                    }



                }else{
                    Toast.makeText(MainActivity.this, " Usuario ou senha invalidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



    public  Boolean usuarioLogado(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            return true;
        }else{
            return false;
        }

    }

    public void AbrirNovaActivity(Intent intent){
        startActivity(intent);
    }


}
