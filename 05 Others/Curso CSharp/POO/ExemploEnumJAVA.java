import java.time.LocalDate;

public class Main
{
	public static void main(String[] args) {
	    
		Order os = new Order(
    	    1,
	        LocalDate.now(),
	        OrderStatus.Processando
		);
		
		String status = OrderStatus.Entregue.toString();
		String status2 = "Enviado";
		OrderStatus oStatus = OrderStatus.valueOf(status2);
		
		System.out.println(os.toString());
		System.out.println("enum em String: " + status);
		System.out.println("tipo enum: " + oStatus);
	}
	
	public static class Order {
	    
	    public Integer id;
	    public LocalDate  moment;
	    public OrderStatus status;
	    
	    public Order(Integer id, LocalDate moment, OrderStatus status ) {
	        this.id = id;
	        this.moment = moment;
	        this.status = status;
	    } 
	    
	    @Override
	    public String toString(){
	        return "Id: " + id + " " +
	               "Status: " + status;
	    }
	}
	
	enum OrderStatus {
	    
	    PendentePagamento(0),
	    Processando(1),
	    Enviado(2),
	    Entregue(3);
	    
        private final int statusCode;

        OrderStatus(int statusCode) {
            this.statusCode = statusCode;
        }

       public int getStatusCode() {
            return statusCode;
       }
	};
}
