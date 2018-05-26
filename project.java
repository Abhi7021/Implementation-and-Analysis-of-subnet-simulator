import java.util.Scanner;

 public class project {
                static project pr;
		public static void calcSHbits(int maxnh, int maxsnw,int db, int oct1, int oct2, int oct3)		//This function calculates the no. of host bits
		{
			int hpn, snw = 0, choice;
			float nhb, nsb;
			Scanner in2 = new Scanner(System.in);
			System.out.println("This Project is mainly on subnetting. Thus,");
			System.out.println("A subnetwork or subnet is a logical subdivision of an IP network. " );
			System.out.println("The practice of dividing a network into two or more networks is called subnetting.");
			System.out.println("Computers that belong to a subnet are addressed with a common, identical, most-significant bit-group in their IP address.");
			System.out.println("This results in the logical division of an IP address into two fields, a network number or routing prefix and the rest field or host identifier.");
			System.out.println("The rest field is an identifier for a specific host or network interface.");
			System.out.println("\n");
			System.out.println("The benefits of subnetting an existing network vary with each deployment scenario. ");
			System.out.println("In the address allocation architecture of the Internet using CIDR and in large organizations, it is necessary to allocate address space efficiently.");
			System.out.println("Subnetting may also enhance routing efficiency, or have advantages in network management when subnetworks are administratively controlled by different entities in a larger organization.");
			System.out.println("Subnets may be arranged logically in a hierarchical architecture, partitioning an organization's network address space into a tree-like routing structure.");
			System.out.println("\n");
			System.out.println("Kindly choose the preferred option(1 or 2) as per your network requirements: \n");
			System.out.println("1. If you want to subnet according to the number of hosts per network \n");
			System.out.println("2. If you want to subnet according to the number of sub-networks \n");
			choice = in2.nextInt();
			System.out.println("\n");
			while (choice != 1 && choice != 2)
			{
				System.out.println("Invalid choice entered.");
				System.out.println("Kindly enter either 1 or 2: ");
				choice = in2.nextInt();
				System.out.println("\n");
			}
			if(choice == 1)
			{
				System.out.println("Enter the required number of hosts per sub-network :-");
				hpn = in2.nextInt();
				System.out.println("\n");
				while(hpn > maxnh)
				{
					System.out.println("Entered number of hosts exceed the maximum number of possible hosts per network.");
					System.out.println("Thus it is not possible. Try Again:-");
					System.out.println("Enter the required number of hosts per sub-network :-");
					hpn = in2.nextInt();
					System.out.println("\n");
				}
				nhb = (float) (Math.log(hpn+2)/Math.log(2));		//this formula calculates the no of host bits
				System.out.println("Number of host bits after subnetting= " +Math.ceil(nhb));
				int store = (int) Math.ceil(nhb);
				System.out.println("Number of IPs that can be assigned to hosts (Number of valid hosts) per network " + (Math.pow(2, store)-2));
				int store2 = (int) (Math.pow(2, store)-2);
				if(store2>512)
				{
					System.out.println("Suggestion :");
					System.out.println("We'd like to point out that number of hosts per network from this network configuration is not recommended.");
					System.out.println("It jams the network with traffic.");
					System.out.println("To avoid, kindly do not go for any more than 512 hosts per network.");
				}
				System.out.println("\nNumber of subnet bits = " +(db-store));
				System.out.println("\nNumber of sub-networks= " +Math.pow(2, db-store));
				float var1 = store;
				float var2 = db-store;
				calcNSM(db, choice, snw, var1, var2, oct1, oct2, oct3);
			}
			if(choice == 2)
			{
				System.out.println("Enter the total no. of required sub-networks :-");
				snw = in2.nextInt();
				System.out.println("\n");
				while(snw > maxsnw)
				{
					System.out.println("Entered number of subnets exceed the maximum number of sub-networks possible.");
					System.out.println(" Try again.");
					System.out.println("Enter the total no. of required sub-networks :-");
					snw = in2.nextInt();
					System.out.println("\n");
				}
				nsb = (float) (Math.log(snw)/Math.log(2));		//this formula calculates the no. of Subnet bits
				System.out.println("Number of subnet bits = " +Math.ceil(nsb));
				int store3 = (int) Math.ceil(nsb);
				System.out.println("Number of sub-networks= " +Math.pow(2, store3));
				System.out.println("Number of host bits after subnetting= " +(db-store3));
				int store4 = db-store3;
				System.out.println("Number of IPs that can be assigned to hosts (Number of valid hosts per network) per network = " +(Math.pow(2, store4)-2));
				int store5 = (int) (Math.pow(2, store4)-2);
				if(store5 >512)
				{
					System.out.println("Suggestion :");
					System.out.println("We'd like to point out that number of hosts per network from this network configuration is not recommended.");
					System.out.println(" It jams the network with traffic.");
					System.out.println("To avoid, kindly do not go for any network configuration that allows more than 512 hosts per network.");
				}
				float var1 = store4;
				float var2 = store3;
				calcNSM(db, choice, snw, var1, var2, oct1, oct2, oct3);
			}
		}
		public static void calcNSM(float db, int choice, int snw, float var1, float var2, int oct1, int oct2, int oct3 )
		{
			int  i,inc = 0, incOct = 0, a = 0;
			if(db == 8)
			{
				for( i = 7; i >= var1; i--)
				{
					a = a + (int) (Math.pow(2, i));
				}
				System.out.println("The new subnet mask is 255.255.255." +a+"\n");
				inc = (int) Math.pow(2, var1);
				incOct=4;
			}
			//for class B
			else if(db == 16 && var2 >=1 && var2 <=8)
			{
				for(i = 7; i >= (8-var2); i--)
				{
					a = a + (int) (Math.pow(2, i));
				}
				System.out.println("The new subnet mask is 255.255."+a+".0\n");
				inc = (int) Math.pow(2, (8-var2));
				incOct=3;
			}
			else if(db == 16 && var2 >=9 && var2 <=15)
			{
				for(i=0; i < var2-8; i++)
				{
					a = a + (int) (Math.pow(2, (7-i)));
				}
				System.out.println("The new subnet mask is 255.255."+a+".0\n");
				inc = (int) Math.pow(2, (16-var2));
				incOct= 4;
			}
			//for class A
			else if(db == 24 && var2 >=1 && var2 <=8)
			{
				for(i=7; i>=(8-var2); i--)
				{
					a = a + (int) (Math.pow(2,i));
				}
				System.out.println("The new subnet mask is 255."+a+".0.0\n");

				inc = (int) Math.pow(2, (8-var2));
				incOct=2;
			}
			else if(db == 24 && var2 >= 9 && var2 <= 16)
			{
				for(i = 7; i >= (16-var2); i--)
				{
					a = a + (int) (Math.pow(2, i));
				}
				System.out.println("The new subnet mask is 255."+a+".0.0\n");

				inc = (int) Math.pow(2, (16-var2));
				incOct= 3;
			}

			else if(db == 24 && var2 >= 17 && var2 <= 23)
			{
				for(i = 7; i >= (24-var2); i--)
				{
					a = a + (int) (Math.pow(2, i));
				}
				System.out.println("The new subnet mask is 255."+a+".0.0\n");

				inc = (int) Math.pow(2, (24-var2));
				incOct= 4;
			}
			calcRange(snw, choice, oct1, oct2, oct3, inc, incOct);
		}
		public static void calcRange(int snw, int choice, int oct1, int oct2, int oct3, int inc, int incOct)
		{
			int i, j, k, nwb, rb, nwn, lnbp, inc2= 0; 		//nwb is network bits and nwn is number of networks, rb is rest bits.
			if (oct1>=1 && oct1<=126)						// For class A addresses
			{
				System.out.println("The network ID/address of the entered address is :- "+oct1 +".0.0.0\n");
				System.out.println("The broadcast address of the entered address is :- "+oct1 +".255.255.255\n");
				System.out.println("The details of sub-networks are as follows: \n");
				System.out.println(oct1+".0.0.0\n");
				if(incOct == 2)
				{
					for(i = 2; i <= ((256/inc)); i++ )
					{
						inc2 = inc2+inc;
						System.out.println( oct1+"."+(inc2-1)+".255.255\n");

						System.out.println( oct1+"."+inc2+"0.0\n");
						System.out.println("\n");
					}
					System.out.println (oct1+".255.255.255");

				}



				else if(incOct == 3)
				{

					for(j = 0; j<256; j++)
					{
						System.out.println(oct1+"."+j+"0.0\n");

						inc2 = 0;

						for(i = 2; i <= ((256/inc)); i++ )
						{
							inc2 = inc2+inc;
							System.out.println(oct1+"."+j+"."+(inc2-1)+".255\n");

							System.out.println (oct1+"."+j+"."+inc2+".0\n");
							System.out.println("\n");

						}
						System.out.println(oct1+"."+j+".255.255\n");

					}
				}



				else
				{
					System.out.println(+oct1+".0.0.0\n");
					System.out.println(oct1+".0.0"+(inc-1)+"\n");

					for(k = 0; k<=255; k++)
					{

						for(j = 0; j<256; j++)
						{

							inc2 = 0;

							for(i = 2; i <= ((256/inc)); i++ )
							{
								System.out.println(oct1+"."+k+"."+j+"."+inc2+"\n");
								
								inc2 = inc2+inc;

								System.out.println ( oct1 +"." +k+"." +j +"."+ (inc2-1));

								System.out.println("\n");
							}
						}


					}
				}
			}

			//for class B
			else if (oct1>=128 && oct1<=191)
			{
				System.out.println("The network ID/address of the entered address is :- "+oct1+"."+oct2+".0.0\n");
				System.out.println("The broadcast address of the entered address is :-" +oct1+"."+oct2+".255.255\n");
				System.out.println("\nThe detail of sub-networks is as follows: \n");
				System.out.println(oct1+"."+oct2+".0.0\n");

				//dual testing done
				if(incOct == 3)
				{
					for(i = 2; i <= ((256/inc)); i++ )
					{

						inc2 = inc2+inc;

						System.out.println(oct1+"."+oct2+"."+(inc2-1)+".255\n");
						System.out.println(oct1 +"."+ oct2 +"."+ inc2+".0\n");
						System.out.println("\n");
					}
					System.out.println(oct1+"."+oct2+"255.255\n");
				}

				// dual testing done

				else
				{

					for(j = 0; j<256; j++)
					{
						System.out.println(oct1+"."+oct2+"."+j+".0");
						inc2 = 0;

						for(i = 2; i <= ((256/inc)); i++ )
						{
							inc2 = inc2+inc;
							System.out.println(oct1+"."+oct2+"."+j+"."+(inc2-1));
							System.out.println(oct1+"."+oct2+"."+j+"."+inc2);
							System.out.println("\n");
						}
						System.out.println(oct1+"."+oct2+"."+j+".255\n");
					}
				}
			}


			else
			{
				System.out.println("\nThe network ID/address of the entered address is :-" +oct1+"."+oct2+"."+oct3+".0\n" );
				System.out.println("\nThe broadcast address of the entered address is- " +oct1+"."+oct2+"."+oct3+".255\n");
				System.out.println("\nThe detalis of sub-networks is as follows: \n");
				System.out.println(oct1+"."+oct2+"."+oct3+".0\n");

				for(i = 2; i <= ((256/inc)); i++ )
				{
					inc2 = inc2+inc;
					System.out.println(oct1+"."+oct2+"."+oct3+"."+(inc2-1));
					System.out.println(oct1+"."+oct2+"."+oct3+"."+inc2);
					System.out.println("\n");
				}
				System.out.println(oct1+"."+oct2+"."+oct3+".255\n");

			}
			if (choice ==1)
				System.out.println("\n\nYou can choose any number of networks from these ranges. All of them have equal number of hosts. \n");

			if(choice == 2)
				System.out.println("\n\nYou can choose any "+snw+" networks from this range. \n" );

		}
		public static void choice1()
		{
			int oct1, oct2, oct3, oct4;
			int maxnh, maxsnw, db;
			Scanner in= new Scanner(System.in);
			System.out.println("An Internet Protocol address (IP address) is a numerical label assigned to each device connected to a computer network that uses the Internet Protocol for communication.");
			System.out.println("An IP address serves two principal functions: host or network interface identification and location addressing.");
			System.out.println("An IPv4 address has a size of 32 bits, which limits the address space to 4294967296 (232) addresses.");
			System.out.println("\n");
			System.out.println("Of this number, some addresses are reserved for special purposes such as private networks (~18 million addresses) and multicast addressing (~270 million addresses) .");
			System.out.println("IPv4 addresses are usually represented in dot-decimal notation, consisting of four decimal numbers, each ranging from 0 to 255, separated by dots, e.g., 172.16.254.1 . ");
			System.out.println("Each part represents a group of 8 bits (an octet) of the address.");
			System.out.println("\n");
			System.out.println("In this project you have to provide values for each octect through which subnetting will be done in two differnt ways");
			System.out.println("These two ways, you'll get to know about them further but for now");
			System.out.println("\n");
			System.out.println("Please fill in the details of IP Address:");
			System.out.println("Enter the values of each octect one by one as per instructions:");
			System.out.println("First Octate:");
			oct1 = in.nextInt();
			System.out.println("\n");
			while(oct1<1 || oct1>255)
			{
				System.out.println("Invalid number entered.");
				System.out.println("You cannot enter any number less than 1 or greater than 255 for octect 1 as:");
				System.out.println("0 in octect 1 will mean that there is no network address.");
				System.out.println("256 in octect 1 will simply be illegal assigning of octect value as we only have range of 0 to 255 for each octect.");
				System.out.println("Kindly enter the valid value after checking it once:");
				System.out.println("First Octate:");
				oct1 = in.nextInt();
				System.out.println("\n");
			}
			while(oct1>223 && oct1<255)
			{
				if(oct1>223 && oct1<=239)
				{
					System.out.println("This is class D IP address.");
					System.out.println("Class D does not have any subnet mask");
					System.out.println("The range of class D IP address is 224.0.0.0 to 239.255.255.255");
					System.out.println("Class D IP address are reserved for multicast groups.");
				}
				else
				{
					System.out.println("This is class E IP address.");
					System.out.println("Class E does not have any subnet mask");
					System.out.println("The range of class E IP address is 224.0.0.0 to 239.255.255.255");
					System.out.println("Class E IP address are reserved for experimental purposes only for R&D or Study.");
				}
				System.out.println("You can't enter this value as these values because their reserverd bits for network and hosts are not defined, thus cannot perform operations on this range of addresses.\n Kindly enter again:\n");
				System.out.println("First Octate:");
				oct1 = in.nextInt();
				System.out.println("\n");
			}
			System.out.println("Second Octate:");
			oct2 = in.nextInt();
			System.out.println("\n");
			while(oct2<0 || oct2>255)
			{
				System.out.println("Invalid number entered.");
				System.out.println("0 or 256 octect 2 will simply be illegal assigning of octect value as we only have range of 0 to 255 for each octect.");
				System.out.println("Kindly enter a valid value within the range after checking it for once");
				System.out.println("Second octate:");
				oct2 = in.nextInt();
				System.out.println("\n");
			}
			System.out.println("Third octate:");
			oct3 = in.nextInt();
			System.out.println("\n");
			while(oct3<0 || oct3>255)
			{
				System.out.println("Invalid number entered.");
				System.out.println("0 or 256 octect 3 will simply be illegal assigning of octect value as we only have range of 0 to 255 for each octect.");
				System.out.println("Kindly enter a valid value within the range after checking it for once");
				System.out.println("Third octate:");
				oct3 = in.nextInt();
				System.out.println("\n");
			}
			System.out.println("Fourth octate:");
			oct4 = in.nextInt();
			System.out.println("\n");
			while(oct4<0 || oct4>255)
			{
				System.out.println("Invalid number entered.");
				System.out.println("0 or 256 octect 4 will simply be illegal assigning of octect value as we only have range of 0 to 255 for each octect.");
				System.out.println("Kindly enter a valid value within the range after checking it for once");
				System.out.println("Fourth octate:");
				oct4 = in.nextInt();
				System.out.println("\n");
			}
			System.out.println("The entered IP address is " +oct1 +"." +oct2 +"." +oct3 +"." +oct4);
			if (oct1>=1 && oct1<=126)
			{
				System.out.println("\n");
				System.out.println("This is class A IP address. Default Subnet mask= 255.0.0.0");
				System.out.println("The range of class A IP address is 1.0.0.1 to 126.255.255.254");
				System.out.println("Class A IP address supports 16 million hosts on each of 127 networks.");
				System.out.println("In Class A, only the first octet is used as Network identifier and rest of three octets are used to be assigned to Hosts (i.e. 16777214 Hosts per Network).");
				System.out.println("To make more subnet in Class A, bits from Host part are borrowed and the subnet mask is changed accordingly.");
				maxnh = 8388607;		//Maximum no. of hosts
				maxsnw = 8388608;		// Maximum sub network window(total possible addresses)		
				db = 24;				//db is the default number of bits to be played with
				System.out.println("Maximum no. of host possible are:-"+maxnh);
				System.out.println("Data bit available to be played with :-"+db);
				System.out.println("Maximum no. of subnets possible are :-"+maxsnw);
				System.out.println("\n");
				calcSHbits(maxnh, maxsnw, db, oct1, oct2, oct3);
			}
			else
			{
				if(oct1>=128 && oct1<=191)
				{	
					System.out.println("\n");
					System.out.println("This is class B IP address. Default Subnet mask= 255.255.0.0\n");
					System.out.println("The range of class B IP address is 128.1.0.1 to 191.255.255.254");
					System.out.println("Class B IP address supports 65,000 hosts on each of 16,000 networks.");
					System.out.println("Class B IP Addresses can be subnetted the same way as Class A addresses, by borrowing bits from Host bits.");
					maxnh = 32767;		//Maximum no. of hosts
					db = 16;			//db is the default number of bits to be played with
					maxsnw = 32768;		//Maximum sub network window(total possible addresses
					System.out.println("Maximum no. of host possible are:-"+maxnh);
					System.out.println("Data bit available to be played with :-"+db);
					System.out.println("Maximum no. of subnets possible are :-"+maxsnw);
					System.out.println("\n");
					calcSHbits(maxnh, maxsnw, db, oct1, oct2, oct3);
				}
				else if(oct1>=192 && oct1<=223)
				{
					System.out.println("\n");
					System.out.println("This is class C IP address. Default Subnet mask= 255.255.255.0\n");
					System.out.println("The range of class C IP address is 192.0.1.1 to 223.255.254.254");
					System.out.println("Class C IP address supports 254 hosts on each of 2 million networks.");
					System.out.println("Class C IP addresses are normally assigned to a very small size network because it can only have 254 hosts in a network.");
					maxnh = 127;		//Maximum no. of hosts
					db = 8;				//db is the default number of bits to be played with
					maxsnw = 128;		// Maximum sub network window(total possible addresses)
					System.out.println("Maximum no. of host possible are:-"+maxnh);
					System.out.println("Data bit available to be played with :-"+db);
					System.out.println("Maximum no. of subnets possible are :-"+maxsnw);
					System.out.println("\n");
					calcSHbits(maxnh, maxsnw, db, oct1, oct2, oct3);
				}
			}
		}
		int calcRange2(int oct1, int oct2, int oct3, int snw, int inc, int incOct)
		{
		        int i, j, k, inc2 = 0;
		        char ch;


		        if (oct1>=1 && oct1<=126)
		            {
		                System.out.println("\nThe network ID/address of the entered address is "+oct1+".0.0.0\n");
		                System.out.println("The broadcast address of the entered address is "+oct1+".255.255.255\n");

		                System.out.println("\nThe above entered IP address with custom subnet mask lies in one of the following network ranges.\n\n ");
		                System.out.println("\nPress any key to display all the "+snw+" networks below...\n\n");

		                System.out.println("\nThe detail of sub-networks is as follows: \n\n");
		                System.out.println("1. "+oct1+".0.0.0    -    \n");

		              //testing done
		                    if(incOct == 2)
		                    {
		                        for(i = 2; i <= ((256/inc)); i++ )
		                        {
		                            inc2 = inc2+inc;
		                            System.out.println(oct1+"."+(inc2-1)+".255.255\n");
		                            System.out.println(i+"."+oct1+"."+inc2+".0.0\n");
		                        }
		                        System.out.println(oct1+".255.255.255\n");

		                    }


		            //dual testing done except for serial number's bug
		                    else if(incOct == 3)
		                    {
		                    //printf(" %d.0.0.0\n",  oct1);
		                    //printf(" %d.0.%d.0\n",  oct1, inc);

		                        for(j = 0; j<256; j++)
		                        {
		                            System.out.println(oct1+"."+j+".0.0    -    \n");
		                            inc2 = 0;

		                            for(i = 2; i <= ((256/inc)); i++ )
		                            {
		                                inc2 = inc2+inc;
		                                System.out.println(oct1+"."+j+"."+(inc2-1)+".255\n");
		                                System.out.println(oct1+"."+j+"."+inc2+".0    -    \n");

		                            }
		                            System.out.println(oct1+"."+j+".255.255\n");
		                        //printf(" %d.%d.%d.%d\n",  oct1, oct2, j, 0);
		                        }
		                    }

		                //else{printf("You don't want the display.");}

		                //if(incOct == 4)
		                    else
		                    {
		                        System.out.println(oct1+".0.0.0    -    \n");
		                        System.out.println(oct1+".0.0."+(inc-1)+"  \n");

		                        for(k = 0; k<=255; k++)
		                        {
		                        //printf(" %d.%d.0.0\n",  oct1, );

		                            for(j = 0; j<256; j++)
		                            {
		                                //printf(" %d.%d.%d.%d    -    ", oct1, k, j, inc);
		                                inc2 = 0;

		                                for(i = 0; i < ((256/inc)); i++ )
		                                {
		                                    //inc2 = inc2+inc;
		                                    System.out.println( oct1+"."+k+"."+j+"."+inc2);
		                                    inc2 = inc2+inc;
		                                    System.out.println(oct1+"."+k+"."+j+"."+(inc2-1));

		                                }
		                            }
		                        //printf(" %d.%d.%d.%d\n",  oct1, oct2, j, 0);

		                        }
		                    }
		                }


		        else if (oct1>=128 && oct1<=191)   //means class B network
		            {
		                System.out.println("\nThe network ID/address of the entered address is- "+oct1+"."+oct2+".0.0\n");
		                System.out.println("The broadcast address of the entered address is- "+oct1+"."+oct2+".255.255\n");

		                System.out.println("\nThe above entered IP address with custom subnet mask lies in one of the following network ranges.\n\n ");
		                System.out.println("\nPress any key to display all the "+snw+" networks below...\n\n");

		                System.out.println("\nThe detail of sub-networks is as follows: \n\n");
		                System.out.println("1. "+oct1+"."+oct2+".0.0    -    \n");

		            //dual testing done
		                if(incOct == 3)
		                {
		                    for(i = 2; i <= ((256/inc)); i++ )
		                    {
		                        //printf(" %d.%d.%d.255\n",  oct1, oct2, inc2-1);
		                        inc2 = inc2+inc;
		                        //printf("1. %d.%d.0.0\n",oct1, oct2);
		                        System.out.println(oct1+"."+oct2+"."+(inc2-1)+".255\n");
		                        System.out.println(i+".  "+oct1+"."+oct2+"."+inc2+".0    -    \n");
		                    }
		                    System.out.println(oct1+"."+oct2+".255.255\n");
		                }

		            // dual testing done
		                //if(incOct == 4)
		                else
		                {
		                //printf(" %d.%d.0.0\n",  oct1, oct2);
		                //printf(" %d.%d.0.%d\n",  oct1, oct2, inc);

		                    for(j = 0; j<256; j++)
		                    {
		                        System.out.println(oct1+"."+oct2+"."+j+"."+j+".0    -    \n");
		                        inc2 = 0;

		                        for(i = 2; i <= ((256/inc)); i++ )
		                        {
		                            inc2 = inc2+inc;
		                            System.out.println(oct1+"."+oct2+"."+j+(inc2-1)+" \n");
		                            System.out.println(oct1+"."+oct2+"."+j+"."+inc2+"     -    \n");

		                        }
		                        System.out.println(oct1+"."+oct2+"."+j+".255\n");
		                    }
		                }
		            }


		        else//(oct1>=192 && oct1<=223)  //means class C network
		        {
		                System.out.println("\nThe network ID/address of the entered address is- "+oct1+"."+oct2+"."+oct3+".0\n");
		                System.out.println("The broadcast ID of the entered address is- "+oct1+"."+oct2+"."+oct3+".255\n");

		                System.out.println("\nThe above entered IP address with custom subnet mask lies in one of the following network ranges.\n\n ");
		                System.out.println("\nPress any key to display all the "+snw+" networks below...\n\n");

		                System.out.println("\nThe details of sub-networks for this subnet mask is as follows: \n\n");
		                System.out.println("1. "+oct1+"."+oct2+"."+oct3+".0    -    \n");

		                for(i = 2; i <= ((256/inc)); i++ )
		                {
		                    inc2 = inc2+inc;
		                    //inc3 = 1
		                    System.out.println(oct1+"."+oct2+"."+oct3+"."+(inc2-1)+" \n");
		                    //printf("Valid host(IP) range in this network : %d.%d.%d.%d - %d.%d.%d.%d", oct1, oct2, oct3, inc2)
		                    System.out.println(i+". "+oct1+"."+oct2+"."+oct3+"."+inc2+"    -    \n");
		                }
		                System.out.println(oct1+"."+oct2+"."+"."+oct3+".255\n");

		        }
		        return 0;

		}

		int validsmaskA(int oct1, int oct2, int oct3)
	    {
	            int i, inc=0, incOct=0;
	            int bitsBorrowed=0, snw=0, hpsnw=0, totalnwb=0, bsize=0, totalhb=0, flash=0;
	            char smaskuser[] = new char[]{'\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};


	            char smaska111[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '.', '0', '\0', '\0', '\0', '\0', '\0', '\0'};
	            char smaska112[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '.', '0', '0', '\0', '\0', '\0', '\0', '\0'};
	            char smaska113[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '.', '0', '0', '0', '\0', '\0', '\0', '\0'};
	            char smaska114[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '0', '.', '0', '\0', '\0', '\0', '\0', '\0'};
	            char smaska115[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '0', '.', '0', '0', '\0', '\0', '\0', '\0'};
	            char smaska116[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '0', '.', '0', '0', '0', '\0', '\0', '\0'};
	            char smaska117[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '0', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska118[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '0', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska119[] = new char[]{'2', '5', '5', '.', '0', '.', '0', '0', '0', '.', '0', '0', '0', '\0', '\0'};

	            char smaska121[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '.', '0', '\0', '\0', '\0', '\0', '\0'};
	            char smaska122[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '.', '0', '0', '\0', '\0', '\0', '\0'};
	            char smaska123[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '.', '0', '0', '0', '\0', '\0', '\0'};
	            char smaska124[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska125[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska126[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska127[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska128[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska129[] = new char[]{'2', '5', '5', '.', '0', '0', '.', '0', '0', '0', '.', '0', '0', '0', '\0'};

	            char smaska131[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska132[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska133[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska134[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska135[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska136[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska137[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska138[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska139[] = new char[]{'2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska21281[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska21282[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska21283[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska21284[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska21285[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska21286[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska21287[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska21288[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska21289[] = new char[]{'2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska21921[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska21922[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska21923[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska21924[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska21925[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska21926[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska21927[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska21928[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska21929[] = new char[]{'2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska22241[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska22242[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska22243[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska22244[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska22245[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska22246[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska22247[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska22248[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska22249[] = new char[]{'2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '0', '.', '0', '0', '0'};

	            
	            char smaska22401[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska22402[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska22403[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska22404[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska22405[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska22406[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska22407[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska22408[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska22409[] = new char[]{'2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska22481[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska22482[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska22483[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska22484[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska22485[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska22486[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska22487[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska22488[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska22489[] = new char[]{'2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '0', '.', '0', '0', '0'};

	            char smaska22521[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska22522[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska22523[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska22524[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska22525[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska22526[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska22527[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska22528[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska22529[] = new char[]{'2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska22541[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska22542[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska22543[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska22544[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska22545[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska22546[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska22547[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska22548[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska22549[] = new char[]{'2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska22551[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaska22552[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaska22553[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaska22554[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaska22555[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaska22556[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaska22557[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaska22558[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaska22559[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '0'};


	            char smaska31281[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8', '.', '0', '\0', '\0'};
	            char smaska31282[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '\0'};
	            char smaska31283[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '0'};

	            char smaska31921[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2', '.', '0', '\0', '\0'};
	            char smaska31922[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '\0'};
	            char smaska31923[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '0'};

	            char smaska32241[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4', '.', '0', '\0', '\0'};
	            char smaska32242[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '\0'};
	            char smaska32243[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '0'};

	            char smaska32401[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0', '.', '0', '\0', '\0'};
	            char smaska32402[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '\0'};
	            char smaska32403[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '0'};

	            char smaska32481[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8', '.', '0', '\0', '\0'};
	            char smaska32482[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '\0'};
	            char smaska32483[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '0'};

	            char smaska32521[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2', '.', '0', '\0', '\0'};
	            char smaska32522[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '\0'};
	            char smaska32523[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '0'};

	            char smaska32541[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4', '.', '0', '\0', '\0'};
	            char smaska32542[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '\0'};
	            char smaska32543[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '0'};


	            char smaska32551[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '\0', '\0'};
	            char smaska32552[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '\0'};
	            char smaska32553[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0'};


	            char smaska4128[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8'};
	            char smaska4192[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2'};
	            char smaska4224[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4'};
	            char smaska4240[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0'};
	            char smaska4248[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8'};
	            char smaska4252[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2'};
	            //char smaskc8[15] = {'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4'};


	            System.out.println("Enter the subnet mask which your network is to be masked with:-  \n");
	            System.out.println("Note: Only first 15 entered characters will be taken into account \nbecause that's all you need to enter a valid subnet mask address.\n");
	            System.out.println("*Kindly enter no less or no more than 15 digits in your subnet mask.\n");
	            System.out.println("Subnet mask: ");
	            Scanner in = new Scanner(System.in);
	            String s="";
	            for(i = 0; i < 15; i++)
	            {
	            	s = s+in.next();
	            }
	            for(i = 0; i < 15; i++)
	            {
	            	smaskuser[i]=s.charAt(i);
	            }
	            for(i = 0; i < 15; i++)
	            {
	                while(smaskuser[i] != smaska111[i] && smaskuser[i] != smaska112[i] && smaskuser[i] != smaska113[i] && smaskuser[i] != smaska114[i] &&
	                            smaskuser[i] != smaska115[i] && smaskuser[i] != smaska116[i] && smaskuser[i] != smaska117[i] && smaskuser[i] != smaska118[i] &&
	                            smaskuser[i] != smaska119[i] &&                                                               smaskuser[i] != smaska121[i] && smaskuser[i] != smaska122[i] &&
	                            smaskuser[i] != smaska123[i] && smaskuser[i] != smaska124[i] && smaskuser[i] != smaska125[i] && smaskuser[i] != smaska126[i] &&
	                            smaskuser[i] != smaska127[i] && smaskuser[i] != smaska128[i] && smaskuser[i] != smaska129[i] &&
	                            smaskuser[i] != smaska131[i] && smaskuser[i] != smaska132[i] && smaskuser[i] != smaska133[i] && smaskuser[i] != smaska134[i] &&
	                            smaskuser[i] != smaska135[i] && smaskuser[i] != smaska136[i] && smaskuser[i] != smaska137[i] && smaskuser[i] != smaska138[i] &&
	                            smaskuser[i] != smaska139[i] &&

	                            smaskuser[i] != smaska21281[i] && smaskuser[i] != smaska21282[i] && smaskuser[i] != smaska21283[i] &&
	                            smaskuser[i] != smaska21284[i] && smaskuser[i] != smaska21285[i] && smaskuser[i] != smaska21286[i] &&
	                            smaskuser[i] != smaska21287[i] && smaskuser[i] != smaska21288[i] && smaskuser[i] != smaska21289[i] &&

	                            smaskuser[i] != smaska21921[i] && smaskuser[i] != smaska21922[i] && smaskuser[i] != smaska21923[i] &&
	                            smaskuser[i] != smaska21924[i] && smaskuser[i] != smaska21925[i] && smaskuser[i] != smaska21926[i] &&
	                            smaskuser[i] != smaska21927[i] && smaskuser[i] != smaska21928[i] && smaskuser[i] != smaska21929[i] &&

	                            smaskuser[i] != smaska22241[i] && smaskuser[i] != smaska22242[i] && smaskuser[i] != smaska22243[i] &&
	                            smaskuser[i] != smaska22244[i] && smaskuser[i] != smaska22245[i] && smaskuser[i] != smaska22246[i] &&
	                            smaskuser[i] != smaska22247[i] && smaskuser[i] != smaska22248[i] && smaskuser[i] != smaska22249[i] &&

	                            smaskuser[i] != smaska22401[i] && smaskuser[i] != smaska22402[i] && smaskuser[i] != smaska22403[i] &&
	                            smaskuser[i] != smaska22404[i] && smaskuser[i] != smaska22405[i] && smaskuser[i] != smaska22406[i] &&
	                            smaskuser[i] != smaska22407[i] && smaskuser[i] != smaska22408[i] && smaskuser[i] != smaska22409[i] &&

	                            smaskuser[i] != smaska22481[i] && smaskuser[i] != smaska22482[i] && smaskuser[i] != smaska22483[i] &&
	                            smaskuser[i] != smaska22484[i] && smaskuser[i] != smaska22485[i] && smaskuser[i] != smaska22486[i] &&
	                            smaskuser[i] != smaska22487[i] && smaskuser[i] != smaska22488[i] && smaskuser[i] != smaska22489[i] &&

	                            smaskuser[i] != smaska22521[i] && smaskuser[i] != smaska22522[i] && smaskuser[i] != smaska22523[i] &&
	                            smaskuser[i] != smaska22524[i] && smaskuser[i] != smaska22525[i] && smaskuser[i] != smaska22526[i] &&
	                            smaskuser[i] != smaska22527[i] && smaskuser[i] != smaska22528[i] && smaskuser[i] != smaska22529[i] &&

	                            smaskuser[i] != smaska22541[i] && smaskuser[i] != smaska22542[i] && smaskuser[i] != smaska22543[i] &&
	                            smaskuser[i] != smaska22544[i] && smaskuser[i] != smaska22545[i] && smaskuser[i] != smaska22546[i] &&
	                            smaskuser[i] != smaska22547[i] && smaskuser[i] != smaska22548[i] && smaskuser[i] != smaska22549[i] &&

	                            smaskuser[i] != smaska22551[i] && smaskuser[i] != smaska22552[i] && smaskuser[i] != smaska22553[i] &&
	                            smaskuser[i] != smaska22554[i] && smaskuser[i] != smaska22555[i] && smaskuser[i] != smaska22556[i] &&
	                            smaskuser[i] != smaska22557[i] && smaskuser[i] != smaska22558[i] && smaskuser[i] != smaska22559[i] &&

	                            smaskuser[i] != smaska31281[i] && smaskuser[i] != smaska31282[i] && smaskuser[i] != smaska31283[i] &&
	                            smaskuser[i] != smaska31921[i] && smaskuser[i] != smaska31922[i] && smaskuser[i] != smaska31923[i] &&
	                            smaskuser[i] != smaska32241[i] && smaskuser[i] != smaska32242[i] && smaskuser[i] != smaska32243[i] &&
	                            smaskuser[i] != smaska32401[i] && smaskuser[i] != smaska32402[i] && smaskuser[i] != smaska32403[i] &&
	                            smaskuser[i] != smaska32481[i] && smaskuser[i] != smaska32482[i] && smaskuser[i] != smaska32483[i] &&
	                            smaskuser[i] != smaska32521[i] && smaskuser[i] != smaska32522[i] && smaskuser[i] != smaska32523[i] &&
	                            smaskuser[i] != smaska32541[i] && smaskuser[i] != smaska32542[i] && smaskuser[i] != smaska32543[i] &&
	                            smaskuser[i] != smaska32551[i] && smaskuser[i] != smaska32552[i] && smaskuser[i] != smaska32553[i] &&

	                            smaskuser[i] != smaska4128[i] && smaskuser[i] != smaska4192[i] && smaskuser[i] != smaska4224[i] &&
	                            smaskuser[i] != smaska4240[i] && smaskuser[i] != smaska4248[i] && smaskuser[i] != smaska4252[i]
	                      )
	                {
	                    System.out.println("Invalid subnet mask entered! Try again.\n");
	                    smaskuser[i] = in.next().charAt(i);
	                    i = 0;
	                 }
	            }



	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' &&smaskuser[9] == '\0' &&
	                smaskuser[10] == '\0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0; 
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0; 
	                snw = 0; 
	                totalnwb = 8;
	                totalhb = 24; 
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '\0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0; 
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '\0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '\0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' &&smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '.' && smaskuser[6] == '0' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '\0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '.' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24; 
	                hpsnw = 16777214; 
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0')
	            {
	                inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '.' && smaskuser[7] == '0' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' &&smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '0' && smaskuser[5] == '0' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 0;
	                incOct = 0;

	                System.out.println("The entered subnet mask is:  255.0.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 8;
	                totalhb = 24;
	                hpsnw = 16777214;
	                flash = 8;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '2' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 128;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.128.0.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 9;
	                totalhb = 23;
	                hpsnw = 8388606;
	                flash = 9;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '1' && smaskuser[5] == '9' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 64;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.192.0.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 10;
	                totalhb = 22;
	                hpsnw = 4194302;
	                flash = 10;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '2' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 32;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.224.0.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 11; 
	                totalhb = 21;
	                hpsnw = 2097150;
	                flash = 11;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '0' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 16;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.240.0.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 12;
	                totalhb = 20;
	                hpsnw = 1048574;
	                flash = 12;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '4' && smaskuser[6] == '8' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 8;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.248.0.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 13;
	                totalhb = 19;
	                hpsnw = 524286;
	                flash = 13;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '2' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 4;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.252.0.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 14;
	                totalhb = 18;
	                hpsnw = 262142;
	                flash = 14;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '4' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 2; incOct = 2;

	                System.out.println("The entered subnet mask is:  255.254.0.0\n");
	                bitsBorrowed = 7;
	                snw = 128; 
	                totalnwb = 15;
	                totalhb = 17;
	                hpsnw = 131070;
	                flash = 15;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	                inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '.' &&
	                smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }

	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '0' && smaskuser[9] == '0' &&
	                smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	            {
	            	inc = 1;
	                incOct = 2;

	                System.out.println("The entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 8;
	                snw = 256; 
	                totalnwb = 16; 
	                totalhb = 16;
	                hpsnw = 65534;
	                flash = 16;
	            }




	            if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.')
	            {
	                    if(smaskuser[8] == '1' && smaskuser[9] == '2' && smaskuser[10] == '8' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 128;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.128.0\n");
	                        bitsBorrowed = 9;
	                        snw = 512;
	                        totalnwb = 17;
	                        totalhb = 15;
	                        hpsnw = 32766;
	                        flash = 17;
	                    }

	                    if(smaskuser[8] == '1' && smaskuser[9] == '2' && smaskuser[10] == '8' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 128;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.128.0\n");
	                        bitsBorrowed = 9;
	                        snw = 512;
	                        totalnwb = 17;
	                        totalhb = 15;
	                        hpsnw = 32766;
	                        flash = 17;
	                    }

	                    if(smaskuser[8] == '1' && smaskuser[9] == '2' && smaskuser[10] == '8' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 128;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.128.0\n");
	                        bitsBorrowed = 9;
	                        snw = 512;
	                        totalnwb = 17;
	                        totalhb = 15;
	                        hpsnw = 32766;
	                        flash = 17;
	                    }

	                    if(smaskuser[8] == '1' && smaskuser[9] == '9' && smaskuser[10] == '2' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 64;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.192.0\n");
	                        bitsBorrowed = 10;
	                        snw = 1024;
	                        totalnwb = 18;
	                        totalhb = 14;
	                        hpsnw = 16382;
	                        flash = 18;
	                    }

	                    if(smaskuser[8] == '1' && smaskuser[9] == '9' && smaskuser[10] == '2' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 64;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.192.0\n");
	                        bitsBorrowed = 10;
	                        snw = 1024;
	                        totalnwb = 18;
	                        totalhb = 14;
	                        hpsnw = 16382;
	                        flash = 18;
	                    }

	                    if(smaskuser[8] == '1' && smaskuser[9] == '9' && smaskuser[10] == '2' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 64;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.192.0\n");
	                        bitsBorrowed = 10;
	                        snw = 1024;
	                        totalnwb = 18;
	                        totalhb = 14;
	                        hpsnw = 16382;
	                        flash = 18;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '2' && smaskuser[10] == '4' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 32;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.224.0\n");
	                        bitsBorrowed = 11;
	                        snw = 2048;
	                        totalnwb = 19;
	                        totalhb = 13;
	                        hpsnw = 8190;
	                        flash = 19;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '2' && smaskuser[10] == '4' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 32;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.224.0\n");
	                        bitsBorrowed = 11;
	                        snw = 2048;
	                        totalnwb = 19;
	                        totalhb = 13;
	                        hpsnw = 8190;
	                        flash = 19;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '2' && smaskuser[10] == '4' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 32;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.224.0\n");
	                        bitsBorrowed = 11;
	                        snw = 2048;
	                        totalnwb = 19;
	                        totalhb = 13;
	                        hpsnw = 8190;
	                        flash = 19;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '4' && smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 16;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.240.0\n");
	                        bitsBorrowed = 12;
	                        snw = 4096;
	                        totalnwb = 20;
	                        totalhb = 12;
	                        hpsnw = 4094;
	                        flash = 20;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '4' && smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 16;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.240.0\n");
	                        bitsBorrowed = 12;
	                        snw = 4096;
	                        totalnwb = 20;
	                        totalhb = 12;
	                        hpsnw = 4094;
	                        flash = 20;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '4' && smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 16;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.240.0\n");
	                        bitsBorrowed = 12;
	                        snw = 4096;
	                        totalnwb = 20;
	                        totalhb = 12;
	                        hpsnw = 4094;
	                        flash = 20;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '4' && smaskuser[10] == '8' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 8;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.248.0\n");
	                        bitsBorrowed = 13;
	                        snw = 8192;
	                        totalnwb = 21;
	                        totalhb = 11;
	                        hpsnw = 2046;
	                        flash = 21;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '4' && smaskuser[10] == '8' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 8;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.248.0\n");
	                        bitsBorrowed = 13;
	                        snw = 8192;
	                        totalnwb = 21;
	                        totalhb = 11;
	                        hpsnw = 2046;
	                        flash = 21;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '4' && smaskuser[10] == '8' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 8;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.248.0\n");
	                        bitsBorrowed = 13;
	                        snw = 8192;
	                        totalnwb = 21;
	                        totalhb = 11;
	                        hpsnw = 2046;
	                        flash = 21;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '2' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 4;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.252.0\n");
	                        bitsBorrowed = 14;
	                        snw = 16384;
	                        totalnwb = 22;
	                        totalhb = 10;
	                        hpsnw = 1022;
	                        flash = 22;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '2' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 4;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.252.0\n");
	                        bitsBorrowed = 14;
	                        snw = 16384;
	                        totalnwb = 22;
	                        totalhb = 10;
	                        hpsnw = 1022;
	                        flash = 22;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '2' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 4;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.252.0\n");
	                        bitsBorrowed = 14;
	                        snw = 16384;
	                        totalnwb = 22;
	                        totalhb = 10;
	                        hpsnw = 1022;
	                        flash = 22;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '4' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 2;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.254.0\n");
	                        bitsBorrowed = 15;
	                        snw = 32768;
	                        totalnwb = 23;
	                        totalhb = 9;
	                        hpsnw = 510;
	                        flash = 23;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '4' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 2;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.254.0\n");
	                        bitsBorrowed = 15;
	                        snw = 32768;
	                        totalnwb = 23;
	                        totalhb = 9;
	                        hpsnw = 510;
	                        flash = 23;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '4' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 2;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.254.0\n");
	                        bitsBorrowed = 15;
	                        snw = 32768;
	                        totalnwb = 23;
	                        totalhb = 9;
	                        hpsnw = 510;
	                        flash = 23;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '5' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '\0' && smaskuser[14] == '\0')
	                    {
	                        inc = 1;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.255.0\n");
	                        bitsBorrowed = 16;
	                        snw = 65536;
	                        totalnwb = 24;
	                        totalhb = 8;
	                        hpsnw = 254;
	                        flash = 24;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '5' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '\0')
	                    {
	                    	inc = 1;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.255.0\n");
	                        bitsBorrowed = 16;
	                        snw = 65536;
	                        totalnwb = 24;
	                        totalhb = 8;
	                        hpsnw = 254;
	                        flash = 24;
	                    }

	                    if(smaskuser[8] == '2' && smaskuser[9] == '5' && smaskuser[10] == '5' && smaskuser[11] == '.' && smaskuser[12] == '0' &&
	                        smaskuser[13] == '0' && smaskuser[14] == '0')
	                    {
	                    	inc = 1;
	                        incOct = 3;

	                        System.out.println("The entered subnet mask is:  255.255.255.0\n");
	                        bitsBorrowed = 16;
	                        snw = 65536;
	                        totalnwb = 24;
	                        totalhb = 8;
	                        hpsnw = 254;
	                        flash = 24;
	                    }

	                    //else do nothing;
	            }

	            else if(smaskuser[4] == '2' && smaskuser[5] == '5' && smaskuser[6] == '5' && smaskuser[7] == '.' && smaskuser[8] == '2' && smaskuser[9] == '5' &&
	                smaskuser[10] == '5'   && smaskuser[11] == '.')
	            {
	                    if(smaskuser[12] == '1' && smaskuser[13] == '2' && smaskuser[14] == '8')
	                    {
	                        inc = 128;
	                        incOct = 4;

	                        System.out.println("The entered subnet mask is:  255.255.255.128\n");
	                        bitsBorrowed = 17;
	                        snw = 131072;
	                        totalnwb = 25;
	                        totalhb = 7;
	                        hpsnw = 126;
	                        flash = 25;
	                    }
	                    if(smaskuser[12] == '1' && smaskuser[13] == '9' && smaskuser[14] == '2')
	                    {
	                        inc = 64;
	                        incOct = 4;

	                        System.out.println("The entered subnet mask is:  255.255.255.192\n");
	                        bitsBorrowed = 18;
	                        snw = 262144;
	                        totalnwb = 26;
	                        totalhb = 6;
	                        hpsnw = 62;
	                        flash = 26;
	                    }
	                    if(smaskuser[12] == '2' && smaskuser[13] == '2' && smaskuser[14] == '4')
	                    {
	                        inc = 32;
	                        incOct = 4;

	                        System.out.println("The entered subnet mask is:  255.255.255.224\n");
	                        bitsBorrowed = 19;
	                        snw = 524288;
	                        totalnwb = 27;
	                        totalhb = 5;
	                        hpsnw = 30;
	                        flash = 27;
	                    }
	                    if(smaskuser[12] == '2' && smaskuser[13] == '4' && smaskuser[14] == '0')
	                    {
	                        inc = 16;
	                        incOct = 4;

	                        System.out.println("The entered subnet mask is:  255.255.255.240\n");
	                        bitsBorrowed = 20;
	                        snw = 1048576;
	                        totalnwb = 28;
	                        totalhb = 4;
	                        hpsnw = 14;
	                        flash = 28;
	                    }
	                    if(smaskuser[12] == '2' && smaskuser[13] == '4' && smaskuser[14] == '8')
	                    {
	                        inc = 8;
	                        incOct = 4;

	                        System.out.println("The entered subnet mask is:  255.255.255.248\n");
	                        bitsBorrowed = 21;
	                        snw = 2097152;
	                        totalnwb = 29;
	                        totalhb = 3;
	                        hpsnw = 6;
	                        flash = 29;
	                    }
	                    if(smaskuser[12] == '2' && smaskuser[13] == '5' && smaskuser[14] == '2')
	                    {
	                        inc = 4;
	                        incOct = 4;

	                        System.out.println("The entered subnet mask is:  255.255.255.252");
	                        bitsBorrowed = 22;
	                        snw = 4194304;
	                        totalnwb = 30;
	                        totalhb = 2;
	                        hpsnw = 2;
	                        flash = 30;
	                    }
	            }

	            else
	            {
	                    System.out.println("\n\n\tThe entered subnet mask is utterly stupid !\n\tRead a bit about subnet masks and come back later.\n");
	                    System.exit(0);
	            }
	                        revEngDisplay(bitsBorrowed, snw, totalnwb, totalhb, hpsnw, inc, flash);
	                        calcRange2(oct1, oct2, oct3, snw, inc, incOct);

	            return 0;
	    }
		int validsmaskB(int oct1, int oct2, int oct3)
	    {
	            int i=0, inc=0, incOct=0;
	            int bitsBorrowed=0, snw=0, hpsnw=0, totalnwb=0, bsize=0, totalhb=0, flash=0;
	            char smaskuser[] = new char[]{'\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};

	            char smaskb11[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '.', '0', '\0', '\0', '\0', '\0'};
	            char smaskb12[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '.', '0', '0', '\0', '\0', '\0'};
	            char smaskb13[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '.', '0', '0', '0', '\0', '\0'};
	            char smaskb14[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '.', '0', '\0', '\0', '\0'};
	            char smaskb15[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '.', '0', '0', '\0', '\0'};
	            char smaskb16[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '.', '0', '0', '0', '\0'};
	            char smaskb17[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0', '.', '0', '\0', '\0'};
	            char smaskb18[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '\0'};
	            char smaskb19[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0', '.', '0', '0', '0'};

	            char smaskb21[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8', '.', '0', '\0', '\0'};
	            char smaskb22[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '\0'};
	            char smaskb23[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8', '.', '0', '0', '0'};

	            char smaskb31[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2', '.', '0', '\0', '\0'};
	            char smaskb32[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '\0'};
	            char smaskb33[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2', '.', '0', '0', '0'};

	            char smaskb41[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4', '.', '0', '\0', '\0'};
	            char smaskb42[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '\0'};
	            char smaskb43[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4', '.', '0', '0', '0'};

	            char smaskb51[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0', '.', '0', '\0', '\0'};
	            char smaskb52[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '\0'};
	            char smaskb53[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0', '.', '0', '0', '0'};

	            char smaskb61[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8', '.', '0', '\0', '\0'};
	            char smaskb62[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '\0'};
	            char smaskb63[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8', '.', '0', '0', '0'};

	            char smaskb71[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2', '.', '0', '\0', '\0'};
	            char smaskb72[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '\0'};
	            char smaskb73[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2', '.', '0', '0', '0'};

	            char smaskb81[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4', '.', '0', '\0', '\0'};
	            char smaskb82[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '\0'};
	            char smaskb83[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4', '.', '0', '0', '0'};


	            char smaskc11[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '\0', '\0'};
	            char smaskc12[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '\0'};
	            char smaskc13[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0'};
	            char smaskc2[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8'};
	            char smaskc3[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2'};
	            char smaskc4[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4'};
	            char smaskc5[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0'};
	            char smaskc6[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8'};
	            char smaskc7[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2'};
	            char smaskc8[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '4'};

	            System.out.println("\n\tEnter the subnet mask which your network is to be masked with-  \n\t");
	            System.out.println("\n\n\tNote: Only first 15 entered characters will be taken into account \n\tbecause that's all you need to enter a valid subnet mask address.\n");
	            System.out.println("\n\n\t*Kindly enter no less or no more than 15 digits in your subnet mask.\n\n");
	            System.out.println("Subnet mask: \n");

	            Scanner in = new Scanner(System.in);
	            String s="";
	            for(i = 0; i < 15; i++)
	            {
	            	s = s+in.next();
	            }
	            for(i = 0; i < 15; i++)
	            {
	            	smaskuser[i]=s.charAt(i);
	            }

	            for(i = 0; i < 15; i++)
	            {
	                while(smaskuser[i] != smaskb11[i] && smaskuser[i] != smaskb12[i] && smaskuser[i] != smaskb13[i] &&  smaskuser[i] != smaskb14[i]
	                && smaskuser[i] != smaskb15[i] && smaskuser[i] != smaskb16[i] && smaskuser[i] != smaskb17[i] && smaskuser[i] != smaskb18[i] &&
	                smaskuser[i] != smaskb19[i] &&
	                smaskuser[i] != smaskb21[i] && smaskuser[i] != smaskb22[i] && smaskuser[i] != smaskb23[i] &&
	                smaskuser[i] != smaskb31[i] && smaskuser[i] != smaskb32[i] && smaskuser[i] != smaskb33[i] &&
	                smaskuser[i] != smaskb41[i] && smaskuser[i] != smaskb42[i] && smaskuser[i] != smaskb43[i] &&
	                smaskuser[i] != smaskb51[i] && smaskuser[i] != smaskb52[i] && smaskuser[i] != smaskb53[i] &&
	                smaskuser[i] != smaskb61[i] && smaskuser[i] != smaskb62[i] && smaskuser[i] != smaskb63[i] &&
	                smaskuser[i] != smaskb71[i] && smaskuser[i] != smaskb72[i] && smaskuser[i] != smaskb73[i] &&
	                smaskuser[i] != smaskb81[i] && smaskuser[i] != smaskb82[i] && smaskuser[i] != smaskb83[i] &&

	                smaskuser[i] != smaskc11[i] && smaskuser[i] != smaskc12[i] && smaskuser[i] != smaskc13[i] &&smaskuser[i] != smaskc2[i]
	                &&smaskuser[i] != smaskc3[i] && smaskuser[i] != smaskc4[i] &&smaskuser[i] != smaskc5[i] &&
	                smaskuser[i] != smaskc6[i] && smaskuser[i] != smaskc7[i])
	                {
	                    System.out.println("\nInvalid subnet mask entered! Try again.\n\t");
	                    for(i = 0; i < 15; i++)
	    	            {
	    	            	smaskuser[i] = in.next().charAt(i);
	    	            }

	                    i = 0;
	                 }
	            }


	        if(smaskuser[8] == '0' && smaskuser[9] ==  '.' && smaskuser[10] == '0' && smaskuser[11] == '\0' && smaskuser[12] == '\0')
	        {
	                inc = 0;
	                incOct = 0;

	                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
	                bitsBorrowed = 0;
	                snw = 0;
	                totalnwb = 16;
	                totalhb =  16;
	                hpsnw = 65534;
	                flash = 16;
	        }

	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '.' && smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '\0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;

	        }
	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '.'  && smaskuser[10] == '0' && smaskuser[11] == '0' && smaskuser[12] == '0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;

	        }
	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '0'  && smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '\0' && smaskuser[13] == '\0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;
	        }

	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '0'  && smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '\0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;
	        }

	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '0'  && smaskuser[10] == '.' && smaskuser[11] == '0' && smaskuser[12] == '0' && smaskuser[13] == '0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;
	        }

	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '0'  && smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '\0' && smaskuser[14] == '\0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;
	        }

	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '0'  && smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '\0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;
	        }

	        else if(smaskuser[8] == '0' && smaskuser[9] ==  '0'  && smaskuser[10] == '0' && smaskuser[11] == '.' && smaskuser[12] == '0' && smaskuser[13] == '0' && smaskuser[14] == '0')
	        {
	        	inc = 0;
                incOct = 0;

                System.out.println("\nThe entered subnet mask is:  255.255.0.0\n");
                bitsBorrowed = 0;
                snw = 0;
                totalnwb = 16;
                totalhb =  16;
                hpsnw = 65534;
                flash = 16;
	        }

	        else
	        {
	            if(smaskuser[8] == '1' && smaskuser[9] ==  '2' && smaskuser[10] == '8' && smaskuser[12] == '0')
	            {
	                inc = 128;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.128.0\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 17;
	                totalhb =  15;
	                hpsnw = 32766;
	                flash = 17;
	            }

	            else if(smaskuser[8] == '1' && smaskuser[9] ==  '9' && smaskuser[10] == '2' && smaskuser[12] == '0')
	            {
	                inc = 64;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.192.0\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 18;
	                totalhb = 14;
	                hpsnw = 16382;
	                flash = 18;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '2' && smaskuser[10] == '4' && smaskuser[12] == '0')
	            {
	                inc = 32;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.224.0\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 19;
	                totalhb = 13;
	                hpsnw = 8190;
	                flash = 19;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '4' && smaskuser[10] == '0' && smaskuser[12] == '0')
	            {
	                inc = 16;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.240.0\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 20;
	                totalhb = 12;
	                hpsnw = 4094;
	                flash = 20;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '4' && smaskuser[10] == '8' && smaskuser[12] == '0')
	            {
	                inc = 8;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.248.0\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 21;
	                totalhb = 11;
	                hpsnw = 2046;
	                flash = 21;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '2' && smaskuser[12] == '0')
	            {
	                inc = 4;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.252.0\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 22;
	                totalhb = 10;
	                hpsnw = 1022;
	                flash = 22;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '4' && smaskuser[12] == '0')
	            {
	                inc = 2;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.254.0\n");
	                bitsBorrowed = 7;
	                snw = 128;
	                totalnwb = 23;
	                totalhb = 9;
	                hpsnw = 510;
	                flash = 23;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[12] == '0')
	            {
	                inc = 1;
	                incOct = 3;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.0\n");
	                bitsBorrowed = 8;
	                snw = 256;
	                totalnwb = 24;
	                totalhb = 8;
	                hpsnw = 254;
	                flash = 24;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[11] == '.' &&
	                smaskuser[12] == '1' && smaskuser[13] ==  '2' && smaskuser[14] == '8')
	            {
	                inc = 128;
	                incOct = 4;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.128\n");
	                bitsBorrowed = 9;
	                snw = 512;
	                totalnwb = 25;
	                totalhb = 7;
	                hpsnw = 126;
	                flash = 25;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[11] == '.' &&
	                smaskuser[12] == '1' && smaskuser[13] ==  '9' && smaskuser[14] == '2')
	            {
	                inc = 64;
	                incOct = 4;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.192\n");
	                bitsBorrowed = 10;
	                snw = 1024;
	                totalnwb = 26;
	                totalhb = 6;
	                hpsnw = 62;
	                flash = 26;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[11] == '.' &&
	                smaskuser[12] == '2' && smaskuser[13] ==  '2' && smaskuser[14] == '4')
	            {
	                inc = 32;
	                incOct = 4;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.224\n");
	                bitsBorrowed = 11;
	                snw = 2048;
	                totalnwb = 27;
	                totalhb = 5;
	                hpsnw = 30;
	                flash = 27;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[11] == '.' &&
	                smaskuser[12] == '2' && smaskuser[13] ==  '4' && smaskuser[14] == '0')
	            {
	                inc = 16;
	                incOct = 4;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.240\n");
	                bitsBorrowed = 12;
	                snw = 4096;
	                totalnwb = 28;
	                totalhb = 4;
	                hpsnw = 14;
	                flash = 28;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[11] == '.' &&
	                smaskuser[12] == '2' && smaskuser[13] ==  '4' && smaskuser[14] == '8')
	            {
	                inc = 8;
	                incOct = 4;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.248\n");
	                bitsBorrowed = 13;
	                snw = 8192;
	                totalnwb = 29;
	                totalhb = 3;
	                hpsnw = 6;
	                flash = 29;
	            }

	            else if(smaskuser[8] == '2' && smaskuser[9] ==  '5' && smaskuser[10] == '5' && smaskuser[11] == '.' &&
	                smaskuser[12] == '2' && smaskuser[13] ==  '5' && smaskuser[14] == '2')
	            {
	                inc = 4;
	                incOct = 4;

	                System.out.println("\nThe entered subnet mask is:  255.255.255.252");
	                bitsBorrowed = 14;
	                snw = 16384;
	                totalnwb = 30;
	                totalhb = 2;
	                hpsnw = 2;
	                flash = 30;
	            }



	            else
	            {
	                    System.out.println("\n\n\tThe entered subnet mask is utterly stupid !\n\tRead a bit about subnet masks and come back later.\n");
	                    System.exit(0);
	            }

	        }

	            revEngDisplay(bitsBorrowed, snw, totalnwb, totalhb, hpsnw, inc, flash);
	            calcRange2(oct1, oct2, oct3, snw, inc, incOct);

	        return 0;
	    }
		int validsmaskC(int oct1, int oct2, int oct3)
	    {
	            int i, inc=0, incOct = 4;
	            int bitsBorrowed=0, snw=0, hpsnw=0, totalnwb=0, bsize=0, totalhb=0, flash=0;
	            char smaskuser[] = new char[]{'\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
	            

	            char smaskc11[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '\0', '\0'};
	            char smaskc12[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '\0'};
	            char smaskc13[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '0', '0', '0'};
	            char smaskc2[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '1', '2', '8'};
	            char smaskc3[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '1', '9', '2'};
	            char smaskc4[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '2', '4'};
	            char smaskc5[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '0'};
	            char smaskc6[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '4', '8'};
	            char smaskc7[] = new char[]{'2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '5', '.', '2', '5', '2'};

	            System.out.println("\n\tEnter the subnet mask which your network is to be masked with-  \n\t");
	            System.out.println("\n\n\tNote: Only first 15 entered characters will be taken into account \n\tbecause that's all you need to enter a valid subnet mask address.\n");
	            System.out.println("\n\n\t*Kindly enter no less or no more than 15 digits in your subnet mask.\n\n");
	            System.out.println("Subnet mask: \n");
	            Scanner in = new Scanner(System.in);
	            String s="";
	            for(i = 0; i < 15; i++)
	            {
	            	s = s+in.next();
	            }
	            for(i = 0; i < 15; i++)
	            {
	            	smaskuser[i]=s.charAt(i);
	            }
	            for(i = 0; i < 15; i++)
	            {
	                while(smaskuser[i] != smaskc11[i] && smaskuser[i] != smaskc12[i] && smaskuser[i] != smaskc13[i] &&smaskuser[i] != smaskc2[i]
	                &&smaskuser[i] != smaskc3[i] && smaskuser[i] != smaskc4[i] &&smaskuser[i] != smaskc5[i] &&
	                smaskuser[i] != smaskc6[i] && smaskuser[i] != smaskc7[i])
	                {
	                    System.out.println("\nInvalid subnet mask entered! Try again.\n\t");
	                    for(i = 0; i < 15; i++)
	    	            {
	    	            	smaskuser[i] = in.next().charAt(i);
	    	            }
	                    

	                    i = 0;
	                }
	            }


	            if(smaskuser[12] == '0' )
	            {
	                inc = 256;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.0\n\n");
	                bitsBorrowed = 0;
	                snw = 1;
	                totalnwb = 24;
	                totalhb = 8;
	                hpsnw = 254;
	                flash = 24;

	            }
	            else if(smaskuser[12] == '1' && smaskuser[13] ==  '2' && smaskuser[14] == '8')
	            {
	                inc = 128;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.128\n\n");
	                bitsBorrowed = 1;
	                snw = 2;
	                totalnwb = 25;
	                totalhb = 7;
	                hpsnw =126;
	                flash = 25;
	            }

	            else if(smaskuser[12] == '1' && smaskuser[13] ==  '9' && smaskuser[14] == '2')
	            {
	                inc = 64;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.192\n\n");
	                bitsBorrowed = 2;
	                snw = 4;
	                totalnwb = 26;
	                totalhb = 6;
	                hpsnw = 62;
	                flash = 26;
	            }

	            else if(smaskuser[12] == '2' && smaskuser[13] ==  '2' && smaskuser[14] == '4')
	            {
	                inc = 32;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.224\n\n");
	                bitsBorrowed = 3;
	                snw = 8;
	                totalnwb = 27;
	                totalhb = 5;
	                hpsnw = 30;
	                flash = 27;
	            }

	            else if(smaskuser[12] == '2' && smaskuser[13] ==  '4' && smaskuser[14] == '0')
	            {
	                inc = 16;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.240\n\n");
	                bitsBorrowed = 4;
	                snw = 16;
	                totalnwb = 28;
	                totalhb = 4;
	                hpsnw = 14;
	                flash = 28;
	            }

	            else if(smaskuser[12] == '2' && smaskuser[13] ==  '4' && smaskuser[14] == '8')
	            {
	                inc = 8;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.248\n\n");
	                bitsBorrowed = 5;
	                snw = 32;
	                totalnwb = 29;
	                totalhb = 3;
	                hpsnw = 6;
	                flash = 29;
	            }

	            else if(smaskuser[12] == '2' && smaskuser[13] ==  '5' && smaskuser[14] == '2')
	            {
	                inc = 4;
	                System.out.println("\n\nThe entered subnet mask is:  255.255.255.252\n\n");
	                bitsBorrowed = 6;
	                snw = 64;
	                totalnwb = 30;
	                totalhb = 2;
	                hpsnw = 2;
	                flash = 30;
	            }


	            else
	            {
	                    System.out.println("\n\n\tThe entered subnet mask is utterly stupid !\n\tRead a bit about subnet masks and come back later.\n");
	                    System.exit(0);
	            }

	            revEngDisplay(bitsBorrowed, snw, totalnwb, totalhb, hpsnw, inc, flash);
	            calcRange2(oct1, oct2, oct3, snw, inc, incOct);

	    return 0;
	}
		int revEngDisplay(int bitsBorrowed, int snw, int totalnwb, int totalhb, int hpsnw, int inc, int flash)
        {
            System.out.println("\n*This subnet mask in slash notation, is expressed as: "+flash+"\n");
            System.out.println("\nA class A network with this subnet mask has following details:\n");
            System.out.println("1) The number of bits borrowed from host bits are : "+bitsBorrowed+"\n");
            System.out.println("2) Total number of network bits are: "+totalnwb+"\n");
            System.out.println("3) The total number of subnetworks: "+snw+"\n");
            System.out.println("4) Total number of host bits are: "+totalhb+"\n");
            System.out.println("5) The total number of valid hosts per subnetwork: "+hpsnw+"\n");
            System.out.println("6) The block size(increment) is: "+inc+"\n");

            return 0;
        }

		public static void choice2()
		{
			int oct1, oct2, oct3, oct4;
		    int i;
		    System.out.println("Please fill in the details of the IP address:-\n");
		    System.out.println("First octate: \n");
		    Scanner in = new Scanner(System.in);
		    oct1 = in.nextInt();

		    for(i=0; i<=1000000; i++)
		    {

		        while(oct1<1 || oct1>255)
		        {
		            System.out.println("Invalid number entered! Kindly try again.\n");
		            System.out.println("First octate: \n");
		            oct1 = in.nextInt();
		        }
		        while(oct1>223 && oct1<=255)
		        {
		            System.out.println("You can't enter this number. Such IPs are reserved for government and research purposes. Kindly try again.\n");
		            System.out.println("First octate: \n");
		            oct1 = in.nextInt();
		        }
		    }
		    System.out.println("Second octate: \n");
		    oct2 = in.nextInt();
		    while(oct2<0 || oct2>255)
		    {
		        System.out.println("Invalid number entered! Kindly try again.\n");
		        System.out.println("Second octate: \n");
		        oct2 = in.nextInt();
		    }
		    System.out.println("Third octate: \n");
		    oct3 = in.nextInt();
		    while(oct3<0 || oct3>255)
		    {
		        System.out.println("Invalid number entered! Kindly try again.\n");
		        System.out.println("Third octate: \n");
		        oct3 = in.nextInt();
		    }
		    System.out.println("Fourth octate: \n");
		    oct4 = in.nextInt();
		    while(oct4<0 || oct4>255)
		    {
		        System.out.println("Invalid number entered! Kindly try again.\n");
		        System.out.println("Fourth octate: \n");
		        oct4 = in.nextInt();
		    }
		    System.out.println("Entered IP address is :" +oct1+"."+oct2+"."+oct3+"."+oct4);

		    if (oct1>=1 && oct1<=126)
		    {   project pr=new project();
		        System.out.println("This is class A IP address. Default subnet mask= 255.0.0.0\n");
		        pr.validsmaskA(oct1, oct2, oct3);
		    }
		    else
		    {
		        if(oct1>=128 && oct1<=191)
		        {project pr=new project();
		            System.out.println("This is class B IP address. Default subnet mask= 255.255.0.0\n");
		            pr.validsmaskB(oct1, oct2, oct3);

		        }
		        else
		        {   project pr=new project();
		            System.out.println("This is class C IP address. Default subnet mask= 255.255.255.0\n");
		            pr.validsmaskC(oct1, oct2, oct3);
		        }
		    }
		}
		
		public static void main(String[] args) {
			pr=new project();
			System.out.println("\t**********WELCOME TO SUBNET CALCULATOR**********\n");
		    System.out.println("This project has two modules: \nFirst one deals with IP /SUBNETTING\n(Use it to gather basic subnetting information).\n");
		    System.out.println("\nOther one is for different but most common type of subnetting,\nwhich is REVERSE ENGINEERING SUBNETTING.\n");
		    System.out.println("Choose 1 to enter first module and 2 to enter second module -\t");
		    int choice;
		    Scanner in = new Scanner(System.in);
		    choice = in.nextInt();
		    while(choice!= 1 && choice!=2)
		    {
		    	System.out.println("Wrong Input, Try again.\n");
		    	choice = in.nextInt();
		    }
		    if(choice == 1)
		    {
		    	choice1();
		    }
		    else
		    {
		    	choice2();
		    }
		}
		

	}

