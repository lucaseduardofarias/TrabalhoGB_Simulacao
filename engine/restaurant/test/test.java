package engine.restaurant.test;

import engine.Scheduler;

public class test {

	public static void main(String[] args) {

		Scheduler schedule = new Scheduler();

		schedule.createResource("Caixa 1", 1); // FILA 0
        schedule.createEntitySet("FIFO", 1000); //fila caixa 1
        
        schedule.createResource("Caixa 2", 1); // FILA 1
        schedule.createEntitySet("FIFO", 1000); //fila caixa 2
        
        schedule.createResource("Balcão", 6); // FILA 2
        schedule.createEntitySet("FIFO", 1000); //fila do balcão
        
        schedule.createResource("Mesas para 2", 4); //FILA 3
        schedule.createEntitySet("FIFO", 1000); //fila das mesas para 2
        
        schedule.createResource("Mesas para 4", 4); //FILA 4
        schedule.createEntitySet("FIFO", 1000); //fila das mesas para 4
        
        schedule.createResource("Cozinheiros", 3); //FILA 5
        schedule.createEntitySet("FIFO", 1000); //fila dos pedidos a serem cozinhados
        
        schedule.createEntitySet("FIFO", 1000); // FILA 6 - Fila de pedidos prontos
        
        schedule.startArrival("ClientsArrival");//Evento inicial

		schedule.simulate();

	}

}
