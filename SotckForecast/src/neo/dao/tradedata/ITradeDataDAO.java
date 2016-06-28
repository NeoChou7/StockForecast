package neo.dao.tradedata;

import java.util.Stack;

import neo.bean.TradeDataBean;

public interface ITradeDataDAO {
	//private DataSource dataSource;
	public void insert(TradeDataBean data);
	public void insert(Stack<TradeDataBean> datas);
	
}
