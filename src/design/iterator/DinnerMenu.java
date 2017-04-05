package design.iterator;

public class DinnerMenu {
	
	static final int MAX_ITEMS = 6 ;
	int numberOfItems = 0 ;
	MenuItem[] menuItems;
	
	public DinnerMenu(){
		menuItems = new MenuItem[MAX_ITEMS];
        addItem("BLT",
                "Bacon with lettuce & tomato on whole wheat",
                false,
                2.99);
	}
	
	public void addItem(String name,String description,boolean vagetarian,double price){
		MenuItem menuItem = new MenuItem(name,description,vagetarian,price);
		if(numberOfItems >= MAX_ITEMS){
			System.out.println("sorry,menu is full");
		}else{
			menuItems[numberOfItems] = menuItem ;
			numberOfItems = numberOfItems + 1 ;
		}
	}
	
	public MenuItem[] getMenuItems(){
		return menuItems;
	}

}
