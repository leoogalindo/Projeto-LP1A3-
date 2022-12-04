package PROJETO;

import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.*;
import java.util.Iterator;

public class Main {

	static ArrayList<Aviao> listaDeAvioes = new ArrayList<Aviao>();
	static ArrayList<Passageiro> listaDePassageiros = new ArrayList<Passageiro>();
	static ArrayList<Voo> listaDeVoos = new ArrayList<Voo>(10);

	static int numAv = -1;
	static int numVoo = -1;

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Sistema de reserva de passagens da companhia aérea XXXXX");

		while (true) {
			int opc = menuPrincipal();
			switch (opc) {
			case 1:
				parametrosDoSistema();
				break;
			case 2:
				reservaDePassagem();
				break;
			case 3:
				System.exit(1);
				break;
			default:
				JOptionPane.showMessageDialog(null, "Opção inválida!");

			}
		}

	}

	private static int menuPrincipal() {
		int valido;
		int aux = 1;
		while (true) {
			String resp = JOptionPane
					.showInputDialog("                Menu Principal \nSelecione uma das opções abaixo: "
							+ "\n1) Parâmetros de Sistema\n2) Reserva de Passagem\n3) Sair");
			valido = validaResp(resp, aux);
			if (valido == 1 || valido == 2 || valido == 3) {
				break;
			}

		}
		return valido;
	}

	private static int validaResp(String resp, int aux) {
		int respNum;
		int[] menu1 = { 1, 2, 3 };
		int[] menu2 = { 1, 2, 3, 4 };

		while (true) {
			if (resp == null) {
				JOptionPane.showMessageDialog(null, "Saindo do sistema!");
				System.exit(1);
			}
			try {
				respNum = Integer.parseInt(resp);
				if (respNum == 1) {
					int x = (menu1[(respNum - 1)]);
				} else {
					int x = (menu2[respNum - 1]);
				}
				break;
			} catch (IndexOutOfBoundsException | NumberFormatException e) {
				if (e.getClass().toString().equals("class java.lang.ArrayIndexOutOfBoundsException")) {
					JOptionPane.showMessageDialog(null, "Esta opção não existe");
				} else if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "Não é um número!");
				}
			}
			return -1;
		}
		return respNum;
	}
	
	private static void parametrosDoSistema() {
		String opc;
		int aux = 1, valido, control = 0;

		while (control == 0) {
			opc = JOptionPane
					.showInputDialog("Parâmetros do sistema \n 1) Cadastrar Aeronave \n 2) Cadastrar Voo \n 3) Voltar");
			valido = validaResp(opc, aux);
			switch (valido) {
			case 1:
				cadastraAeronave();
				break;
			case 2:
				cadastraVoo();
				break;
			case 3:
				control = 1;
				break;
			}
		}
	}
	
	private static void cadastraAeronave() {
		String modeloAeronave, fileiras, assentos;

		while (true) {
			try {
				modeloAeronave = JOptionPane.showInputDialog(null,
						"Cadastrando Aeronave \n Insira o modelo da aeronave: ");
				if (modeloAeronave.length() < 1) {
					JOptionPane.showMessageDialog(null, "Modelo vázio!");
					continue;
				}

				fileiras = JOptionPane.showInputDialog(null, "Cadastrando Aeronave \n Insira o número de fileiras: ");
				int fileirasNum = Integer.parseInt(fileiras);
				assentos = JOptionPane.showInputDialog(null, "Cadastrando Aeronave \n Insira o número de assentos: ");
				int assentosNum = Integer.parseInt(assentos);

				listaDeAvioes.add(new Aviao(modeloAeronave, fileirasNum, assentosNum));
				break;

			} catch (NumberFormatException | NullPointerException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "Não é um número!");
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
					JOptionPane.showMessageDialog(null, "Cancelando a operação");
				}
			}
		}

		numAv++;
		JOptionPane.showMessageDialog(null,
				"Avião com modelo:  " + listaDeAvioes.get(numAv).modelo + " \n Criado com sucesso.");
	}
	
	private static void cadastraVoo() {
		String nVoo, data, hora, aviao;
		int numAviao, voo, numHora;

		while (true) {
			try {
				aviao = JOptionPane.showInputDialog(null, "Cadastrando Voo \nTemos  aviões cadastrados " + exibeLista()
						+ "\n Informe nº da aeronave desejada: ");

				numAviao = Integer.parseInt(aviao);
				if (numAviao > listaDeAvioes.size()) {
					JOptionPane.showMessageDialog(null, "Fora do range de aviões cadastrados!");
				} else {
					nVoo = JOptionPane.showInputDialog(null, "Cadastrando Voo \n Informe o nº do voo desejado: ");
					voo = Integer.parseInt(nVoo);
					hora = JOptionPane.showInputDialog(null, "Cadastrando hora do Voo \n Informe a hora: ");
					numHora = Integer.parseInt(hora);
					if (hora.length() > 0) {
						if (numHora > 24) {
							JOptionPane.showMessageDialog(null, "Hora inválida!");
						} else {
							data = JOptionPane.showInputDialog(null, "Cadastrando data do Voo \n Informe a data: ");
							if (data.length() > 0) {
								numVoo++;
								listaDeVoos.add(numVoo, new Voo(listaDeAvioes.get(numAviao - 1), voo, data, hora));
								break;
							} else {
								JOptionPane.showMessageDialog(null, "Data não pode ser vazia!");
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "Hora não pode ser vazia!");
					}
				}
			} catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "Não é um número!");
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
					JOptionPane.showMessageDialog(null, "Cancelando a operação");
				} else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
				}
			}

		}

		JOptionPane.showMessageDialog(null,
				"Voo com número: " + listaDeVoos.get(numVoo).getNro() + " criado com sucesso!");
	}

	public static String exibeLista() {
		String av = "";
		for (int i = 0; i < listaDeAvioes.size(); i++) {
			av = av + "\n" + (i + 1) + " - " + listaDeAvioes.get(i);
		}
		return av;
	}
	
	public static String exibeVoo() {
		String voo = "";
		for (int i = 0; i < listaDeVoos.size(); i++) {
			voo = voo + "\n" + listaDeVoos.get(i);
		}
		return voo;
	}

	private static void reservaDePassagem() {
		String opc;
		int aux = 1, valido, control = 0;
		while (control == 0) {
			opc = JOptionPane.showInputDialog(
					"Reserva de passagem \n 1) Realizar reserva \n 2) Consultar lugares disponiveis \n 3) Consultar reservas \n 4) Voltar");
			valido = validaResp(opc, aux);

			switch (valido) {
			case 1:
				reservar();
				break;
			case 2:
				consultarLugar();
				break;
			case 3:
				consultarReservas();
				break;
			case 4:
				control = 1;
				break;
			}
		}

	}
	
	private static void reservar() {
		boolean lugar;
		int control = 0;
		int num = 0, fileira = 0, assento = 0;
		String nome = "", cpf = "";
		Iterator<Voo> itr = listaDeVoos.iterator();

		if (itr.hasNext() == false) {
			JOptionPane.showMessageDialog(null, "Nenhum voo cadastrado!");

		}

		while (control == 0) {
			try {
				nome = JOptionPane.showInputDialog(null,
						"             Reserva de passagem\n Insira o nome do passageiro:");
				if (nome == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				}
				if (nome.length() < 1) {
					JOptionPane.showMessageDialog(null, "O nome do passageiro não pode ser vazio!");
					continue;
				}
				cpf = JOptionPane.showInputDialog(null,
						"             Reserva de passagem\n Insira o CPF do passageiro:");
				if (cpf == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				}
				if (validaCPF(cpf) == false) {
					JOptionPane.showMessageDialog(null, "O CPF é inválido!");
					continue;
				}
				String sNum = JOptionPane.showInputDialog(null,
						"          Reserva de passagem: \n" + exibeVoo() + "\n " + "Insira o número do voo desejado:");
				if (sNum == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				}
				if (sNum.length() == 0) {
					JOptionPane.showMessageDialog(null, "O número do voo não pode ser vazio!");
					continue;
				}
				num = Integer.parseInt(sNum);
				String sFileira = JOptionPane.showInputDialog(null,
						"                  Cadastro de voo\n Insira o número da fileira:");
				if (sFileira == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				}
				if (sFileira.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número da fileira não pode ser vazio!");
					continue;
				}
				fileira = Integer.parseInt(sFileira);
				String sAssento = JOptionPane.showInputDialog(null,
						"                  Cadastro de voo\n Insira o número do assento:");
				if (sAssento == null) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				}
				if (sAssento.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número do assento não pode ser vazio!");
					continue;
				}
				assento = Integer.parseInt(sAssento);
			}

			catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				} else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
					continue;
				} else if ((e.getClass().toString().equals("class java.lang.ArrayIndexOutOfBoundsException"))) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				}
			}

			int len = listaDeVoos.size();
			for (int k = 0; k < len; k++) {
				Voo voo = listaDeVoos.get(k);
				if (voo.getNro() == num) {
					lugar = voo.getAeronave().verificaLugarOcupado((fileira - 1), (assento - 1));

					if (lugar == false) {
						voo.getAeronave().setPassageiro((fileira - 1), (assento - 1), new Passageiro(nome, cpf));

						listaDePassageiros.add(new Passageiro(nome, cpf));
						JOptionPane.showMessageDialog(null,
								String.format("Reserva realizada com sucesso!\n\nFileira:      [%d]\nAssento:  [%d]",
										fileira, assento));
					}
					control = 1;
				}

			}

		}
	}

	private static void consultarReservas() {
		Iterator<Voo> itr = listaDeVoos.iterator();
		int len = listaDeVoos.size();
		int num = 0;
		int control = 0;

		if (itr.hasNext() == false) {
			JOptionPane.showMessageDialog(null, "Nenhum voo cadastrado!");
		}

		while (control == 0) {
			try {
				String sNum = (JOptionPane.showInputDialog("Digite o número do Voo: \n" + exibeVoo()));
				if (sNum.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número do voo não pode ser vazio!");
					continue;
				}
				num = Integer.parseInt(sNum);

			}

			catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
					continue;
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");
				} else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
					continue;
				}
			}

			for (int k = 0; k < len; k++) {
				Voo voo = listaDeVoos.get(k);
				if (voo.getNro() == num) {

					int i, j;

					String numb = " ";
					for (i = 0; i < voo.getAeronave().lugares.length; i++) {
						for (j = 0; j < voo.getAeronave().lugares.length; j++) {
							if (voo.getAeronave().lugares[i][j] != null) {
								numb = numb + "Fileira [" + (i + 1) + "] | Assento [" + (j + 1) + "]\n";
							}
						}
					}

					JOptionPane.showMessageDialog(null,
							"O voo de número " + num + " tem os seguintes lugares reservados: \n\n" + numb);
				}

				else {
					JOptionPane.showMessageDialog(null, "O número do voo não existe.\nPor favor, escolha outro!");
				}
				
				control = 1;
			}
		}
	}


	private static void consultarLugar() {
		Iterator<Voo> itr = listaDeVoos.iterator();
		int control = 0;

		if (itr.hasNext() == false) {
			JOptionPane.showMessageDialog(null, "Nenhum voo cadastrado!");

		}


		while (control == 0) {
			int num = 0;
			try {
				String sNum = (JOptionPane.showInputDialog("Digite o número do Voo: \n" + exibeVoo()));
				
				if (sNum.length() < 1) {
					JOptionPane.showMessageDialog(null, "O número do voo não pode ser vazio!");
					continue;
				}
				num = Integer.parseInt(sNum);
			}

			catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")) {
					JOptionPane.showMessageDialog(null, "A opção selecionada não é um número!");
					continue;
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
					JOptionPane.showMessageDialog(null, "Operação cancelada!");

				} else if (e.getClass().toString().equals("class java.lang.IndexOutOfBoundsException")) {
					JOptionPane.showMessageDialog(null, "O avião escolhido não existe.");
					continue;
				}
			}


			int len = listaDeVoos.size();
			for (int k = 0; k < len; k++) {
				Voo voo = listaDeVoos.get(k);
				if (voo.getNro() == num) {
					int lugar = 0;
					for (int i = 0; i < voo.getAeronave().lugares.length; i++) {
						for (int j = 0; j < voo.getAeronave().lugares[0].length; j++) {
							if (voo.getAeronave().lugares[i][j] == null) {
								lugar++;
							}
						}
					}

					JOptionPane.showMessageDialog(null,
							"O Voo de número " + voo.getNro() + " tem " + lugar + " lugares disponível(s)");
				} else {

					JOptionPane.showMessageDialog(null, "O número do voo não existe.\nPor favor, escolha outro!");
				}
				control = 1;
			}
		}
	}


	public static boolean validaCPF(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11)) {

			return (false);
		}
		char dig10, dig11;
		int sm, i, r, num, peso;
		try {

			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48);
			}

			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
				return (true);
			} else
				return (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}
}
