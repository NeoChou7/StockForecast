package neo.batch;

import java.util.Timer;
import java.util.TimerTask;
import neo.batch.timertask.FetchDataTask;

public class BatchFetchData {
	public void startFetch(){
		Timer timmer=new Timer();
		TimerTask fetchdata=new FetchDataTask();
		//�]�w���
		
		//�]�w����ɶ�
		timmer.scheduleAtFixedRate(fetchdata, 0, 60*1000);
	}
}
