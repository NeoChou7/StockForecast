package neo.parseJSON;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import neo.bean.TradeDataBean;

public class FetchYahooDataIml implements FetchData {
	private String JSONData;
	private String today;
	private JSONObject jobj;
	private String market;
	private String id;

	public FetchYahooDataIml(String JSONData) {
		if (!JSONData.startsWith("{")) {
			this.JSONData = JSONData.substring(JSONData.indexOf("{"));
		} else {
			this.JSONData = JSONData;
		}
		
		this.jobj = new JSONObject(this.JSONData);
		this.today = String.valueOf(jobj.getJSONObject("mem").getInt("144"));
		this.market = jobj.getJSONObject("mem").getString("name");
		this.id = jobj.getJSONObject("mem").getString("id");
	}

	public void setToday(String today) {
		this.today = today;
	}

	public String getToday() {
		return this.today;
	}

	public Stack<TradeDataBean> parseJSONtoBean() {
		// ¨úDATA
		Stack<TradeDataBean> tradestack = new Stack<TradeDataBean>();
		TradeDataBean bean;

		// String pageName =
		// obj.getJSONObject("pageInfo").getString("pageName");

		JSONArray arr = jobj.getJSONArray("tick");
		for (int i = 0; i < arr.length(); i++) {
			bean = new TradeDataBean();
			// t
			bean.setTime(String.valueOf(arr.getJSONObject(i).getLong("t")));
			// p
			bean.setPrice(String.valueOf(arr.getJSONObject(i).getDouble("p")));
			// v
			bean.setVolume(String.valueOf(arr.getJSONObject(i).getLong("v")));

			bean.setMarket(market);
			bean.setId(id);
			tradestack.push(bean);
		}
		return tradestack;
	}

}
