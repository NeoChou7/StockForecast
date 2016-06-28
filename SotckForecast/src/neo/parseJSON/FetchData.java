package neo.parseJSON;

import java.util.Stack;

import neo.bean.TradeDataBean;

public interface FetchData {
	public Stack<TradeDataBean> parseJSONtoBean();
}
