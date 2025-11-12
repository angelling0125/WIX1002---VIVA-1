package viva1;
import java.util.Scanner;

public class VIVA1Q2 {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        
        double subtotal = 0;
        double itemPrice = 0;
        double rate = 0;
        double sst = 0;
        double totalBeforeDiscount = 0;
        String day;
        int hour;
        double studentSaver = 0;
        double happyHour = 0;
        double weekendCombo = 0;
        double totalPayable = 0;
        double loyaltyCashback = 0;
        
        do {
            System.out.print("Enter item price (0 to finish): ");
            itemPrice = input.nextDouble();
            
            if (itemPrice < 0) {
                System.out.println("Invalid amount. Price cannot be negative. Please re-enter.");
                continue;
            }
            
            if (itemPrice == 0 && subtotal == 0) {
                System.out.println("At least one items needed to proceed. Please re-enter.");
                continue;
            }
            subtotal += itemPrice;
        } while (itemPrice != 0 || subtotal == 0);
        
        if (subtotal <= 30) {
            rate = 0.06;
        } else if (subtotal > 30 && subtotal <= 100) {
            rate = 0.08;
        } else {
            rate = 0.1;
        }
        
        sst = subtotal * rate;
        totalBeforeDiscount = subtotal + sst;
        
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        
        boolean checkDay = false;
        do {
            System.out.print("Enter day of week: ");
            day = input.next();
            
            for (int i = 0; i < 7; i++) {
                if (day.equalsIgnoreCase(days[i])) {
                    checkDay = true;
                    break;
                }
            } 
            
            if (checkDay == false) {
                System.out.println("Invalid day. Please re-enter.");
            }
        } while (checkDay == false);
        
        boolean checkHour = false;
        do {
            System.out.print("Enter hour (24-hour format): ");
            hour = input.nextInt();
            
            if (hour >= 0 && hour <= 23) {
                checkHour = true;
                break;
            }
            
            if (checkHour == false) {
                System.out.println("Invalid hour. Please re-enter.");
            }
        } while (checkHour == false);
                
        boolean checkWeekday = false;
        for (int i = 0; i < 5; i++) {
            if (day.equalsIgnoreCase(days[i])) {
                checkWeekday = true;
            }
        }
        
        if (checkWeekday == true) {
            if (totalBeforeDiscount > 25) {
                studentSaver = totalBeforeDiscount * 0.1;
            }

            if (hour >= 15 && hour < 17) {
                happyHour = (totalBeforeDiscount - studentSaver) * 0.05;
            }
        } else {
            if (subtotal >= 50) {
                weekendCombo = subtotal * 0.05;
            }
        }
        
        totalPayable = totalBeforeDiscount - studentSaver - happyHour - weekendCombo;
        
        System.out.print("Is customer a member (Y/N)? ");
        char member = input.next().charAt(0);
        
        if (member == 'Y' || member == 'y') {
            loyaltyCashback = totalPayable * 0.02;
        }
        
        printReceipt(subtotal, rate, sst, totalBeforeDiscount, studentSaver, happyHour, weekendCombo, totalPayable, loyaltyCashback);
    }
    
    public static void printReceipt(double subtotal, double rate, double st, double tbd, double ss, double hh, double wc, double tp, double lc) {
        System.out.println("");
        System.out.println("---------- Kopi-Satu Receipt ---------- ");
        System.out.printf("%-30s RM %.2f \n", "Subtotal: ", subtotal);
        
        if (rate == 0.06) {
            System.out.printf("%-30s RM %.2f \n", "Service Tax (6%): ", st);
        } else if (rate == 0.08) {
            System.out.printf("%-30s RM %.2f \n", "Service Tax (8%): ", st);
        } else {
            System.out.printf("%-30s RM %.2f \n", "Service Tax (10%): ", st);
        }
        
        System.out.printf("%-30s RM %.2f \n", "Total before discount: ", tbd);
        
        if (ss != 0) {
            System.out.printf("%-30s RM %.2f \n", "Student Discount (10%): ", ss);
        }
        
        if (hh != 0) {
            System.out.printf("%-30s RM %.2f \n", "Happy Hour Discount (5%): ", hh);
        }
        
        System.out.println("---------------------------------------");
        System.out.printf("%-30s RM %.2f \n", "Total Payable: ", tp);
        
        if (lc != 0) {
            System.out.printf("%-30s RM %.2f \n", "Loyalty Cashback (2%): ", lc);
        }
                
        System.out.println("---------------------------------------");
        System.out.printf("%-30s RM %.2f \n", "Final Amount to Collect: ", tp);
   
    }
}
