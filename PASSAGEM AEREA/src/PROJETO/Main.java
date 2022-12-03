package PROJETO;
import java.util.ArrayList;
import javax.swing.*;
public class Main {
	
	static ArrayList<Aviao> listaDeAvioes = new ArrayList<Aviao>();
	static ArrayList<Passageiro> listaDePassageiros = new ArrayList<Passageiro>();
	static ArrayList<Voo> listaDeVoos = new ArrayList<Voo>(10);

	static int numAv = -1;
	static int numVoo = -1;
	
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Sistema de reserva de passagens da companhia aérea XXXXX");
		
		while(true) {
			int r = menuPrincipal();
			switch(r) {
				case 1:
					prametrosDoSistema();
					break;
				case 2:
					reservaDePassagem();
					break;
				case 3: 
					 System.exit(1);
					 break;
				default:JOptionPane.showMessageDialog(null, "Opção inválida!");
					
			}
		}

	}
	
	private static int menuPrincipal() {
		int valido;
		int aux = 1;
		while(true) {
			String resp = JOptionPane.showInputDialog("                Menu Principal \nSelecione uma das opções abaixo: "
					+ "\n1) Parâmetros de Sistema\n2) Reserva de Passagem\n3) Sair");
			valido = validaResp(resp, aux);
			if(valido == 1 || valido == 2 || valido == 3) {
				break;
			}
			
		}
		return valido;
	}

	private static int validaResp(String resp, int aux) {
		int respNum;
		int [] menu1 = {1, 2, 3};
		int [] menu2 = {1, 2, 3, 4};
		
		while(true) {
			if(resp == null) {
				JOptionPane.showMessageDialog(null,"Saindo do sistema!");
				System.exit(1);
			}
			try {
				respNum = Integer.parseInt(resp);
				if(aux == 1) {
					int x = (menu1[(respNum-1)]);
				} else {
					int x = (menu2[respNum-1]);
				}
				break;
			} catch(IndexOutOfBoundsException | NumberFormatException e) {
				if(e.getClass().toString().equals("class java.lang.ArrayIndexOutOfBoundsException")) {
					JOptionPane.showMessageDialog(null, "Esta opção não existe");
				} else if (e.getClass().toString().equals("class java.lang.NumberFormatException")){
					JOptionPane.showMessageDialog(null, "Não é um número!");
				}
			}
			return -1;
		}
		return respNum;
	}
	
	private static void reservaDePassagem() {
		String opc;
		int aux = 1, valido;
		opc = JOptionPane.showInputDialog("Reserva de passagem \n 1) Realizar reserva \n 2) Consultar lugares disponiveis \n 3) Consultar reservas \n 4) Voltar");
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
				menuPrincipal();
				break;
		}
		
	}

	private static void consultarReservas() {
		// TODO Auto-generated method stub
		
	}

	private static void consultarLugar() {
		// TODO Auto-generated method stub
		
	}

	private static void reservar() {
				
	}

	private static void prametrosDoSistema() {
		String opc;
		int aux = 1, valido;
		
		while (true) {
			opc = JOptionPane.showInputDialog("Parâmetros do sistema \n 1) Cadastrar Aeronave \n 2) Cadastrar Voo \n 3) Voltar");
			valido = validaResp(opc, aux);
			switch (valido) {
				case 1:
					cadastraAeronave();
					break;
				case 2:
					cadastraVoo();
					break;
				case 3: 
					menuPrincipal();
					break;
			}
		}
		
	}

	private static void cadastraAeronave() {
		String modeloAeronave, fileiras, assentos;
		
		while(true) {
			try {
				modeloAeronave = JOptionPane.showInputDialog(null, "Cadastrando Aeronave \n Insira o modelo da aeronave: ");
				if(modeloAeronave.length() < 1) {
					JOptionPane.showMessageDialog(null, "Modelo vázio!");
					continue;
				}
				
				fileiras = JOptionPane.showInputDialog(null, "Cadastrando Aeronave \n Insira o número de fileiras: ");
				int fileirasNum = Integer.parseInt(fileiras);
				assentos = JOptionPane.showInputDialog(null, "Cadastrando Aeronave \n Insira o número de assentos: ");
				int assentosNum = Integer.parseInt(assentos);
				
				listaDeAvioes.add(new Aviao(modeloAeronave, fileirasNum, assentosNum));
				break;
				
			} catch (NumberFormatException |  NullPointerException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")){
					JOptionPane.showMessageDialog(null, "Não é um número!");
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")){
					JOptionPane.showMessageDialog(null, "Cancelando a operação");
				}
			}
		}
		
		numAv++;
		JOptionPane.showMessageDialog(null, "Avião com modelo:  " + listaDeAvioes.get(numAv).modelo + " \n Criado com sucesso.");
	}

	private static void cadastraVoo() {
		String nVoo, data, hora, aviao;
		int numAviao, voo, numHora;
		
		while(true) {
			try {
				aviao = JOptionPane.showInputDialog(null, "Cadastrando Voo \n Temos " + listaDeAvioes.size() + " aviões cadastrados "
						+ "\n Informe nº da aeronave desejada: ");
				numAviao = Integer.parseInt(aviao);
				if(numAviao > listaDeAvioes.size()) {
					JOptionPane.showMessageDialog(null, "Fora do range de aviões cadastrados!");
				} else {
					nVoo = JOptionPane.showInputDialog(null, "Cadastrando Voo \n Informe o nº do voo desejado: ");
					voo = Integer.parseInt(nVoo);
					hora = JOptionPane.showInputDialog(null, "Cadastrando hora do Voo \n Informe a hora: ");
					numHora = Integer.parseInt(hora);
					if(hora.length() > 0) {
						if(numHora > 24) {
							JOptionPane.showMessageDialog(null, "Hora inválida!");
						} else {
							data = JOptionPane.showInputDialog(null, "Cadastrando data do Voo \n Informe a data: ");
							if(data.length() > 0) {
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
			} catch (NumberFormatException |  NullPointerException | IndexOutOfBoundsException e) {
				if (e.getClass().toString().equals("class java.lang.NumberFormatException")){
					JOptionPane.showMessageDialog(null, "Não é um número!");
				} else if (e.getClass().toString().equals("class java.lang.NullPointerException")) {
					JOptionPane.showMessageDialog(null, "Cancelando a operação");
				}  
			}
			
		}
		
		JOptionPane.showMessageDialog(null, "Voo com número: " + listaDeVoos.get(numVoo).getNro() + " criado com sucesso!" );
	}

}
