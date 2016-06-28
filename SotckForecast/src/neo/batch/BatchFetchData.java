package neo.batch;

import java.util.Timer;
import java.util.TimerTask;
import neo.batch.timertask.FetchDataTask;

public class BatchFetchData {
	public void startFetch(){
		Timer timmer=new Timer();
		TimerTask fetchdata=new FetchDataTask();
		//設定日期
		
		//設定停止時間
		timmer.scheduleAtFixedRate(fetchdata, 0, 60*1000);
	}
}
