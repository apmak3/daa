package fi.utu.tech.ThreadRunner.dispatchers;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import fi.utu.tech.ThreadRunner.tasks.Countable;
import fi.utu.tech.ThreadRunner.tasks.TaskFactory;
import fi.utu.tech.ThreadRunner.workers.Worker;
import fi.utu.tech.ThreadRunner.workers.WorkerFactory;

/*
 * Luokka, jossa toteutetaan staattinen tehtävien jako ts. työn tehtävä 1
 * 
* @author      
* @version     1.0                 
* @since       1.0          
*/

public class StaticDispatcher implements Dispatcher{

	/**
	 * Metodi, jossa on toteutettu staattinen tehtäväjako toiminnallisuus.
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
			//käytetään executorserviceä
			ExecutorService exec = Executors.newFixedThreadPool(controlSet.getThreadCount());
			
			//Jaetaan ilist pienempiin listoihin tekemällä lista lista johon tallennetaan kyseiset sublistit
			int y = 0;
			int x = 0;
			int time = 0; 
			int threadAmount = controlSet.getThreadCount();
			int partitionSize = ilist.size()/threadAmount;
		    List<List<Integer>> partitions = new LinkedList<List<Integer>>();
			for (int i = 0; i < ilist.size(); i += partitionSize) {	
				//if-helvetin funktio on pitää huoli siitä, että vaikka tehtävälista olisikin pariton, niin tämä ylimääräinen tehtävä tulee kuitenkin suoritettua.
				if (y == 1) {
					y--;
				}
				if (x == 0) {
						if (i == ilist.size()-partitionSize) {
				}
					if (ilist.size() % 2 != 0) {
						partitions.add(ilist.subList(i, Math.min(i + partitionSize+1, ilist.size())));
						x++;
						y++;
					}
				}
			    if (y == 0) {
			    	partitions.add(ilist.subList(i, Math.min(i + partitionSize, ilist.size())));
			    }
			}
			if (ilist.size() % 2 == 0) {
				
			}else {
				
			}
			
			
			
			//luodaan ja suoritetaan säikeet
			for (int i = 0; i < threadAmount; i++) {
				StaticThread s = new StaticThread(partitions.get(i), time, worker);
				exec.execute(s);
			}
			
			exec.shutdown();
			while(!exec.isTerminated()) {}
			
			
			
			
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
	
/**
 * 
 * Säieluokka staattiselle dispatcherille
 *
 */
class StaticThread extends Thread{	
	private List<Integer> ilist;
	private int time;
	private Worker worker;
	
	
	public StaticThread(List<Integer> ilist, int time, Worker worker) {				
		this.ilist = ilist;
		this.time = time;
		this.worker = worker;
		
		
	}

//säikeiden suoritus tapahtuu tässä
public void run() {
	
	
	try {	
		laske();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
//ja säikeiden itseasiallinen laskenta tapahtuu tässä.
synchronized void laske() {
	try {
		
			for (int time : ilist) {
				worker.count(time);
			}
			
		
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}
