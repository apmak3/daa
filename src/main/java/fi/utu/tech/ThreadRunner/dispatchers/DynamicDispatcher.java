package fi.utu.tech.ThreadRunner.dispatchers;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;
import fi.utu.tech.ThreadRunner.workers.Worker;
import fi.utu.tech.ThreadRunner.workers.WorkerFactory;

/*
 * Luokka, jossa toteutetaan dynaaminen tehtävien jako ts. työn tehtävä 2
 * 
* @author      
* @version     1.0                 
* @since       1.0          
*/

public class DynamicDispatcher implements Dispatcher { 

	/**
	 * Metodi, jossa on toteutetaan dynaamisen tehtävän jaon toiminnallisuus.
	 *
	 *
	 * @param ControlSet Käyttöliittymässä asetettu arvot välittyvät tässä oliossa
	 * @return void
	 * 
	 */
	public void dispatch(ControlSet controlSet) {

		try {
		Countable co = TaskFactory.createTask(controlSet.getTaskType());
		ArrayList<Integer> ilist = co.generate(controlSet.getAmountTasks(), controlSet.getMaxTime());

		Worker worker = WorkerFactory.createWorker(controlSet.getWorkerType());
		
		ExecutorService exec = Executors.newFixedThreadPool(controlSet.getThreadCount());
		
		for (int time : ilist) {
			DynThread D = new DynThread(time, worker);
			
			exec.execute(D);
		}//for
		
		exec.shutdown();
		while(!exec.isTerminated()) {}
		
		}catch(Exception ex) {	
			ex.printStackTrace();
			}//catch
		}//dispatch
}//class


/**
 * 
 * Säieluokka dynaamiselle dispatcherille
 *
 */
class DynThread extends Thread{
	private Worker worker;
	private int time;

	public DynThread(int time, Worker worker) {
		this.time = time;
		this.worker = worker;
	}
	/**
	 * run metodi kutsuu laske -metodia jossa laskenta suoritetaan
	 */
	public void run() {
		try {
			laske();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//run
	/**
	 * Metodi jossa toteutetaan dynaamisen dispatcherin laskenta
	 * @param
	 * @return void
	 */
	synchronized void laske() {
		try {
			worker.count(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//laske
	
}//class