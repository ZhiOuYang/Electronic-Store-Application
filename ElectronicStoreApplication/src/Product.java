//Base class for all products the store will sell
public class Product{
 private double price;
 private int stockQuantity;
 private int soldQuantity;
 private int cartAmount;

 public Product(double initPrice, int initQuantity){
   price = initPrice;
   stockQuantity = initQuantity;
   cartAmount = 0;
 }
 
 public int getStockQuantity(){
   return stockQuantity;
 }
 
 public int getSoldQuantity(){
   return soldQuantity;
 }
 
 public double getPrice() { return price; }

 public int getCartAmount() { return cartAmount; }

 //Returns the total revenue (price * amount) if there are at least amount items in stock
 //Return 0 otherwise (i.e., there is no sale completed)
 public double sellUnits(int amount){
   if(amount > 0 && stockQuantity >= amount){
       //stockQuantity -= amount;
     soldQuantity += amount;
     return price * amount;
   }
   return 0.0;
 }

    public void cartZero(){
     cartAmount = 0;
    }

    public void cartAdd(){
     cartAmount ++;
    }
    public void cartRemove(){
     cartAmount --;
    }

    public void returnToStock () {
        stockQuantity ++;
    }
    public void removeFromStock(){
        stockQuantity --;
    }
    public void setStockQuantity(int newStockQuantity){
     stockQuantity=newStockQuantity;
    }
    public void setSoldQuantity(int newSoldQuantity){
     soldQuantity = newSoldQuantity;
    }
}