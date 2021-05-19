public class FragmentUse {
    public static void main(String[] args)
    {
        Fragment fragFirst = new Fragment();
        Fragment fragSecond = new Fragment();

        // Initializing fragment values
        fragFirst.setNumerator(2);
        fragFirst.setDenominator(0);
        fragSecond.setNumerator(4);
        fragSecond.setDenominator(5);

        // Checking Print
        System.out.println();
        System.out.println("Checking Print\n--------------");
        System.out.println("The first fragment is:");
        fragFirst.print();
        System.out.println("The second fragment is:");
        fragSecond.print();
        System.out.println("");

        // Checking Addition
        System.out.println("Checking Addition\n-----------------");
        fragFirst.add(fragSecond).print();
        System.out.println("");

        // Checking Subtraction
        System.out.println("Checking Subtraction\n--------------------");
        fragFirst.sub(fragSecond).print();
        System.out.println("");

        // Checking Multiplication
        System.out.println("Checking Multiplication\n-----------------------");
        fragFirst.mult(fragSecond).print();
        System.out.println("");

        // Checking Division
        System.out.println("Checking Division\n-----------------");
        fragFirst.div(fragSecond).print();
        System.out.println("");
    }
}
