import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class Converter
	{
		
		static int cost;
		static int preservedCost;
		static int amountPaid;
		static String currencyChoice;
		static int currencyVal;
		static int[][]coinValues;
		static ArrayList<String> currencies = new ArrayList<String>();
		
		public static void main(String[] args) throws IOException
			{
				getCoinValues();
				gatherInfo();
				calculateChange();

			}
		
		public static int getFileLines() throws IOException
			{
				int lineCount = 0;
				Scanner f = new Scanner(new File("currency.txt"));
				while(f.hasNext())
				{
					lineCount++;
					f.nextLine();
				}
				return lineCount;
				
			}
		
		public static void getCoinValues() throws IOException
			{
				int counter = 0;
				
				coinValues = new int[(getFileLines() / 2)][8];
				Scanner file = new Scanner(new File("currency.txt"));
				while(file.hasNext())
				{
					currencies.add(file.nextLine());
					String[] temp = file.nextLine().split(" ");
					for(int j = 0; j < temp.length; j++)
						coinValues[counter][j] = Integer.parseInt(temp[j]);
					counter++;
				}
				
						
			}
		
		public static void gatherInfo()
			{
				Scanner currencyIn = new Scanner(System.in);
				System.out.println("Pick a currency: ");
				int counter = 1;
				for(String s : currencies)
				{
					System.out.println("\t" + counter + ".) " + s);
					counter++;
				}
				currencyVal = currencyIn.nextInt();
				currencyChoice = currencies.get(currencyVal - 1);
				Scanner costIn = new Scanner(System.in);
				System.out.println();
				System.out.println("Cost: ");
				cost = costIn.nextInt();
				preservedCost = cost;
				Scanner amountPaidIn = new Scanner(System.in);
				System.out.println();
				System.out.println("Amount Paid: ");
				amountPaid = amountPaidIn.nextInt();
				System.out.println();
				
			}
		
		public static void calculateChange()
			{
				int[] numCoinsReq = {0, 0, 0, 0, 0, 0, 0, 0};
				for(int i = 7; i >= 0; i--)
				{
					while(cost + coinValues[currencyVal - 1][i] <= amountPaid)
					{					
							cost += coinValues[currencyVal - 1][i];
							numCoinsReq[i] ++;
					}
				}
				
				System.out.println("Change: " + (amountPaid - preservedCost));
				System.out.println();
//				System.out.println("Most efficient distribution: ");
//				System.out.println();
				for(int j = 0; j < numCoinsReq.length; j++)
					System.out.println("\t" + (coinValues[currencyVal - 1][j]) + ": " + numCoinsReq[j]);
				
				
			}

	}
