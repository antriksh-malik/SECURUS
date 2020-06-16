import javafx.stage.Stage;

import java.util.*;



public class complete_speech {

	static boolean variable = false;
	public boolean speech() {

		String name = Controller3.name;
		String ans = Controller3.ans;

		//For speaker recognition:
		String audio_speaker = "C:/Users/Apurva Bhardwaj/Desktop/S3CURUS/src/Audio.wav";
		Speaker_rec obj1 = new Speaker_rec();

		boolean flag = false;
		try {
			flag = obj1.recognito(name, audio_speaker);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag == true) {

			String audio_stt = "C:/Users/Apurva Bhardwaj/Desktop/S3CURUS/src/Audio.wav";
			STT obj2 = new STT();
			String text = "";
			try {
				text = obj2.text_converter(audio_stt);
				System.out.println(text);

				if (text.equals(ans)){
					variable = true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("ACCESS DENIED");

			//For speech to text pass code:
		}

		return variable;
	}
}
