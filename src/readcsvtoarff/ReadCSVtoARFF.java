package readcsvtoarff;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/* @author alessandro */

public class ReadCSVtoARFF {

    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        // Path
        String lineardata = "/home/alessandro/Documentos/weka-example/houses.csv";
        BufferedReader file_b = new BufferedReader(new FileReader(lineardata));
        String linha = file_b.readLine();

        FastVector atts;
        Instances data = null;
        double[] vals;
        boolean first = true;

        while (linha != null) {

            String[] items = linha.split(",");

            if (first) {
                first = false;

                // Set up attributes
                atts = new FastVector();

                for (int i = 0; i < items.length; i++) {
                    // - numeric
                    atts.addElement(new Attribute(items[i]));
                }

                // Create Instances object
                data = new Instances("MyRelation", atts, 0);
            } else {

                // create data for attributes
                vals = new double[data.numAttributes()];

                for (int j = 0; j < items.length; j++) {
                    // - numeric
                    vals[j] = Double.parseDouble(items[j]);
                }

                data.add(new Instance(1.0, vals));

            }
            linha = file_b.readLine();

            if (linha == null) {
                System.out.println(data);
                Instances linear2 = new Instances(data);
                // Number the atributes
                linear2.setClassIndex(linear2.numAttributes() - 1);
                // Create a Linear Regression
                LinearRegression model = new LinearRegression();
                // Run the classifier
                model.buildClassifier(linear2);
                // Print the model classifier
                System.out.println(model);
            }
        } //while (linha != null) {

    }

}
