import org.apache.commons.vfs2.FileSystemException;
import org.openimaj.data.dataset.GroupedDataset;
import org.openimaj.data.dataset.ListDataset;
import org.openimaj.data.dataset.VFSGroupDataset;
import org.openimaj.experiment.dataset.split.GroupedRandomSplitter;
import org.openimaj.experiment.dataset.util.DatasetAdaptors;
import org.openimaj.feature.DoubleFV;
import org.openimaj.feature.DoubleFVComparison;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.model.EigenImages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagProj {

    VFSGroupDataset<FImage> dataset1 = new VFSGroupDataset<>("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\test", ImageUtilities.FIMAGE_READER);
    VFSGroupDataset<FImage> dataset2 = new VFSGroupDataset<>("C:\\Users\\Apurva Bhardwaj\\Desktop\\S3CURUS\\train", ImageUtilities.FIMAGE_READER);

    public ImagProj() throws FileSystemException {
    }

    public double Imag() throws IllegalArgumentException {

        int nTraining = 5;
        int nTesting = 5;
        GroupedRandomSplitter<String, FImage> split1 = new GroupedRandomSplitter<>(dataset1, nTraining, 0, nTesting);
        GroupedRandomSplitter<String, FImage> split2 = new GroupedRandomSplitter<>(dataset2, 0, 0, nTesting);
        GroupedDataset<String, ListDataset<FImage>, FImage> training = split1.getTrainingDataset();
        GroupedDataset<String, ListDataset<FImage>, FImage> testing = split2.getTestDataset();

        List<FImage> basisImages = DatasetAdaptors.asList(training);
        int nEigenvectors = 100;
        EigenImages eigen = new EigenImages(nEigenvectors);
        eigen.train(basisImages);
        List<FImage> eigenFaces = new ArrayList<>();
       //for (int i = 0; i < 12; i++) {
         //   eigenFaces.add(eigen.visualisePC(i));
        //}
        //DisplayUtilities.display("EigenFaces", eigenFaces);

        Map<String, DoubleFV[]> features = new HashMap<>();
        for (final String person : training.getGroups()) {
            final DoubleFV[] fvs = new DoubleFV[nTraining];

            for (int i = 0; i < nTraining; i++) {
                final FImage face = training.get(person).get(i);
                fvs[i] = eigen.extractFeature(face);
            }
            features.put(person, fvs);
        }

        double correct = 0, incorrect = 0;
        for (String truePerson : testing.getGroups()) {
            for (FImage face : testing.get(truePerson)) {
                DoubleFV testFeature = eigen.extractFeature(face);

                String bestPerson = null;
                double minDistance = Double.MAX_VALUE;
                for (final String person : features.keySet()) {
                    for (final DoubleFV fv : features.get(person)) {
                        double distance = fv.compare(testFeature, DoubleFVComparison.EUCLIDEAN);

                        if (distance < minDistance) {
                            minDistance = distance;
                            bestPerson = person;
                        }
                    }
                }

                System.out.println("Actual: " + truePerson + "\tguess: " + bestPerson);

                if (truePerson.equals(bestPerson))
                    correct++;
                else
                    incorrect++;
            }
        }
        return  (correct / (correct + incorrect));


    }

    public static void main(String[] args) throws FileSystemException , IllegalArgumentException {
        ImagProj imagProj = new ImagProj();
        imagProj.Imag();
    }


}
