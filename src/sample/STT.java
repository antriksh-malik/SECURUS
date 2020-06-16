import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;

public class STT {       

    String text_converter (String audio) throws Exception {

        Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

	StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
	InputStream stream = new FileInputStream(new File(audio));
	String text = ""; 

        recognizer.startRecognition(stream);
	    SpeechResult result;
        while ((result = recognizer.getResult()) != null) 
        {
        	//System.out.printf("Hypothesis: %s\n", result.getHypothesis());
        	text = result.getHypothesis();
        }
	return text;
    }
}