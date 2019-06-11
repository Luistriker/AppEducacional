package com.example.appeducacional.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.appeducacional.Classes.Usuarios;
import com.example.appeducacional.DAO.ConfiguracaoFireBase;
import com.example.appeducacional.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText Nome,Turma,Email,Senha,ConfirmaSenha;
    private Button Cadastrar,Cancelar;
    private Usuarios usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Nome = (EditText) findViewById(R.id.NomeId);
        Turma = (EditText) findViewById(R.id.TurmaId);
        Email = (EditText) findViewById(R.id.EmailCadastroId);
        Senha = (EditText) findViewById(R.id.SenhaCadastroId);
        ConfirmaSenha = (EditText)findViewById(R.id.ConfirmaSenhaId);
        Cadastrar = (Button) findViewById(R.id.ConfirmarId);
        Cancelar = (Button) findViewById(R.id.CancelarId);

        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NomeDigitado = Nome.getText().toString();
                String TurmaDigitada = Turma.getText().toString();
                String EmailDigitado = Email.getText().toString();
                String SenhaDigitada = Senha.getText().toString();
                String ConfirmarSenhaDigitada = ConfirmaSenha.getText().toString();


                if(!NomeDigitado.equals("") && !TurmaDigitada.equals("") &&  !EmailDigitado.equals("") && !SenhaDigitada.equals("") && !ConfirmarSenhaDigitada.equals("")){
                  if(SenhaDigitada.equals(ConfirmarSenhaDigitada)){
                        usuario = new Usuarios();
                        usuario.setNome(NomeDigitado);
                        usuario.setTurma(TurmaDigitada);
                        usuario.setEmail(EmailDigitado);
                        usuario.setSenha(SenhaDigitada);


                  }else{
                      ConfirmaSenha.setError("A senha não é igual, digite novamente");
                  }
                }else if(NomeDigitado.equals("")){
                    Nome.setError("Erro digite seu Nome");

                }else if(TurmaDigitada.equals("")){
                    Nome.setError("Erro digite sua Turma");

                }else if(EmailDigitado.equals("")){
                    Nome.setError("Erro digite seu E-mail");

                }else if(SenhaDigitada.equals("")){
                    Nome.setError("Erro digite sua Senha");

                }else {
                    Nome.setError("Erro digite sua Senha Novamente");
                }



            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }



}
