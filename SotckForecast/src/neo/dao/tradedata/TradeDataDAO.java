package neo.dao.tradedata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Stack;

import neo.bean.TradeDataBean;

public class TradeDataDAO implements ITradeDataDAO {
	private String jdbcURL = "jdbc:mysql://127.0.0.1/forecastdb";
	private String UID = "root";
	private String PWD = "dontgiveup";
	private Connection conn = null;
	private String classname = "com.mysql.jdbc.Driver";

	public TradeDataDAO() {

	}

	@Override
	public void insert(TradeDataBean data) {
		// TODO Auto-generated method stub
		try {
			// 載入 JDBC 驅動程式
			Class.forName(classname).newInstance();
			String sql="INSERT INTO forecastdb.tradedata(Year,Month,Day,Time,Price,Volumn,Market,id) Select ?,?,?,?,?,?,?,? FROM DUAL WHERE NOT EXISTS(SELECT id FROM forecastdb.tradedata WHERE Year=? and Month=? and Day=? and Time=? and id=?)";
			// connect to Database
			conn = DriverManager.getConnection(jdbcURL, UID, PWD);
			
			PreparedStatement  statement =conn.prepareStatement(sql);
			statement.setString(1, data.getTime().substring(0, 4));
			statement.setString(2, data.getTime().substring(4, 6));
			statement.setString(3, data.getTime().substring(6, 8));
			statement.setString(4, data.getTime().substring(8, 12)+"00");
			
			statement.setString(5, data.getPrice());
			statement.setString(6, data.getVomune());
			statement.setString(7, data.getMarket());
			statement.setString(8, data.getId());
			
			statement.setString(9, data.getTime().substring(0, 4));
			statement.setString(10, data.getTime().substring(4, 6));
			statement.setString(11, data.getTime().substring(6, 8));
			statement.setString(12, data.getTime().substring(8, 12)+"00");
			statement.setString(13, data.getId());
			
			statement.execute();
		} catch (Exception sqle) {
			System.out.println(sqle);
		} finally {
			if (conn != null) {
				// Display current content
				try {
					conn.close();
				} catch (Exception ex) {

				}
			}
		}
	}

	public void insert(Stack<TradeDataBean> datas) {
		try {
			while (datas.size() > 0)
				insert(datas.pop());

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
