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
			String resp = JOptionPane.showInputDialog("                Menu Principal \nSelecione uma das opções abaixo: \n1) Parêmetros de Sistema\n2) Reserva de Passagem\n3) Sair");
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
		// TODO Auto-generated method stub
		
	}

	private static void prametrosDoSistema() {
		// TODO Auto-generated method stub
		
	}

}