import java.util.*;
class Main{
static Scanner s = new Scanner(System.in);
static String Admin_name="raj";
static String Admin_Password="1234";
static int Admin_attempt = 3;
static int Merchant_attempt = 3;
static int User_Attempt = 3;
static int M_Id = 1;
static int U_Id = 1;
static int Current_Merchant = -1;
static int Current_User = -1;
static Dictionary<String, ArrayList<String>> allproducts = new Hashtable<>();
static ArrayList<Create_Merchant_obj> Merchants = new ArrayList<>();
static ArrayList<Creat_User_Obj> User_List = new ArrayList<>();
static void Clr_scr() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}
public static void Adminlogin(){
    while(Admin_attempt>=0){
        System.out.print("Enter the Admin id : ");
        String id=s.next();
        s.nextLine();
        System.out.print("Enter the password : ");
        String pass=s.next();
        if(id.equals(Admin_name) && (pass.equals(Admin_Password))){
            Admin();
        }
        else{
            Admin_attempt-=1;
            System.out.print("You have entered wrong Id or password");
        }
    }
}
public static void Admin(){
    while(true){
        System.out.print("Welcome to Admin panel \n ******************* \n 1.View all products \n 2.Add new Merchent \n 3.Merchant's Approval \n 4.List of Merchents \n 5.Delete Merchant \n 6.Add new Product \n 7.Back \n *******************");
        int r=s.nextInt();
        if(r==1){
            Clr_scr();
            System.out.println(allproducts);  
        }
        else if(r==2){
            Addmerchant();
        }
        else if(r==3){
            System.out.println("Enter Merchant id:");
            String MerId =s.next();
            s.nextLine();
            for (Create_Merchant_obj merch : Merchants) {
                if (MerId.equals(Integer.toString(merch.M_Id))) {
                    merch.M_Verified = "Yes";
                    System.out.println("==> Merchant Name : " + merch.M_Name + " Merchant Id : " + merch.M_Id
                            + " is Verified : " + merch.M_Verified);
                } else {
                    System.out.println("User Id is not founded");
                }
            }
        }
        else if (r == 4) {
            ListOfMerchants();
        } else if (r== 5) {
            System.out.print("Enter the Id of the Merchant : ");
            String id = s.next();
            s.nextLine();
            int temp = -1;
            int i = 0;
            for (Create_Merchant_obj merch : Merchants) {
                if ((Integer.toString(merch.M_Id)).equals(id)) {
                    temp = i;
                }
                i += 1;
            }
            if (temp != -1) {
                Merchants.remove(temp);
            } else {
                System.out.println("Id is not match with any other Id's :-)");
            }
        } else if (r == 6) {
            System.out.print("Enter the Product Name : ");
            String P_Name = s.next();
            s.nextLine();
            List<String> arr = new ArrayList<>();
            arr.add("0");
            arr.add("0");
            Enumeration enu = allproducts.keys();
            int k = -1;
            while (enu.hasMoreElements()) {
                if (P_Name.equals(enu.nextElement())) {
                    k = 1;
                }
            }
            if (k == -1) {
                allproducts.put(P_Name, (ArrayList<String>) arr);
            } else {
                System.out.println("Product is already found in the List !");
            }
        } else if (r == 7) {
            main(null);
        } else {
            System.out.println("Enter the valid Chooise !");
        }
    }
}
    public static void ListOfMerchants() {
        System.out.println("Merchants Names :");
        for (Create_Merchant_obj merch : Merchants) {
            System.out.println(" Merchant Name : " + merch.M_Name + " Merchant Id : " + merch.M_Id
                    + " is Verified : " + merch.M_Verified);
        }
    }
    public static void Addmerchant() {
        System.out.print("Enter Merchent Name : ");
        String name = s.next();
        s.nextLine();
        System.out.print("Enter Merchent Password : ");
        String password = s.next();
        s.nextLine();
        Merchants.add(new Create_Merchant_obj(name, password, "Yes", M_Id));
        System.out.println("Your User Id => " + M_Id);
        M_Id += 1;
    }
    static void Merlogin(){
        while(Merchant_attempt>=0 || true){
            System.out.print("Welcome to Merchant Login \n******************* \nEnter your Id : ");
            String ID = s.next();
            s.nextLine();
            System.out.print("\nEnter your Password : ");
            String Password = s.next();
            s.nextLine();
            int i = 0;
            for (Create_Merchant_obj merch : Merchants) {
                if (ID.equals(Integer.toString(merch.M_Id)) && Password.equals(merch.M_Password)) {
                    if (merch.M_Verified.equals("Yes")) {
                        Merchant_attempt = 3;
                        Current_Merchant = i;
                        Merchant();
                    } else {
                        System.out.println("User is not verified !");
                        Merch();
                    }
                }
                i += 1;
            }
            Merchant_attempt -= 1;
            Merch();          
        }
    }
    static void Merch() {
        System.out.println("Welcome to Merchant \n********************\n1.For Login\n2.For Regester\n3.Back\n********************");
        int Ch = s.nextInt();
        if (Ch == 1) {
            Merlogin();
        } else if (Ch == 2) {
            Mer_Reg(Ch);
        } else if (Ch == 3) {
            main(null);
        } else {
            System.out.println("Enter the valid Chooise !");
        }
    }
    static void Mer_Reg(int Ch) {
        System.out.println("\nMerchent Register \n*******************\nEnter Your Name : ");
        String name = s.next();
        s.nextLine();
        System.out.println("Enter your Password : ");
        String password = s.next();
        s.nextLine();
        Merchants.add(new Create_Merchant_obj(name, password, "No", M_Id));
        System.out.println("Your User Id => " + M_Id);
        M_Id += 1;
        Merch();
    }
    static void Merchant() {
        while (true) {
            System.out.println(
                    "Welcome to Merchant Panal\n********************\n1 => Add Product\n2 => Update Product\n3 => Compare Product\n4 => Remove Product\n5 => List the Products\n6 => Back");
            int Ch = s.nextInt();
            if (Ch == 1) {
                Add_Product(Current_Merchant);
            } else if (Ch == 2) {
                M_Update_Product();
            } else if (Ch == 3) {
                M_Comp_Product();
            } else if (Ch == 4) {
                M_Remove_Product();
            } else if (Ch == 5) {
                M_Show_List();
            } else if (Ch == 6) {
                main(null);
            } else {
                System.out.println("Enter the valid Input !");
            }
        }
    }
    static void M_Comp_Product() {
        System.out.println("Enter the Product Name : ");
        String product = s.next();
        s.nextLine();
        System.out.println("Product Name : " + product);
        int k = 0;
        for (int i = 0; i < Merchants.size(); i++) {
            Enumeration enu = Merchants.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)) {
                    System.out.println("Merchant Name : " + Merchants.get(i).M_Name + "  Product Price : "
                            + Merchants.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        }
    }
    static void M_Remove_Product() {
        System.out.print("Enter the product name : ");
        String product = s.next();
        s.nextLine();
        try {
            List<String> s = Merchants.get(Current_Merchant).M_Products.remove(product);
            System.out.println(s + "   " + Merchants.get(Current_Merchant).M_Products);
            List<String> arr = new ArrayList<>();
            int count1 = Integer.parseInt(allproducts.get(product).get(0)) - Integer.parseInt(s.get(0));
            String price = allproducts.get(product).get(1);
            arr.add(Integer.toString(count1));
            arr.add(price);
            allproducts.put(product, (ArrayList<String>) arr);
            System.out.println("Product removed Successfully !");
        } catch (Exception e) {
            System.out.println("Product is Not Found in your Product List !");
        }
    }
    static void M_Update_Product() {
        try {
            System.out.print("Enter the product name : ");
            String product = s.next();
            s.nextLine();
            if (!Merchants.get(Current_Merchant).M_Products.get(product).get(0).equals("null")) {

                System.out.println("Enter the product Count : ");
                int count = s.nextInt();
                System.out.println("Enter the Updated Price : ");
                int price = s.nextInt();
                List<String> arr1 = new ArrayList<>();
                // count += Integer.parseInt(g);
                String h = Merchants.get(Current_Merchant).M_Products.get(product).get(0);
                arr1.add(Integer.toString(count));
                arr1.add(Integer.toString(price));
                Merchants.get(Current_Merchant).M_Products.put(product, (ArrayList<String>) arr1);
                List<String> arr = new ArrayList<>();
                int count1 = (Integer.parseInt(allproducts.get(product).get(0))) - (Integer.parseInt(h) - count);
                arr.add(Integer.toString(count1));
                arr.add(Integer.toString(price));
                allproducts.put(product, (ArrayList<String>) arr);
            }
        } catch (Exception e) {
            System.out.println("The product is not found in the List !");
        }
    }
    static void Add_Product(int id) {
        System.out.println("Enter the Product Name : ");
        String product = s.next();
        s.nextLine();
        Enumeration enu = allproducts.keys();
        int k = -1;
        while (enu.hasMoreElements()) {
            if (product.equals(enu.nextElement())) {
                k = 1;
            }
        }
        if (k == -1) {
            System.out.println("Product is not found in the Admin's List !");
        } else {
            System.out.println("Enter the count of the product : ");
            int count = s.nextInt();
            System.out.println("Enter the price for a Item : ");
            int price = s.nextInt();
            int count1 = count + Integer.parseInt(allproducts.get(product).get(0));
            List<String> arr = new ArrayList<>();
            arr.add(Integer.toString(count1));
            arr.add(Integer.toString(price));
            allproducts.put(product, (ArrayList<String>) arr);
            List<String> arr1 = new ArrayList<>();
            String g = "0";
            try {
                g = Merchants.get(Current_Merchant).M_Products.get(product).get(0);
            } catch (Exception e) {
                System.out.println("");
            }
            count += Integer.parseInt(g);
            arr1.add(Integer.toString(count));
            arr1.add(Integer.toString(price));
            Merchants.get(Current_Merchant).M_Products.put(product, (ArrayList<String>) arr1);
        }
    }
    static void M_Show_List() {
        System.out.println(Merchants.get(Current_Merchant).M_Products);
    }
    static void User() {
        System.out.println("Welcome to User Panel \n********************\n1 => Login\n2 => Register\n3 => Go Back");
        int Ch = s.nextInt();
        User_Attempt = 3;
        if (Ch == 1) {
            U_Login();
        } else if (Ch == 2) {
            U_Register();
        } else if (Ch == 3) {
            main(null);
        } else {
            System.out.println("Enter the valid Chooise !");
        }
    }
    static void U_Login() {
        while (User_Attempt >= 0) {
            System.out.print("Login Page\nEnter the User User Id : ");
            String id = s.next();
            s.nextLine();
            System.out.print("Enter the Password : ");
            String password = s.next();
            s.nextLine();
            for (int i = 0; i < User_List.size(); i++) {
                if (Integer.toString(User_List.get(i).U_Id).equals(id)
                        && User_List.get(i).U_Password.equals(password)) {
                    Current_User = i;
                    User_panel();
                    break;
                }
            }
            User_Attempt -= 1;
            User();
        }
    }
    static void User_panel() {
        System.out.println("Hello " + User_List.get(Current_User).U_Name + " Welcome to user Panel : ");
        while (true) {
            System.out.println(
                    "1 => List of Products \n2 => Show Cart \n3 => Purchase History \n4 => Wallet \n5 => Exit ");
            int Ch = s.nextInt();
            if (Ch == 1) {
                List_User_Products();
            } else if (Ch == 2) {
                User_Cart();
            } else if (Ch == 3) {
                System.out.println("Purchase History !");
                for (int k = User_List.get(Current_User).U_Buy.size() - 1; k >= 0; k--) {
                    System.out.println(User_List.get(Current_User).U_Buy.get(k));
                }
            } else if (Ch == 4) {
                Wallet();
            } else if (Ch == 5) {
                main(null);
            } else {
                System.out.println("Please Enter the valid Chooise !");
            }
        }
    }
    static void List_User_Products() {
        while (true) {
            Enumeration enu = allproducts.keys();
            while (enu.hasMoreElements()) {
                String pro = (String) enu.nextElement();
                System.out.println("==> Product Name : " + pro + " Product Count : "
                        + allproducts.get(pro).get(0));
            }
            System.out.println("1 => Add to Cart \n2 => Back");
            int Ch = s.nextInt();
            if (Ch == 1) {
                Buy_User();
            } else if (Ch == 2) {
                User_panel();
            } else {
                System.out.println("Please Enter the valid Chooise !");
            }
        }
    }
    static void Buy_User() {
        System.out.print("Enter the Product Name : ");
        String Product = s.next();
        s.nextLine();;
        int k = -1;
        for (int i = 0; i < allproducts.size(); i++) {
            if (Integer.parseInt(allproducts.get(Product).get(0)) > 0) {
                k = i;
            }
        }
        if (k == -1) {
            System.out.println("Product Out Of Stock !");
        } else {
            Show_Product_List(Product);
        }
    }
    static void Show_Product_List(String product) {
        int k = 0;
        for (int i = 0; i < Merchants.size(); i++) {
            Enumeration enu = Merchants.get(i).M_Products.keys();
            while (enu.hasMoreElements()) {
                if (enu.nextElement().equals(product)
                        && Integer.parseInt(Merchants.get(i).M_Products.get(product).get(0)) > 0) {
                    System.out.println("==> Merchant Name : " + Merchants.get(i).M_Name + " -- Merchant Id : "
                            + Merchants.get(i).M_Id + "  --Product Count : "
                            + Merchants.get(i).M_Products.get(product).get(0) + "  --Product Price : "
                            + Merchants.get(i).M_Products.get(product).get(1));
                    k += 1;
                }
            }
        }
        if (k <= 0) {
            System.out.println("Product is Not Found !");
        } else {

            System.out.print("Enter the Merchant Id :");
            String M_Id = s.next();
            s.nextLine();
            int not = 0;
            for (int i = 0; i < Merchants.size(); i++) {
                if (Integer.toString(Merchants.get(i).M_Id).equals(M_Id)) {
                    System.out.println(M_Id + " -- " + product);
                    System.out.print("Enter the Product Count : ");
                    int count = s.nextInt();
                    if (Integer.parseInt(Merchants.get(i).M_Products.get(product).get(0)) >= count) {
                        List<String> arr = new ArrayList<>();
                        arr.add(Integer
                                .toString(Integer.parseInt(Merchants.get(i).M_Products.get(product).get(0)) - count));
                        arr.add(Merchants.get(i).M_Products.get(product).get(1));
                        Merchants.get(i).M_Products.put(product, (ArrayList<String>) arr);
                        List<String> arr1 = new ArrayList<>();
                        arr1.add(Integer.toString(Integer.parseInt(allproducts.get(product).get(0)) - count));
                        arr1.add(allproducts.get(product).get(1));
                        allproducts.put(product, (ArrayList<String>) arr1);
                        int cou = 0;
                        try {
                            cou = Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0));
                        } catch (Exception e) {
                            cou = 0;
                        }
                        List<String> arr2 = new ArrayList<>();
                        arr2.add(Integer.toString(count + cou));
                        arr2.add(Merchants.get(i).M_Products.get(product).get(1));
                        product = Merchants.get(i).M_Id+"-"+product;
                        User_List.get(Current_User).U_Cart.put(product, (ArrayList<String>) arr2);
                        System.out.println("--------Added to Cart SuccessFully--------");
                        not = 1;
                        break;
                    } else {
                        System.out.println("Your count is more then the available Product !");
                    }
                } else {
                    not = 1;
                }
            }
            if (not == 0) {
                System.out.println("User Not Found !");
            }
        }
    }
    static void User_Cart() {
        while (true) {
            System.out.println(User_List.get(Current_User).U_Cart);
            System.out.println("1 => Buy \n2 => Back");
            int ch = s.nextInt();
            if (ch == 1) {
                System.out.println("Enter the Product Name :");
                String product = s.next();
                s.nextLine();
                try {
                    System.out.println("Enter the Product Count : ");
                    int count = s.nextInt();
                    if (count <= Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0))) {
                        if (Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0)) > 0) {
                            if (count
                                    * Integer.parseInt(
                                            User_List.get(Current_User).U_Cart.get(product).get(1)) <= User_List
                                                    .get(Current_User).U_Money) {
                                List<String> arr = new ArrayList<>();
                                arr.add(Integer.toString(
                                        Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(0))
                                                - count));
                                arr.add(User_List.get(Current_User).U_Cart.get(product).get(1));
                                User_List.get(Current_User).U_Cart.put(product, (ArrayList<String>) arr);
                                User_List.get(Current_User).U_Buy
                                        .add(java.time.LocalDateTime.now() + " ---Buy--- " + product + " ---Price--- "
                                                + count
                                                        * Integer.parseInt(User_List.get(Current_User).U_Cart
                                                                .get(product).get(1)));
                                User_List.get(Current_User).U_Money -= (count
                                        * Integer.parseInt(User_List.get(Current_User).U_Cart.get(product).get(1)));
                                System.out.println("------Success------");
                            } else {
                                System.out.println("Insuficient Amount In your Wallet !");
                            }
                        } else {
                            System.out.println("Product is Empty! :-)");
                        }
                    }else{
                        System.out.println("Count is more than the Available Count in the Cart !");
                    }
                } catch (Exception e) {
                    System.out.println("Product Not Found !");
                }
            } else if (ch == 2) {
                User_panel();
            } else {
                System.out.println("Please Enter the valid Chooise !");
            }
        }
    }
    static void Wallet() {
        while (true) {
            System.out.println("1 => Check Balance \n2 => Deposite \n3 => Statement \n4 => Back");
            int ch = s.nextInt();
            if (ch == 1) {
                System.out.println("User Name : " + User_List.get(Current_User).U_Name + "\nAvailable Balance : "
                        + User_List.get(Current_User).U_Money);
            } 
            else if (ch == 2) {
                System.out.println("Enter the Amount : ");
                int amount = s.nextInt();
                User_List.get(Current_User).Statement.add(
                        java.time.LocalDateTime.now() + "---Deposit---"
                                + (amount + User_List.get(Current_User).U_Money));
                User_List.get(Current_User).U_Money += amount;
                System.out.println("Amount Added Successfully !");
            } 
            else if (ch == 3) {
                System.out.println("Mini Statement !");
                for (int k = User_List.get(Current_User).Statement.size() - 1; k >= 0; k--) {
                    System.out.println(User_List.get(Current_User).Statement.get(k));
                }
            } 
            else if (ch == 4) {
                User_panel();
            } 
            else {
                System.out.println("Enter the valid chooise !");
            }
        }
    }
    static void U_Register() {
        System.out.print("Register Page\nEnter the User User Name : ");
        String name = s.next();
        s.nextLine();
        System.out.print("Enter the Password : ");
        String password = s.next();
        s.nextLine();
        User_List.add(new Creat_User_Obj(name, password, U_Id));
        System.out.println("User Registered successfuly !\n\nYour U_Id : " + U_Id + "\n");
        U_Id += 1;
        U_Login();
    }
public static void main(String[] args){
        System.out.println("Welcome to Amazon ");
        System.out.println("********************");
        while(true){
            System.out.println(" 1.Admin \n 2.Merchant \n 3.User \n 4.Exit");
            System.out.println("********************");
            int u=s.nextInt();
            if(u==1){
               Clr_scr();
              Adminlogin();
            }
            else if(u==2){
              Clr_scr();
              Merch();
            }
            else if(u==3){
              Clr_scr();
              User();
            }
            else if(u==4){
               Clr_scr();
               System.out.println("Thank you for your visit !");
               System.out.println("***************************");
               System.exit(0);
            }
            else{
                System.out.println("Please Enter the valid input !");
            }
        }
    }
}
    class Create_Merchant_obj {
        public String M_Name, M_Password, M_Verified;
        public int M_Id;
        public Dictionary<String, ArrayList<String>> M_Products = new Hashtable<>();
        Create_Merchant_obj(String name, String password, String verified, int id) {
            this.M_Name = name;
            this.M_Password = password;
            this.M_Verified = verified;
            this.M_Id = id;
        }
    }
    class Creat_User_Obj {
        public String U_Name, U_Password;
        public int U_Id, U_Money;
        public Dictionary<String, ArrayList<String>> U_Cart = new Hashtable<>();
        public List<String> Statement = new ArrayList<>();
        public List<String> U_Buy = new ArrayList<>();
        Creat_User_Obj(String name, String password, int id) {
            this.U_Name = name;
            this.U_Password = password;
            this.U_Id = id;
            this.U_Money = 0;
        }
    }
