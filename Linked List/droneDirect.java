/************************************************************
*   Name: Hussain Mehdi                                     *
*   Date: 02/04/2021                                        *
*   Purpose: Program to read inventory, stock               *
*************************************************************/
import java.util.*;
import java.io.*;
import java.io.FileWriter;
public class droneDirect
{
    public static void main(String[] args)
    {       
    	orderLinkedList o2 = new orderLinkedList();		// Object creation 
		if(args.length == 0)				// if no arguments given on line
		{
		System.out.println("Please enter commands like this 'java droneDirect –r(-i) <location_file> <invenroy_file> <order_file>");
		}
		
    if(args.length == 1)			// if one argument given -i
    { 
    	String mode = args[0];
    	if(mode.equals("-i"))
    	{
        	interactiveDisplay(o2);
    	}
    }
    else if(args.length > 1)			// running with -r multiple arguments 
    {
    	String mode = args[0];
    	String locationFile = args[1];
    	String inventoryFile = args[2];
    	String orderFile = args[3];
    	
   	reportMode(mode, locationFile, inventoryFile, orderFile, o2);
   	
    }
    }   
/************************************************************
*   Name: Hussain Mehdi                                     *
*   Date: 2/04/2021                                        	*
*   Purpose: For Report Mode Display                        *
*************************************************************/
    public static void reportMode(String mode, String locationFile, String inventoryFile, String orderFile, orderLinkedList o2)
    {
    	int secondChoice = 1;
    	int thirdChoice =0;
    	int choice =2;
    	System.out.println("*********** Stores Location Data *************");
    	readStores(mode, locationFile, secondChoice);					// Display Store
    	System.out.println("*********** Inventory File Data *************");
    	System.out.println("");			
    	readInventory(inventoryFile, thirdChoice, orderFile, secondChoice, o2);	// Display Inventory
    	System.out.println("*********** Order File Data *************");
    	readFileOrder(orderFile,choice, orderFile, secondChoice, o2);		// Display order
    	System.out.println("*********** Location Overview *************");
    	readStores(mode, locationFile, secondChoice);					// Write store 
    }
/*************************************************************
*   Name: Hussain Mehdi                                     	*
*   Date: 02/04/2021                                        	*
*   Purpose: Displays Menu               			*
*************************************************************/
    public static void menuDisplay(orderLinkedList o2)
    {
        String userChoice;
        System.out.println("Please select one of the below Modes: ");
        System.out.println("- Interactive Mode (ENTER: 'i')");
         System.out.println("\n- Report Mode (ENTER: 'r')");
    
        Scanner sc = new Scanner(System.in);
        userChoice = sc.nextLine();
   
        if(userChoice.equals("r"))
        {
            System.out.println("Welcome to Report Mode!");
        }
            else if (userChoice.equals("i"))
            {
                interactiveDisplay(o2);       
            }   
    }
/************************************************************
*   Name: Hussain Mehdi                                     *
*   Date: 02/04/2021                                        *
*   Purpose: Interactive menu               			*
*************************************************************/
    public static void interactiveDisplay(orderLinkedList o2)
    {
        int secondChoice;
        Scanner sc = new Scanner(System.in);        
        System.out.println("Welcome to Interactive Mode!"); 
        System.out.println("Please select one of the below options: ");
        System.out.println(" 1. Load Data \n 2. Find and Display location Inventory \n 3. Find and Display distance between two locations \n 4. Find and Display route for collecting order \n 5. Location Overview \n 6. Inventory Overview \n 7. Save data (serialized) \n 8. Exit");
        secondChoice = sc.nextInt();
        menuProcess(secondChoice, o2);
    }
/************************************************************
*   Name: Hussain Mehdi                                     *
*   Date: 02/04/2021                                        *
*   Purpose: menu processing for interactive               	*
*************************************************************/
    public static void menuProcess(int secondChoice, orderLinkedList o2)
    {
    	Scanner sc = new Scanner(System.in);
    	String mode ="";
        int thirdChoice=0;
        String fourthChoice="";
        String order="";
        if(secondChoice == 1)
        {
            
            System.out.println("  1. Location Data");
            System.out.println("  2. Order Data");
            System.out.println("  3. Serialized Data");
            thirdChoice = sc.nextInt();
        
             
            if(thirdChoice == 1)
            {
            Scanner scanner = new Scanner(System.in); 
            System.out.println("Enter filename to load: ");
            fourthChoice = scanner.nextLine();
                if(fourthChoice.equals("inventories.csv"))
                {
               
                readInventory(fourthChoice, thirdChoice, order, secondChoice, o2);
                }
                else if(fourthChoice.equals("stores.csv"))
                {
                readStores(mode, fourthChoice, secondChoice);
                }
               else 
                {
                    System.out.println("Please enter either 'inventories.csv' or 'stores.csv' to load data.");
                }   
            } 
            else if(thirdChoice == 2)
            {
                Scanner s = new Scanner(System.in); 
                System.out.println("Enter filename to load: 'order1.csv' or 'order2.csv' or 'order12.csv'");
                order = s.nextLine();
              	readFileOrder(fourthChoice, thirdChoice, order, secondChoice, o2);
    
            }
            else if(thirdChoice == 3)
            {
            	String fileName ="serialized.txt";
                openSerial(fileName);
            }
        }
            else if(secondChoice == 2)
            {
                fourthChoice = "inventories.csv";
        	readInventory(fourthChoice, thirdChoice, order, secondChoice, o2);
            }
            else if(secondChoice == 3)
            {
                fourthChoice = "stores.csv";
            readStores(mode, fourthChoice, secondChoice);
            }
            else if(secondChoice == 4)
            { 
           	 Scanner a = new Scanner(System.in); 
            	System.out.println("Please enter Order File to load: ");
            	 order = a.nextLine();
         	readInventory(fourthChoice, thirdChoice, order, secondChoice, o2);
            }
            else if(secondChoice == 5)
            {
            	readStores(mode, fourthChoice, secondChoice);
            }
            else if(secondChoice == 6)
            {
            	readInventory(fourthChoice, thirdChoice, order, secondChoice, o2);
            }
            else if(secondChoice == 7)
            {
            	String filename= "serialized.txt";
            	save(o2, filename);
            }
            else if(secondChoice == 8)
            {
            	System.out.println("BYE!!");
            }
    }

    public static void readStores(String mode, String fourthChoice, int secondChoice)
    {
	String file = fourthChoice;
	orderLinkedList s1 = new orderLinkedList();
	Scanner sc = new Scanner(System.in);
      	Scanner scanner = new Scanner(System.in);
	String To="";
	String From="";
	double Distance=0.0; 
	String loc1 = "";
	String loc2 = "";
   	String line = "";  
    	String splitBy = ",";  
    	String[] dataB;
    try
    {
  		if(secondChoice == 3)
		{
		  System.out.println("Please enter 2 locations to check distance between: ");
		  System.out.println("Location 1: ");
        		loc1 = sc.nextLine();
        		System.out.println("Location 2: ");
			loc2 = scanner.nextLine();
		}
    	BufferedReader br = new BufferedReader(new FileReader(file));  
        FileWriter myWriter = new FileWriter("storeData.txt");
        while ((line = br.readLine()) != null)           
        {
           dataB = line.split(splitBy);          
            From = dataB[0]; 
            To = dataB[1];
            Distance = Double.parseDouble(dataB[2]);
	
            s1.insert(From);
            s1.insert(To);
            s1.insert(Distance);
		if(secondChoice == 3)
		{
			if(dataB[0].equals(loc1) && (dataB[1].equals(loc2)))
			{
				System.out.println("From: "+ dataB[0]);
				System.out.println("To: "+ dataB[1]);
				System.out.println("Distance: " + dataB[2]);  

				if(mode.equals("-r"))
            			{
            				try 
            				{
     						myWriter.write(dataB[0]);
      						System.out.println("Successfully wrote to the file.");
            				}
            			catch (IOException e) 
            			{
      					System.out.println("An error occurred.");
      					e.printStackTrace();
    				}
				}
       		 }
       	}
       	}
       		myWriter.close();
            if(secondChoice == 1)
            {
                s1.display();    
            }
            else if(secondChoice == 5)
            { 
            	s1.display();
            }
         
      }
   	catch (IOException e)   
        {  
            e.printStackTrace();  
        }
 	catch (Exception e) 
 	{
 		throw new IllegalArgumentException("Unable to load file");
 	}
   } 
   
    public static void readInventory(String fourthChoice, int thirdChoice, String order, int secondChoice, orderLinkedList o2)
    {
        String file = "inventories.csv";
        
        String Store="";
        String Product="";
        int StockAtHand=0; 

    String line = "";  
    String splitBy = ",";  
    try
    {
        BufferedReader br = new BufferedReader(new FileReader(file));  
        String[] dataA;

        while ((line = br.readLine()) != null)   //returns a Boolean value          
        {
            dataA = line.split(splitBy);          
            Store = dataA[0];
            Product = dataA[1];
            StockAtHand = Integer.parseInt(dataA[2]);

		if((secondChoice == 4))
		{
            		o2.insert(Product);
            		o2.insert(Store);
            	}
            	else
            	{
            		o2.insert(Product);
            		o2.insert(Store);
            		o2.insert(StockAtHand);
            	}
        }
        if(secondChoice == 1)
        {
            System.out.println("****** Display Format *******");
            System.out.println("xStore");
            System.out.println("xProduct");
            System.out.println("xStockAtHand");
            System.out.println("\n");
    	    o2.display(); 
        }
        else if(secondChoice == 2)
        {
          	o2.searchInv();
        }
        else if((secondChoice == 4))
        {
          readFileOrder(fourthChoice, thirdChoice, order, secondChoice, o2);
        }
         else if(secondChoice == 6)
    	{ 
    		o2.display();
    	} 
    }
         catch (IOException e)   
        {  
            e.printStackTrace();  
        }
 	catch (Exception e) 
 	{
 		throw new IllegalArgumentException("Unable to load file");
 	}
   }
    public static void readFileOrder(String fourthChoice,int thirdChoice, String order, int secondChoice, orderLinkedList o2)
    {
    	String line = "";  
    	String splitBy = ",";  
   
        String filea = order;
        orderLinkedList o1 = new orderLinkedList();
        int Quantity=0; 
        String[] data;
        String product="";
        try
    	{
        BufferedReader rb = new BufferedReader(new FileReader(filea));
        
        while ((line = rb.readLine()) != null)   //returns a Boolean value          
        {
            data = line.split(splitBy);          
            product = data[0];
            Quantity = Integer.parseInt(data[1]);

            o1.insert(product);
            o1.insert(Quantity);
       //   System.out.println(data[0]+", "+ data[1]);
        }
        if(secondChoice == 4)
        {
        	o2.route(o1);
        }
       
      else if(thirdChoice == 2)
      {    
	      o1.display();    
	}
	}
	catch (IOException e)   
        {  
            e.printStackTrace();  
        }
 	catch (Exception e) 
 	{
 		throw new IllegalArgumentException("Unable to load file");
 	}
    }
    private static void save (orderLinkedList data, String filename)
    {
    	FileOutputStream fileStrm;
 	ObjectOutputStream objStrm;
    try 
    	{
 	fileStrm = new FileOutputStream(filename);
    	objStrm = new ObjectOutputStream(fileStrm);
    	objStrm.writeObject(data); 
    	
    	objStrm.close();
    	 }
 	catch (Exception e) 
 	{
 		throw new IllegalArgumentException("Unable to save object to file");
 	}
    } 
    private orderLinkedList load(String filename)
    {
    	FileInputStream fileStrm;
	ObjectInputStream objStrm;
 	orderLinkedList inObj = new orderLinkedList();
 	try 
 	{
 		fileStrm = new FileInputStream(filename);
 		objStrm = new ObjectInputStream(fileStrm);
 		inObj = (orderLinkedList)objStrm.readObject(); 
 		objStrm.close();
    	}
    	catch (FileNotFoundException e)
    	{ 
 		System.out.println("Couldnt find your file " + e.getMessage() + "try again!");
 	}
    	catch (ClassNotFoundException e) 
    	{
 		System.out.println("Class ContainerClass not found");
 	}
 	catch (Exception e) 
 	{
 		throw new IllegalArgumentException("Unable to load object from file");
 	}
 		return inObj;
    }
/************************************************************
*   Name: Hussain Mehdi                                     *
*   Date: 02/04/2021                                        *
*   Purpose: Opening Serializattion file               	*
*************************************************************/
    public static void openSerial(String fileName)
    {
    	if(fileName != null)
    	{
    	String line = "";  
    	try
    	{
    		BufferedReader br = new BufferedReader(new FileReader(fileName));  
    		while ((line = br.readLine()) != null)           
       	{
       		System.out.println(line);
       	}
    	}
    	catch (IOException e)   
        {  
            e.printStackTrace();  
        }
 	catch (Exception e) 
 	{
 		throw new IllegalArgumentException("Unable to load file");
 	}
    	
    	}
    }
}
