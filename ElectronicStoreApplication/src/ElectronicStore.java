//Class representing an electronic store
//Has an array of products that represent the items the store can sell

public class ElectronicStore{
  public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
  private int curProducts;
  String name;
  Product[] stock; //Array to hold all products
  Product[] cart;
  private int cartProducts;
  double revenue;
  double cartRevenue;
  private int numOfSales;
  private double dollarPerSale;
  int[] initialValue;

  public ElectronicStore(String initName){
    revenue = 0.0;
    name = initName;
    stock = new Product[MAX_PRODUCTS];
    curProducts = 0;
    cart = new Product[MAX_PRODUCTS];
    cartProducts = 0;
    cartRevenue = 0.0;
    numOfSales = 0;
    dollarPerSale = 0;
    initialValue = new int[MAX_PRODUCTS];

  }

  public String getName(){
    return name;
  }

  //Adds a product and returns true if there is space in the array
  //Returns false otherwise
  public boolean addProduct(Product newProduct){
    if(curProducts < MAX_PRODUCTS){
     stock[curProducts] = newProduct;
     initialValue[curProducts] = newProduct.getStockQuantity();
     curProducts++;

     return true;
    }
    return false;
  }

  public boolean addToCart(int index){
    //index from store
    if (cartProducts < MAX_PRODUCTS){
      for (int i = 0; i < cartProducts;i++){
        if (stock[index] == cart[i]){
          stock[index].cartAdd();
          stock[index].removeFromStock();
          return true;
        }
      }
      cart[cartProducts] = stock[index];
      cart[cartProducts].cartAdd();
      stock[index].removeFromStock();
      cartProducts++;
      return true;
    }
    return false;
  }

    public void removeFromCart(int index) {
      //index from cart
      cart[index].cartRemove();
      boolean temp = false; // whether or not there are items in the cartproducts
      for (int i = 0; i < curProducts; i++) {
        if (stock[i] == cart[index]) {
          stock[i].returnToStock();
        }
        if (stock[i].getCartAmount() !=0){
          temp = true;
        }

      }
      if (temp == false){
        cartProducts = 0;
      }
  }

  public void calculateCartRevenue() {
    cartRevenue = 0;
    for (int i = 0; i < cartProducts; i++) {
      int amount = cart[i].getCartAmount();
      cartRevenue += ((cart[i].getPrice()) * amount);
    }
  }
  public double getCartRevenue(){
    calculateCartRevenue();
      return cartRevenue;

    }




  public int[] mostPopular(){
    int sold1Index = -1;
    int sold2Index = -1;
    int sold3Index = -1;

    for(int i = 0; i<curProducts;i++){
      int soldAmount = stock[i].getSoldQuantity();
      if (soldAmount == 0){ continue; }
      else{
        if (sold1Index == -1) {
          sold1Index = i;
        }
        else if(soldAmount > stock[sold1Index].getSoldQuantity()){
          sold3Index = sold2Index;
          sold2Index = sold1Index;
          sold1Index = i;
        }
        else{
          if (sold2Index == -1){
            sold2Index = i;
          }
          else if(soldAmount > stock[sold2Index].getSoldQuantity()){
            sold3Index = sold2Index;
            sold2Index = i;
          }
          else{
            if (sold3Index == -1){
              sold3Index = i;
            }
            else if(soldAmount > stock[sold3Index].getSoldQuantity()){
              sold3Index = i;
            }
          }
        }
      }
    }

    int[] a = new int[3];
    a[0] = sold1Index;
    a[1] = sold2Index;
    a[2] = sold3Index;
    return a;
  }

  public void completeSales(){
    numOfSales = 0;

        for (int j = 0; j<curProducts; j++){
          if (stock[j].getSoldQuantity()+stock[j].getCartAmount() <= initialValue[j]){
                  revenue += stock[j].getPrice()*stock[j].getCartAmount();
                  stock[j].setSoldQuantity(stock[j].getSoldQuantity()+stock[j].getCartAmount());
          }
          else{
            stock[j].setStockQuantity(stock[j].getCartAmount());
          }

              stock[j].cartZero();
              numOfSales += stock[j].getSoldQuantity();

            }


    dollarPerSale = revenue/numOfSales;
    cartProducts =0;
  }

  public void resetStore(){
    for (int i=0; i<curProducts;i++){
      stock[i].setStockQuantity(initialValue[i]);
      stock[i].cartZero();
      stock[i].setSoldQuantity(0);
      revenue = 0.0;
      cartProducts = 0;
      cartRevenue = 0.0;
      numOfSales = 0;
      dollarPerSale = 0;
    }


  }


  //Sells 'amount' of the product at 'index' in the stock array
  //Updates the revenue of the store
  //If no sale occurs, the revenue is not modified
  //If the index is invalid, the revenue is not modified
  public void sellProducts(int index, int amount){
    if(index >= 0 && index < curProducts){
      revenue += stock[index].sellUnits(amount);
    }
  }
  
  public double getRevenue(){
    return revenue;
  }
  
  //Prints the stock of the store
  public void printStock(){
    for(int i = 0; i < curProducts; i++){
      System.out.println(i + ". " + stock[i] + " (" + stock[i].getPrice() + " dollars each, " + stock[i].getStockQuantity() + " in stock, " + stock[i].getSoldQuantity() + " sold)");
    }
  }
  public void printCartStock(){
    for(int i = 0; i < cartProducts; i++){
      System.out.println(i + ". " + cart[i] + " (" + cart[i].getPrice() + " dollars each, " + cart[i].getStockQuantity() + " in stock, " + cart[i].getSoldQuantity() + " sold) cart amount: "+cart[i].getCartAmount());
    }
  }
  public int getCurProducts() {
    return curProducts;
  }
  public int getCartProducts(){
    return cartProducts;
  }
  public int getNumOfSales(){
    return numOfSales;
  }
  public double getDollarPerSale(){
    return dollarPerSale;
  }

  public static ElectronicStore createStore(){
    ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
    Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
    Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
    Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
    Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
    Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", 15.5, false);
    Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", 23, true);
    ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", 8, false);
    ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", 12, true);
    store1.addProduct(d1);
    store1.addProduct(d2);
    store1.addProduct(l1);
    store1.addProduct(l2);
    store1.addProduct(f1);
    store1.addProduct(f2);
    store1.addProduct(t1);
    store1.addProduct(t2);
    return store1;
  }

} 