package factory;

public class CategoryFactory {

    public static String createCategory(String type) {

        if (type.equalsIgnoreCase("grocery")) {
            return "Grocery";
        }

        if (type.equalsIgnoreCase("dairy")) {
            return "Dairy";
        }

        if (type.equalsIgnoreCase("snack")) {
            return "Snack";
        }

        return "Unknown Category";
    }
}