package neo.batch.timertask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Stack;
import java.util.TimerTask;

import neo.bean.TradeDataBean;
import neo.dao.tradedata.ITradeDataDAO;
import neo.dao.tradedata.TradeDataDAO;
import neo.parseJSON.*;

public class FetchDataTask extends TimerTask {
	private HttpURLConnection conn;
	private FetchData fetchdata;
	private ITradeDataDAO dao;
	@Override
	public void run() {
		String url = "https://tw.screener.finance.yahoo.net/future/q?type=tick&perd=1m&mkt=01&sym=WTX%26";// 此參數以後要從xml取得
		String data = "";
		Stack<TradeDataBean> beans;
		// 取得資料
		try {
			data = getJSON(url);
			// 轉換成Bean
			beans = parseJSON(data);
			// InserDB
			if (beans.size() != 0) {
				InsertDB(beans);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	private String getJSON(String url) {
		try {
			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept-Language", "zh-TW,zh;q=0.8,en-US;q=0.6,en;q=0.4");
			// conn.setRequestProperty("Content-Type",
			// "text/html;charset=UTF-8");
			// conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			int status = conn.getResponseCode();
			switch (status) {
			case 200:
			case 201:
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				br.close();
				return sb.toString();
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());

		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
				} catch (Exception ex) {
					System.out.println(ex.toString());
				}
			}
		}
		return null;
		// System.out.println(System.currentTimeMillis() + " : getData");

	}

	private Stack<TradeDataBean> parseJSON(String data) {
		// 相依介面，呼叫介面的相同方法來取得
		Stack<TradeDataBean> tradedata;
		fetchdata = new FetchYahooDataIml(data);// 此處可用Spring自動塞obj，若以後換怕別的網站，只要改xml就可以
		tradedata = fetchdata.parseJSONtoBean();
		return tradedata;

	}

	private void InsertDB(Stack<TradeDataBean> datas) {
		dao=new TradeDataDAO();
		dao.insert(datas);
	}
}
