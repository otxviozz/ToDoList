package procedures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import main.Application;
import show.ShowCarregar;

public class Carregar {
    public static void LogicaCarregar() {
        ShowCarregar.MostrarCarregar();

        try (BufferedReader br = new BufferedReader(new FileReader(Application.path))) {
            Application.tarefas.clear();
            String linha;

            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                String[] partes = linha.split(",");

                if (partes.length == 2) {
                    String tarefa = partes[0].trim();
                    String status = partes[1].trim();

                    if (!tarefa.isEmpty() && !status.isEmpty()) {
                        Application.tarefas.add(tarefa + "," + status);
                    } else {
                        System.out.println("Linha ignorada (campos vazios): " + linha);
                    }
                } else {
                    System.out.println("Linha ignorada (formato inválido): " + linha);
                }
            }

            AtualizarPainel.atualizarPainelTarefas(Application.painelTarefas, Application.tarefas);
        } catch (IOException ex) {
            System.out.println("Erro ao ler o arquivo: " + ex.getMessage());
        }
    }
}
