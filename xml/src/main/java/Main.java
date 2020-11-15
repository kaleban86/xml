import com.app.dao.Dao;
import com.app.dao.DaoImpl;
import com.app.processor.DataProcessor;


public class Main {


    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        Dao dao = new DaoImpl(100, "jdbc:mysql://localhost:3306/xml", "root", "530433");
        DataProcessor dataProcessor = new DataProcessor(dao);
        long sum = dataProcessor.process();

        System.out.println(sum + " sum");


        System.out.println(System.currentTimeMillis() - time);

    }


}