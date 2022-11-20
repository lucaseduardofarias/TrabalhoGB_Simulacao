package engine;

public class NumberGenerators {

	public Double geraRandom() {
		Integer semente = 0;
		Integer auxInt;
		while (semente < 1000) {
			semente = (int) Math.floor(Math.random() * (9999 - 1000 + 1));
		}
		String aux;
		String aux2;

		for (int i = 0; i < 4; i++) {
			auxInt = semente * semente;

			aux = Integer.toString(auxInt);
			aux = aux.substring(3, 7);
			semente = Integer.parseInt(aux);
			if (semente < 1000) {
				aux = Integer.toString(auxInt);
			}
			while (semente < 1000) {
				aux2 = "0" + aux;

				aux = aux2.substring(3, 7);

				semente = Integer.parseInt(aux);

				aux = aux2;

			}

		}

		double d = semente / 9999.0;

		return d;
	}

	public double normalDist(int media, int desvio) {
		double res = 0;
		double rand1;
		double rand2;
		double w = 2;
		double y = 0;
		double var = 0;
		double vAux1 = 0;
		double vAux2 = 0;

		while (w > 1) {
			rand1 = geraRandom();
			rand2 = geraRandom();

			vAux1 = (2 * rand1) - 1;
			vAux2 = (2 * rand2) - 1;

			w = (Math.pow(vAux1, 2)) + (Math.pow(vAux2, 2));
		}

		y = Math.sqrt((-2 * Math.log(w)) / w);

		var = vAux1 * y;

		res = media + (desvio * var);

		return res;

	}

	public Double exponencial(int media) {
		Double result = 0.0;
		Double rand = geraRandom();

		result = (-media) * Math.log(1 - rand);

		return result;
	}
}
