import java.util.Scanner;

public class gpa
{
        double market = 0.;
        public static void main(String args[])
        {
            Scanner input = new Scanner(System.in);
            String first = " ";
            String last = " ";
            String grades = "ABCDF";
            System.out.println("Hi welcome, to our program!");
            System.out.println("This is a GPA calculator that is weighted on a 4.0 scale");
            System.out.println("Please answer the following questions: ");
            System.out.println("What is your First name? ");
            first = input.next();
            System.out.println("What is your Last name? ");
            last = input.next();
            System.out.println("How many classes do you have? ");
            double value = input.nextDouble();
            if(value > 0)
            {
                System.out.println(sortingClasses(value));
            }
            if(value >= 0)
            {
                System.out.println("How many classes do you have?");
                double worth = value;
                System.out.println(unweightedGPA(worth));
            }
        }

        public static String sortingClasses(double str)
        {
            String grade = " ";
            String Classlevel = " ";
            double GPA = 0.0;
            String all = " ";
            String level = " ";
            double sum = 0.0;
            String grades = "ABCDF";
            Scanner input = new Scanner(System.in);

            int counter = 1;
            double length = input.nextInt();
            for (int i = 0; i < length; i++) {
                System.out.println("Please enter your name for your class: ");//how to allow this to take spaces between words?
                String name = input.next();
                System.out.println("Please enter your grade for your class " + counter + ": either A,B,C,D or F");
                grade = input.next();
                System.out.println("Is this a Honors, AP, or On-level course?");
                Classlevel = input.next();
                if ((grade.charAt(0) == (grades.charAt(0)) || grade.charAt(0) == (grades.charAt(1)) || grade.charAt(0) == (grades.charAt(2)) || grade.charAt(0) == (grades.charAt(3)) || grade.charAt(0) == (grades.charAt(4)))) {
                    if (grade.equals("A")) {
                        if (Classlevel.equals("Honors") || Classlevel.equals("honors"))
                            str = 4.5;
                        else if (Classlevel.equals("AP") || Classlevel.equals("ap")) {
                            str = 5;
                        } else if (Classlevel.equals("On-level") || Classlevel.equals("on-level"))
                            str = 4.00;

                    } else if (grade.equals("B")) {
                        if (Classlevel.equals("Honors") || Classlevel.equals("honors"))
                            str = 3.5;
                        else if (Classlevel.equals("AP") || Classlevel.equals("ap"))
                            str = 4;
                        else if (Classlevel.equals("On-level") || Classlevel.equals("on-level"))
                            str = 3.00;
                    } else if (grade.equals("C")) {
                        if (Classlevel.equals("Honors") || Classlevel.equals("honors"))
                            str = 2.5;
                        else if (Classlevel.equals("AP") || Classlevel.equals("ap"))
                            str = 3;
                        else if (Classlevel.equals("On-level") || Classlevel.equals("on-level"))
                            str = 2.00;
                    } else if (grade.equals("D")) {
                        if (Classlevel.equals("Honors") || Classlevel.equals("honors"))
                            str = 1.5;
                        else if (Classlevel.equals("AP") || Classlevel.equals("ap"))
                            str = 2;
                        else if (Classlevel.equals("On-level") || Classlevel.equals("on-level"))
                            str = 1.00;
                    } else if (grade.equals("F")) {
                        if (Classlevel.equals("Honors") || Classlevel.equals("honors")) {
                            str = .5;
                        }
                        if (Classlevel.equals("AP") || Classlevel.equals("ap"))
                            str = 1;
                        else if (Classlevel.equals("On-level") || Classlevel.equals("on-level"))
                            str = 0.00;
                    }
                    sum = sum + str;

                }
                counter++;

                level = level + Classlevel;

                all = all + "\n" + Classlevel + " " + name + ": grade: " + grade + "\n";
            }
            GPA = sum/length;
            all = all + "\n" + "Your GPA weighted is: " + GPA;
            return all;
        }
        int count = 0;

        public static String unweightedGPA(double worth)
        {
            String grade = " ";
            String Classlevel = " ";
            double GPA = 0.0;
            String unweighted = " ";
            String level = " ";
            double average = 0.0;
            Scanner input = new Scanner(System.in);
            int counter = 1;
            double length = input.nextInt();
            for (int i = 0; i < length; i++) {
                System.out.println("Please enter your name for your class: ");//how to allow this to take spaces between words?
                String name = input.next();
                System.out.println("Please enter your grade for your class " + counter + ": either A,B,C,D or F");
                grade = input.next();
                //System.out.println("Is this a Honors, AP, or On-level course?");
                //Classlevel = input.next();
                if (grade.equals("A") || grade.equals("a"))
                {
                    worth = 4.00;
                }
                else if (grade.equals("B") || grade.equals("b"))
                {
                    worth = 3.00;
                }
                else if (grade.equals("C") || grade.equals("c"))
                {
                    worth = 2.00;
                }
                else if (grade.equals("D") || grade.equals("d"))
                {
                    worth = 1.00;
                }
                else if (grade.equals("F") || grade.equals("f"))
                {
                    worth = 0.00;
                }
                average = average + worth;

                counter++;
                level = level + Classlevel;

                unweighted = unweighted + "\n" + Classlevel + " " + name + ": grade: " + grade + "\n";
            }

            GPA = average/length;
            unweighted = unweighted + "\n" + "Your unweighted GPA is: " + GPA;
            return unweighted;

        }
        public static boolean contains(String abcdf, String input)
        {
            String empty = "";
            int bool = 0;
            for (int i = 0; i < abcdf.length(); i++)
            {
                empty += abcdf.charAt(i);
                if (empty.equals(input))
                    bool = 1;
            }
            if (bool == 1)
            {
                return true;
            }
            return false;
        }

    }


