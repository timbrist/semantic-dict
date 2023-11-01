package dictionary;

public class MainApplication {
    public static void main( String[] args ){
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println( "Hello World!" );

        CottageRepository cottageRepository = new CottageRepository();
        String result = cottageRepository.ListAll();
        System.out.println(result); 
        // System.out.println("========break line=============");
        // System.out.println(cottageRepository.ListAllCottage());
        // System.out.println("========break line=============");
        // System.out.println(cottageRepository.ListBookedCottage());
        System.out.println("========break line=============");
        System.out.println(cottageRepository.ShowCottageInfo(1));
        System.out.println( "----------END OF MAIN -------!" );
    }
}
