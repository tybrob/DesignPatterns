import java.util.*;

public class IteratorDesignPattern {
    //provides a way to access the elements of an aggregate object
    //sequentially without exposing its underlying representation

    public void run() {
        Menu pancakeHouseMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();

        Waitress waitress = new Waitress(pancakeHouseMenu,dinerMenu);

        waitress.printMenu();
    }

    public class MenuItem {
        String name;
        String description;
        boolean vegetarian;
        double price;

        public MenuItem(String name, String description, boolean vegetarian, double price) {
            this.name = name;
            this.description = description;
            this.vegetarian = vegetarian;
            this.price = price;
        }

        @Override
        public String toString() {
            return "MenuItem{" +
                    "name='" + name + '\'' +
                    ", description='" + description + '\'' +
                    ", vegetarian=" + vegetarian +
                    ", price=" + price +
                    '}';
        }
    }

    public interface Menu {
        Iterator<MenuItem> createIterator();
    }

    public class PancakeHouseMenu implements Menu {

        List<MenuItem> menuItems;

        public PancakeHouseMenu() {
            menuItems = new ArrayList<>();
            addItem("Regular Pancake Breakfast",
                    "pancakes with scrambled eggs",
                    false,
                    2.99);
            addItem("Blueberry Pancakes",
                    "pancakes with scrambled eggs",
                    true,
                    3.99);
        }

        public void addItem(String name, String description, boolean vegetarian, double price) {
            MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
            menuItems.add(menuItem);
        }

        @Override
        public Iterator<MenuItem> createIterator() {
            return menuItems.iterator();
        }
    }

    public class DinerMenu implements Menu {

        static final int MAX_ITEMS = 6;
        int numberOfItems = 0;
        MenuItem[] menuItems;

        public DinerMenu() {
            menuItems = new MenuItem[MAX_ITEMS];

            addItem("Hotdog",
                    "A hot dog with various staff",
                    false,
                    9.99);
            addItem("Vegetarian blt",
                    "lettuce and tomato salad",
                    true,
                    6.99);
        }

        public void addItem(String name, String description, boolean vegetarian, double price) {
            if (numberOfItems >= MAX_ITEMS){
                System.out.println("Sorry menu is full, can't add item");
            } else {
                MenuItem menuItem = new MenuItem(name,description,vegetarian,price);
                menuItems[numberOfItems] = menuItem;
                numberOfItems++;
            }
        }

        @Override
        public Iterator<MenuItem> createIterator() {
            return new DinerMenuIterator(menuItems); //arrays don't implement Iterator, so we have to create a new class
        }
    }

    public class Waitress {
        Menu pancakeHouseMenu;
        Menu dinerMenu;

        public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
            this.pancakeHouseMenu = pancakeHouseMenu;
            this.dinerMenu = dinerMenu;
        }

        public void printMenu() {
            Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
            Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
            System.out.println("Breakfast");
            printMenu(pancakeIterator);
            System.out.println("Lunch");
            printMenu(dinerIterator);
        }

        private void printMenu(Iterator<MenuItem> iterator) {
            while (iterator.hasNext()) {
                MenuItem menuItem = iterator.next();
                System.out.println(menuItem);
            }
        }
    }

    public class DinerMenuIterator implements Iterator<MenuItem> {

        MenuItem[] menuItems;
        int position = 0;

        public DinerMenuIterator (MenuItem[] menuItems) {
            this.menuItems = menuItems;
        }

        @Override
        public boolean hasNext() {
            if (position >= menuItems.length || menuItems[position] == null) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public MenuItem next() {
            MenuItem menuItem = menuItems[position];
            position++;
            return menuItem;
        }
    }
}
