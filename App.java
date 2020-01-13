package _15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) throws InterruptedException {
		ExecutorService service = null;
		Philosopher[] philosophers = null;
		try {
			philosophers = new Philosopher[Constant.NUMBER_OF_PHILOSOPHERS];
			Chopstick[] chopsticks = new Chopstick[Constant.NUMBER_OF_CHOPSTICKS];

			for (int i = 0; i < Constant.NUMBER_OF_CHOPSTICKS; i++) {
				chopsticks[i] = new Chopstick(i);
			}

			service = Executors.newFixedThreadPool(Constant.NUMBER_OF_PHILOSOPHERS);

			for (int i = 0; i < Constant.NUMBER_OF_PHILOSOPHERS; i++) {
				philosophers[i] = new Philosopher(i, chopsticks[i],
						chopsticks[(i + 1) % Constant.NUMBER_OF_CHOPSTICKS]);
				service.execute(philosophers[i]);
			}

			Thread.sleep(Constant.SIMULATION_RUNNING_TIME);

			for (Philosopher p : philosophers) {
				p.setFull(true);
			}
		} catch (Exception e) {
		} finally {
			service.shutdown();

			while (!service.isTerminated())
				Thread.sleep(1000);

			for (Philosopher p : philosophers) {
				System.out.println(p + " eats " + p.getCounter());
			}

		}

	}

}
