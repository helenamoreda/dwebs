package urna;

import java.util.ArrayList;
import java.util.List;

public class UrnaBolas {
	private List<Integer> bolas;

	public UrnaBolas() {
		this.bolas = new ArrayList<Integer>();
	}

	public List<Integer> getBolas() {
		return bolas;
	}

	public void setBolas(List<Integer> bolas) {
		this.bolas = bolas;
	}

	void generaNegras() {
		int num = (int) (Math.random() * 50 + 1);
		for (int i = 0; i < num; i++) {
			bolas.add(Integer.valueOf(0));
		}
	}

	void generaRojas() {
		int num = (int) (Math.random() * 50 + 1);
		for (int i = 0; i < num; i++) {
			bolas.add(Integer.valueOf(1));
		}
	}

	String cuentaNegras() {
		return String.valueOf(bolas.stream().filter(e -> e.equals(0)).count());
	}

	String cuentaRojas() {
		return String.valueOf(bolas.stream().filter(e -> e.equals(1)).count());
	}
}
