package view;

import controller.UserController;
import exception.DadoIncorreto;
import exception.DadosNaoPreenchidos;
import model.db.UserDB;
import utils.JOptionUtils;

public class LoginView {
    private static final UserController userController = new UserController();
    private static final UserDB userDB = new UserDB();


    public static final int DIGITAR_EMAIL = 1;
    public static final int DIGITAR_SENHA = 2;
    public static final int ENTRAR = 3;
    public static final int FAZER_CADASTRO = 4;


    public static void loginView() {
        String email = "";
        String senha = "";
        while (true) {
            int option = JOptionUtils.dialogInt("Escolha a opção: " +
                    "\n  " + DIGITAR_EMAIL + " - Digitar Email " +
                    "\n  " + DIGITAR_SENHA + " - Digitar senha " +
                    "\n  " + ENTRAR + " - Entrar" +
                    "\n  " + FAZER_CADASTRO + " - Cadastrar");

            switch (option) {
                case DIGITAR_EMAIL:
                    email = JOptionUtils.input("Email:");

                    break;
                case DIGITAR_SENHA:
                    senha = JOptionUtils.input("Senha: ");

                    break;
                case ENTRAR:
                    try {
                        userController.entrar(email, senha);
                        JOptionUtils.message("Acesso Liberado");
                        HomeView.homeView(userDB.getUser(email));
                    } catch (DadosNaoPreenchidos dadosNaoPreenchidos) {
                        dadosNaoPreenchidos.printStackTrace();
                        JOptionUtils.message("Dados não preenchidos");
                    } catch (DadoIncorreto dadoIncorreto) {
                        dadoIncorreto.printStackTrace();
                        JOptionUtils.message("Dados incorretos");
                    }
                    break;

                case FAZER_CADASTRO:
                    CadastroView.cadastroView();

                    break;
                default:
                    JOptionUtils.message("Opção inválida");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        LoginView.loginView();
    }
}
