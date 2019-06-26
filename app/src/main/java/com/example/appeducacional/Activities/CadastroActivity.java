package com.example.appeducacional.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appeducacional.Activities.MainActivity;
import com.example.appeducacional.Classes.Usuarios;
import com.example.appeducacional.DAO.ConfiguracaoFireBase;
import com.example.appeducacional.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.EnumMap;

public class CadastroActivity extends AppCompatActivity {

    private EditText Nome,Turma,Email,Senha,ConfirmaSenha;
    private Button Cadastrar,Cancelar;
    private Usuarios usuario;

    //Objeto do tipo autenticação
    private FirebaseAuth autenticacao;
    //Objetos do tipo banco de dados para armazenar informações
    public static FirebaseDatabase database;
    private  DatabaseReference referencia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Associa os campos do xml as variáveis de mesmo tipo
        Nome = (EditText) findViewById(R.id.NomeId);
        Turma = (EditText) findViewById(R.id.TurmaId);
        Email = (EditText) findViewById(R.id.EmailCadastroId);
        Senha = (EditText) findViewById(R.id.SenhaCadastroId);
        ConfirmaSenha = (EditText)findViewById(R.id.ConfirmaSenhaId);
        Cadastrar = (Button) findViewById(R.id.ConfirmarId);
        Cancelar = (Button) findViewById(R.id.CancelarId);

        //Evento que acontece apos clicar no botao cadastrar
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Pega as informações digitadas nos campos pelo usuário
                String NomeDigitado = Nome.getText().toString();
                String TurmaDigitada = Turma.getText().toString();
                String EmailDigitado = Email.getText().toString();
                String SenhaDigitada = Senha.getText().toString();
                String ConfirmarSenhaDigitada = ConfirmaSenha.getText().toString();

                //Verifica se os campos foram todos digitados
                if(!NomeDigitado.equals("") && !TurmaDigitada.equals("") &&  !EmailDigitado.equals("") && !SenhaDigitada.equals("") && !ConfirmarSenhaDigitada.equals("")){
                    //Verificação se a senha e a confirmação da senha são iguais
                    if(SenhaDigitada.equals(ConfirmarSenhaDigitada)){
                        // Cria um novo usuario da classe usuario com esses atributos
                        usuario = new Usuarios();
                        usuario.setNome(NomeDigitado);
                        usuario.setTurma(TurmaDigitada);
                        usuario.setEmail(EmailDigitado);
                        usuario.setSenha(SenhaDigitada);
                        cadastrarUsuario();
                    }else{
                      ConfirmaSenha.setError("Digite novamente");
                      Toast.makeText(CadastroActivity.this, "A senhas não coincidem",Toast.LENGTH_SHORT).show();
                    }


                }else if(NomeDigitado.equals("") && TurmaDigitada.equals("") &&  EmailDigitado.equals("") && SenhaDigitada.equals("") && ConfirmarSenhaDigitada.equals("")){
                    Nome.setError("Erro digite seu Nome");
                    Turma.setError("Erro digite sua Turma");
                    Email.setError("Erro digite seu E-mail");
                    Senha.setError("Erro digite sua Senha");
                    ConfirmaSenha.setError("Erro digite sua Senha Novamente");

                }else{
                    if(NomeDigitado.equals("")){
                        Nome.setError("Erro digite seu Nome");

                    }if(TurmaDigitada.equals("")){
                        Turma.setError("Erro digite sua Turma");

                    }if(EmailDigitado.equals("")){
                        Email.setError("Erro digite seu E-mail");

                    }if(SenhaDigitada.equals("")){
                        Senha.setError("Erro digite sua Senha");

                    }if(ConfirmarSenhaDigitada.equals("")){
                        Nome.setError("Erro digite sua Senha Novamente");
                    }
                }




            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void cadastrarUsuario(){
            autenticacao = ConfiguracaoFireBase.getFirebaseAuth();
            autenticacao.createUserWithEmailAndPassword(
                    usuario.getEmail(),
                    usuario.getSenha()
            ).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            insereUsuario(usuario);
                            validarLogin(usuario.getEmail());

                        }else{
                            String erroExecao = "";
                            try{
                                throw task.getException();

                            } catch (FirebaseAuthWeakPasswordException e){
                                erroExecao = "Digite uma senha mais forte contendo no mínimo 8 caracteres, com letras e números";

                            } catch (FirebaseAuthInvalidCredentialsException e){
                                erroExecao = "E-mail digitado é invalido,digite um novo e-mail";

                            } catch (FirebaseAuthUserCollisionException e){
                                erroExecao = "E-mail digitado já está em uso,digite um novo e-mail";

                            } catch (Exception e) {
                                erroExecao = "Erro ao fazer o cadastro";
                                e.printStackTrace();
                            }

                            Toast.makeText(CadastroActivity.this, "Erro: "+erroExecao, Toast.LENGTH_SHORT).show();
                        }

                }
            });
    }

        private boolean insereUsuario( Usuarios usuario){
                try{
                    referencia = ConfiguracaoFireBase.getFirebase().child("usuários");
                    referencia.push().setValue(usuario);
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    return true;
                }
                catch (Exception e){
                    Toast.makeText(CadastroActivity.this, "Erro ao gravar o usuário", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return false;
                }
        }

    //Função que verifica se o usuário esta contido no Banco de dados e valida sua entrada
    private void validarLogin(final String email){
        autenticacao = ConfiguracaoFireBase.getFirebaseAuth();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    //Verificação do tipo de usuário(adm,aluno,professor)
                    if(usuario.getSenha().equals("adminmaster")){
                        Toast.makeText(CadastroActivity.this, email+" Logado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroActivity.this, MenuAdminActivity.class);
                        startActivity(intent);
                    }else if(usuario.getSenha().equals("proflucianabio")){
                        Toast.makeText(CadastroActivity.this, email+" Logado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroActivity.this, MenuProfessorActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(CadastroActivity.this, email+" Logado com sucesso", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CadastroActivity.this, MenuAlunoActivity.class);
                        startActivity(intent);
                    }

                }else{
                    Toast.makeText(CadastroActivity.this, " Não foi possivel Fazer login direto,tente logar manualmente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastroActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


}
