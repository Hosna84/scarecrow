import java.util.Arrays;
import java.util.Scanner;

public class Q3{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int howManyEmployers = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        double[] location = new double[howManyEmployers];// Use double for floating-point numbers
        double[] speedForRight = new double[howManyEmployers]; // Use double for floating-point numbers
        double[] speedForLeft = new double[howManyEmployers]; // Use double for floating-point numbers
        double[] reactionTime = new double[howManyEmployers]; // Use double for floating-point numbers
        double[] totalTimes = new double[10000]; 
        double[] matarsakLocation = new double[10000];
        int counter = 0; //for totalTimes index

       scanInputs(location, speedForRight, speedForLeft, reactionTime, howManyEmployers, scanner);
        double indexOfMinimumLocation=findMinimumLocation(location);
        double indexOfMaximumLocation=findMaximumLocation(location);
        double gameHalghe;
        
            gameHalghe = (indexOfMaximumLocation - indexOfMinimumLocation) / 500;
            for (double currentLocation = indexOfMinimumLocation; currentLocation <= indexOfMaximumLocation; 
            currentLocation += gameHalghe) {
                totalTimes[counter]=Time(currentLocation, location, speedForRight, speedForLeft, reactionTime, howManyEmployers);
                matarsakLocation[counter] = currentLocation;
        counter++; 
            }
            double minimumTime=findMinValue(totalTimes);
            int indexOfMinimumValue=findMinValueIndex(totalTimes);
            int compare=indexOfMinimumValue-1;
    if(indexOfMinimumValue-1<0){
        compare=indexOfMinimumValue+1;
    }
           if(Math.abs(minimumTime-totalTimes[compare]) <= 0.1)
        System.out.println(String.format("%.2f", minimumTime)); 
         else{
            exactTime(totalTimes, minimumTime, indexOfMinimumValue, matarsakLocation, 
            counter, gameHalghe, matarsakLocation, howManyEmployers, speedForRight, speedForLeft, reactionTime);
        }
}
//Scanning inputs
static void scanInputs(double[] location, double[] speedForRight, double[] speedForLeft, double[] reactionTime, int howManyEmployers, Scanner scanner) {
    for (int i = 0; i < howManyEmployers; i++) {
        location[i] = scanner.nextDouble();
    }
    scanner.nextLine(); // Consume the newline character
    String[] speedsRight = scanner.nextLine().split(" ");
    for (int j = 0; j < howManyEmployers; j++) {
        speedForRight[j] = Double.parseDouble(speedsRight[j]); // Parse as double
    }
    String[] speedsLeft = scanner.nextLine().split(" ");
    for (int k = 0; k < howManyEmployers; k++) {
        speedForLeft[k] = Double.parseDouble(speedsLeft[k]); // Parse as double
    }
    String[] reactionTimes = scanner.nextLine().split(" ");
    for (int g = 0; g < howManyEmployers; g++) {
        reactionTime[g] = Double.parseDouble(reactionTimes[g]); // Parse as double
    }
}
//finding minimum index of location
    static double findMinimumLocation(double[] location){
        double min=location[0];
        
        for(int h=0;h<location.length;h++){
            if(location[h]<min){
                min=location[h];
               
            }
        }
        return min;
    }

    static double findMaximumLocation(double[] location){
        double max=location[0];
        for(int h=0;h<location.length;h++){
            if(location[h]>max){
                max=location[h];
        
            }
        }
        return max;
    }

public static double findMaxInArray(double[] data) {
    double max = Double.MIN_VALUE;
    for (double val : data) {
        if (val > max) {
            max = val;
        }
    }
    
    return max;
}
public static double findMinValue(double[] arr) {
        double min = arr[0];
    
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min && arr[i]!=0) {
                min = arr[i];
           
            }
        }
        
        return min;
    }
    public static int findMinValueIndex(double[] arr) {
        double min = arr[0];
        int index=0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min && arr[i]!=0) {
                min = arr[i];
                index=i;
            }
        }
        
        return index;
    }

    static double Time(double currentLocation,double[] location , double[] speedForRight,double[] speedForLeft,
                       double[] reactionTime,int howManyEmployers){
        double[] totalTime=new double[howManyEmployers];
        double total_Time=0;
        int count=0;
        for(int s=0;s<howManyEmployers;s++){
           
            if(currentLocation<location[s]){
                //leftSpeed;
                totalTime[count]=reactionTime[s]+(Math.abs(currentLocation-location[s]))/speedForLeft[s];
    count++;
            }
            if(currentLocation>location[s]){
                //RightSpeed;
                totalTime[count]=reactionTime[s]+(Math.abs(currentLocation-location[s]))/speedForRight[s];
    count++;
            }
            if(currentLocation==location[s]){
                totalTime[count]=reactionTime[s];
    count++;
            }
        }
    total_Time=findMaxInArray(totalTime);
        return total_Time;
    } 

    static void exactTime(double[] totalTimes,double minimumTime,int indexOfMinimumValue, 
                        double[] matarsakLocation,int counter,double gameHalghe,double[] location,int howManyEmployers, 
                        double[] speedForRight,double[] speedForLeft, double[] reactionTime){
        while ( Math.abs(minimumTime-totalTimes[indexOfMinimumValue -1]) > 0.1 &&  
        Math.abs(minimumTime-totalTimes[indexOfMinimumValue+1] )> 0.1 ) {
            //work with the nearest time
            double absMatarsakAndLastIndex =  Math.abs(totalTimes[indexOfMinimumValue-1]-totalTimes[indexOfMinimumValue]);
            double absMatarsakAndNextIndex =  Math.abs(totalTimes[indexOfMinimumValue+1]-totalTimes[indexOfMinimumValue]);
            counter=0;
            totalTimes = new double[10000]; 
            if(absMatarsakAndLastIndex < absMatarsakAndNextIndex){
                if(matarsakLocation[indexOfMinimumValue-1] < matarsakLocation[indexOfMinimumValue]){
                   
                    gameHalghe = (matarsakLocation[indexOfMinimumValue] - matarsakLocation[indexOfMinimumValue-1]) / 500;
                    for (double currentLocation =matarsakLocation[indexOfMinimumValue-1] ;
                                currentLocation < matarsakLocation[indexOfMinimumValue]; 
                                currentLocation += gameHalghe) {

                        totalTimes[counter]=Time(currentLocation, location, speedForRight, speedForLeft, reactionTime, howManyEmployers);
                        matarsakLocation[counter] = currentLocation;
                    counter++; 
               
                    }
                }
            
                else{
                    gameHalghe = (matarsakLocation[indexOfMinimumValue-1] - matarsakLocation[indexOfMinimumValue]) / 500;
                    for (double currentLocation =matarsakLocation[indexOfMinimumValue] ; 
                        currentLocation < matarsakLocation[indexOfMinimumValue-1]; 
                        currentLocation += gameHalghe) {
                        totalTimes[counter]=Time(currentLocation, location, speedForRight, speedForLeft, reactionTime, howManyEmployers);
                        matarsakLocation[counter] = currentLocation;
                    counter++; 
                }

            }
        }
            else{
                if(matarsakLocation[indexOfMinimumValue+1] < matarsakLocation[indexOfMinimumValue]){
                    gameHalghe = (matarsakLocation[indexOfMinimumValue] - matarsakLocation[indexOfMinimumValue+1]) / 500;
                    for (double currentLocation =matarsakLocation[indexOfMinimumValue+1] ; 
                                currentLocation < matarsakLocation[indexOfMinimumValue]; 
                                currentLocation += gameHalghe) {

                        totalTimes[counter]=Time(currentLocation, location, speedForRight, speedForLeft, reactionTime, howManyEmployers);
                        matarsakLocation[counter] = currentLocation;
                    counter++; 
                }
            }
                else{
                    gameHalghe = (matarsakLocation[indexOfMinimumValue+1] - matarsakLocation[indexOfMinimumValue]) /500;
                    for (double currentLocation =matarsakLocation[indexOfMinimumValue] ; 
                                currentLocation < matarsakLocation[indexOfMinimumValue+1]; 
                                currentLocation += gameHalghe) {
                        totalTimes[counter]=Time(currentLocation, location, speedForRight, speedForLeft, reactionTime, howManyEmployers);
                        matarsakLocation[counter] = currentLocation;
                    counter++; 
                }

            }
        }
        minimumTime=findMinValue(totalTimes);
        indexOfMinimumValue=findMinValueIndex(totalTimes);
       if( Math.abs(minimumTime - totalTimes[indexOfMinimumValue-1]) <= 0.1)
       {
            System.out.println(String.format("%.2f", minimumTime)); 
            break;
       }
    }   


}
}