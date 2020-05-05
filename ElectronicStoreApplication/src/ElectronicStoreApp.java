import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;




public class ElectronicStoreApp extends Application{
    ElectronicStore model;
    ElectronicStoreView view;

    public void start(Stage primaryStage){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);

        //get button presses here
        view.getAddToCartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {addToCartButtonPressed();
            }
        });
        view.getRemoveFromCartButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {removeFromCartButtonPressed();

            }
        });
        view.getCompleteSalesButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {completeSalesButtonPressed();

            }
        });
        view.getResetStoreButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {resetStoreButtonPressed();

            }
        });
        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {handleStockListSelection();

            }
        });
        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {handleCartListSelection();

            }
        });

        primaryStage.setTitle(model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view, 800,400));
        primaryStage.show();
        view.update();
    }















    public static void main(String[] args) {
        launch(args);
    }


    public void addToCartButtonPressed(){
        //code for button press here
        int index = view.stockList.getSelectionModel().getSelectedIndex();
        model.addToCart(index);
        view.addToCartButton.setDisable(true);
        view.completeSalesButton.setDisable(false);
        view.update();
    }
    public void removeFromCartButtonPressed(){
        int index = view.cartList.getSelectionModel().getSelectedIndex();
        model.removeFromCart(index);
        if (model.getCartProducts() != 0){
            view.completeSalesButton.setDisable(false);
        }
        else{
            view.completeSalesButton.setDisable(true);
        }
        view.removeFromCartButton.setDisable(true);

        view.update();
    }
    public void completeSalesButtonPressed(){
        view.completeSalesButton.setDisable(true);
        model.completeSales();
        view.update();

    }
    public void resetStoreButtonPressed(){
        model.resetStore();
        view.completeSalesButton.setDisable(true);
        view.addToCartButton.setDisable(true);
        view.removeFromCartButton.setDisable(true);
        view.update();
    }

    public void handleStockListSelection(){
        view.addToCartButton.setDisable(false);
        view.update();
    }
    public void handleCartListSelection(){
        if (model.getCartProducts() != 0){
            view.removeFromCartButton.setDisable(false);
        }
        else{
            view.removeFromCartButton.setDisable(true);
        }

        view.update();
    }





}




