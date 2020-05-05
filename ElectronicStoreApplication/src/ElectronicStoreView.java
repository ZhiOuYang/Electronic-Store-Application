import javafx.scene.layout.Pane;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;


public class ElectronicStoreView extends Pane {
    ElectronicStore model;
    ListView<String> stockList;
    ListView<String> cartList;
    ListView<String> mostPopularList;
    Button addToCartButton;
    Button removeFromCartButton;
    Button completeSalesButton;
    Button resetStoreButton;
    TextField numOfSalesAmount;
    TextField revenueAmount;
    TextField dollarPerSale;
    TextField cartText;

    public ElectronicStoreView(ElectronicStore aModel){
        model = aModel;

        //add to cart button
        addToCartButton = new Button();
        addToCartButton.setText("Add to Cart");
        addToCartButton.relocate(430,350);
        addToCartButton.setPrefSize(100,30);
        addToCartButton.setDisable(true);
        getChildren().add(addToCartButton);

        //remove from cart button
        removeFromCartButton = new Button();
        removeFromCartButton.setText("Remove from Cart");
        removeFromCartButton.relocate(570,350);
        removeFromCartButton.setPrefSize(120,30);
        removeFromCartButton.setDisable(true);

        getChildren().add(removeFromCartButton);

        //complete sales button
        completeSalesButton = new Button();
        completeSalesButton.setText("Complete Sales");
        completeSalesButton.relocate(690,350);
        completeSalesButton.setPrefSize(100,30);
        completeSalesButton.setDisable(true);
        getChildren().add(completeSalesButton);

        //reset store button
        resetStoreButton = new Button();
        resetStoreButton.setText("Reset Store");
        resetStoreButton.relocate(50,350);
        resetStoreButton.setPrefSize(100,30);
        getChildren().add(resetStoreButton);

        //stock list
        stockList = new ListView<String>();
        stockList.relocate(370, 30);
        stockList.setPrefSize(200, 300);
        getChildren().add(stockList);

        //stock list label
        Text stockText = new Text("Stock");
        stockText.relocate(460,10);
        getChildren().add(stockText);

        //cart list
        cartList = new ListView<String>();
        cartList.relocate(590, 30);
        cartList.setPrefSize(200, 300);
        getChildren().add(cartList);

        //cart list label
        String cartLabelText = String.format("Cart (%.2f)",model.getCartRevenue());
        cartText = new TextField(cartLabelText);
        cartText.relocate(620,5);
        cartText.setEditable(false);
        getChildren().add(cartText);

        //most popular list
        mostPopularList = new ListView<String>();
        mostPopularList.relocate(50, 150);
        mostPopularList.setPrefSize(200, 180);
        getChildren().add(mostPopularList);

        //most popular list label
        Text mostPopularText = new Text("Most popular items");
        mostPopularText.relocate(100,130);
        getChildren().add(mostPopularText);

        //store summary text
        Text storeSummaryText = new Text("Store Summary");
        storeSummaryText.relocate(80,30);
        getChildren().add(storeSummaryText);

        //Number of sales text
        Text numOfSalesText = new Text("Number of sales: ");
        numOfSalesText.relocate(50,50);
        getChildren().add(numOfSalesText);
        numOfSalesAmount = new TextField();
        numOfSalesAmount.setText(""+model.getNumOfSales());
        numOfSalesAmount.relocate(142,47);
        numOfSalesAmount.setEditable(false);
        getChildren().add(numOfSalesAmount);

        //revenue Text
        Text revenueText = new Text("Revenue: ");
        revenueText.relocate(50,75);
        getChildren().add(revenueText);
        String revenue = String.format("%.2f",model.getRevenue());
        revenueAmount = new TextField("");
        revenueAmount.setText(revenue);
        revenueAmount.relocate(142,72);
        revenueAmount.setEditable(false);
        getChildren().add(revenueAmount);

        //$/sale text
        Text dollarPerSaleText = new Text("Dollar per Sale: ");
        dollarPerSaleText.relocate(50,100);
        dollarPerSale = new TextField("");
        dollarPerSale.setText("N/A");
        getChildren().add(dollarPerSaleText);
        dollarPerSale.relocate(142,95);
        dollarPerSale.setEditable(false);
        getChildren().add(dollarPerSale);

        update();
    }

    public Button getAddToCartButton(){return addToCartButton; }
    public Button getRemoveFromCartButton(){return removeFromCartButton;}
    public Button getCompleteSalesButton(){return completeSalesButton;}
    public Button getResetStoreButton(){return resetStoreButton;}
    public ListView getStockList(){return stockList;}
    public ListView getCartList(){return cartList;}





    public void update() {
        //updates summary
        numOfSalesAmount.setText("" + model.getNumOfSales());

        String revenue = String.format("%.2f", model.getRevenue());
        revenueAmount.setText(revenue);
        String dollar = String.format("%.2f", model.getDollarPerSale());
        dollarPerSale.setText(dollar);
        //update cart revenue
        String cartLabelText = String.format("Cart (%.2f)", model.getCartRevenue());
        cartText.setText(cartLabelText);

        //fills stock lists
        String[] stock = new String[model.getCurProducts()];
        for (int i = 0; i < stock.length; i++) {
            if (model.stock[i].getStockQuantity() > 0) {
                stock[i] = model.stock[i].toString();
            } else {
                stock[i] = "Out of Stock";
            }

        }
        stockList.setItems(FXCollections.observableArrayList(stock));


        //fills cart list
        String[] cart = new String[model.getCartProducts()];
        for (int i = 0; i < cart.length; i++) {
            if (model.cart[i].getCartAmount() > 0) {
                cart[i] = "("+model.cart[i].getCartAmount()+")"+model.cart[i].toString();
            } else {
                cart[i] = "";
            }
        }

        cartList.setItems(FXCollections.observableArrayList(cart));

        //fills most popular
        int[] mostPopular = model.mostPopular();
        String[] mostPopularString = new String[3];

            if (mostPopular[0] != -1){
                mostPopularString[0] = "("+model.stock[mostPopular[0]].getSoldQuantity()+") "+ model.stock[mostPopular[0]].toString();
            }
            else{
                mostPopularString[0] ="("+model.stock[0].getSoldQuantity()+") "+ model.stock[0].toString();
            }
            if (mostPopular[1] != -1){
                mostPopularString[1] = "("+model.stock[mostPopular[1]].getSoldQuantity()+") "+ model.stock[mostPopular[1]].toString();
            }
            else{
                mostPopularString[1] ="("+model.stock[1].getSoldQuantity()+") "+ model.stock[1].toString();
            }
            if (mostPopular[2] != -1){
                mostPopularString[2] = "("+model.stock[mostPopular[2]].getSoldQuantity()+") "+ model.stock[mostPopular[2]].toString();
            }
            else{
                mostPopularString[2] ="("+model.stock[2].getSoldQuantity()+") "+ model.stock[2].toString();
            }
        mostPopularList.setItems(FXCollections.observableArrayList(mostPopularString));
    }


}