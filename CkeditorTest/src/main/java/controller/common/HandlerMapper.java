package controller.common;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapper {
	private Map<String, Action> mapping;
	
	public HandlerMapper() {
		mapping = new HashMap<String,Action>();
		
		//main 기능
		mapping.put("/main.do", new MainAction());

	}
	
	public Action getAction(String command) {
		return this.mapping.get(command);
	}
	
}
