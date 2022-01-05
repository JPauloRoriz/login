package controller;

import exception.DadoIncorreto;
import exception.DadosNaoPreenchidos;
import exception.SenhaNaoConfirmada;
import model.db.UserDB;
import model.entidades.User;
import utils.EmailUtils;
import view.LoginView;

public class UserController {
    private final UserDB userDB = new UserDB();

    public void cadastrar(User user, String confirmarSenha) throws DadosNaoPreenchidos, DadoIncorreto, SenhaNaoConfirmada {
        if (user.getNome() == null ||
                user.getSenha() == null ||
                user.getEmail() == null ||
                confirmarSenha == null) {
            throw new DadosNaoPreenchidos();
        }
        else if(!EmailUtils.isValidEmail(user.getEmail())){
            throw new DadoIncorreto();

        } else if(!user.getSenha().equals(confirmarSenha)){
            throw new SenhaNaoConfirmada();
        } else if (user.getSenha().trim().length()< 3){
            throw new DadoIncorreto();
        }
        userDB.addUser(user);

    }

    public void entrar(String email, String senha) throws DadosNaoPreenchidos, DadoIncorreto {
        if(userDB.doLogin(email,senha)) {

        } else if( email.equals("") || senha.equals("") ){
            throw new DadosNaoPreenchidos();
        } else {
            throw new DadoIncorreto();
        }
    }


    }

