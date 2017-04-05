package design.iterator;

import java.util.ArrayList;

public class PancakeHouseMenu {
	
	ArrayList menuItems;
	
	public PancakeHouseMenu(){
		menuItems = new ArrayList();
		addItem("Blueberry Pancakes","Pancakes made with fresh blueberries",true,3.49);
	}
	
	public void addItem(String name ,String description,boolean vegetarian,double price){
		MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
		menuItems.add(menuItem);
	}
	
	public ArrayList getMenuItems(){
		return menuItems;
	}

}
