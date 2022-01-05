package view;


import controller.AlterarContaController;
import exception.DadoIncorreto;
import exception.DadosNaoPreenchidos;
import exception.SenhaIgualAnteriorException;
import model.entidades.User;
import utils.JOptionUtils;

import java.util.Locale;

public class AlterarContaView {


    public static final int ALTERAR_NOME = 1;
    public static final int ALTERAR_SENHA = 2;
    public static final int CONFIRM_CHANGE = 3;
    public static final int SAIR = 4;

    public static void alterarContaStart(User user) {
        String newName = "";
        String newPassword = "";
        while (true) {
            int option = JOptionUtils.dialogInt("Escolha a opção abaixo:" +
                    "\n" + ALTERAR_NOME + " - Alterar nome" +
                    "\n" + ALTERAR_SENHA + " - Alterar senha" +
                    "\n" + CONFIRM_CHANGE + " - Confirmar" +
                    "\n" + SAIR + " - Sair");

            switch (option) {

                case ALTERAR_NOME:
                    newName = JOptionUtils.input("Digite o novo nome: ");
                    break;

                case ALTERAR_SENHA:
                    newPassword = JOptionUtils.input("Digite a nova senha: ");

                    break;
                case CONFIRM_CHANGE:
                    try {
                        AlterarContaController.alterarContaController(user, newName, newPassword);
                        JOptionUtils.message("Alterado com exito!");
                        HomeView.homeView(user);
                    } catch (SenhaIgualAnteriorException e) {
                        e.printStackTrace();
                        JOptionUtils.message("A senha não pode ser igual a anterior!");
                    } catch (DadoIncorreto dadoIncorreto) {
                        dadoIncorreto.printStackTrace();
                        JOptionUtils.message("Dados incorretos");

                    } catch (DadosNaoPreenchidos dadosNaoPreenchidos) {
                        dadosNaoPreenchidos.printStackTrace();
                        JOptionUtils.message("Dados nao preenchidos");

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        JOptionUtils.message("Erro!");


                    }
                    break;
                case SAIR:
                    HomeView.homeView(user);
                    JOptionUtils.message("Até logo,  " + user.getNome().toUpperCase(Locale.ROOT));
                    break;

                default:
                    JOptionUtils.message("Opção inválida!");
                    break;
            }

        }
    }
}
