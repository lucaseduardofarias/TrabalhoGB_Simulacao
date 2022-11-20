package engine.restaurant.test;

import engine.Scheduler;

public class test {

	public static void main(String[] args) {

		Scheduler s = new Scheduler();

		s.createResource("Caixa 1", 1); // 0
		s.createEntitySet("LIFO", 1000); // fila caixa 1
		s.createResource("Caixa 2", 1); // 1
		s.createEntitySet("LIFO", 1000); // fila caixa 2
		s.createResource("Balcão", 6); // 2
		s.createEntitySet("LIFO", 1000); // fila do balcão
		s.createResource("Mesas para 2", 4); // 3
		s.createEntitySet("LIFO", 1000); // fila das mesas para 2
		s.createResource("Mesas para 4", 4); // 4
		s.createEntitySet("LIFO", 1000); // fila das mesas para 4
		s.createResource("Cozinheiros", 3); // 5
		s.createEntitySet("LIFO", 1000); // fila dos pedidos a serem cozinhados
		s.createEntitySet("LIFO", 1000); // Fila de pedidos prontos: ID 6
		s.startArrival("ClientsArrival");

		s.simulate();

	}

}
