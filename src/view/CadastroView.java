package view;

import controller.UserController;
import exception.DadoIncorreto;
import exception.DadosNaoPreenchidos;
import exception.SenhaNaoConfirmada;
import model.entidades.User;
import utils.JOptionUtils;

public class CadastroView {

    public static final int DIGITAR_NOME = 0;
    public static final int DIGITAR_EMAIL = 1;
    public static final int DIGITAR_SENHA = 2;
    public static final int CONFIRMAR_SENHA = 3;
    public static final int CADASTRAR = 4;
    public static final int VOLTAR_LOGIN = 5;

    private static final UserController userController  = new UserController();

    public static void cadastroView() {
        String confirmarSenha = "";
        User user = new User();
        while (true) {
            int option = JOptionUtils.dialogInt("Escolha a opção: " +
                    "\n" + DIGITAR_NOME + " - " + "Digitar Nome " +
                    "\n" + DIGITAR_EMAIL + " - " + "Digitar Email " +
                    "\n" + DIGITAR_SENHA + " - " + "Digitar senha " +
                    "\n" + CONFIRMAR_SENHA + " - " + "Confirmar senha " +
                    "\n" + CADASTRAR + " - " + "Cadastrar" +
                    "\n" + VOLTAR_LOGIN + " - " + "Voltar para login");

            switch (option) {
                case DIGITAR_NOME:
                    user.setNome(JOptionUtils.input("Nome"));
                    break;

                case DIGITAR_EMAIL:
                    user.setEmail(JOptionUtils.input("Email:"));

                    break;
                case DIGITAR_SENHA:
                    user.setSenha(JOptionUtils.input("Senha: "));

                    break;
                case CONFIRMAR_SENHA:
                    confirmarSenha = JOptionUtils.input("Confirme a Senha: ");

                    break;
                case CADASTRAR:
                    try {
                        userController.cadastrar(user, confirmarSenha);
                        JOptionUtils.message("Cadastro adicionado com sucesso!");
                        LoginView.loginView();
                    } catch (DadosNaoPreenchidos dadosNaoPreenchidos) {
                        JOptionUtils.message("Erro! Dados não preenchidos");
                    } catch (DadoIncorreto dadoIncorreto) {
                        JOptionUtils.message("Erro! Dados incorretos");
                    } catch (SenhaNaoConfirmada senhaNaoConfirmada) {
                       JOptionUtils.message("Erro! Senha não confirmada!");
                    }
                    break;

                case VOLTAR_LOGIN:
                   LoginView.loginView();
                    break;

                default:
                    JOptionUtils.message("Opção inválida");
                    break;
            }
        }
    }

}
