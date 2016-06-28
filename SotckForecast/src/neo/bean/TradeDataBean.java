package neo.bean;

public class TradeDataBean {
	private String price;
	private String volume;
	
	private String time;
	private String market;
	private String id;
	
	public void setPrice(String price){
		this.price=price;
	}
	public void setVolume(String volume){
		this.volume=volume;
	}
	
	public void setTime(String time){
		this.time=time;
	}
	public void setMarket(String market){
		this.market=market;
	}
	public void setId(String id){
		this.id=id;
	}
	public String getPrice(){
		return this.price;
	}
	public String getVomune(){
		return this.volume;
	}
	
	public String getTime(){
		return this.time;
	}
	public String getMarket(){
		return this.market;
	}
	public String getId(){
		return this.id;
	}
}
