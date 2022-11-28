package PROJETO;
public class Aviao extends Aeronave {
	public Passageiro lugares [][];
	private int fileira, assento;
	
	public Aviao(String modelo, int fileira, int assento) {
		super(modelo);
		lugares = new Passageiro[fileira][assento];
		this.fileira = fileira;
		this.assento = assento;
	}
		
	public Passageiro getPassageiro (int fileira, int assento) {
		if(lugares[fileira][assento] != null) {
			return lugares[fileira][assento];
		} else {
			return null;
		}
	}
	
	public void setPassageiro (int fileira, int assento, Passageiro passageiro) {
		lugares[fileira][assento] = passageiro;
	}
	
	public boolean verificaLugarOcupado (int fileira, int assento) {
		if(lugares[fileira][assento] != null) {
			return true;
		} else {
			return false;
		}
	}


	@Override
	public String toString() {
		return "Aviao [fileira=" + fileira + ", assento=" + assento + ", modelo=" + modelo + "]";
	}
	
}
