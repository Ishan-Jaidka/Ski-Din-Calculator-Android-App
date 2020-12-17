package com.example.dincalcemptyactivity;

/* Creates object of type Skier, and stores information about a skier,
 * including name, weight, height, skier type, skier code, and din.
 * Skier code and din are calculated based on the following DIN chart (p126):
 * https://www.marker.net/storage/user_upload/marker/Downloads/THB_Marker_16-17_englisch.pdf
 */
public class Skier {

//Fields:

    private String name;            //name
    private int age;                //age
    private int weight;             //weight
    private int height;             //height
    private int skierType;          //skier type
    private int bsl;                //boot sole length (mm)
    private int bslCol;             //bsl column (for calculating din)
    private int skierCodeInt = -1;  //skier code (int, for calculating din)
    private char skierCode = 'Z';   //skier code (calculated from the above)
    private double din = -1;        //calculated from skier code and boot sole length

    private double[][] dinChart;    //din chart loaded from Marker



//Constructors:

    //constructor: accepts name, weight, height, and skierType
    public Skier(String name, int age, int weight, int height, int skierType, int bsl){
        //initializes variables
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.skierType = skierType;
        this.bsl = bsl;
        loadDinChart();
    }

    //default constructor: sets name to empty string and all others to -1 (invalid)
    public Skier(){
        this("", -1, -1, -1, -1, -1);
    }

    //constructor: sets name to empty string and all others to provided
    public Skier(int age, int weight, int height, int skierType, int bsl){
        this("", age, weight, height, skierType, bsl);
    }



//Accessors:

    //returns name
    public String getName() {
        return name;
    }

    //returns height
    public int getHeight(){
        return height;
    }

    //returns weight
    public int getWeight(){
        return weight;
    }

    //returns age
    public int getAge(){
        return age;
    }

    //returns skier type
    public int getSkierType(){
        return skierType;
    }

    //returns bsl
    public int getBSL(){
        return bsl;
    }

    //calculates and returns skier code
    public int getSkierCode(){
        checkSkierCode();
        return skierCode;
    }

    //calculates and returns DIN
    public double getDin(){
        checkDin();
        return din;
    }



//Setters:

    //sets all fields to default
    public void setDefault() {
        this.name = "";
        this.weight = -1;
        this.height = -1;
        this.skierType = -1;
        this.bsl = -1;
    }

    //sets name to provided string
    public void setName(String name) {
        this.name = name;
    }

    //sets age to provided age, if valid
    public void setAge(int age){
        checkAge(age);
        this.age = age;
    }

    //sets weight to provided weight, if valid
    public void setWeight(int weight) {
        checkWeight(weight);
        this.weight = weight;
    }

    //sets height to provided height, if valid
    public void setHeight(int height) {
        checkHeight(height);
        this.height = height;
    }

    //sets boot sole length to provided bsl, if valid
    public void setBsl(int bsl) {
        checkBSL(bsl);
        this.bsl = bsl;
    }

    //sets skier type to provided type, if valid
    public void setSkierType(int type) {
        checkSkierType(type);
        this.skierType = type;
    }



//Private helper functions:

    //calculates skier code, if all fields are valid
    private char calculateSkierCode(){
        checkSkierType(skierType);
        checkAge(age);
        checkHeight(height);
        checkWeight(weight);

        //skier code calculation based on height (+65 converts to ASCII value for capital letters)
        int hCode;
        if(height<59) hCode = 7;       //H
        else if(height<62) hCode = 8;  //I
        else if(height<66) hCode = 9;  //J
        else if(height<71) hCode = 10; //K
        else if(height<77) hCode = 11; //L
        else hCode = 12;                //M

        //skier code calculation based on weight;
        int wCode;
        if(weight<30) wCode = 0;        //A
        else if(weight<39) wCode = 1;   //B
        else if(weight<48) wCode = 2;   //C
        else if(weight<57) wCode = 3;   //D
        else if(weight<67) wCode = 4;   //E
        else if(weight<79) wCode = 5;   //F
        else if(weight<92) wCode = 6;   //G
        else if(weight<108) wCode = 7;  //H
        else if(weight<126) wCode = 8;  //I
        else if(weight<148) wCode = 9;  //J
        else if(weight<175) wCode = 10; //K
        else if(weight<210) wCode = 11; //L
        else wCode = 12;                //M

        //Skier Code Int = minimum(skierCodeFromHeight, skierCodeFromWeight) adjusted for skierType
        skierCodeInt = Math.min(hCode, wCode) + skierType - 1;

        //adjusts skier code for age (if younger than 10 or older than 49)
        if(age<10 || age>49) skierCodeInt--;

        //Skier Code = character representation of skierCodeInt
        skierCode = (char)(skierCodeInt + 65);

        return skierCode;
    }

    //calculates DIN, if all fields are valid
    public double calculateDin(){
        calculateSkierCode();
        checkBSL(bsl);

        //gets din from the given row/column of the din chart
        din = dinChart[skierCodeInt][bslCol];
        return din;
    }

    //checks for valid skier type; throws IllegalArgumentException if invalid (<0 or >4)
    private void checkSkierType(int type){
        if(type <0 || type >4)
            throw new IllegalArgumentException("Choose a valid Skier Type");
    }

    //checks for valid weight; throws IllegalArgumentException if invalid (<22 or >2000)
    private void checkWeight(int weight){
        if(weight<22 || weight>2000)
            throw new IllegalArgumentException("Enter a valid weight");
    }

    //checks for valid height; throws IllegalArgumentException if invalid (<24" or >120")
    private void checkHeight(int height){
        if(height<24 || height>120)
            throw new IllegalArgumentException("Enter a valid height");
    }

    //checks for valid boot sole length; throws IllegalArgumentException if invalid (<165mm or >405mm)
    private void checkBSL(int bsl){
        if(bsl<165 || bsl>405)
            throw new IllegalArgumentException("Enter a valid boot sole length");
        else{
            if(bsl<231) bslCol = 0;
            else if(bsl<251) bslCol = 1;
            else if(bsl<271) bslCol = 2;
            else if(bsl<291) bslCol = 3;
            else if(bsl<311) bslCol = 4;
            else if(bsl<331) bslCol = 5;
            else if(bsl<351) bslCol = 6;
            else bslCol = 7;
        }
    }

    //checks for valid age; throws IllegalArgumentException if invalid (<1 or >200)
    private void checkAge(int age){
        if(age<1 || age>200)
            throw new IllegalArgumentException("Enter a valid age");
    }

    //checks for valid skierCode, otherwise calculates it
    private void checkSkierCode(){
        if((int)skierCode == 90) calculateSkierCode();
    }

    //checks for valid DIN, otherwise calculates it
    private void checkDin(){
        if(din<0) calculateDin();
    }

    //loads din chart from Marker manual (p126): https://www.marker.net/storage/user_upload/marker/Downloads/THB_Marker_16-17_englisch.pdf
    private void loadDinChart(){
        dinChart = new double[][]{                                  //Skier Code:
                {0.75, 0.75, 0.75, 0,    0,    0,    0,    0},          //A
                {1,    0.75, 0.75, 0.75, 0,    0,    0,    0},          //B
                {1.5,  1.25, 1.25, 1,    0,    0,    0,    0},          //C
                {2,    1.75, 1.5,  1.5,  1.25, 0,    0,    0},          //D
                {2.5,  2.25, 2,    1.75, 1.5,  1.5,  0,    0},          //E
                {3,    2.75, 2.5,  2.25, 2,    1.75, 1.75, 0},          //F
                {0,    3.5,  3,    2.75, 2.5,  2.25, 2,    0},          //G
                {0,    0,    3.5,  3,    3,    2.75, 2.5,  0},          //H
                {0,    0,    4.5,  4,    3.5,  3.5,  3,    0},          //I
                {0,    0,    5.5,  5,    4.5,  4,    3.5,  3},          //J
                {0,    0,    6.5,  6,    5.5,  5,    4.5,  4},          //K
                {0,    0,    7.5,  7,    6.5,  6,    5.5,  5},          //L
                {0,    0,    0,    8.5,  8,    7,    6.5,  6},          //M
                {0,    0,    0,    10,   9.5,  8.5,  8,    7.5},        //N
                {0,    0,    0,    11.5, 11,   10,   9.5,  9},          //O
                {0,    0,    0,    0,    0,    12,   11,   10.5}        //holy shit
        };
    }
}
