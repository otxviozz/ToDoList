package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField campoTarefa;
	private JButton btnConfirmar;
	private JRadioButton rdbtnNaoIniciado;
	private JRadioButton rdbtnEmAndamento;
	private JRadioButton rdbtnConcluido;
	private JPanel painelStatus;
	private JLabel lblStatus;
	String path="";
	ArrayList<String> tarefas = new ArrayList<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void atualizarPainelTarefas(JPanel painelTarefas) {
		painelTarefas.removeAll();
		for (int i = 1; i < tarefas.size(); i++) {
		    String tarefa = tarefas.get(i);
		    String[] partes = tarefa.split(",");

		    JPanel linha = new JPanel(new BorderLayout());
		    linha.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

		    JLabel lblTarefa = new JLabel(tarefa);
		    lblTarefa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		    lblTarefa.setVerticalAlignment(SwingConstants.TOP);
		    linha.add(lblTarefa, BorderLayout.WEST);

		    JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		    JButton btnEditar = new JButton("Editar");
		    JButton btnRemover = new JButton("Remover");
		    painelBotoes.add(btnEditar);
		    painelBotoes.add(btnRemover);
		    linha.add(painelBotoes, BorderLayout.EAST);
		    
		    int index = i;
		    
		    btnEditar.addActionListener(e -> {
		        String tarefaAtual = tarefas.get(index);

		        if (partes.length == 2) {
		            String tarefaAntiga = partes[0];
		            String statusAntigo = partes[1];
		            

		            campoTarefa.setText(tarefaAntiga);
		            switch (statusAntigo) {
		                case "Não iniciado": rdbtnNaoIniciado.setSelected(true); break;
		                case "Em andamento": rdbtnEmAndamento.setSelected(true); break;
		                case "Concluído": rdbtnConcluido.setSelected(true); break;
		            }

		            lblStatus.setVisible(false);
		            campoTarefa.setVisible(true);
		            painelStatus.setVisible(true);
		            btnConfirmar.setVisible(true);
		            btnConfirmar.setText("Atualizar");

		            for (ActionListener al : btnConfirmar.getActionListeners()) {
		                btnConfirmar.removeActionListener(al);
		            }

		            btnConfirmar.addActionListener(ev -> {
		                String novoTexto = campoTarefa.getText().trim();
		                String novoStatus = rdbtnNaoIniciado.isSelected() ? rdbtnNaoIniciado.getText() :
		                                    rdbtnEmAndamento.isSelected() ? rdbtnEmAndamento.getText() :
		                                    rdbtnConcluido.getText();
		                String novaTarefa = novoTexto + "," + novoStatus;
		                tarefas.set(index, novaTarefa);
		                atualizarPainelTarefas(painelTarefas);

		                campoTarefa.setText("");
		                campoTarefa.setVisible(false);
		                painelStatus.setVisible(false);
		                btnConfirmar.setVisible(false);
		            });
		        } else {
		            System.out.println("Formato inválido para: " + tarefaAtual);
		        }
		    });
		    
		    btnRemover.addActionListener(e -> {
		        tarefas.remove(index);
		        atualizarPainelTarefas(painelTarefas);
		    });
		    
		    painelTarefas.add(linha);
		}

		painelTarefas.revalidate();
		painelTarefas.repaint();
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		tarefas.add("Tarefas a serem feitas, Status");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel painelTarefas = new JPanel();
		painelTarefas.setLayout(new BoxLayout(painelTarefas, BoxLayout.Y_AXIS));
		painelTarefas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane scrollPane = new JScrollPane(painelTarefas);
		scrollPane.setBounds(48, 250, 646, 282);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		contentPane.add(scrollPane);
		
		rdbtnNaoIniciado = new JRadioButton("Não iniciado");
		rdbtnNaoIniciado.setBounds(0, 0, 120, 40); 
		rdbtnEmAndamento = new JRadioButton("Em andamento");
		rdbtnEmAndamento.setBounds(130, 0, 130, 40);
		rdbtnConcluido = new JRadioButton("Concluído");
		rdbtnConcluido.setBounds(270, 0, 100, 40);

		ButtonGroup grupoStatus = new ButtonGroup();
		grupoStatus.add(rdbtnNaoIniciado);
		grupoStatus.add(rdbtnEmAndamento);
		grupoStatus.add(rdbtnConcluido);

		painelStatus = new JPanel();
		painelStatus.setLayout(null);
		painelStatus.setBounds(48, 179, 415, 42);

		painelStatus.add(rdbtnNaoIniciado);
		painelStatus.add(rdbtnEmAndamento);
		painelStatus.add(rdbtnConcluido);
		painelStatus.setVisible(false);
		contentPane.add(painelStatus);
		
		campoTarefa = new JTextField();
		campoTarefa.setBounds(48, 127, 415, 30);
		campoTarefa.setVisible(false);
		contentPane.add(campoTarefa);

		lblStatus = new JLabel("");
		lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblStatus.setBounds(48, 153, 415, 42);
		lblStatus.setVisible(false);
		contentPane.add(lblStatus);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String statusSelecionado = "";

				if (rdbtnNaoIniciado.isSelected()) {
				    statusSelecionado = rdbtnNaoIniciado.getText();
				} else if (rdbtnEmAndamento.isSelected()) {
				    statusSelecionado = rdbtnEmAndamento.getText();
				} else if (rdbtnConcluido.isSelected()) {
				    statusSelecionado = rdbtnConcluido.getText();
				}

				String inserir = campoTarefa.getText().trim() + "," + statusSelecionado;

				if (!inserir.isEmpty() && !inserir.equals("Insira a tarefa")) {
					tarefas.add(inserir);
					atualizarPainelTarefas(painelTarefas);

					System.out.println("Tarefa adicionada: " + inserir);
					lblStatus.setVisible(true);
					lblStatus.setText("Tarefa adicionada com sucesso!");
					

					campoTarefa.setText("");
					campoTarefa.setVisible(false);
					painelStatus.setVisible(false);
					btnConfirmar.setVisible(false);
				} else {
					javax.swing.JOptionPane.showMessageDialog(null, "Digite uma tarefa válida.");
				}
			}
		});
		btnConfirmar.setBounds(516, 151, 150, 30);
		btnConfirmar.setVisible(false);
		atualizarPainelTarefas(painelTarefas);
		contentPane.add(btnConfirmar);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoTarefa.setVisible(true);
				painelStatus.setVisible(true);
				lblStatus.setVisible(false);
				btnConfirmar.setVisible(true);
				btnConfirmar.setText("Inserir");
			}
		});
		btnInserir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnInserir.setBounds(139, 64, 100, 40);
		contentPane.add(btnInserir);
		
		JLabel lblNewLabel = new JLabel("To Do List");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(200, 14, 281, 39);
		contentPane.add(lblNewLabel);
		
		JButton btnCarregar = new JButton("Carregar");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoTarefa.setVisible(false);
				painelStatus.setVisible(false);
				lblStatus.setVisible(false);
				btnConfirmar.setVisible(false);

				JFileChooser fileChooser = new JFileChooser();
				int resultado = fileChooser.showOpenDialog(null);

				if (resultado == JFileChooser.APPROVE_OPTION) {
					File arquivoSelecionado = fileChooser.getSelectedFile();
					String caminhoArquivo = arquivoSelecionado.getAbsolutePath();
					path = caminhoArquivo;

					System.out.println("Arquivo escolhido: " + caminhoArquivo);
					lblStatus.setText("Arquivo carregado com sucesso!");
					lblStatus.setVisible(true);

					try (BufferedReader br = new BufferedReader(new FileReader(path))) {
						tarefas.clear();
						String linha;

						while ((linha = br.readLine()) != null) {
							linha = linha.trim();
							String[] partes = linha.split(",");

							if (partes.length == 2) {
								String tarefa = partes[0].trim();
								String status = partes[1].trim();

								if (!tarefa.isEmpty() && !status.isEmpty()) {
									tarefas.add(tarefa + "," + status);
								} else {
									System.out.println("Linha ignorada (campos vazios): " + linha);
								}
							} else {
								System.out.println("Linha ignorada (formato inválido): " + linha);
							}
						}

						atualizarPainelTarefas(painelTarefas);
					} catch (IOException ex) {
						System.out.println("Erro ao ler o arquivo: " + ex.getMessage());
					}
				}
			}
		});
		btnCarregar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCarregar.setBounds(302, 64, 100, 40);
		contentPane.add(btnCarregar);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int resultado = fileChooser.showSaveDialog(null);

		        if (resultado == JFileChooser.APPROVE_OPTION) {
		            File arquivoSelecionado = fileChooser.getSelectedFile();
		            path = arquivoSelecionado.getAbsolutePath();

		            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
		                for (String linha : tarefas) {
		                    bw.write(linha);
		                    bw.newLine();
		                }
		                javax.swing.JOptionPane.showMessageDialog(null, "Tarefas salvas com sucesso!");
		                lblStatus.setVisible(false);
		                lblStatus.setText("");
		            } catch (IOException ex) {
		                javax.swing.JOptionPane.showMessageDialog(null, "Erro ao salvar tarefas!");
		            }
		        }
		    }
		});
		btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSalvar.setBounds(464, 64, 100, 40);
		contentPane.add(btnSalvar);

		
		JButton btnSair = new JButton("SAIR");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(605, 11, 89, 23);
		contentPane.add(btnSair);
	}
}
