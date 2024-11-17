public class ProductNameValidator {
    public boolean validateProductName(String userInput){
        return userInput.matches("^[A-Za-z0-9()][A-Za-z0-9 .()]");
    }
}
