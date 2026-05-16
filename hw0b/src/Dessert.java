public class Dessert {
    int flavor;
    int price;
    static int numDesserts;

    public Dessert(int a, int b){
        flavor = a;
        price = b;
        numDesserts += 1;
    }

    public void printDessert(){
        System.out.print(flavor + " ");
        System.out.print(price + " ");
        System.out.println(numDesserts);
    }

    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
}
