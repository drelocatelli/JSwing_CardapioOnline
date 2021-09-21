package view;

public class ItemController {
	
	public String[] titles() {
		
		String [] titles = {"ID", "Lanche", "Pre\u00E7o (R$)"};
		
		return titles;
	}
	
	public Object[][] items() {
		
		Object [][] items = {
				{"1", "X-Bacon", new Double(20.0), new Integer(0)},
				{"2", "X-Salada", new Double(15.0), new Integer(0)},
				{"3", "X-Picanha", new Double(22.0), new Integer(0)},
				{"4", "X-Tudo", new Double(30.0), new Integer(0)},
		};
		
		return items;
		
	}


}
