import java.util.*;

public class CompositeDesignPattern {
    //allows you to compose objects into tree structures to represent part-whole hierarchies.
    //Composite lets clients treat individual objects and compositions of objects uniformly

    public void run() {
                MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MENU", "Breakfast");
                MenuComponent dinerMenu = new Menu("DINER MENU", "Lunch");
                MenuComponent cafeMenu = new Menu("CAFE MENU", "Dinner");
                MenuComponent dessertMenu = new Menu("DESSERT MENU", "Dessert of course!");

                MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");

                allMenus.add(pancakeHouseMenu);
                allMenus.add(dinerMenu);
                allMenus.add(cafeMenu);

                pancakeHouseMenu.add(new MenuItem(
                        "K&B's Pancake Breakfast",
                        "Pancakes with scrambled eggs and toast",
                        true,
                        2.99));
                pancakeHouseMenu.add(new MenuItem(
                        "Regular Pancake Breakfast",
                        "Pancakes with fried eggs, sausage",
                        false,
                        2.99));
                pancakeHouseMenu.add(new MenuItem(
                        "Blueberry Pancakes",
                        "Pancakes made with fresh blueberries, and blueberry syrup",
                        true,
                        3.49));

                dinerMenu.add(new MenuItem(
                        "Soup of the day",
                        "A bowl of the soup of the day, with a side of potato salad",
                        false,
                        3.29));
                dinerMenu.add(new MenuItem(
                        "Hot Dog",
                        "A hot dog, with saurkraut, relish, onions, topped with cheese",
                        false,
                        3.05));
                dinerMenu.add(new MenuItem(
                        "Steamed Veggies and Brown Rice",
                        "Steamed vegetables over brown rice",
                        true,
                        3.99));

                cafeMenu.add(new MenuItem(
                        "Veggie Burger and Air Fries",
                        "Veggie burger on a whole wheat bun, lettuce, tomato, and fries",
                        true,
                        3.99));
                cafeMenu.add(new MenuItem(
                        "Soup of the day",
                        "A cup of the soup of the day, with a side salad",
                        false,
                        3.69));
                cafeMenu.add(new MenuItem(
                        "Burrito",
                        "A large burrito, with whole pinto beans, salsa, guacamole",
                        true,
                        4.29));

                dinerMenu.add(dessertMenu);

                dessertMenu.add(new MenuItem(
                        "Apple Pie",
                        "Apple pie with a flakey crust, topped with vanilla icecream",
                        true,
                        1.59));
                dessertMenu.add(new MenuItem(
                        "Cheesecake",
                        "Creamy New York cheesecake, with a chocolate graham crust",
                        true,
                        1.99));
                dessertMenu.add(new MenuItem(
                        "Sorbet",
                        "A scoop of raspberry and a scoop of lime",
                        true,
                        1.89));

                Waitress waitress = new Waitress(allMenus);

                waitress.printMenu();
    }

    public abstract class MenuComponent {

        public void add(MenuComponent menuComponent) {
            throw new UnsupportedOperationException();
        }

        public void remove(MenuComponent menuComponent) {
            throw new UnsupportedOperationException();
        }

        public MenuComponent getChild(int i) {
            throw new UnsupportedOperationException();
        }

        public String getName() {
            throw new UnsupportedOperationException();
        }

        public String getDescription() {
            throw new UnsupportedOperationException();
        }

        public boolean isVegetarian() {
            throw new UnsupportedOperationException();
        }

        public double getPrice() {
            throw new UnsupportedOperationException();
        }

        public void print() {
            throw new UnsupportedOperationException();
        }
    }

    public class MenuItem extends MenuComponent{
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

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public double getPrice() {
            return price;
        }

        public void print() {
            System.out.println(getName());
            System.out.println(getDescription());
            System.out.println(getPrice());
            System.out.println(isVegetarian());
        }
    }

    public class Menu extends MenuComponent {
        List<MenuComponent> menuComponents = new ArrayList<>();
        String name;
        String description;

        public Menu(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public void add (MenuComponent menuComponent) {
            menuComponents.add(menuComponent);
        }

        public void remove (MenuComponent menuComponent) {
            menuComponents.remove(menuComponent);
        }

        public MenuComponent getChild(int i) {
            return menuComponents.get(i);
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

        public void print() {
            System.out.println(getName());
            System.out.println(getDescription());

            for (MenuComponent menuComponent : menuComponents){
                menuComponent.print();
            }
        }
    }

    public class Waitress {
        MenuComponent allMenus;

        public Waitress(MenuComponent allMenus) {
            this.allMenus = allMenus;
        }

        public void printMenu() {
            allMenus.print();
        }
    }
}
